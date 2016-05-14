package cn.wangxi.tankwar.bean;

import java.awt.Color;
import java.awt.Graphics;

import cn.wangxi.tankwar.bean.Tank.Direction;
import cn.wangxi.tankwar.gui.GameFrame;

public class Bullet {
	private int x,y;
	private int width=10, height=10;
	Direction dir;
	private int speed = 2;
	private boolean live = true;
	
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
			if(y<=26) {
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
	

}
