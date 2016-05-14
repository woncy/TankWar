package cn.wangxi.tankwar.bean;

import java.awt.Color;
import java.awt.Graphics;

import cn.wangxi.tankwar.gui.GameFrame;

public class Tank {
	private int x,y;
	private int width,height;
	private Color color;
	
	
	
	private boolean bL = false,bR = false,bD = false,bU = false;
	
	

	public enum Direction {LEFT,RIGHT,UP,DOWN,STOP};
	private Direction dir=Direction.STOP;
	private Paotong pt = new Paotong(x, y, Direction.UP);
	
	/**
	 * 
	 * class Direction {
	 * 	public static final Direction LEFT,UP,DOWN,RIGHT,STOP;
	 * 
	 * }
	 * 
	 * Color.red Color.green
	 * Color c = Color.green;
	 * 
	 * Direction.Left 
	 */
	
	
	public Tank(int x, int y, int width, int height, Color color) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	
	
	public void setDir() {
		if(bU && !bD && !bR && !bL) {
			dir = Direction.UP;
		}
		if(!bU && bD && !bR && !bL) {
			dir = Direction.DOWN;
		}
		if(!bU && !bD && bR && !bL) {
			dir = Direction.RIGHT;
		}
		if(!bU && !bD && !bR && bL) {
			dir = Direction.LEFT;
		}if(!bU && !bD && !bR && !bL) {
			dir = Direction.STOP;
		}
	}
	
	public void move() {
		setDir();
		if(dir == Direction.STOP)  {
			
		}else if(dir == Direction.RIGHT) {
			
			x++;
			if(x>=GameFrame.WIDTH-width-pt.getWidth()) {
				x = GameFrame.WIDTH-width-pt.getWidth();
			}
		} else if(dir == Direction.LEFT) {
			
			x--;
			if(x<=0+pt.getWidth()) {
				x = 0+pt.getWidth();
			}
		} else if(dir == Direction.UP) {
			
			y--;
			if(y<=26+pt.getHeight()) {
				y=26+pt.getHeight();
			}
		} else {
			y++;
			if(y>=GameFrame.HEIGHT-height-pt.getHeight()) {
				y=GameFrame.HEIGHT-height-pt.getHeight();
			}
		} 
		pt.move(this);
	}
	public boolean isbL() {
		return bL;
	}
	public void setbL(boolean bL) {
		this.bL = bL;
	}
	public boolean isbR() {
		return bR;
	}
	public void setbR(boolean bR) {
		this.bR = bR;
	}
	public boolean isbD() {
		return bD;
	}
	public void setbD(boolean bD) {
		this.bD = bD;
	}
	public boolean isbU() {
		return bU;
	}
	public void setbU(boolean bU) {
		this.bU = bU;
	}
	
	public Bullet fire() {
		return pt.fire();
	}
	
	public Direction getDir() {
		return this.dir;
	}
	
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(color);
		g.fillRect(x, y, width, height);
		g.setColor(c);
		pt.draw(g);
		move();
	}
}
