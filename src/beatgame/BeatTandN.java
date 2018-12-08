package beatgame;
//박자타이밍, 노트종류 데이터 
public class BeatTandN {
	
	private int time;
	private String noteName;
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getNoteName() {
		return noteName;
	}
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}
	public BeatTandN(int time, String noteName) {
		super();
		this.time = time;
		this.noteName = noteName;
	}
	
}
