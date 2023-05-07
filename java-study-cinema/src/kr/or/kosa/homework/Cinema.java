package kr.or.kosa.homework;

public class Cinema {
	private int[][] cinemaSeat;	//좌석
	private String subject;		//영화제목
	private String startTime;	//상영 시작시간
	private String endTime;		//상영 끝 시간

	public Cinema(int row, int col , String subject, String startTime,String endTime) {
		this.cinemaSeat = new int[row][col];
		this.subject = subject;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public int[][] getCinemaSeat() {
		return cinemaSeat;
	}

	public void setCinemaSeat(int[][] cinemaSeat) {
		this.cinemaSeat = cinemaSeat;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
