package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.MemberController;
import com.kh.model.vo.Member;

//View : 사용자가 보게될 시각적인 요소(화면) 출력 및 입력
public class MemberMenu {
	//Scanner 객체 생성(전역으로 다 입력받을 수 있도록)
	private Scanner sc = new Scanner(System.in);
	
	//MemberController 객체 생성(클래스 전역에서 바로 요청할 수 있도록)
	private MemberController mc = new MemberController();
	
	/*
	 * 사용자가 보게 될 첫 화면(메인화면)
	 */
	public void mainMenu() {
		while(true) {
			System.out.println("========회원 관리 프로그램=========");
			System.out.println("1. 회원 추가");
			System.out.println("2. 최원 전체 조회");
			System.out.println("3. 회원 정보 변경");
			System.out.println("4. 회원 탈퇴");
//			System.out.println("3. 회원 아이디 검색");
//			System.out.println("4. 회원 이름으로 키워드 검색");
			System.out.println("0. 프로그램 종료");
			
			System.out.println("메뉴 입력 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1:
				this.inputMember();
				break;
			case 2:
				mc.selectList(); //회원정보 내놔 -> controller에 요청
				break;
			case 3:
				//userId
				//패스워드, 이메일, 전화번호, 주소
				
				this.updateMember();
				break;
			case 4:
				//userId
				mc.deleteMember(this.inputMemberId());
				break;
			case 5:
				break;
			case 6:
				break;
			case 0:
				System.out.println("이용해주셔서 감사합니다. 프로그램을 종료합니다.");	
				return;
			default:
				System.out.println("메뉴를 잘못입력하셨습니다. 다시 입력해주세요 : ");
				break;
			}//end switch
		}//end while
		
		
		
	}//end mainMenu
	
	/**
	 * 회원정보 변경시 보게될 화면
	 */
	public void updateMember() {
		System.out.println("\n========회원 정보 변경=========");
		
		//어떤회원인지 찾기위한) 아이디, 비밀번호, 이메일, 전화번호, 주소
		System.out.print("아이디를 입력해주세요 : ");
		String userId = sc.nextLine();
		
		System.out.print("변경할 비밀번호 : ");
		String userPwd = sc.nextLine();
		
		System.out.print("변경할 이메일");
		String email = sc.nextLine();
		
		System.out.print("변경할 전화번호");
		String phone = sc.nextLine();
		
		System.out.print("변경할 주소");
		String address = sc.nextLine();
		
		mc.updateMember(userId, userPwd, email, phone, address);
	}
	
	public void inputMember() {
		//회원정보 (db에 있는 것들을 입력받아야함)
		mc.insertMember();
	}
	public String inputMemberId() {
		System.out.println("아이디 : ");
		String userId = sc.nextLine();
		return userId;
	}
	
	//----------------- 응답화면 ----------------------------
	/*
	 * 서비스 요청 처리 후 성공했을 경우 사용자가 보게될 응답화면
	 * @param : 객체별 성공 메세지
	 */
	public void displaySuccess(String message) {
		System.out.println("\n서비스 요청 성공 : " + message);
	}
	
	/*
	 * 서비스 요청 처리 후 실패했을 경우 사용자가 보게될 응답화면
	 * @param : 객체별 실패 메세지
	 */
	public void displayFail(String message) {
		System.out.println("\n서비스 요청 실패 : " + message);
	}
	
	/*
	 * 조회 서비스 요청 시 조회결과가 없을 때 사용자가 보게될 응답화면
	 * @param : 조회결과에 대한 응답 메세지
	 */
	public void displayNoData(String message) {
		System.out.println("\n"+ message);
	}
	
	/*
	 * 조회 서비스 요청 시 결과가 여러 행일 경우 사용자가 보게될 응답화면
	 * @param : 조회결과에
	 */
	public void displayMemberList(ArrayList<Member> list) {
		//for loop
//		for(int i =0; i < list.size(); i++) {
//			System.out.println(list.get(i));
//		}
		
		//for each
		for(Member m : list) {
			System.out.println(m);
		}
	}
	
	
}
