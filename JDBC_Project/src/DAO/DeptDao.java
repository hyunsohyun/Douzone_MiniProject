package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.Dept;
import Utils.Input;
import Utils.SingletonHelper;

public class DeptDao {
	
	//전체리스트 조회
	public List<Dept> getDeptAllList(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Dept> deptAllList = new ArrayList<>();

		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "select dno,dname from dept10";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Dept dept = new Dept();
				dept.setDno(rs.getString("dno"));
				dept.setDname(rs.getString("dname"));
				deptAllList.add(dept);
			}

		} catch (Exception e) {
			System.out.println("모든 사원 조회중 오류가 발생하였습니다.");
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}

		return deptAllList;
	}
	
	
	// 조건조회 (부서번호로 부서정보 찾기)
	public Dept selectDept(String dno) {
		Dept searchDept = new Dept();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "select dno,dname from dept10 where dno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				searchDept.setDno(rs.getString("dno"));
				searchDept.setDname(rs.getString("dname"));
			}
			
		} catch (Exception e) {
			System.out.println("부서정보 조회중 오류가 발생하였습니다.");
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}
		
		return searchDept;
	}
	
	
	// 사원등록(회원가입)
	public int insertDept() {
		
		System.out.println("등록할 부서의 정보를 입력해주세요.");
		System.out.print("부서번호 : ");
		String dno = Input.String();
		System.out.print("부서이름 : ");
		String dname = Input.String();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;

		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "insert into dept10(dno, dname) values (?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dno);
			pstmt.setString(2, dname);

			row = pstmt.executeUpdate();
			if(row>0) {
				System.out.println("부서가 정상적으로 등록되었습니다.");
			}else {
				System.out.println("부서 등록에 실패하였습니다.");
			}
			
		} catch (Exception e) {
			System.out.println("사원 등록중 오류가 발생하였습니다.");
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}
		
		return row;
	}
	
	// 부서 수정
	public int updateDept() {
		
		System.out.println("수정할 부서의 정보를 입력해주세요.");
		System.out.print("부서번호 : ");
		String dno = Input.String();
		System.out.print("부서이름 : ");
		String dname = Input.String();
		
		int row = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "update dept10 set dname = ? where dno=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dname);
			pstmt.setString(2, dno);
			row = pstmt.executeUpdate();
			if(row>0) {
				System.out.println("부서정보가 정상적으로 수정되었습니다.");
			}else {
				System.out.println("해당 부서가 존재하지 않습니다.");
			}

		} catch (Exception e) {
			System.out.println("부서 수정중 오류가 발생하였습니다.");
		} finally {
			SingletonHelper.close(pstmt);
			SingletonHelper.close(rs);
		}

		return row;
	}

	// 부서 삭제
	public int deleteDept() {
		
		System.out.println("삭제할 부서번호를 입력해주세요.");
		System.out.print("부서번호 : ");
		String dno = Input.String();
		
		int row = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "delete from dept10 where dno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dno);

			row = pstmt.executeUpdate();
			if(row>0) {
				System.out.println("부서 정보가 삭제되었습니다.");
			}else {
				System.out.println("해당 부서가 존재하지 않습니다.");
			}
			
		} catch (Exception e) {
			System.out.println("부서 삭제중 오류가 발생하였습니다.");
			
		} finally {
			SingletonHelper.close(pstmt);
			SingletonHelper.close(rs);
		}

		return row;
	}
	
}
