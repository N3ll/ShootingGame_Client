package game;

public class Player {
	String name;
	int xpos;
	int ypos;
	int xShoot;
	int yShoot;
	int point;
	String direction;
	boolean active;

	public Player() {
		this.name = "";
		xpos = 0;
		ypos = 0;
		point = 0;
		direction = "";
		active = true;
		xShoot = 0;
		yShoot = 0;
	}

	public Player(String name) {
		this.name = name;
		xpos = 0;
		ypos = 0;
		point = 0;
		direction = "";
		active = true;
		xShoot = 0;
		yShoot = 0;
	}

	public int getxShoot() {
		return this.xShoot;
	}

	public void setxShoot(int xShoot) {
		this.xShoot = xShoot;
	}

	public int getyShoot() {
		return this.yShoot;
	}

	public void setyShoot(int yShoot) {
		this.yShoot = yShoot;
	}

	public int getPoint() {
		return this.point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setInactive() {
		this.active = false;
	}

	public boolean isActive() {
		return this.active;
	}

	public int getXpos() {
		return xpos;
	}

	public void setXpos(int xpos) {
		this.xpos = xpos;
	}

	public int getYpos() {
		return ypos;
	}

	public void setYpos(int ypos) {
		this.ypos = ypos;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	@Override
	public String toString() {
		return name + " " + xpos + " " + ypos + " " + direction + " " + point
				+ " " + xShoot + " " + yShoot;
	}

	String ToString() {
		return name + "   " + point;
	}

	public void addOnePoint() {

		point++;
	}

	public void subOnePoint() {
		point--;
	}
}
