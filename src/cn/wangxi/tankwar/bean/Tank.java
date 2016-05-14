package cn.wangxi.tankwar.bean;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

import cn.wangxi.tankwar.gui.GameFrame;

public class Tank {
	private int x,y;
	private int width,height;
	private Color color;
	
	private boolean live = true;
	private int moveCount = 0;
	private boolean good = false;
	private int speed = 5;
	
	GameFrame game;
	
	Random dirControl = new Random();
	public boolean isGood() {
		return good;
	}
	public void setGood(boolean good) {
		this.good = good;
	}

	private boolean bL = false,bR = false,bD = false,bU = false;
	
	private int minRandomCount = 10;
	private int maxRandomCount = 30;
	private Random random = new Random();
	private int nextnum = 10;

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
	
	public Tank(int x, int y, int width, int height, GameFrame game) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.game = game;
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
	
	public void setRandomDir() {
		int dirNum = dirControl.nextInt(4);
		if(dirNum == 0) {
			dir = Direction.UP;
		}else if(dirNum == 1) {
			dir = Direction.DOWN;
		}else if(dirNum == 2) {
			dir = Direction.LEFT;
		}else if(dirNum == 3) {
			dir = Direction.RIGHT;
		}
		this.game.addBullet(fire());
		this.game.addBullet(fire());
		nextnum = random.nextInt(maxRandomCount-minRandomCount)+minRandomCount;
	}
	
	public void move() {
		
		if(good == false) {
			moveCount ++;
			if(moveCount == nextnum ) {
				setRandomDir();
				moveCount =0;
			}
		}else 
			setDir();
		if(dir == Direction.STOP)  {
			
		}else if(dir == Direction.RIGHT) {
			
			x+=speed;
			if(x>=GameFrame.WIDTH-width-pt.getWidth()) {
				x = GameFrame.WIDTH-width-pt.getWidth();
			}
		} else if(dir == Direction.LEFT) {
			
			x-=speed;
			if(x<=0+pt.getWidth()) {
				x = 0+pt.getWidth();
			}
		} else if(dir == Direction.UP) {
			
			y-=speed;
			if(y<=pt.getHeight()) {
				y=pt.getHeight();
			}
		} else {
			y+=speed;
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
		if(live){
			Color c = g.getColor();
			if(good == true)
				g.setColor(color);
			else if(good == false)
				g.setColor(Color.red);
			g.fillRect(x, y, width, height);
			g.setColor(c);
			pt.draw(g);
			move();
		}
	}
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
}
