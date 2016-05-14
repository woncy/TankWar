package cn.wangxi.tankwar.bean;

import java.awt.Color;
import java.awt.Graphics;

import cn.wangxi.tankwar.bean.Tank.Direction;

public class Paotong {
	
	private static final int  W = 10,H = 20;
	
	private int x,y;
	private int width,height;
	private Direction dir;
	
	public Paotong(int x,int y,Direction dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	public void move(Tank tank) {
		Direction oldDir = dir;
		this.dir = tank.getDir();
		if(dir == Direction.STOP) {
			dir = oldDir;
		}
	
		setShape();
		
		if(dir == Direction.UP) {
			this.x = tank.getX() + (tank.getWidth() -width )/2;
			this.y = tank.getY() - height;
		}else if(dir == Direction.DOWN) {
			this.x = tank.getX() + (tank.getWidth() -width )/2;
			this.y = tank.getY() + tank.getHeight();
		}else if(dir == Direction.LEFT) {
			this.x = tank.getX() - width;
			this.y = tank.getY() + (tank.getHeight()-height)/2;
		}else if(dir == Direction.RIGHT) {
			this.x = tank.getX() + tank.getWidth();
			this.y = tank.getY() + (tank.getHeight()-height)/2;
		}
		
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

	public void setShape() {
		
		if(dir == Direction.UP || dir == Direction.DOWN) {
			width = W;
			height = H;
		}else {
			width = H;
			height = W;
		}
	}
	
	public Bullet fire() {
		return new Bullet(x, y, dir);
		
	}
	
	public void draw(Graphics g ) {
		
		Color c = g.getColor();
		g.setColor(Color.black);
		g.fillRect(x, y, width, height);
		g.setColor(c);
	}
	

}
