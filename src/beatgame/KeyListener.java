package beatgame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//키보드입력 이벤트
public class KeyListener extends KeyAdapter {

	@Override
	public void keyPressed(KeyEvent e) { //키누름
		if(Beat.game == null) {
			return; //게임중이지 않으면 키보드 입력 받지 않음
		}
		if(e.getKeyCode() == KeyEvent.VK_S){
			Beat.game.pressS();
		}else if(e.getKeyCode() == KeyEvent.VK_D) {
			Beat.game.pressD();
		}else if(e.getKeyCode() == KeyEvent.VK_F) {
			Beat.game.pressF();
		}else if(e.getKeyCode() == KeyEvent.VK_J) {
			Beat.game.pressJ();
		}else if(e.getKeyCode() == KeyEvent.VK_K) {
			Beat.game.pressK();
		}else if(e.getKeyCode() == KeyEvent.VK_L) {
			Beat.game.pressL();

		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) { //키 땜
		if(Beat.game == null) {
			return;
		}
		if(e.getKeyCode() == KeyEvent.VK_S){
			Beat.game.releaseS();
		}else if(e.getKeyCode() == KeyEvent.VK_D) {
			Beat.game.releaseD();
		}else if(e.getKeyCode() == KeyEvent.VK_F) {
			Beat.game.releaseF();
		}else if(e.getKeyCode() == KeyEvent.VK_J) {
			Beat.game.releaseJ();
		}else if(e.getKeyCode() == KeyEvent.VK_K) {
			Beat.game.releaseK();
		}else if(e.getKeyCode() == KeyEvent.VK_L) {
			Beat.game.releaseL();
		}
	}
	
	
}
