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
		setLocationRelativeTo(null); // ȭ���߾ӿ� ������ �߰�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		
		JPanel top= new JPanel();
		JLabel label = new JLabel("���⿡ ���̵� �Է��ϼ���");
		top.add(label);
		add(top, BorderLayout.NORTH);
		
		TextField tf = new TextField(20);
		JPanel jp = new JPanel();
		JButton jb = new JButton("�����ϱ�");

		
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

public class Beat extends JFrame { // GUI ���α׷� ��������� JFrame ���

	private Image screenImage; // ������۸�-��üȭ�� �̹��� ��� �ν��Ͻ� ����
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

	private JButton exitButton = new JButton(exitButton1Image); // ��ư ��������
	private JButton startButton = new JButton(startButton1Image);
	private JButton scoreButton = new JButton(scoreButton1Image);
	private JButton quitButton = new JButton(quitButton1Image);
	private static JButton backButton = new JButton(backButton1Image);
	private static JButton backMainButton = new JButton(backMainButton1Image);
	private static JButton leftButton = new JButton(leftButton1Image);
	private static JButton rightButton = new JButton(rightButton1Image);
	private static JButton playButton = new JButton(playButton1Image);

	private int mouseX, mouseY;

	private static boolean isMainScreen = false; // �⺻ false ���ӽ��� �̵��� true��
	private static boolean isGameScreen = false; // ����ȭ������ �Ѿ�Դ����� ���� ����

	static ArrayList<Track> trackList = new ArrayList<Track>();
	ArrayList<DataBean> scoreman = null;
	
	private static Music selectedMusic;
	private static Image titleImage;
	private static Image selectedImage;
	private Music introMusic = new Music("introMusic.mp3", true); // ��� : DRIVE - Nicolai Heidlas Music (2)
	private static int nowSelected = 0; // ���缱���̵� Ʈ�� ��ȣ �ε��� 0 ����
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

		trackList.add(new Track("HookSounds Title Image.png", "HookSounds Start Image.png", // 0���ε���
				"HookSounds Game Image.png", "Electronic Vibes - HookSounds.mp3", "Electronic Vibes - HookSounds.mp3",
				"Electronic Vibes - HookSounds"));
		trackList.add(new Track("Copyright Title Image.png", "Copyright Start Image.png", // 1
				"Copyright Game Image.png", "Scooter - Copyright.mp3", "Scooter - Copyright.mp3",
				"Scooter - Copyright"));
		trackList.add(new Track("FortyThr33 Title Image.png", "FortyThr33 Start Image.png", // 2
				"FortyThr33 Game Image.png", "Bay Breeze - FortyThr33.mp3", "Bay Breeze - FortyThr33.mp3",
				"Bay Breeze - FortyThr33"));

		setUndecorated(true); // �⺻�޴��� ����
		setTitle("Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false); // ����ڰ� ���������� ����â ũ�������Ұ�
		setLocationRelativeTo(null); // ȭ���߾ӿ� ������ �߰�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0)); // paintComponents �� ȭ�������
		setLayout(null);

		addKeyListener(new KeyListener());

		introMusic.start();

		// exitButton
		exitButton.setBounds(1250, 0, 30, 30);
		exitButton.setBorderPainted(false); // �ܰ��� ����
		exitButton.setContentAreaFilled(false); // ���뿵�� ä��� x
		exitButton.setFocusPainted(false); // ���ý� ����� �׵θ���� x
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { // ���콺 x�� �ø��� 1->2
				exitButton.setIcon(exitButton2Image);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) { // ���콺 x���� ġ��� 2->1
				exitButton.setIcon(exitButton1Image);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) { // ���콺 x ������ ����
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
				// ���� ���� �̺�Ʈ
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
				// ���ھ� ���� �̺�Ʈ

				DB db = new DB();
				scoreman = db.callscore();
				JFrame jf = new JFrame();
				jf.setLayout(new FlowLayout(FlowLayout.CENTER));
				JLabel label = new JLabel("���� ������� ǥ��");
				JPanel panel = new JPanel();
				panel.add(label);
				for(int i=0; i < scoreman.size(); i++) {
							
				JLabel g = new JLabel(i+1 +"��  �г��� : "+scoreman.get(i).getId()
						+ "    ����  :  " + scoreman.get(i).getPoint());
				panel.add(g);
				}
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

				jf.add(panel);
				jf.pack();	
				jf.setTitle("��������");
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
				// �ڷΰ��� �̺�Ʈ
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
				// �ڷΰ��� �̺�Ʈ
				backMain();
			}
		});
		add(backMainButton);
		// leftButton
		leftButton.setVisible(false); // ó���� ���̱�x
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
				// ���ʹ�ư �̺�Ʈ
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
				// �����ʹ�ư �̺�Ʈ
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
				// �÷��̹�ư �̺�Ʈ
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
		menuBar.addMouseMotionListener(new MouseMotionAdapter() { // �޴��� �巡�� �̺�Ʈ �߻��� ��ũ��x,y��ǥ ������
			@Override // �ڵ����� ������ġ �ٲ۴�
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
		screenDraw((Graphics2D) screenGraphic); // Graphics2D���ڱ��� �������
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null); // g.drawImage �ܼ��̹��� ���
		if (isMainScreen) {
			g.drawImage(selectedImage, 340, 100, null);
			g.drawImage(titleImage, 340, 70, null);
		}
		if (isGameScreen) {
			game.screenDraw(g);
		}
		paintComponents(g); // JFrame �� �߰��� ��� add() �̷���
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.repaint(); // JFrame ��� GUI ù��° ȭ�� �׸���
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
		setFocusable(true); // Ű���� ��Ŀ�������ֱ�����
		requestFocus(); // ���Ǽ��þ��ϰ� �ٷ� ���۽� ������ ���ؼ� �߰�
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
		startButton.setVisible(false); // ���ӽ��� �尥�� ���۹�ư �����
		scoreButton.setVisible(false); // ������ư �����
		quitButton.setVisible(false); // �����ư �����
		playButton.setVisible(true); // �÷��� ��ư ����
		backButton.setVisible(true); // �ڷΰ��� ����
		leftButton.setVisible(true); // �¹�ư ����
		rightButton.setVisible(true); // ���ư ����

		background = new ImageIcon(Main.class.getResource("../images/mainBackground.gif")).getImage();
		isMainScreen = true; // ���۹�ư ������ isMainScreen ����
	}

}
