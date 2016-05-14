package cn.wangxi.tankwar.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import cn.wangxi.tankwar.bean.Bullet;
import cn.wangxi.tankwar.bean.Tank;

public class GameFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8338324132629882171L;

	private Tank tank;
	private List<Bullet> bullets = new ArrayList<Bullet>();
	
	public static final int WIDTH=500;
	public static final int HEIGHT=600;

	public GameFrame() {
		super();
		this.setBackground(Color.GREEN);
		this.setBounds(200, 100, WIDTH, HEIGHT);
		this.setVisible(true);
		
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tank = new Tank(100,50,50,50,Color.black);
		
	
		
		this.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_UP) {
					tank.setbU(true);
				}else if(key == KeyEvent.VK_DOWN) {
					tank.setbD(true);
				}else if(key == KeyEvent.VK_LEFT) {
					tank.setbL(true);
				}else if(key == KeyEvent.VK_RIGHT) {
					tank.setbR(true);
				}else if(key == KeyEvent.VK_SPACE) {
					bullets.add(tank.fire());
				}
			}

			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_UP) {
					tank.setbU(false);
				}else if(key == KeyEvent.VK_DOWN) {
					tank.setbD(false);
				}else if(key == KeyEvent.VK_LEFT) {
					tank.setbL(false);
				}else if(key == KeyEvent.VK_RIGHT) {
					tank.setbR(false);
				}
			}
			
		});
		
		Thread reflash = new Thread(new ReFlashThread());
		reflash.start();
		
		
	}
	
	
	
	public void paint(Graphics g) {
		super.paint(g);
		tank.draw(g);
		for (Bullet bullet : bullets) {
			bullet.draw(g);
		}
	}
	
	class ReFlashThread implements Runnable {

		public void run() {
			while(true) {
				repaint(1000, 0, 0, WIDTH, HEIGHT);
			}
			
		}
		
	}
}
