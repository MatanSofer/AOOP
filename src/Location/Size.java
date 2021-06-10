//* Authors: Maor Arnon (ID: 205974553) and Matan Sofer (ID:208491811)
package Location;

public class Size {
	private int width;
	private int height;
	
	public Size(int width , int height) { //constructor for all fields
		this.width=width;
		this.height=height;
	}
	
	public int getWidth() { // return the width
		return this.width;
	}
	public int getHeight() { // return the width
		return this.height;
	}
	public String toString() { //to string
		return "Width is : " + width +
				" Height is : " +height;
	}

	public boolean isEqual(Size other) { //is equal 
		return this.getHeight() == other.getHeight() && this. getWidth() == other. getWidth();
	}
}
