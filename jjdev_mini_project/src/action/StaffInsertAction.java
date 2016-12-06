package action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StaffDao;
import dto.Staff;

@WebServlet("/StaffInsertAction")
public class StaffInsertAction extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("StaffInsertAction doGet()");
		request.getRequestDispatcher("/staff/staffInsertForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		response.sendRedirect(request.getContextPath()+"/StaffSearchAction");
	}

}
