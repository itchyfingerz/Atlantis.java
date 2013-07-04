package atlantis.samples.platformer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

import atlantis.engine.Atlantis;
import atlantis.engine.graphics.Sprite;
import atlantis.engine.state.State;
import atlantis.framework.GameTime;
import atlantis.framework.Vector2;
import atlantis.framework.content.ContentManager;

enum MovementState {
	JumpingUp, JumpingDown, Walking	
}

enum GameMode {
	Playing, Died, Lose, Win
}

public class GameState extends State {
	private static Random random = new Random();
	private Sprite background;
	private Sprite layer;
	private Sprite subLayer;
	private Sprite player;
	private ArrayList<Sprite> tiles;
	private ArrayList<Sprite> items;
	private Sprite[] overlays;
	private GameMode gameMode;
	
	private MovementState movementState;
	private int jumpHeight;
	private Vector2 initialJumpPosition;
	private float jumpSpeed;
	
	public GameState(String name) {
		super(name);
		this.background = new Sprite("img/Backgrounds/Layer0_0.png");
		this.scene.add(this.background);
		
		this.layer = new Sprite("img/Backgrounds/Layer1_0.png");
		this.scene.add(this.layer);
		
		this.subLayer = new Sprite("img/Backgrounds/Layer2_0.png");
		this.scene.add(this.subLayer);
		
		this.player = new Sprite("img/Player.png");
		this.scene.add(this.player);
		
		// Overlays
		this.overlays = new Sprite[3];
		this.overlays[0] = new Sprite("overlays/you_died.png");
		this.overlays[1] = new Sprite("overlays/you_lose.png");
		this.overlays[2] = new Sprite("overlays/you_win.png");
		
		// Tiles and items for easily search
		this.tiles = new ArrayList<Sprite>();
		this.items = new ArrayList<Sprite>();
		
		this.gameMode = GameMode.Playing;
		
		// Jumping
		this.movementState = MovementState.Walking;
		this.jumpHeight = 90;
		this.jumpSpeed = 6.5f;
		this.initialJumpPosition = Vector2.Zero();
	}
	
	public void loadContent(ContentManager content) {
		// Background and layer
		this.background.loadContent(content);
		this.layer.loadContent(content);
		this.subLayer.loadContent(content);
		
		// Player
		this.player.loadContent(content);
		this.player.prepareAnimation(64, 64);
		this.player.addAnimation("left", new int[] { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 }, 30);
		this.player.addAnimation("right", new int[] { 19, 18, 17, 16, 15, 14, 13, 12, 11, 10 }, 30);
		this.player.addAnimation("jumpLeft", new int[] { 20, 21, 22 }, 30);
		this.player.addAnimation("jumpRight", new int[] { 23, 24, 25 }, 30);
		this.player.addAnimation("idle", new int[] { 26 }, 30);
		this.player.addAnimation("win", new int[] { 39, 38, 37, 36, 35, 34, 33, 32, 31, 30 }, 10);
		this.player.addAnimation("die", new int[] { 40, 41, 42, 43, 44, 45, 46 }, 10);
		this.player.setPosition(50, 350);
		this.player.setSize(72, 72);
		//this.player.forceInsideScreen(true);
		
		// Create level
		this.createLevel(1);
		
		// Overlays
		for (Sprite overlay : this.overlays) {
			overlay.loadContent(content);
			overlay.setPosition(Atlantis.width / 2 - overlay.getWidth() / 2, Atlantis.height / 2 - overlay.getHeight() / 2);
			overlay.setActive(false);
			this.scene.add(overlay);
		}
	}
	
	private void createLevel(int levelId) {
		BufferedReader reader = null;
		StringBuilder jsonString = new StringBuilder();

		try {
			reader = new BufferedReader(new FileReader("Content/Platformer/levels/level" + levelId + ".json"));
			String line;
			while((line = reader.readLine()) != null) {
				jsonString.append(line);
			}
			reader.close();
			JSONArray json = new JSONArray(jsonString.toString());
			JSONArray row = null;
			Sprite tile;
			
			for (int y = 0, ly = json.length(); y < ly; y++) {
				row = json.getJSONArray(y);
				
				for (int x = 0, lx = row.length(); x < lx; x++) {
					int id = row.getInt(x);
					
					if (id == 1) {
						player.setPosition(x * 24, y * 32 - player.getHeight() / 2);
					}
					
					else if (id == 2 || id == 5 || id == 6 || id == 9) {
						String asset = "img/Tiles/Gem.png";
						
						asset = (id == 2) ? "img/Tiles/Exit.png" : asset;
						asset = (id == 5) ? getRandomBlockName() : asset;
						asset = (id == 6) ? "img/Tiles/Platform.png" : asset;
						tile = new Sprite(asset);
						tile.loadContent(Atlantis.content);
						tile.setPosition(x * 40, y * 32);
						this.scene.add(tile);
			
						if (id == 2 || id == 9) {
							tile.setName((id == 9) ? "gem" : "exit");
							this.items.add(tile);
						}
						else if (id == 5 || id == 6) {
							this.tiles.add(tile);
						}
					}
				}
			}
			
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String getRandomBlockName() {
		return ("img/Tiles/BlockA" + random.nextInt(6) + ".png").toString();
	}
	
	public void update(GameTime gameTime) {
		super.update(gameTime);
		
		if (this.gameMode == GameMode.Playing) {
			if (Atlantis.keyboard.left()) {
				this.player.play("left");
				this.player.setPosition(this.player.getX() - 2, this.player.getY());
			}
			else if (Atlantis.keyboard.right()) {
				this.player.play("right");
				this.player.setPosition(this.player.getX() + 2, this.player.getY());
			}
			else {
				this.player.play("idle");
			}
			
			if (Atlantis.keyboard.space()) {
				if (this.player.getDirection().x < 0) {
					this.player.play("jumpLeft");
				}
				else {
					this.player.play("jumpRight");
				}
				jump();
			}
			
			if (this.movementState != MovementState.Walking) {
				if (this.movementState == MovementState.JumpingUp) {
					this.player.setPosition(this.player.getX(), (int) (this.player.getY() - this.jumpSpeed));
					if (this.player.getY() < (this.initialJumpPosition.y - this.jumpHeight)) {
						this.movementState = MovementState.JumpingDown;
					}
				}
				
				if (movementState == MovementState.JumpingDown) {
					this.player.setPosition(this.player.getX(), (int) (this.player.getY() + this.jumpSpeed));
				}
				
				if (this.player.getY() >= this.initialJumpPosition.y) {
					this.movementState = MovementState.Walking;
					this.player.setY((int) this.initialJumpPosition.y);
				}
			}
			
			for (int i = 0, l = this.items.size(); i < l; i++) {
				if (this.player.getRectangle().contains(this.items.get(i).getRectangle())) {
					if (this.items.get(i).getName() == "exit") {
						if (!this.overlays[2].isActive()) {
							this.overlays[2].setActive(true);
							this.gameMode = GameMode.Win;
						}
					}
					else {
						
					}
				}
			}
		}
		else if (this.gameMode == GameMode.Win) {
			this.player.play("win");
		}
	}
	
	private void jump() {
		if (this.movementState == MovementState.Walking) {
			this.movementState = MovementState.JumpingUp;
			this.initialJumpPosition = new Vector2(this.player.getX(), this.player.getY());
		}
	}
}
