package Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;

public class Input {
	
	public Input() {};
	
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static int Int() {
		int value;
		try {
			value = Integer.parseInt(br.readLine());
		} catch (Exception e) {
			System.out.println("숫자만 입력하세요");
			value = Int();
		}		
		return value;
	}
	
	public static String String() {
		String answer;
		try {
			answer = br.readLine();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			answer = String();
		}		
		return answer;
	}
	
	public static Date Date() {
		Date date = null;
		try {
			date = Date.valueOf(String());
		} catch (Exception e) {
			System.out.println("올바른 형식으로 입력하세요. ex) yyyy-mm-dd");
			date = Date();
		} 
		return date;
	}
}
