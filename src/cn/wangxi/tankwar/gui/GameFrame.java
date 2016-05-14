package cn.wangxi.tankwar.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cn.wangxi.tankwar.bean.Bullet;
import cn.wangxi.tankwar.bean.Tank;

public class GameFrame extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8338324132629882171L;

	GameFrame gameframe = this;
	
	JFrame game = new JFrame();
	
	//Image offScreenImage = null;
	private Tank tank;
	private List<Bullet> bullets = new ArrayList<Bullet>();
	private List<Bullet> bulletsBad = new ArrayList<Bullet>();
	private List<Tank> tanks = new ArrayList<Tank>();
	
	private Random random = new Random();
	
	public static final int WIDTH=500;
	public static final int HEIGHT=600;

	public GameFrame() {
		super();
		this.setBackground(Color.GREEN);
		game.setBounds(200, 100, WIDTH, HEIGHT);
		game.setVisible(true);
		
		game.add(this);
		game.setResizable(false);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tank = new Tank(100,50,50,50,Color.black);
		tank.setGood(true);
	
		
		game.addKeyListener(new KeyAdapter() {

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
				}else if(key == KeyEvent.VK_ENTER) {
					tanks.add(new Tank(random.nextInt(300)+100,random.nextInt(400)+100,50,50,gameframe));
				}else if(key == KeyEvent.VK_F1) {
					tank = new Tank(100,50,50,50,Color.black);
					tank.setGood(true);
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
	/**
	 public void update(Graphics g) {  
		    if (offScreenImage == null) {  
		        offScreenImage = this.createImage(GameFrame.WIDTH, GameFrame.HEIGHT);  
		    }  
		    Graphics goffScreen = offScreenImage.getGraphics();// 重新定义一个画虚拟桌布的画笔//  
		    Color c = goffScreen.getColor();  
		    goffScreen.setColor(Color.darkGray);  
		    goffScreen.fillRect(0, 0, GameFrame.WIDTH, GameFrame.HEIGHT);  
		    goffScreen.setColor(c);  
		    paint(goffScreen);  
		    g.drawImage(offScreenImage, 0, 0, null);  
	 }
	 */

	
	public void addBullet(Bullet bullet) {
		bulletsBad.add(bullet);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		tank.draw(g);

		for(int i=0; i<bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			if(bullet.isLive()) {
				bullet.draw(g);
				bullet.killTank(tanks);
				
			}else {
				bullets.remove(i);
				
			}
		}
		
		for(int i=0; i<bulletsBad.size(); i++) {
			Bullet bullet = bulletsBad.get(i);
			if(bullet.isLive()) {
				bullet.draw(g);
				bullet.killTank(tank);
				
			}else {
				bulletsBad.remove(i);
				
			}
		}
		
		
		
		for(int i=0; i<tanks.size(); i++) {
			Tank tank = tanks.get(i);
			if(tank.isLive()) {
				tank.draw(g);
			}else
				tanks.remove(i);
		}
		
	}
	
	class ReFlashThread implements Runnable {

		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
			
		}
		
	}
}
