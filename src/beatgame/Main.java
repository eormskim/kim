package beatgame;//2018-11-07시작 ->

public class Main {
	
	public static final int SCREEN_WIDTH = 1280;  //프로젝트 게임 해상도 설정
	public static final int SCREEN_HEIGHT = 720;
	public static final int NOTE_SPEED = 5; //노트 떨어지는 속도
	public static final int SLEEP_TIME = 5; //노트 떨어지는 시간 주기
	public static final int REACH_TIME = 1; // 노트생성되고 판정바까지 가는데 시간
	
	public static void main(String[] args) {
		
//		new Beat();
		new Login();
		
	}
}
//Calendar rightNow = Calendar.getInstance(); 날짜 시간 정보 java책 495p
