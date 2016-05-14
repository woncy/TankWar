package cn.wangxi.tankwar.bean;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

import cn.wangxi.tankwar.bean.Tank.Direction;
import cn.wangxi.tankwar.gui.GameFrame;

public class Bullet {
	private int x,y;
	private int width=10, height=10;
	Direction dir;
	private int speed = 8;
	private boolean live = true;
	
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public Bullet(int x, int y,Direction dir) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	public void draw (Graphics g) {
		if(live) {
			Color c = g.getColor();
			g.setColor(Color.black);
			g.fillOval(x, y, width, height);
			g.setColor(c);
			move();
		}
	}
	
	public void move() {
		if(dir == Direction.UP) {
			y-=speed;
			if(y<=0) {
				this.live = false;
			}
		}else if(dir == Direction.DOWN) {
			y+=speed;
			if(y>=GameFrame.HEIGHT-height) {
				this.live = false;
			}
		}else if(dir == Direction.LEFT) {
			x-=speed;
			if(x<=0) {
				this.live = false;
			}
		}else if(dir == Direction.RIGHT) {
			x+=speed;
			if(x>=GameFrame.WIDTH-width) {
				this.live = false;
			}
		}
	}
	
	public void killTank(Tank tank) {
		if(new Rectangle(tank.getX(),tank.getY(),tank.getWidth(),
				tank.getHeight()).intersects(new Rectangle(x,y,width,height))){
			tank.setLive(false);
			this.live = false;
		}
	}
	
	public void killTank(List<Tank> tanks) {
		for(int i=0; i<tanks.size(); i++) {
			Tank tank = tanks.get(i);
			if(new Rectangle(tank.getX(),tank.getY(),tank.getWidth(),
					tank.getHeight()).intersects(new Rectangle(x,y,width,height))){
				tank.setLive(false);
				this.live = false;
			}
		}
	}
	

}
