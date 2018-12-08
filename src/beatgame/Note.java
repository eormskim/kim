package beatgame;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread{
	
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private int x, y = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME;
	private String noteType;
	private boolean proceeded = true; //현재 노트 진행 여부 체크
	
	public String getNoteType() { //현재 노트의 타입을 알 수 있게
		return noteType;
	}
	
	public boolean isProceeded() {
		return proceeded;
	}
	
	public void close() { //해당 노트가 움직이지 않게
		proceeded = false;
	}
	
	public Note(String noteType) {
		if(noteType.equals("S")) {
			x = 298;
		}else if(noteType.equals("D")) {
			x = 402;
		}else if(noteType.equals("F")) {
			x = 506;
		}else if(noteType.equals("J")) {
			x = 674;
		}else if(noteType.equals("K")) {
			x = 778;
		}else if(noteType.equals("L")) {
			x = 882;
		}
		this.noteType =noteType;
	}
	
	public void screenDraw(Graphics2D g) {
		if(!noteType.equals("Space")) {
			g.drawImage(noteBasicImage, x, y, null);
		}

	}
	
	public void drop() {
		y += Main.NOTE_SPEED;
		if(y > 620) {
			System.out.println("Miss");
			close();
		}
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				drop();
				if(proceeded) {
					Thread.sleep(Main.SLEEP_TIME);
				}else {
					interrupt();
					break;
				}
				
			}
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public String judge() { //판정 
		if(y > 620) {
			System.out.println("Miss");
			close();
			return"Miss";
	}else
		if(y >= 613) {
			System.out.println("Late");
			close();
			return"Late";
		}
		else if(y >= 600) {
			System.out.println("Good");
			close();
			return"Good"; 
		}
		else if(y >= 573) {
			System.out.println("Perfect");
			close();
			return"Perfect"; 
		}
		else if(y >= 550) {
			System.out.println("Good");
			close();
			return"Good"; 
		}
		else if(y >= 535) {
			System.out.println("Early");
			close();
			return"Early";
		}
		
		
		return"None";
	}


	
	public int getY() {
		return y;
	}
}
