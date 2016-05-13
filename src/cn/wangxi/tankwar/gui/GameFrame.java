package cn.wangxi.tankwar.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

import cn.wangxi.tankwar.bean.Tank;

public class GameFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8338324132629882171L;

	private Tank tank;
	
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
		Thread reflash = new Thread(new ReFlashThread());
		reflash.start();
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		tank.draw(g);
	}
	
	class ReFlashThread implements Runnable {

		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
}
