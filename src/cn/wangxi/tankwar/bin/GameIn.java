package cn.wangxi.tankwar.bin;

import cn.wangxi.tankwar.gui.GameFrame;

public class GameIn {
	
	private GameFrame game;

	public static void main(String[] args) {
		new GameIn().launchGame();
	}
	
	public void launchGame() {
		game = new GameFrame();
	}
}
