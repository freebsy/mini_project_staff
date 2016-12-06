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
	SkillDao skillDao = new SkillDao();
	ArrayList<Skill> skillList = skillDao.allSkill();
	System.out.println("sikillList.size():"+skillList.size());
	Skill skill = null;;
	
	SchoolDao schoolDao = new SchoolDao();
	ArrayList<School> schoolList = schoolDao.allSchool();
	System.out.println("schoolList.size():"+schoolList.size());
	School school = null;
	
	ReligionDao religionDao = new ReligionDao();
	ArrayList<Religion> religionList = religionDao.allReligion();
	System.out.println("religionList.size():"+religionList.size());
	Religion religion = null;
	
%>


<form action="<%= request.getContextPath() %>/staff/staffSearchList.jsp" method="post">
	<table border="1" style="width:70%">
		<tr>
			<td>�̸�</td>
			<td><input type="text" name="name"></td>
			<td>����</td>
			<td><input type="checkbox" name="gender" value="m">��<input type="checkbox" name="gender" value="w">��</td>
			<td>����</td>
			<td>
				<select name="religionNo">
<%
					//���� ���
					for(int i = 0 ; i< religionList.size();i++){
						religion = religionList.get(i);
%>
						<option value="<%= religion.getNo()%>" ><%= religion.getName() %></option>
<%
					}
%>
				</select>
			</td>
		</tr>
		<tr>
			<td>�з�</td>
			<td>
<%
				//�з� ���
				for(int i = 0 ; i< schoolList.size();i++){
					school = schoolList.get(i);
%>
				<input type="checkbox" name="schoolNo" value="<%=school.getNo()%>"><%= school.getGraduate() %>
<%
				}
%>
			</td>
			<td>���</td>
			<td colspan="3">
<%
			//��ų ���
			for(int i = 0 ; i< skillList.size();i++){
				skill = skillList.get(i);
%>
				<input type="checkbox" name="skillNo" value="<%= skill.getNo()%>"><%= skill.getName() %>
<%
			}
%>
				
			</td>
		</tr>
		<tr>
			<td>������</td>
			<td colspan="5">
				<input type="date" name="graduateDay1">~<input type="date" name="graduateDay2">
			</td>
		</tr>
		<tr>
			<td colspan="6" align="center">
				<input type="submit" value="��ȸ">
			</td>
			
		</tr>
	</table>
</form>
</body>
</html>