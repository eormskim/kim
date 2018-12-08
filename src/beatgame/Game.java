package beatgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//인스턴스 변수를 이용해 게임을 컨트롤
public class Game extends Thread {

	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage();
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();
	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image OrangeFlareImage;
	private Image judgeImage;
	private Image keyPadSImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadLImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	
	private Graphics2D g;
	private String titleName;
	private String play;
	private String musicTitle;
	private Music gameMusic;
	public static int point;
	public int no;
	ArrayList<Note> noteList = new ArrayList<Note>(); // 노트 관리 배열

	public Game(String titleName, String play, String musicTitle) {
		this.titleName = titleName;
		this.play = play;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);

	}
	public void setno() {
		
	}
	



	public void screenDraw(Graphics2D g) {
		g.drawImage(noteRouteSImage, 298, 30, null);
		g.drawImage(noteRouteDImage, 402, 30, null);
		g.drawImage(noteRouteFImage, 506, 30, null);
		g.drawImage(noteRouteJImage, 674, 30, null);
		g.drawImage(noteRouteKImage, 778, 30, null);
		g.drawImage(noteRouteLImage, 882, 30, null);
		g.drawImage(noteRouteLineImage, 294, 30, null);
		g.drawImage(noteRouteLineImage, 398, 30, null);
		g.drawImage(noteRouteLineImage, 502, 30, null);
		g.drawImage(noteRouteLineImage, 606, 30, null);
		g.drawImage(noteRouteLineImage, 670, 30, null);
		g.drawImage(noteRouteLineImage, 774, 30, null);
		g.drawImage(noteRouteLineImage, 878, 30, null);
		g.drawImage(noteRouteLineImage, 982, 30, null);
		g.drawImage(gameInfoImage, 0, 660, null);
		g.drawImage(judgementLineImage, 0, 580, null);

		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if(note.getY() > 620) {
				judgeImage = new ImageIcon(Main.class.getResource("../images/judgeMiss.png")).getImage();
				point -= 100;
			}
			if (!note.isProceeded()) { // 사용되지않는 노트 지움
				noteList.remove(i);
				i--;
			} else {
				note.screenDraw(g);
			}
		} // S-298 D-402 F-506 J-674 K-778 L-882

		g.setColor(Color.WHITE);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);// 텍스트 깨짐 방지
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(titleName, 20, 702); // 곡정보
		g.drawString(play, 1190, 702);
		g.setColor(Color.DARK_GRAY);
		g.drawString("S", 338, 609);
		g.drawString("D", 442, 609);
		g.drawString("F", 546, 609);
		g.drawString("J", 714, 609);
		g.drawString("K", 818, 609);
		g.drawString("L", 922, 609);
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.drawString(getscoreEvent()+"", 565, 702); // 점수
		g.drawImage(OrangeFlareImage, 170, 100, null);
		g.drawImage(judgeImage, 460, 360, null);
		g.drawImage(keyPadSImage, 298, 580, null);
		g.drawImage(keyPadDImage, 402, 580, null);
		g.drawImage(keyPadFImage, 506, 580, null);
		g.drawImage(keyPadJImage, 674, 580, null);
		g.drawImage(keyPadKImage, 778, 580, null);
		g.drawImage(keyPadLImage, 882, 580, null);

	}

	public void pressS() {
		judge("S");
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadSImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
	}

	public void releaseS() {
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadSImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
	}

	public void releaseD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
	}

	public void releaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
	}

	public void releaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
	}

	public void releaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressL() {
		judge("L");
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadLImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
	}

	public void releaseL() {
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadLImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	@Override
	public void run() {
		dropNotes(this.titleName); // 현재 선택된 타이틀 실행
	}

	public void close() {
		gameMusic.closeMusic();
		this.interrupt();
	}


	
	public void dropNotes(String titleName) {
		// S-298 D-402 F-506 J-674 K-778 L-882
		setno();
		BeatTandN[] beats = null;
		if (titleName.equals("Electronic Vibes - HookSounds")) {
			int startTime = 2000 - Main.REACH_TIME * 1000;
			int gap = 125;
			beats = new BeatTandN[] { new BeatTandN(startTime, "S"), new BeatTandN(startTime + gap * 2, "D"),
					new BeatTandN(startTime + gap * 6, "F"), new BeatTandN(startTime + gap * 7, "D"),
					new BeatTandN(startTime + gap * 8, "F"), new BeatTandN(startTime + gap * 9, "D"),
					new BeatTandN(startTime + gap * 14, "J"), new BeatTandN(startTime + gap * 16, "K"),
					new BeatTandN(startTime + gap * 18, "J"), new BeatTandN(startTime + gap * 20, "K"),
					new BeatTandN(startTime + gap * 23, "L"), new BeatTandN(startTime + gap * 24, "L"),
					new BeatTandN(startTime + gap * 25, "L"),

					new BeatTandN(startTime + gap * 30, "S"), new BeatTandN(startTime + gap * 32, "D"),
					new BeatTandN(startTime + gap * 36, "F"), new BeatTandN(startTime + gap * 37, "D"),
					new BeatTandN(startTime + gap * 38, "F"), new BeatTandN(startTime + gap * 39, "D"),
					new BeatTandN(startTime + gap * 44, "J"), new BeatTandN(startTime + gap * 46, "K"),
					new BeatTandN(startTime + gap * 48, "J"), new BeatTandN(startTime + gap * 50, "K"),
					new BeatTandN(startTime + gap * 53, "J"), new BeatTandN(startTime + gap * 54, "J"),
					new BeatTandN(startTime + gap * 55, "J"),

					new BeatTandN(startTime + gap * 60, "S"), new BeatTandN(startTime + gap * 62, "D"),
					new BeatTandN(startTime + gap * 66, "F"), new BeatTandN(startTime + gap * 67, "D"),
					new BeatTandN(startTime + gap * 68, "F"), new BeatTandN(startTime + gap * 69, "D"),
					new BeatTandN(startTime + gap * 74, "J"), new BeatTandN(startTime + gap * 76, "K"),
					new BeatTandN(startTime + gap * 78, "J"), new BeatTandN(startTime + gap * 80, "K"),
					new BeatTandN(startTime + gap * 83, "J"), new BeatTandN(startTime + gap * 84, "J"),
					new BeatTandN(startTime + gap * 85, "J"),

					new BeatTandN(startTime + gap * 90, "L"), new BeatTandN(startTime + gap * 92, "K"),
					new BeatTandN(startTime + gap * 96, "J"), new BeatTandN(startTime + gap * 97, "K"),
					new BeatTandN(startTime + gap * 98, "J"), new BeatTandN(startTime + gap * 99, "K"),
					new BeatTandN(startTime + gap * 104, "F"), new BeatTandN(startTime + gap * 106, "D"),
					new BeatTandN(startTime + gap * 108, "F"), new BeatTandN(startTime + gap * 110, "D"),
					new BeatTandN(startTime + gap * 113, "S"), new BeatTandN(startTime + gap * 114, "S"),
					new BeatTandN(startTime + gap * 115, "S"),

					new BeatTandN(startTime + gap * 123, "S"), new BeatTandN(startTime + gap * 125, "D"),
					new BeatTandN(startTime + gap * 129, "F"), new BeatTandN(startTime + gap * 130, "D"),
					new BeatTandN(startTime + gap * 131, "F"), new BeatTandN(startTime + gap * 132, "D"),
					new BeatTandN(startTime + gap * 137, "J"), new BeatTandN(startTime + gap * 139, "K"),
					new BeatTandN(startTime + gap * 141, "J"), new BeatTandN(startTime + gap * 143, "K"),
					new BeatTandN(startTime + gap * 146, "J"), new BeatTandN(startTime + gap * 147, "J"),
					new BeatTandN(startTime + gap * 148, "J"),

					new BeatTandN(startTime + gap * 153, "S"), new BeatTandN(startTime + gap * 155, "D"),
					new BeatTandN(startTime + gap * 159, "F"), new BeatTandN(startTime + gap * 160, "D"),
					new BeatTandN(startTime + gap * 161, "F"), new BeatTandN(startTime + gap * 162, "D"),
					new BeatTandN(startTime + gap * 167, "J"), new BeatTandN(startTime + gap * 169, "K"),
					new BeatTandN(startTime + gap * 171, "J"), new BeatTandN(startTime + gap * 173, "K"),
					new BeatTandN(startTime + gap * 176, "J"), new BeatTandN(startTime + gap * 177, "J"),
					new BeatTandN(startTime + gap * 179, "J"), new BeatTandN(startTime + gap * 180, "J"),

					new BeatTandN(startTime + gap * 184, "S"), new BeatTandN(startTime + gap * 186, "D"),
					new BeatTandN(startTime + gap * 190, "F"), new BeatTandN(startTime + gap * 191, "D"),
					new BeatTandN(startTime + gap * 192, "F"), new BeatTandN(startTime + gap * 193, "D"),
					new BeatTandN(startTime + gap * 198, "J"), new BeatTandN(startTime + gap * 200, "K"),
					new BeatTandN(startTime + gap * 202, "J"), new BeatTandN(startTime + gap * 204, "K"),
					new BeatTandN(startTime + gap * 205, "J"), new BeatTandN(startTime + gap * 206, "J"),
					new BeatTandN(startTime + gap * 207, "J"),

					new BeatTandN(startTime + gap * 212, "S"), new BeatTandN(startTime + gap * 214, "D"),
					new BeatTandN(startTime + gap * 218, "F"), new BeatTandN(startTime + gap * 219, "D"),
					new BeatTandN(startTime + gap * 220, "F"), new BeatTandN(startTime + gap * 221, "D"),
					new BeatTandN(startTime + gap * 226, "J"), new BeatTandN(startTime + gap * 228, "K"),
					new BeatTandN(startTime + gap * 230, "J"), new BeatTandN(startTime + gap * 232, "K"),
					new BeatTandN(startTime + gap * 233, "J"), new BeatTandN(startTime + gap * 234, "J"),
					new BeatTandN(startTime + gap * 235, "J"),

					new BeatTandN(startTime + gap * 300, "S"),
					
					

			};
		} else if (titleName.equals("Scooter - Copyright")) {
			int startTime = 1000;
			beats = new BeatTandN[] { new BeatTandN(startTime, "S"), };
		} else if (titleName.equals("Bay Breeze - FortyThr33")) {
			int startTime = 1000;
			beats = new BeatTandN[] { new BeatTandN(startTime, "S"), };
		}
		

		int i = 0;
		gameMusic.start();
		while (i < beats.length && !isInterrupted()) { // 현재재생되는곡 시점 실시간파악 해당위치에 맞는 노트 떨어뜨리기
//			boolean dropped = false;
			if (beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
//				dropped = true;
			}
//			if (!dropped) {
//				try {
//					Thread.sleep(10);
//				} catch (InterruptedException e) {
//				}
//			}
		}
				
				if(getscoreEvent()+"" != null) {
					try {
						sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				DB b = new DB();
				int bcad=b.select();
				JOptionPane.showMessageDialog(null, getscoreEvent()+"점입니다.");
				b.update(bcad,getscoreEvent());
				Beat.backMain();
				if(getscoreEvent()+"" != null) {
					point = 0;	
				}
				}

	}
	

	public void judge(String input) {
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (input.equals(note.getNoteType())) {
				judgeEvent(note.judge());// 해당 노트의 판정을 불러와 아래로 넘겨줌
				setscoreEvent(note.judge());
				break;
			}
		}
	}

	public void judgeEvent(String judge) {

		if (!judge.equals("None")) {
			OrangeFlareImage = new ImageIcon(Main.class.getResource("../images/OrangeFlare.png")).getImage();
		}
		if (judge.equals("Miss")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeMiss.png")).getImage();
		} else if (judge.equals("Late")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeLate.png")).getImage();
		} else if (judge.equals("Good")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeGood.png")).getImage();
		} else if (judge.equals("Perfect")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgePerfect.png")).getImage();
		} else if (judge.equals("Early")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeEarly.png")).getImage();
		}

	}

	public void setscoreEvent(String judge) {
			

			if(judge.equals("Miss")) {
				point -= 100;
			}else
			if(judge.equals("Late")) {
				point += 100;
			}else
			 if(judge.equals("Good")) {
				point += 500;
			}else
			 if(judge.equals("Perfect")) {
				point += 1000;
			}else
			 if(judge.equals("Early")) {
				point += 100; 
			 }
		}	
	public int getscoreEvent() {
		
		return point;
		
	}


}
