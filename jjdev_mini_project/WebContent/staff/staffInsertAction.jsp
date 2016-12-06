<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="dao.*" %>
<%@ page import="dto.*" %>
<%@ page import="java.util.*" %>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title></title>
</head>
<body>
<%
	request.setCharacterEncoding("euc-kr");
	
	//이름받기
	String name = request.getParameter("name");
	System.out.println("name:"+name);
	
	//주민번호받기
	String sn1 = request.getParameter("sn1");
	String sn2 = request.getParameter("sn2");
	String sn = sn1 +"-"+ sn2;
	System.out.println("sn:"+sn);
	
	//종교번호
	int religionNo = Integer.parseInt(request.getParameter("religionNo"));
	System.out.println("religionNo:"+religionNo);
	
	//학력번호
	int schoolNo = Integer.parseInt(request.getParameter("schoolNo"));
	System.out.println("schoolNo:"+schoolNo);
	
	//기술번호 여러값
	String[] skillStr = request.getParameterValues("skillNo");
	int[] skillNo = new int[skillStr.length];
	for(int i = 0;i<skillStr.length;i++){
		skillNo[i] = Integer.parseInt(skillStr[i]);
		System.out.println("skillNo:"+skillNo[i]);
	}
	
	//졸업일
	String graduateDay = request.getParameter("graduateDay");
	System.out.println("graduateDay:"+graduateDay);
	
	Staff staff = new Staff();
	staff.setName(name);
	staff.setSn(sn);
	staff.setReligionNo(religionNo);
	staff.setSchoolNo(schoolNo);
	staff.setGraduateDay(graduateDay);
	
	StaffDao staffDao = new StaffDao();
	staffDao.insert(staff, skillNo);
	
	response.sendRedirect(request.getContextPath()+"/staff/staffSearchForm.jsp");
%>
</body>
</html>