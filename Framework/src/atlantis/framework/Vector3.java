// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework;

/**
 * A Vector3 class that represent a vector with 3 coordinates X, Y and Z.
 * Some methods has been ported from the open source project SharpDX.
 * Check this project here : https://github.com/sharpdx/SharpDX
 * @author Yannick
 */
public class Vector3 {
	public static final Vector3 Zero() {
		return new Vector3(0, 0, 0);
	}
	
	public static final Vector3 One() {
		return new Vector3(1.0f, 1.0f, 1.0f);
	}
	
	public static final Vector3 UnitX() {
		return new Vector3(1.0f, 0.0f, 0.0f);
	}
	
	public static final Vector3 UnitY() {
		return new Vector3(0.0f, 1.0f, 0.0f);
	}
	
	public static final Vector3 UnitZ() {
		return new Vector3(0.0f, 0.0f, 1.0f);
	}
	
	public static final Vector3 Up() {
		return new Vector3(0.0f, 1.0f, 0.0f);
	}
	
	public static final Vector3 Down() {
		return new Vector3(0.0f, -1.0f, 0.0f);
	}
	
	public static final Vector3 Right() {
		return new Vector3(1.0f, 0.0f, 0.0f);
	}
	
	public static final Vector3 Left() {
		return new Vector3(-1.0f, 0.0f, 0.0f);
	}
	
    public static final Vector3 Forward() {
    	return new Vector3(0.0f, 0.0f, -1.0f);
    }
    
    public static final Vector3 Backward() {
    	return new Vector3(0.0f, 0.0f, 1.0f);
    }
	
	public float x;
	public float y;
	public float z;
	
	// ---
	// --- Constructors
	// ---
	
	/**
	 * Create a vector with x, y, z = 0, 0, 0
	 */
	public Vector3() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	/**
	 * Create a vector with x, y, z = value, value, value
	 * @param value A value to sets to each coordinates.
	 */
	public Vector3(float value) {
		this.x = value;
		this.y = value;
		this.z = value;
	}
	
	/**
	 * Create a vector with a specified position.
	 * @param x The X coordinate.
	 * @param y The Y coordinate.
	 * @param z The Z coordinate.
	 */
	public Vector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Create a vector with an existing vector. Values of existing vector are
	 * copied to the new vector.
	 * @param vector A vector to use.
	 */
	public Vector3(Vector3 vector) {
		this.x = vector.x;
		this.y = vector.y;
		this.z = vector.z;
	}
	
	// ---
	// --- Basic operations
	// ---
	
	/**
	 * Add a value to the current vector.
	 * @param value The value to add.
	 */
	public void add(float value) {
		this.x += value;
		this.y += value;
		this.z += value;
	}
	
	/**
	 * Add a Vector2 to the current vector.
	 * @param vector The Vector2 to add.
	 */
	public void add(Vector2 vector) {
		this.x += vector.x;
		this.y += vector.y;
	}
	
	/**
	 * Add a Vector3 to the current vector.
	 * @param vector The Vector3 to add.
	 */
	public void add(Vector3 vector) {
		this.x += vector.x;
		this.y += vector.y;
		this.z += vector.z;
	}
	
	/**
	 * Add two vectors.
	 * @param vec1
	 * @param vec2
	 * @return Return a new Vector3
	 */
	public static Vector3 add(Vector3 vec1, Vector3 vec2) {
		Vector3 result = new Vector3(vec1);
		result.add(vec2);
		return result;
	}
	
	/**
	 * Subtract a value to the current vector.
	 * @param value The value to Subtract.
	 */
	public void subtract(float value) {
		this.x -= value;
		this.y -= value;
		this.z -= value;
	}
	
	/**
	 * Subtract a Vector2 to the current vector.
	 * @param vector The Vector2 to Subtract.
	 */
	public void subtract(Vector2 vector) {
		this.x -= vector.x;
		this.y -= vector.y;
	}
	
	/**
	 * Subtract a Vector3 to the current vector.
	 * @param vector The Vector3 to Subtract.
	 */
	public void subtract(Vector3 vector) {
		this.x -= vector.x;
		this.y -= vector.y;
		this.z -= vector.z;
	}
	
	/**
	 * Subtract two vectors.
	 * @param vec1
	 * @param vec2
	 * @return Return a news Vector3.
	 */
	public static Vector3 subtract(Vector3 vec1, Vector3 vec2) {
		Vector3 result = new Vector3(vec1);
		result.subtract(vec2);
		return result;
	}
	
	/**
	 * divide a value to the current vector.
	 * @param value The value to divide.
	 */
	public void divide(float value) {
		if (value != 0) {
			this.x /= value;
			this.y /= value;
			this.z /= value;
		}
	}
	
	/**
	 * divide a Vector2 to the current vector.
	 * @param vector The Vector2 to divide.
	 */
	public void divide(Vector2 vector) {
		if (vector.x != 0) {
			this.x /= vector.x;
		}
		if (vector.y != 0) {
			this.y /= vector.y;
		}
	}
	
	/**
	 * divide a Vector3 to the current vector.
	 * @param vector The Vector3 to divide.
	 */
	public void divide(Vector3 vector) {
		if (vector.x != 0) {
			this.x /= vector.x;
		}
		if (vector.y != 0) {
			this.y /= vector.y;
		}
		if (vector.z != 0) {
			this.z /= vector.z;
		}
	}
	
	/**
	 * Divide two vectors.
	 * @param vec1
	 * @param vec2
	 * @return Return a new Vector3.
	 */
	public static Vector3 divide(Vector3 vec1, Vector3 vec2) {
		Vector3 vec = new Vector3(vec1);
		vec.divide(vec2);
		return vec;
	}
	
	/**
	 * multiply a value to the current vector.
	 * @param value The value to multiply.
	 */
	public void multiply(float value) {
		this.x *= value;
		this.y *= value;
		this.z *= value;
	}
	
	/**
	 * multiply a Vector2 to the current vector.
	 * @param vector The Vector2 to multiply.
	 */
	public void multiply(Vector2 vector) {
		this.x *= vector.x;
		this.y *= vector.y;
	}
	
	/**
	 * multiply a Vector3 to the current vector.
	 * @param vector The Vector3 to multiply.
	 */
	public void multiply(Vector3 vector) {
		this.x *= vector.x;
		this.y *= vector.y;
		this.z *= vector.z;
	}
	
	/**
	 * Multiply two vectors.
	 * @param vec1
	 * @param vec2
	 * @return Return a new Vector3.
	 */
	public static Vector3 multiply(Vector3 vec1, Vector3 vec2) {
		Vector3 vec = new Vector3(vec1);
		vec.multiply(vec2);
		return vec;
	}
	
	public static Vector3 multiply(Vector3 vector, float value) {
		Vector3 vec = new Vector3(vector);
		vec.multiply(value);
		return vec;
	}
	
	// ---
	// --- Advanced methods
	// ---
	
	/**
	 * Calculates the cross products of two vectors.
	 * @param vec1 Fist vector to use.
	 * @param vec2 Second vector to use.
	 * @return Return the cross products of the two vectors.
	 */
	public static Vector3 cross(Vector3 vec1, Vector3 vec2) {
		Vector3 vector = new Vector3();
		vector.x = (vec1.y * vec2.z) - (vec2.y * vec1.z);
		vector.y = -((vec1.x * vec2.z) - (vec2.x * vec1.z));
		vector.z = (vec1.x * vec2.y) - (vec2.x * vec1.y);
		return vector;
	}
	
	/**
	 * Gets the distance between two vectors.
	 * @param vector
	 * @return Return the distance between two vectors.
	 */
	public static double distance(Vector3 vec1, Vector3 vec2) {
		return Math.sqrt(distanceSquared(vec1, vec2));
	}
	
	/**
	 * Gets the distance squared between to vectors.
	 */
	public static double distanceSquared(Vector3 vec1, Vector3 vec2) {
		double dx = vec1.x - vec2.x;
		double dy = vec1.y - vec2.y;
		double dz = vec1.z - vec2.z;
		return (dx * dx) + (dy * dy) + (dz * dz);
	}
	
	/**
	 * Calculates the dot product of two vectors.
	 * @param vec1 First vector to use.
	 * @param vec2 Second vector to use.
	 * @return Return the dot product of two vectors.
	 */
	public static float dot(Vector3 vec1, Vector3 vec2) {
		return (vec1.x * vec2.x) + (vec1.y * vec2.y) + (vec1.z * vec2.z);
	}
	
	/**
	 * Gets the length of the vector.
	 * @return Return the length of the vector.
	 */
	public double length() {
		return Math.sqrt((this.x * this.x) + (this.y * this.y) + (this.z * this.z));
	}
	
	/**
	 * Linear interpolation.
	 * @param vector A vector to use for interpolation
	 * @param amount Value between 0 and 1 indicating the weight of vector.
	 */
	public void lerp(Vector3 vector, float amount) {
		this.x = MathHelper.lerp(this.x, vector.x, amount);
		this.y = MathHelper.lerp(this.y, vector.y, amount);
		this.z = MathHelper.lerp(this.z, vector.z, amount);
	}

	/**
	 * Linearly interpolates between two vectors.
	 * @param vec1 First vector
	 * @param vec2 Second vector
	 * @param amount Value between 0 and 1 indicating the weight of vec2.
	 * @return Return a interpolated Vector3.
	 */
	public static Vector3 lerp(Vector3 vec1, Vector3 vec2, float amount) {
		Vector3 vector = new Vector3(
				MathHelper.lerp(vec1.x, vec2.x, amount), 
				MathHelper.lerp(vec1.y, vec2.y, amount),
				MathHelper.lerp(vec1.z, vec2.z, amount));
		return vector;
	}
	
	/**
	 * Gets a vector of the minimum of the two vectors.
	 * @param vec1
	 * @param vec2
	 * @return Return a vector that correspond of the minimum of the two vectors.
	 */
	public static Vector3 min(Vector3 vec1, Vector3 vec2) {
		Vector3 vector = new Vector3();
		vector.x = (vec1.x < vec2.x) ? vec1.x : vec2.x;
		vector.y = (vec1.y < vec2.y) ? vec1.y : vec2.y;
		vector.z = (vec1.z < vec2.z) ? vec1.z : vec2.z;
		return vector;
	}
	
	/**
	 * Gets a vector of the maximum of the two vectors.
	 * @param vec1
	 * @param vec2
	 * @return Return a vector that correspond of the maximum of the two vectors.
	 */
	public static Vector3 max(Vector3 vec1, Vector3 vec2) {
		Vector3 vector = new Vector3();
		vector.x = (vec1.x > vec2.x) ? vec1.x : vec2.x;
		vector.y = (vec1.y > vec2.y) ? vec1.y : vec2.y;
		vector.z = (vec1.z > vec2.z) ? vec1.z : vec2.z;
		return vector;
	}
	
	/**
	 * Negage the vector.
	 */
	public void negate() {
		this.x *= -1;
		this.y *= -1;
		this.z *= -1;
	}

	/**
	 * Negate a vector.
	 * @param vector
	 * @return Return a negated vector.
	 */
	public static Vector3 negate(Vector3 vector) {
		Vector3 vec = new Vector3(vector);
		vec.negate();
		return vec;
	}
	
	/**
	 * Normalize vector
	 */
	public void normalize() {
		float factor = (float)distance(this, new Vector3());
		
		if (factor != 0) {
			factor = 1.0f / factor;
			this.set(this.x * factor, this.y * factor, this.z * factor);
		}
	}
	
	/**
	 * Normalize a vector.
	 * @param vector A vector to normalize.
	 * @return result A normalized vector.
	 */
	public static Vector3 normalize(Vector3 vector) {
		Vector3 result = new Vector3(vector);
		result.normalize();
		return result;
	}
	
	/**
	 * Gets a transformed Vector3 from a position and a matrix.
	 * @param position
	 * @param matrix
	 * @return A tranformed vector.
	 */
	public static Vector3 transform(Vector3 position, Matrix matrix) {
		Vector3 vector = new Vector3(
			(position.x * matrix.M11) + (position.y * matrix.M21) + (position.z * matrix.M31) + matrix.M41,
	        (position.x * matrix.M12) + (position.y * matrix.M22) + (position.z * matrix.M32) + matrix.M42,
	        (position.x * matrix.M13) + (position.y * matrix.M23) + (position.z * matrix.M33) + matrix.M43 
		);
		
		return vector;
	}
	
	/**
	 * Gets an homogeneous transformed vector from a position and a matrix.
	 * @param position
	 * @param matrix
	 * @return A tranformed vector.
	 */
	public static Vector3 transformCoordinate(Vector3 position, Matrix transform) {
		 Vector4 vector = new Vector4();
         vector.x = (position.x * transform.M11) + (position.y * transform.M21) + (position.z * transform.M31) + transform.M41;
         vector.y = (position.x * transform.M12) + (position.y * transform.M22) + (position.z * transform.M32) + transform.M42;
         vector.z = (position.x * transform.M13) + (position.y * transform.M23) + (position.z * transform.M33) + transform.M43;
         vector.w = 1.0f / ((position.x * transform.M14) + (position.y * transform.M24) + (position.z * transform.M34) + transform.M44);
         return new Vector3(vector.x * vector.w, vector.y * vector.w, vector.z * vector.w);
	}
	
	/**
	 * Changes the 3 coordinates
	 * @param x The new X coordinate.
	 * @param y The new Y coordinate.
	 * @param z The new Z coordinate.
	 */
	public void set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public String toString() {
		return "x: " + this.x + " y: " + this.y + " z: " + this.z;
	}

	public boolean equals(Vector3 other) {
		return this.x == other.x && this.y == other.y && this.z == other.z;
	}
}
