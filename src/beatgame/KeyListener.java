package beatgame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//Ű�����Է� �̺�Ʈ
public class KeyListener extends KeyAdapter {

	@Override
	public void keyPressed(KeyEvent e) { //Ű����
		if(Beat.game == null) {
			return; //���������� ������ Ű���� �Է� ���� ����
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
	public void keyReleased(KeyEvent e) { //Ű ��
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
