import java.util.List;
import java.util.Scanner;

import DAO.AttendanceDao;
import DAO.DeptDao;
import DAO.EmpDao;
import DAO.VacationDao;
import DTO.Attendance;
import DTO.Dept;
import DTO.Emp;
import DTO.Vacation;

public class Main2 {
	
	public static void main(String[] args) {
		EmpDao empdao = new EmpDao();	//사원 DAO
		VacationDao vacationDao = new VacationDao();	//휴가 DAO
		AttendanceDao attendanceDao = new AttendanceDao();	//출퇴근 DAO
		DeptDao deptDao = new DeptDao();	//부서 DAO
		
		/* 부서 CRUD */
		//부서 리스트
//		List<Dept> deptAllList = deptDao.getDeptAllList();
//		for(Dept dept : deptAllList) {
//			System.out.println(dept.toString());
//		}
		
		// 부서번호로 부서 찾기
//		Dept dept = deptDao.selectDept("d1");
//		System.out.println(dept.toString());
//
//		// 부서 등록
//		Dept dept = new Dept();
//		dept.setDname("개발3팀");
//		dept.setDno("d5");
//		deptDao.insertDept(dept);
		
		// 부서수정
//		Dept dept = new Dept();
//		dept.setDname("개발5팀");
//		dept.setDno("d5");
//		deptDao.updateDept(dept);
		
		// 부서삭제
//		deptDao.deleteDept("d5");
		
		
		/* 사원CRUD */
		//1. 사원 넣기(회원가입)
//		Emp insertemp = new Emp();
//		insertemp.setEmpno("sjd");
//		insertemp.setPassword("1111");
//		insertemp.setEname("서장대");
//		insertemp.setBirthday("1997-03-25");
//		insertemp.setJob("사원");
//		insertemp.setSuperior(null);
//		insertemp.setDno("d2");
//		empdao.insertEmp(insertemp);
		
//		2. 사원 전체 조회
//		List<Emp> empAllList = empdao.getEmpAllList();
//		for(Emp emp : empAllList) {
//			System.out.println(emp.toString());
//		}
		
		//3. 사원번호로 사원 조회(조건조회)
//		Emp emp = empdao.selectEmp("hsh");
//		System.out.println(emp.toString());
		
		//4. 사원 수정
//		Emp updateEmp = new Emp();
//		updateEmp.setEmpno("sjd");
//		updateEmp.setPassword("1111");
//		updateEmp.setEname("서장대");
//		updateEmp.setBirthday("1997-03-25");
//		updateEmp.setJob("사원");
//		updateEmp.setSuperior(null);
//		updateEmp.setDno("d1");
//		empdao.updateEmp(updateEmp);
//		
//		5. 사원 삭제
//		empdao.deleteEmp("sjd");
		
		/* 휴가 CRUD */
//		1. 사원 휴가 등록(입력)
//		Vacation vacation = new Vacation();
//		vacation.setEmpno("hsh");
//		vacation.setStartDate("2023-06-01");
//		vacation.setEndDate("2023-06-01");
//		vacation.setVtype("연차휴가");
//		vacationDao.insertVacation(vacation);

//		2. 휴가 전체 조회
//		List<Vacation> vacationAllList = vacationDao.getVacationAllList();
//		for(Vacation vacation : vacationAllList) {
//			System.out.println(vacation.toString());
//		}
		
//		3. 해당 날짜 전체 사원 휴가조회(조건조회)
//		vacationDao.selectVacationList("2023-05-03");
		
//		4. 휴가 수정
//		Scanner sc = new Scanner(System.in);
//		
//		System.out.println("사원번호를 입력하세요.");
//		String empno = sc.nextLine();
//		List<Vacation> vacationListByEmp = vacationDao.selectVno(empno);
//		
//		for(Vacation vacation : vacationListByEmp) {
//			System.out.println(vacation.toString());
//		}
//		
//		System.out.println("슈정할 휴가번호를 입력해주세요.");
//		Vacation updateVaction = new Vacation();
//		//sc.~
//		int vno = 3;
//		updateVaction.setVno(vno);
//		updateVaction.setStartDate("2023-06-05");
//		updateVaction.setEndDate("2023-06-05");
//		updateVaction.setVtype("연차휴가");
//		vacationDao.updateVaction(updateVaction);
		
//		5. 휴가 삭제
//		vacationDao.deleteVaction("4");
		
		/* 근태정보 CRUD */
		//1. 오늘날짜 모든 사원의 근태정보 보기(select)
//		 List<Attendance> attendanceList = attendanceDao.selectAttendanceBydate();
//		 for(Attendance attendance : attendanceList)  {
//			 System.out.println(attendance.toString());
//		 }
		
		//2. 사원 출퇴근 찍기(insert)
//		attendanceDao.insertAttendance("ndw");
		

		
	
	}

}
