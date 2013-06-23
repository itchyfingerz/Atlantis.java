// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework;

/**
 * Represents the time that elapses during the game
 * @author Yannick
 */
public class GameTime {
	private long currentTime;
	protected long elapsedTime;
	protected long totalGameTime;
	
	public GameTime() {
		this.reset();
	}
	
	/**
	 * Update time.
	 */
	public void update() {
		long now = System.currentTimeMillis();
		this.elapsedTime = now - this.currentTime;
		this.totalGameTime += this.elapsedTime;
		this.currentTime = now;
	}

	/**
	 * Get the elapsed time since last update.
	 * @return Return the elapsed time since last update.
	 */
	public long getElapsedTime() {
		return (long)(this.elapsedTime);
	}

	/**
	 * Get the total elapsed time since the beginning.
	 * @return Return the total elapsed time since the beginning.
	 */
	public long getTotalGameTime() {
		return (long)(this.totalGameTime);
	}
	
	/**
	 * Reset the timer.
	 */
	public void reset() {
		this.elapsedTime = 0;
		this.totalGameTime = 0;
		this.currentTime = System.currentTimeMillis();
	}
}
