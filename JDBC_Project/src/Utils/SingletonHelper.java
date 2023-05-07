package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SingletonHelper {

	private static Connection conn = null;
	private SingletonHelper() {}
	
	//connection 함수
	public static Connection getConnection(String dsn) {
		if(conn == null) {
			try {
				if(dsn.equals("oracle")) {
					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","KOSA","1004");
				}else if(dsn.equals("mariadb")) {
					conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/kosadb","kosa","1004");
				}
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		return conn;
	}
	
	

	public static Connection getConnection(String dsn, String id, String pw) {
		if(conn == null) {
			try {
				if(dsn.equals("oracle")) {
					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",id,pw);
				}else if(dsn.equals("mariadb")) {
					conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/kosadb",id,pw);
				}
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return conn;
	}
	
	//close 함수들 오버로딩
	public static void close(ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static void close(Statement stsmt) {
		if(stsmt!=null) {
			try {
				stsmt.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static void close(PreparedStatement pstmt) {
		if(pstmt!=null) {
			try {
				pstmt.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static void dbClose() {
		if(conn!=null) {
			try {
				conn.close();
				conn = null;	//참조 해제
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	

	
}
