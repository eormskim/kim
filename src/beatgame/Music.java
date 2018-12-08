package beatgame;//3
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;


import javazoom.jl.player.Player;

public class Music extends Thread {

	private Player player; // 자바줌 사이트 라이브러리
	private boolean isLoop;
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/"+name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int getTime() {  //음악이 현재 어떤위치에 실행되는지 알려줌
		if (player == null)
			return 0;
		return player.getPosition();
	}
	
	public void closeMusic() { //플레이어 게임 실행시 종료
		isLoop = false;
		player.close();
		this.interrupt(); 
	}
	
	@Override
	public void run() {
		try {
			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			} while (isLoop); //반복 지정 (인트로뮤직)
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
}




