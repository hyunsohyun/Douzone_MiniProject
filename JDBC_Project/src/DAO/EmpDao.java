package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.Emp;
import Utils.Input;
import Utils.SingletonHelper;

public class EmpDao {

	// 모든 사원테이블 조회
	public List<Emp> getEmpAllList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Emp> empAllList = new ArrayList<>();

		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "select empno, ename, to_date(birthday)as birthday, hiredate, job, superior, dno from emp10";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Emp emp = new Emp();
				emp.setEmpno(rs.getString("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setBirthday(rs.getString("birthday"));
				emp.setHiredate(rs.getString("hiredate"));
				emp.setJob(rs.getString("job"));
				emp.setSuperior(rs.getString("superior"));
				emp.setDno(rs.getString("dno"));
				empAllList.add(emp);
			}

		} catch (Exception e) {
			System.out.println("사원 조회중 오류가 발생하였습니다."); 
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}

		return empAllList;
	}

	// 조건조회 (사원번호로 사원정보 찾기)
	public Emp selectEmp(String empno) {
		Emp searchDept = new Emp();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "select empno, ename, birthday, hiredate, job, superior, dno from emp10 where empno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empno);
		
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				searchDept.setEmpno(rs.getString("empno"));
				searchDept.setEname(rs.getString("ename"));
				searchDept.setBirthday(rs.getString("birthday"));
				searchDept.setHiredate(rs.getString("hiredate"));
				searchDept.setJob(rs.getString("job"));
				searchDept.setSuperior(rs.getString("superior"));
				searchDept.setDno(rs.getString("dno"));
			}
			
		} catch (Exception e) {
			System.out.println("사원정보 조회중 오류가 발생하였습니다.");
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}
		
		return searchDept;
	}

	// 사원등록(회원가입)
	public int insertEmp() {
		
		System.out.println("등록하실 사원의 정보를 입력해주세요.");
		System.out.print("사원번호 : ");
		String empno = Input.String();
		System.out.print("이름 : ");
		String ename = Input.String();
		System.out.print("비밀번호 : ");
		String password = Input.String();
		System.out.print("생일 : ");
		String birthday = Input.String();
		System.out.print("직종 : ");
		String job = Input.String();		
		System.out.print("상사 사원번호 :");
		String superior = Input.String();
		System.out.print("부서번호 :");
		String dno = Input.String();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;

		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "insert into emp10(empno, password, ename, birthday, hiredate, job, superior, dno) values (?,?,?,?,sysdate,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, empno);
			pstmt.setString(2, password);
			pstmt.setString(3, ename);
			pstmt.setString(4, birthday);
			pstmt.setString(5, job);
			pstmt.setString(6, superior);
			pstmt.setString(7, dno);

			row = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("사원 등록중 오류가 발생하였습니다.");
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}
		return row;

	}

	// 사원 수정
	public int updateEmp() {
		
		System.out.println("수정하실 사원의 정보를 입력해주세요.");
		System.out.print("사원번호 : ");
		String empno = Input.String();
		System.out.print("이름 : ");
		String ename = Input.String();
		System.out.print("비밀번호 : ");
		String password = Input.String();
		System.out.print("생일 : ");
		String birthday = Input.String();
		System.out.print("직종 : ");
		String job = Input.String();		
		System.out.print("상사 사원번호 :");
		String superior = Input.String();
		System.out.print("부서번호 :");
		String dno = Input.String();
		
		int row = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "update emp10 set ename = ?, birthday =?, job=?, superior=?, dno =? where empno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ename);
			pstmt.setString(2, birthday);
			pstmt.setString(3, job);
			pstmt.setString(4, superior);
			pstmt.setString(5, dno);
			pstmt.setString(6, empno);

			row = pstmt.executeUpdate();
			if(row>0) {
				System.out.println(empno+"사원의 정보가 정상적으로 수정되었습니다.");
			}

		} catch (Exception e) {
			System.out.println("사원정보 수정중 오류가 발생하였습니다.");
		} finally {
			SingletonHelper.close(pstmt);
			SingletonHelper.close(rs);
		}

		return row;
	}

	// 사원 삭제
	public int deleteEmp() {
		
		System.out.println("삭제할 사원의 사원번호를 입력해주세요.");
		String empno = Input.String();
		
		int row = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "delete from emp10 where empno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empno);

			row = pstmt.executeUpdate();
			if(row>0) {
				System.out.println(empno + "사원의 정보가 삭제되었습니다.");
			}
			
		} catch (Exception e) {
			System.out.println("사원삭제중 오류가 발생하였습니다.");
			
		} finally {
			SingletonHelper.close(pstmt);
			SingletonHelper.close(rs);
		}

		return row;
	}

}
