package abadie.aubin;
public class Coord {

	private char x;
	private int y;
	private boolean hit;

	public Coord(String c) {
		this.x = c.charAt(0);
		if (c.length() == 3) {
			this.y = Integer.parseInt(c.substring(1,3));
		} else {
			this.y = Integer.parseInt(c.substring(1));
		}
		this.hit = false;
	}

	public char getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setHit(boolean hit) {
		this.hit = hit;
	}

	public boolean isHit() {
		return hit;
	}
	
	@Override
	public String toString() {
		return getX() + Integer.toString(getY());
	}

	@Override
	public boolean equals(Object obj) {

		boolean result = false;

		if (obj instanceof Coord) {
			Coord c = (Coord) obj;
			result = (this.getX() == c.getX() && this.getY() == c.getY());
		}

		return result;
	}
}
