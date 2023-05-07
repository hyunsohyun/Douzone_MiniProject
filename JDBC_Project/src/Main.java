import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import DAO.AttendanceDao;
import DAO.DeptDao;
import DAO.EmpDao;
import DAO.VacationDao;
import DTO.Attendance;
import DTO.Dept;
import DTO.Emp;
import DTO.Vacation;
import Utils.Input;

public class Main {

	public static void main(String[] args) {
		mainmenu();
	}

	//depth 1
	public static void mainmenu() {
		while(true) {
			System.out.println("**회사 근태관리 시스템입니다.");
			System.out.println("메뉴를 선택해주세요.");
			System.out.println("1.일반사원모드 2.관리자모드 0.시스템종료");
			switch (Input.Int()) {
				case 1 -> {
					empmenu();
				}
				case 2 -> { 
					adminmenu();
				}
				case 0 -> {
					System.exit(0);
				}
				default -> { 
					System.out.println("없는 메뉴번호입니다. 다시 선택해주세요.");
				}
			}
		}
	}
	
	//depth 2
	public static void empmenu() {
		AttendanceDao attendanceDao = new AttendanceDao();
		outer : while(true) {
			System.out.println("일반사원메뉴");
			System.out.println("메뉴를 선택해주세요.");
			System.out.println("1.출퇴근 기록하기 2.휴가관리 9.메인메뉴로 돌아가기 0.시스템종료");
			switch (Input.Int()) {
				case 1 -> {//출퇴근 기록하기
						attendanceDao.insertAttendance();
				}
				case 2 -> {//휴가관리
					empVacationMenu(); 
				}
				case 9 -> { 
					mainmenu(); break outer;
				}
				case 0 -> { 
					System.exit(0);
				}
				default -> {
					System.out.println("없는 메뉴번호입니다. 다시 선택해주세요.");
				}
			}
		}
		
	}
	
	public static void adminmenu() {
		outer: while(true) {
			System.out.println("관리자메뉴");
			System.out.println("메뉴를 선택해주세요.");
			System.out.println("1.부서관리 2.사원관리 3.휴가관리 4.근태관리 9.메인메뉴로 돌아가기 0.시스템종료");		
			switch (Input.Int()) {
				case 1 -> {
					deptManangeMenu();//부서관리
				}
				case 2 -> {
					empManangeMenu();//사원관리
				}
				case 3 -> {
					vacationManageMenu();//휴가관리
				}
				case 4 -> {
					attendanceManangeMenu();//근태관리
				}
				case 9 -> {
					break outer;
				}
				case 0 -> {
					System.exit(0);
				}
				default -> {
					System.out.println("없는 메뉴번호입니다. 다시 선택해주세요.");
				}			
			}
		}
		
	}
	
	//depth 3-일반사원_휴가관리 메뉴
	public static void empVacationMenu() {
		VacationDao vacationdao = new VacationDao();
		outer: while(true) {
			System.out.println("휴가관리메뉴");
			System.out.println("메뉴를 선택해주세요.");
			System.out.println("1.휴가등록 2.나의 휴가목록 조회 9.일반사원메뉴로 돌아가기 0.시스템종료");		
			switch (Input.Int()) {
				case 1 -> {
					vacationdao.insertVacation();//휴가등록
				}
				case 2 -> {
					printVacationList(vacationdao.selectVacationListByEmp());//나의 휴가목록 조회
				}
				case 9 -> {
					break outer;//일반사원메뉴로 돌아가기
				}
				case 0 -> {
					System.exit(0);
				}
				default -> {
					System.out.println("없는 메뉴번호입니다. 다시 선택해주세요.");
					empVacationMenu();
				}
			}
		}
		
	}
	
	//depth 3-관리자_부서관리 메뉴
	public static void deptManangeMenu() {
		DeptDao deptdao = new DeptDao();
		outer: while(true) {
			System.out.println("부서관리메뉴");
			System.out.println("메뉴를 선택해주세요.");
			System.out.println("1.전체부서 리스트 2.부서 등록 3.부서 수정 4.부서 삭제 9.관리자메뉴로 돌아가기 0.시스템종료");
			switch (Input.Int()) {
				case 1 -> {
					printDeptList(deptdao.getDeptAllList()); deptManangeMenu();//전체부서 리스트
				}
				case 2 -> {
					deptdao.insertDept();//부서등록
				}
				case 3 -> {
					deptdao.updateDept();//부서수정
				}
				case 4 -> {
					deptdao.deleteDept();//부서삭제
				}
				case 9 -> {
					break outer;//관리자메뉴로 돌아가기
				}
				case 0 -> {
					System.exit(0);
				}
				default -> {
					System.out.println("없는 메뉴번호입니다. 다시 선택해주세요.");
				}
			}
		}
	}
	
	//dept 3-관리자_사원관리 메뉴
	public static void empManangeMenu() {
		EmpDao empDao = new EmpDao();
		outer: while(true) {
			System.out.println("시원관리메뉴");
			System.out.println("메뉴를 선택해주세요.");
			System.out.println("1.전체사원 리스트 2.사원 등록 3.사원정보 수정 4.사원 삭제 9.관리자메뉴로 돌아가기 0.시스템종료 ");
			switch (Input.Int()) {
				case 1 -> {
					printEmpList(empDao.getEmpAllList()); //전체부서 리스트
				}
				case 2 -> {
					empDao.insertEmp();//사원등록
				}
				case 3 -> {
					empDao.updateEmp();//사원수정
				}
				case 4 -> {
					empDao.deleteEmp();//사원삭제
				}
				case 9 -> {
					break outer;//관리자메뉴로 돌아가기
				}
				case 0 -> {
					System.exit(0);
				}
				default -> {
					System.out.println("없는 메뉴번호입니다. 다시 선택해주세요.");
				}
			}
		}
	}
	
	//dept 3-관리자_휴가관리 메뉴
	public static void vacationManageMenu() {
		VacationDao vacationDao = new VacationDao();

		outer: while(true) {
			System.out.println("휴가관리메뉴");
			System.out.println("메뉴를 선택해주세요.");
			System.out.println("1.전체휴가 리스트 2.휴가등록 3.해당날짜 휴가조회 4.휴가 삭제 9.관리자메뉴로 돌아가기 0.시스템종료");
	
			switch (Input.Int()) {
				case 1 -> {
					printVacationList((vacationDao.getVacationAllList()));//전체휴가 리스트
				}
				case 2 -> {
					vacationDao.insertVacation();//휴가등록
				}
				case 3 -> {
					printMapList(vacationDao.selectVacationList());//휴가수정
				}
				case 4 -> {
					vacationDao.deleteVaction();//휴가삭제
				}
				case 9 -> {
					 break outer;//관리자메뉴로 돌아가기
				}
				case 0 -> {
					System.exit(0);
				}
				default -> {
					System.out.println("없는 메뉴번호입니다. 다시 선택해주세요.");
				}
			}
		}
		
	}
	
	//dept 3-관리자_근태관리 메뉴
	public static void attendanceManangeMenu() {
		AttendanceDao attendanceDao = new AttendanceDao();
		
		outer: while(true) {
			System.out.println("근태관리메뉴");
			System.out.println("메뉴를 선택해주세요.");
			System.out.println("1.당일 모든 사원의 근태정보 2.당일 부서별 근태정보 9.관리자메뉴로 돌아가기 0.시스템종료");
			
			switch (Input.Int()) {
				case 1 -> {
					printAttendanceList(attendanceDao.selectAttendanceBydate());	//당일 모든 사원의 근태정보
				}
				case 2 -> {
					printMapList(attendanceDao.getAttendanceListByDept());//당일 부서별 근태정보
				}
				case 9 -> {
					break outer;//관리자메뉴로 돌아가기
				}
				case 0 -> {
					System.exit(0);
				}
				default -> {
					System.out.println("없는 메뉴번호입니다. 다시 선택해주세요.");
				}
			}
		}

	}
	
	//print함수
	public static void printEmpList(List<Emp> list) {
		for(Emp e:list) {
			System.out.println(e.toString());
		}
	}
	
	public static void printDeptList(List<Dept> list) {
		for(Dept d:list) {
			System.out.println(d.toString());
		}
	}
	
	public static void printVacationList(List<Vacation> list) {
		for(Vacation v:list) {
			System.out.println(v.toString());
		}
	}
	
	public static void printAttendanceList(List<Attendance> list) {
		for(Attendance a:list) {
			System.out.println(a.toString());
		}
	}

	public static void printMapList(List<Map<String, String>> list) {
		for(Map<String, String> m : list) {
			 for (Entry<String, String> entrySet : m.entrySet()) {
		            System.out.print(entrySet.getValue() + " ");
		       }
			 System.out.println();
		}
	}
}
