package com.a225.main;

import javax.swing.JFrame;

import com.a225.frame.GameFrame;
import com.a225.frame.GameJPanel;

/**
 * 游戏启动入口
 * @ClassName: GameStart  
 * @Description:  
 * @author: WeiXiao
 * @CreateDate: 2019年4月8日 下午4:17:37
 */
public class GameStart {

	//游戏启动入口
	public static void main(String[] args) {
		// 资源加载
		// 窗体加载（自动化……）
		GameFrame gameFrame = new GameFrame();
		GameJPanel gameJPanel = new GameJPanel();
		gameFrame.setjPanel(gameJPanel);
		gameFrame.addJPanel();
		// 监听加载
		gameFrame.addListener();
		// 游戏开始
		gameFrame.start();
	}

}
