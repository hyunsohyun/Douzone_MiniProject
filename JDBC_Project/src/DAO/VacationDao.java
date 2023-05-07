package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DTO.Vacation;
import Utils.Input;
import Utils.SingletonHelper;

public class VacationDao {
	
	// 휴가등록
	public int insertVacation() {
		
		//입력받기
		System.out.println("휴가등록할 사원의 번호를 입력해주세요.");
		String empno = Input.String();
		System.out.println("휴가 시작날짜를 입력해주세요.");
		Date startdate = Input.Date();
		System.out.println("휴가 끝날짜를 입력해주세요.");
		Date enddate = Input.Date();
		System.out.println("휴가 타입을 입력해주세요");
		String vtype = Input.String();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;

		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "insert into vacation10(vno, empno, startdate, enddate, vtype) values (vacation_seq.NEXTVAL,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empno);
			pstmt.setDate(2, startdate);
			pstmt.setDate(3, enddate);
			pstmt.setString(4, vtype);
			
			row = pstmt.executeUpdate();
			if(row>0) {
				System.out.println(empno+"님 " +  vtype + "(" + startdate + " ~ " + enddate + ") 정상적으로 등록되었습니다.");
			}
			
		} catch (Exception e) {
			System.out.println("휴가 등록중 오류가 발생하였습니다.");
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}
		return row;
	}
	
	// 휴가 전체조회
	public List<Vacation> getVacationAllList() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Vacation> vactionAllList = new ArrayList<>();

		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "SELECT vno,empno, startdate, enddate, vtype FROM VACATION10 v ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Vacation vacation = new Vacation();
				vacation.setVno(rs.getInt("vno"));
				vacation.setEmpno(rs.getString("empno"));
				vacation.setStartDate(rs.getString("startdate"));
				vacation.setEndDate(rs.getString("endDate"));
				vacation.setVtype(rs.getString("vtype"));
				vactionAllList.add(vacation);
			}

		} catch (Exception e) {
			System.out.println("휴가조회중 오류가 발생하였습니다.");
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}

		return vactionAllList;
	}
	
	// 사원별 휴가정보 찾기(사원번호로)
	public List<Vacation> selectVacationListByEmp() {
		
		System.out.println("조회할 사원의 사원번호를 입력해주세요.");
		String empno = Input.String();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Vacation> vactionListByEmp = new ArrayList<>();

		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "SELECT vno, empno, startdate, enddate, vtype FROM VACATION10 v where empno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empno);
	
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Vacation vacation = new Vacation();
				vacation.setVno(rs.getInt("vno"));
				vacation.setEmpno(rs.getString("empno"));
				vacation.setStartDate(rs.getString("startdate"));
				vacation.setEndDate(rs.getString("endDate"));
				vacation.setVtype(rs.getString("vtype"));
				vactionListByEmp.add(vacation);
			}

		} catch (Exception e) {
			System.out.println("사용자별 휴가조회중 오류가 발생하였습니다.");
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}

		return vactionListByEmp;
	}
	
	// 휴가 수정
	public int updateVaction(Vacation vacation) {
		
		System.out.println("수정할 휴가번호를 입력해주세요.");
		int vno = Input.Int();
		System.out.println("수정할 휴가 시작날짜를 입력해주세요.");
		Date startdate = Input.Date();
		System.out.println("수정할 휴가 끝날짜를 입력해주세요.");
		Date enddate = Input.Date();
		System.out.println("수정할 휴가타입를 입력해주세요.");
		String vtype = Input.String();
		
		int row = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "update vacation10 set startdate = ?, enddate =?, vtype=? where vno=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setDate(1, startdate);
			pstmt.setDate(2, enddate);
			pstmt.setString(3, vtype);
			pstmt.setInt(4, vno);

			row = pstmt.executeUpdate();
			if(row>0) {
				System.out.println("휴가정보가 정상적으로 수정되었습니다.");
			}else {
				System.out.println("해당 순번의 휴가정보가 존재하지 않습니다.");
			}

		} catch (Exception e) {
			System.out.println("휴가정보 수정중 오류가 발생하였습니다.");
		} finally {
			SingletonHelper.close(pstmt);
			SingletonHelper.close(rs);
		}

		return row;
	}
		
	//휴가 삭제
	public int deleteVaction() {
		
		System.out.println("삭제할 휴가번호를 입력해주세요.");
		int vno = Input.Int();
		
		int row = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "delete from vacation10 where vno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vno);
			row = pstmt.executeUpdate();
			
			if(row>0) {
				System.out.println("순번" + vno + "번 의 휴가정보가 삭제되었습니다.");
			}else {
				System.out.println("해당 순번의 휴가정보가 존재하지 않습니다.");
			}
			
		} catch (Exception e) {
			System.out.println("휴가삭제중 오류가 발생하였습니다.");
			
		} finally {
			SingletonHelper.close(pstmt);
			SingletonHelper.close(rs);
		}

		return row;
	}
	
	
	// 해당날짜 휴가 조회 (기간으로 휴가중인 사원리스트 조회)
	public List<Map<String, String>> selectVacationList() {
		
		System.out.println("조회할 날짜를 입력해주세요.");
		Date searchDate = Input.Date();
		List<Map<String, String>> vacationList = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "SELECT e.EMPNO as empno, vno, startdate, enddate, vtype ,e.ENAME as ename ,d.dname as dname from vacation10 v, EMP10 e , DEPT10 d ";
				sql += "WHERE ";
				sql	+= "? BETWEEN v.startDate AND v.endDate ";
				sql	+= "AND e.EMPNO  = v.EMPNO ";
				sql	+= "AND e.DNO = d.DNO";
				
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, searchDate);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Map<String, String> vacation = new HashMap<>();
				vacation.put("empno", rs.getString("empno"));
				vacation.put("vno", rs.getString("vno"));
				vacation.put("startdate", rs.getString("startdate"));
				vacation.put("enddate", rs.getString("enddate"));
				vacation.put("vtype", rs.getString("vtype"));
				vacation.put("ename", rs.getString("ename"));
				vacation.put("dname", rs.getString("dname"));
				vacationList.add(vacation);
			}
			
		} catch (Exception e) {
			System.out.println("휴가조회중 오류가 발생하였습니다.");
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}
		
		return vacationList;
	}
}
