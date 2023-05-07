package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DTO.Attendance;
import Utils.Input;
import Utils.SingletonHelper;

public class AttendanceDao {
	
	//출퇴근 찍기
	public int insertAttendance(){
		System.out.println("본인의 사번을 입력해주세요.");
		String empno = Input.String();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		String msg = "";

		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "";
			
			int chkAttendanceCnt = chkAttendanceCnt(empno);
			if(chkAttendanceCnt > 0){ // 퇴근
				msg = empno + "님 퇴근완료";
				int ano = selectAno(empno);
				sql = "update attendance10 set endTime = sysdate where adate = TO_CHAR(SYSDATE, 'YYYY-MM-DD') and empno = ? and ano = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, empno);
				pstmt.setInt(2, ano);
			}else{	// 출근
				msg =  empno + "님 출근완료";
				sql = "insert into attendance10(ano, adate, empno, startTime) values (attendance_seq.NEXTVAL, TO_CHAR(SYSDATE, 'YYYY-MM-DD'), ?, sysdate)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, empno);
			}
			
			row = pstmt.executeUpdate();
		
			if(row > 0) {
				System.out.println(msg);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("출퇴근기록중 오류가 발생하였습니다.");
			
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}
		
		return row;
	}
	
	//오늘 출근여부
	private int chkAttendanceCnt(String empno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int chkAttendanceCnt = 0;
		
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "select count(*) as chkAttendance from attendance10 where empno = ? and adate = TO_CHAR(SYSDATE, 'YYYY-MM-DD')and ENDTIME IS null";
			pstmt = conn.prepareStatement(sql);
	
			pstmt.setString(1, empno);
			
			rs = pstmt.executeQuery();
			if(rs.next()) chkAttendanceCnt = rs.getInt("chkAttendance");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("출근조회중 오류가 발생하였습니다.");
	
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}
		
		return chkAttendanceCnt;
	} 
	
	//순번 조회하기
	private int selectAno(String empno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int ano = 0;
		
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "select ano from attendance10 where empno = ? and adate = TO_CHAR(SYSDATE, 'YYYY-MM-DD') and ENDTIME IS null";
			pstmt = conn.prepareStatement(sql);
	
			pstmt.setString(1, empno);
			
			rs = pstmt.executeQuery();
			if(rs.next()) ano = rs.getInt("ano");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("근태순번 조회중 오류가 발생하였습니다.");
	
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}
		
		return ano;
	}
	
	//오늘날짜 모든 사원의 근태정보 보기 
	public List<Attendance> selectAttendanceBydate() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<Attendance> attendanceList = new ArrayList<>();
		
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "select empno, ano, adate, starttime, endtime from attendance10 where adate = to_char(sysdate,'yyyy-mm-dd')";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Attendance attendance = new Attendance();
				attendance.setEmpno(rs.getString("empno"));
				attendance.setStartTime(rs.getString("starttime"));
				attendance.setEndTime(rs.getString("endtime"));
				attendance.setAno(rs.getString("ano"));
				attendance.setAdate(rs.getString("adate"));
				attendanceList.add(attendance);
			}

		} catch (Exception e) {
			System.out.println("근태정보 보기중 오류가 발생하였습니다.");
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}

		return attendanceList;
	}
	
	// 부서별 근태 현황 조회
	public List<Map<String, String>> getAttendanceListByDept() {
		
		System.out.println("조회할 부서번호를 입력하세요.");
		String dno = Input.String();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Map<String, String>> attendanceListByDept = new ArrayList<>();
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "SELECT empno , starttime , endtime ";
				sql += "FROM ATTENDANCE10 ";
				sql += "WHERE EMPNO  in(SELECT e.empno FROM EMP10 e, DEPT10 d  WHERE e.dno = ? and e.DNO = d.DNO) ";
				sql += "AND adate = to_char(SYSDATE ,'yyyy-mm-dd')";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Map<String, String> map = new HashMap<>();
				map.put("empno", rs.getString("empno"));
				map.put("starttime", rs.getString("starttime"));
				map.put("endtime", rs.getString("endtime"));
				attendanceListByDept.add(map);
			}

		} catch (Exception e) {
			System.out.println("부서별 근태현황 조회중 오류가 발생하였습니다.");
			e.printStackTrace();
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}

		return attendanceListByDept;
	}
}
