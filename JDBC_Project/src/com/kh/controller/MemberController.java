package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;
import com.kh.view.MemberMenu;

//Controller : View를 통해서 사용자가 요청한 기능에 대해서 처리하는 담당
// 			   해당 메소드로 전달된 데이터[가공처리 후] dao로 전달하며 호출
//			   dao로 부터 반환받은 결과 성공인지 실패인지에 따라서 판단 후 응답화면을 결정(view 메소드 호출)

public class MemberController {
	/*
	 * 사용자의 회원추가 요청을 처리해주는 메소드
	 * @param userId ~ hobby : 사용자가 입력했던 정보들이 담겨있는 매개변수
	 */
	
	//Menu에서 입력받았던 값들을 받는 매개변수 넣어줘야함
	public void insertMember() {
		//view로 부터 전달받은 값을 바로 dao쪽으로 바로 전달하지 않는다.
		//어딘가에 (member 객체(vo))에 담아서 전달
		
		//방법 1) 기본생성자로 생성 후 각 필드마다 setter를 이용해서 데이터에 저장
		//방법 2) 매개변수 있는 생성자로 전부 전달해서 생성
		
		Member m = new Member();//생성자로 객체 만들어서 마지막 단계인 멤버 다오한테 보내서 데이터베이스에 저장할거임
		
		
		int result = new MemberDao().insertMember(m);
		
		if(result > 0) {//insert가 성공적으로 완료했다.
			//성공화면
			new MemberMenu().displaySuccess("회원 추가에 성공했습니다.");
		}else { //insert가 실패했다.
			//실패화면
			new MemberMenu().displayFail("회원 추가에 실패했습니다.");
		}
			
	}
	
	/**
	 * 사용자 모두를 조회하는 메소드
	 */
	public void selectList() {
		ArrayList<Member>list =  new MemberDao().selectList();
		
		if(list.isEmpty()) { //list가 비어있는 경우
			new MemberMenu().displayNoData("전체 조회 결과가 없습니다.");
		}else { //조회된 데이터가 있을 경우
			new MemberMenu().displayMemberList(list);
		}
	}
	
	public void updateMember(String userId, String userPwd, String email, String phone, String address) {
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		m.setEmail(email);
		m.setPhone(phone);
		m.setAdress(address);
		
		int result = new MemberDao().updateMember(m);
		
		if(result>0) {
			new MemberMenu().displaySuccess("성공적으로 회원 정보 변경되었습니다.");
		} else {
			new MemberMenu().displayFail("회원정보 변경에 실패하였습니다.");
		}
	}
	
	public void deleteMember(String userId) {
		int result = new MemberDao().deleteMember(userId);
		
		if(result>0) {
			new MemberMenu().displaySuccess("성공적으로 회원 정보를 삭제하였습니다.");
		} else {
			new MemberMenu().displayFail("회원정보 삭제에 실패하였습니다.");
		}
	}
	
}
