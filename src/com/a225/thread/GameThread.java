package com.a225.thread;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.a225.model.manager.ElementManager;
import com.a225.model.vo.SuperElement;

/**
 * 游戏线程控制
 * @author 麻瓜
 *
 */
public class GameThread extends Thread{
	public void run() {
		while(true) {
			//加载地图
			loadElement();
			//显示人物，流程，自动化
			runGame();
			//结束本地图
			overGame();
		}
		
	}
	
	//加载地图
	private void loadElement() {
		ElementManager.getManager().loadMap();
		ElementManager.getManager().loadElement();
	}
	
	//显示人物，游戏流程，自动化
	private void runGame() {
		while(true) {
			Map<String, List<SuperElement>> map = ElementManager.getManager().getMap();
			Set<String> set = map.keySet();
			for(String key:set) {
				List<SuperElement> list = map.get(key);
				for(int i=list.size()-1; i>=0; i--) {
					list.get(i).update();
					if(!list.get(i).isAlive())
						list.remove(i);
				}
			}
			
			//添加游戏的流程控制linkGame()?
			
			//玩家与炸弹碰撞死亡
			playerBoom();
			
			//控制runGame进程
			try {	
				sleep(50);
			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	//玩家与炸弹碰撞判断
	private void playerBoom() {
		List<SuperElement> players = ElementManager.getManager().getElementList("player");
		List<SuperElement> explodes = ElementManager.getManager().getElementList("explode");
		for(int i=0; i<players.size(); i++) {
			for(int j=0; j<explodes.size(); j++) {
				if(explodes.get(j).crash(players.get(i)))
					players.get(i).setAlive(false);
			}
		}
	}
	
	
	//runGame调用，加入拓展
	public void linkGame() {}
	
	//关卡结束
	private void overGame() {
		ElementManager.getManager().overGame();
	}

}
