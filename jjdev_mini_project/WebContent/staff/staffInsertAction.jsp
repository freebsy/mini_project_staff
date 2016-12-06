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
	
	//�̸��ޱ�
	String name = request.getParameter("name");
	System.out.println("name:"+name);
	
	//�ֹι�ȣ�ޱ�
	String sn1 = request.getParameter("sn1");
	String sn2 = request.getParameter("sn2");
	String sn = sn1 +"-"+ sn2;
	System.out.println("sn:"+sn);
	
	//������ȣ
	int religionNo = Integer.parseInt(request.getParameter("religionNo"));
	System.out.println("religionNo:"+religionNo);
	
	//�з¹�ȣ
	int schoolNo = Integer.parseInt(request.getParameter("schoolNo"));
	System.out.println("schoolNo:"+schoolNo);
	
	//�����ȣ ������
	String[] skillStr = request.getParameterValues("skillNo");
	int[] skillNo = new int[skillStr.length];
	for(int i = 0;i<skillStr.length;i++){
		skillNo[i] = Integer.parseInt(skillStr[i]);
		System.out.println("skillNo:"+skillNo[i]);
	}
	
	//������
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