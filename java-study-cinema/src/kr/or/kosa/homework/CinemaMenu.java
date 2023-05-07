package kr.or.kosa.homework;

import java.util.Scanner;
import java.util.regex.Pattern;

public class CinemaMenu {
	
	CinemaUtils cinemaUtils = new CinemaUtils();
	Scanner sc = new Scanner(System.in);

	// 메인함수
	public void main(Cinema theater) {
		
		this.showMenu();
		String menuNum = sc.next();
		
		switch (menuNum) {
		case "1":	//예매
			ticketingSeat(theater);	
			break;
		case "2": //예매 조회
			checkSeat(theater);
			break;
		case "3":	//예매 취소
			cancleSeat(theater);
		case "4":	//종료
			System.out.println("종료되었습니다.");
			System.exit(0);
			break;
		default:
			System.out.println("정확한 값을 입력하세요");
			this.main(theater);break;

		}
	}

	void showMenu() {
		System.out.println("********************");
		System.out.println("영화 예매 시스템");
		System.out.println("1. 예매");
		System.out.println("2. 예매조회");
		System.out.println("3. 예매취소");
		System.out.println("4. 종료");
		System.out.println("********************");
	}
	
	//1.예매
	void ticketingSeat(Cinema theater) {
		int[][] seat = theater.getCinemaSeat();
		try {
			cinemaUtils.printSeat(seat);										//좌석 보여주기
			int[] selectseat = cinemaUtils.selectSeat(seat);					//좌석 선택하기
			
			if(selectseat!=null) {
				if(cinemaUtils.checkReservationSeat(seat,selectseat)) {			//빈좌석일때만 예약
					cinemaUtils.printReservationDetail(theater);
					int reservationNum = cinemaUtils.createReservationNum(seat);	//예매번호 생성
					cinemaUtils.registerSeat(reservationNum,seat,selectseat);		//예매처리 함수
				}else {
					System.out.println("이미 예약된 좌석입니다. 다시 선택해주세요");
					ticketingSeat(theater);
				}
			}else {
				System.out.println("좌석번호를 다시 입력해주세요.");
				ticketingSeat(theater);
			}

		}catch (Exception e) {
			System.out.println("예매중 오류가 발생하였습니다.");
			System.out.println(e.getMessage());
			
		}finally {
			this.main(theater);
		}
	}
	
	//2.예매 조회
	void checkSeat(Cinema theater) {
		int[][] seat = theater.getCinemaSeat();
		try {
			System.out.println("조회하실 예매번호를 입력해주세요.");
			String reservationStr = sc.next();
			int reservationNum;
			if(Pattern.matches("^\\d{8}$",reservationStr)) {//8자리 숫자형식
				reservationNum = Integer.parseInt(reservationStr);
				cinemaUtils.chkSeat(reservationNum,seat);
				cinemaUtils.printReservationDetail(theater);
				cinemaUtils.printSeat(reservationNum,seat);
				
			}else {
				System.out.println("예약번호는 8자리 숫자형식입니다.");
				checkSeat(theater);
			}
			
		}catch (Exception e) {
			System.out.println("예매조회중 오류가 발생하였습니다.");
		}finally {
			this.main(theater);
		}
	}
	
	//3.예매취소
	void cancleSeat(Cinema theater) {
		int[][] seat = theater.getCinemaSeat();
		try {
			System.out.println("취소하실 예매번호를 입력해주세요.");
			String reservationStr = sc.next();
			int reservationNum;
			if(Pattern.matches("^\\d{8}$",reservationStr)) {//8자리 숫자형식
				reservationNum = Integer.parseInt(reservationStr);
				cinemaUtils.cancleSeat(reservationNum,seat);
			}else {
				System.out.println("예약번호는 8자리 숫자형식입니다.");
				checkSeat(theater);
			}
			
		}catch (Exception e) {
			System.out.println("예매취소중 오류가 발생하였습니다.");
		}finally {
			this.main(theater);
		}
	}
	
	
}