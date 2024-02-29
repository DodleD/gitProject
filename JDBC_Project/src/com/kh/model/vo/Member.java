package com.kh.model.vo;

import java.sql.Date;
/*
 * VO(value object)
 * 한명의 회원(db테이블의 한 행의 데이터)에 대한 데이터를 기록할 수 있는 저장용 객체 
 */





public class Member {
	
	//필드는 기본적으로 db컬럼명과 유사하게 작업
	private int userNo;
	private String userId;
	private String userPwd;
	private String userName;
	private String gender;
	private String email;
	private int age;
	private String phone;
	private String address;
	private String hobby;
	private Date enrollDate; //java.sql.Date
	
	
	
	
	
	public Member() {
		super();
	}
	public Member(int userNo, String userId, String userPwd, String userName, String gender, String email, int age,
			String phone, String address, String hobby, Date enrollDate) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.gender = gender;
		this.email = email;
		this.age = age;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
		this.enrollDate = enrollDate;
	}
	protected int getUserNo() {
		return userNo;
	}
	protected void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	protected String getUserId() {
		return userId;
	}
	protected void setUserId(String userId) {
		this.userId = userId;
	}
	protected String getUserPwd() {
		return userPwd;
	}
	protected void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	protected String getUserName() {
		return userName;
	}
	protected void setUserName(String userName) {
		this.userName = userName;
	}
	protected String getGender() {
		return gender;
	}
	protected void setGender(String gender) {
		this.gender = gender;
	}
	protected String getEmail() {
		return email;
	}
	protected void setEmail(String email) {
		this.email = email;
	}
	protected int getAge() {
		return age;
	}
	protected void setAge(int age) {
		this.age = age;
	}
	protected String getPhone() {
		return phone;
	}
	protected void setPhone(String phone) {
		this.phone = phone;
	}
	protected String getAddress() {
		return address;
	}
	protected void setAddress(String address) {
		this.address = address;
	}
	protected String getHobby() {
		return hobby;
	}
	protected void setHobby(String hobby) {
		this.hobby = hobby;
	}
	protected Date getEnrollDate() {
		return enrollDate;
	}
	protected void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	
	@Override
	public String toString() {
		return userNo + ", " + userId + ", " + userPwd + ", " + userName
				+ ", " + gender + ", " + email + ", " + age + ", " + phone + ", "
				+ address + ", " + hobby + ", " + enrollDate;
	}
	
	
	
	
	
}

