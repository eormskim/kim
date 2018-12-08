package beatgame;//2

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Login extends JFrame {
	public Login() {
		setTitle("Beat");
		setSize(300,120);
		setLocationRelativeTo(null); // 화면중앙에 게임이 뜨게
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		
		JPanel top= new JPanel();
		JLabel label = new JLabel("여기에 아이디를 입력하세요");
		top.add(label);
		add(top, BorderLayout.NORTH);
		
		TextField tf = new TextField(20);
		JPanel jp = new JPanel();
		JButton jb = new JButton("시작하기");

		
		jp.add(tf);
		jp.add(jb);
		
		int a = 0;
		add(jp);


		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (tf.getText() == null) {
					return;
				} else {
					String id = tf.getText();
					Beat b = new Beat();
					b.setId(id);
					b.setPoint(a);
					dispose();
				}
			}
		});
	}
}

public class Beat extends JFrame { // GUI 프로그램 만들기위해 JFrame 상속

	private Image screenImage; // 더블버퍼링-전체화면 이미지 담는 인스턴스 생성
	private Graphics screenGraphic;

	private ImageIcon exitButton1Image = new ImageIcon(Main.class.getResource("../images/exitButton1.png"));
	private ImageIcon exitButton2Image = new ImageIcon(Main.class.getResource("../images/exitButton2.png"));
	private ImageIcon startButton1Image = new ImageIcon(Main.class.getResource("../images/startButton1.png"));//
	private ImageIcon startButton2Image = new ImageIcon(Main.class.getResource("../images/startButton2.png"));//
	private ImageIcon scoreButton1Image = new ImageIcon(Main.class.getResource("../images/scoreButton1.png"));//
	private ImageIcon scoreButton2Image = new ImageIcon(Main.class.getResource("../images/scoreButton2.png"));//
	private ImageIcon quitButton1Image = new ImageIcon(Main.class.getResource("../images/quitButton1.png"));//
	private ImageIcon quitButton2Image = new ImageIcon(Main.class.getResource("../images/quitButton2.png"));//
	private static ImageIcon backButton1Image = new ImageIcon(Main.class.getResource("../images/backButton1.png"));
	private ImageIcon backButton2Image = new ImageIcon(Main.class.getResource("../images/backButton2.png"));
	private static ImageIcon backMainButton1Image = new ImageIcon(Main.class.getResource("../images/backMainButton1.png"));
	private ImageIcon backMainButton2Image = new ImageIcon(Main.class.getResource("../images/backMainButton2.png"));
	private static ImageIcon leftButton1Image = new ImageIcon(Main.class.getResource("../images/leftButton1.png"));
	private ImageIcon leftButton2Image = new ImageIcon(Main.class.getResource("../images/leftButton2.png"));
	private static ImageIcon rightButton1Image = new ImageIcon(Main.class.getResource("../images/rightButton1.png"));
	private ImageIcon rightButton2Image = new ImageIcon(Main.class.getResource("../images/rightButton2.png"));
	private static ImageIcon playButton1Image = new ImageIcon(Main.class.getResource("../images/playButton1.png"));
	private ImageIcon playButton2Image = new ImageIcon(Main.class.getResource("../images/playButton2.png"));

	private static Image background = new ImageIcon(Main.class.getResource("../images/introBackground.gif")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

	private JButton exitButton = new JButton(exitButton1Image); // 버튼 변수생성
	private JButton startButton = new JButton(startButton1Image);
	private JButton scoreButton = new JButton(scoreButton1Image);
	private JButton quitButton = new JButton(quitButton1Image);
	private static JButton backButton = new JButton(backButton1Image);
	private static JButton backMainButton = new JButton(backMainButton1Image);
	private static JButton leftButton = new JButton(leftButton1Image);
	private static JButton rightButton = new JButton(rightButton1Image);
	private static JButton playButton = new JButton(playButton1Image);

	private int mouseX, mouseY;

	private static boolean isMainScreen = false; // 기본 false 게임시작 이동시 true값
	private static boolean isGameScreen = false; // 게임화면으로 넘어왔는지에 대한 변수

	static ArrayList<Track> trackList = new ArrayList<Track>();
	ArrayList<DataBean> scoreman = null;
	
	private static Music selectedMusic;
	private static Image titleImage;
	private static Image selectedImage;
	private Music introMusic = new Music("introMusic.mp3", true); // 곡명 : DRIVE - Nicolai Heidlas Music (2)
	private static int nowSelected = 0; // 현재선택이된 트랙 번호 인덱스 0 시작
	private String id;
	private int point;
	public static Game game;

	public void setId(String id) {
		this.id = id;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public Beat() {

		trackList.add(new Track("HookSounds Title Image.png", "HookSounds Start Image.png", // 0번인덱스
				"HookSounds Game Image.png", "Electronic Vibes - HookSounds.mp3", "Electronic Vibes - HookSounds.mp3",
				"Electronic Vibes - HookSounds"));
		trackList.add(new Track("Copyright Title Image.png", "Copyright Start Image.png", // 1
				"Copyright Game Image.png", "Scooter - Copyright.mp3", "Scooter - Copyright.mp3",
				"Scooter - Copyright"));
		trackList.add(new Track("FortyThr33 Title Image.png", "FortyThr33 Start Image.png", // 2
				"FortyThr33 Game Image.png", "Bay Breeze - FortyThr33.mp3", "Bay Breeze - FortyThr33.mp3",
				"Bay Breeze - FortyThr33"));

		setUndecorated(true); // 기본메뉴바 제거
		setTitle("Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false); // 사용자가 인위적으로 게임창 크기조정불가
		setLocationRelativeTo(null); // 화면중앙에 게임이 뜨게
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0)); // paintComponents 시 화면색조정
		setLayout(null);

		addKeyListener(new KeyListener());

		introMusic.start();

		// exitButton
		exitButton.setBounds(1250, 0, 30, 30);
		exitButton.setBorderPainted(false); // 외곽선 제거
		exitButton.setContentAreaFilled(false); // 내용영역 채우기 x
		exitButton.setFocusPainted(false); // 선택시 생기는 테두리사용 x
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { // 마우스 x에 올리면 1->2
				exitButton.setIcon(exitButton2Image);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) { // 마우스 x에서 치우면 2->1
				exitButton.setIcon(exitButton1Image);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) { // 마우스 x 누르면 종료
				System.exit(0);
			}
		});
		add(exitButton);

		// startButton
		startButton.setBounds(40, 200, 400, 100);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButton2Image);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButton1Image);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// 게임 시작 이벤트
				enterMain();
				
			}
		});
		add(startButton);

		// scoreButton
		scoreButton.setBounds(40, 330, 400, 100);
		scoreButton.setBorderPainted(false);
		scoreButton.setContentAreaFilled(false);
		scoreButton.setFocusPainted(false);
		scoreButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				scoreButton.setIcon(scoreButton2Image);
				scoreButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				scoreButton.setIcon(scoreButton1Image);
				scoreButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// 스코어 보기 이벤트

				DB db = new DB();
				scoreman = db.callscore();
				JFrame jf = new JFrame();
				jf.setLayout(new FlowLayout(FlowLayout.CENTER));
				JLabel label = new JLabel("높은 순위대로 표시");
				JPanel panel = new JPanel();
				panel.add(label);
				for(int i=0; i < scoreman.size(); i++) {
							
				JLabel g = new JLabel(i+1 +"등  닉네임 : "+scoreman.get(i).getId()
						+ "    점수  :  " + scoreman.get(i).getPoint());
				panel.add(g);
				}
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

				jf.add(panel);
				jf.pack();	
				jf.setTitle("점수보기");
				jf.setLocationRelativeTo(null);
				jf.setVisible(true);
				

				
			}
		});
		add(scoreButton);
		// quitButton
		quitButton.setBounds(40, 460, 400, 100);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButton2Image);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButton1Image);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);

			}
		});
		add(quitButton);
		// backButton
		backButton.setVisible(false);
		backButton.setBounds(850, 600, 400, 100);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButton2Image);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButton1Image);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// 뒤로가기 이벤트
				backIntro();

			}
		});
		add(backButton);
		// backMainButton
		backMainButton.setVisible(false);
		backMainButton.setBounds(850, 600, 400, 100);
		backMainButton.setBorderPainted(false);
		backMainButton.setContentAreaFilled(false);
		backMainButton.setFocusPainted(false);
		backMainButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backMainButton.setIcon(backMainButton2Image);
				backMainButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backMainButton.setIcon(backMainButton1Image);
				backMainButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// 뒤로가기 이벤트
				backMain();
			}
		});
		add(backMainButton);
		// leftButton
		leftButton.setVisible(false); // 처음에 보이기x
		leftButton.setBounds(140, 310, 60, 60);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButton2Image);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButton1Image);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// 왼쪽버튼 이벤트
				selectLeft();

			}
		});
		add(leftButton);
		// rightButton
		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButton2Image);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButton1Image);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// 오른쪽버튼 이벤트
				selectRight();
			}
		});
		add(rightButton);
		// playButton
		playButton.setVisible(false);
		playButton.setBounds(510, 560, 250, 70);
		playButton.setBorderPainted(false);
		playButton.setContentAreaFilled(false);
		playButton.setFocusPainted(false);
		playButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				playButton.setIcon(playButton2Image);
				playButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				playButton.setIcon(playButton1Image);
				playButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// 플레이버튼 이벤트
				DB db = new DB();
				db.insert(id, point);
				gameStart(nowSelected, "Play");

			}
		});
		add(playButton);

		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() { // 메뉴바 드래그 이벤트 발생시 스크린x,y좌표 가져와
			@Override // 자동으로 게임위치 바꾼다
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);

	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D) screenGraphic); // Graphics2D글자깨짐 현상방지
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null); // g.drawImage 단순이미지 출력
		if (isMainScreen) {
			g.drawImage(selectedImage, 340, 100, null);
			g.drawImage(titleImage, 340, 70, null);
		}
		if (isGameScreen) {
			game.screenDraw(g);
		}
		paintComponents(g); // JFrame 에 추가된 요소 add() 이런것
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.repaint(); // JFrame 상속 GUI 첫번째 화면 그리기
	}

	public static void selectTrack(int nowSelected) {
		if (selectedMusic != null)
			selectedMusic.closeMusic();
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage()))
				.getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage()))
				.getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic.start();
	}

	public void selectLeft() {
		if (nowSelected == 0)
			nowSelected = trackList.size() - 1;
		else
			nowSelected--;
		selectTrack(nowSelected);

	}

	public void selectRight() {
		if (nowSelected == trackList.size() - 1)
			nowSelected = 0;
		else
			nowSelected++;
		selectTrack(nowSelected);

	}

	public void gameStart(int nowSelected, String play) {
		if (selectedMusic != null)
			selectedMusic.closeMusic();
		isMainScreen = false;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		backButton.setVisible(false);
		playButton.setVisible(false);
		backMainButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage()))
				.getImage();
		isGameScreen = true;
		game = new Game(trackList.get(nowSelected).getTitleName(), play, trackList.get(nowSelected).getGameMusic());
		game.start();
		setFocusable(true); // 키보드 포커스맞춰주기위해
		requestFocus(); // 음악선택안하고 바로 시작시 반응을 안해서 추가
	}

	public void backIntro() {

		backButton.setVisible(false);
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		playButton.setVisible(false);
		startButton.setVisible(true);
		scoreButton.setVisible(true);
		quitButton.setVisible(true);

		background = new ImageIcon(Main.class.getResource("../images/introBackground.gif")).getImage();
		isMainScreen = false;

	}

	public static void backMain() {

		selectTrack(nowSelected);
		backMainButton.setVisible(false);
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		backButton.setVisible(true);
		playButton.setVisible(true);

		background = new ImageIcon(Main.class.getResource("../images/mainBackground.gif")).getImage();
		isMainScreen = true;
		isGameScreen = false;
		game.close();
		
	}

	public void enterMain() {

		introMusic.closeMusic();
		selectTrack(nowSelected);
		startButton.setVisible(false); // 게임시작 드갈시 시작버튼 사라짐
		scoreButton.setVisible(false); // 점수버튼 사라짐
		quitButton.setVisible(false); // 종료버튼 사라짐
		playButton.setVisible(true); // 플레이 버튼 생성
		backButton.setVisible(true); // 뒤로가기 생성
		leftButton.setVisible(true); // 좌버튼 생성
		rightButton.setVisible(true); // 우버튼 생성

		background = new ImageIcon(Main.class.getResource("../images/mainBackground.gif")).getImage();
		isMainScreen = true; // 시작버튼 누를시 isMainScreen 등장
	}

}
