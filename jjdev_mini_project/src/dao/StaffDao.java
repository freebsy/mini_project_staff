package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.Staff;

public class StaffDao {
	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	public StaffDao(){
		System.out.println("---�����ڸ޼��� StaffDao---");
		try {
			Context init = new InitialContext();
			System.out.println(init + "<-- init StaffDao() ");
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
			System.out.println(ds + "<-- ds StaffDao() ");
		} catch (Exception ex) {
			System.out.println("DB ���� ���� : " + ex);
			return;
		}
	}
	
	public int insert(Staff staff, int[] skillNo){
		int rowCount = 0;
		System.out.println("insert() StaffDao.java");
		try{
			conn = ds.getConnection();
			System.out.println("skillNo.size()"+skillNo.length);
			
			//staff ���̺� �Է�
			pstmt = conn.prepareStatement("insert into staff (name,sn, GRADUATEDAY, SCHOOLNO, RELIGIONNO) values(?,?,?,?,?)");
			pstmt.setString(1, staff.getName());
			pstmt.setString(2, staff.getSn());
			pstmt.setString(3, staff.getGraduateDay());
			pstmt.setInt(4, staff.getSchoolNo());
			pstmt.setInt(5, staff.getReligionNo());
			pstmt.executeUpdate();
			System.out.println("staff �Է¼���");
			
			//staff ���̺� ��� �Էµ� no ������
			pstmt = conn.prepareStatement("select no from staff where sn=?");
			pstmt.setString(1, staff.getSn());
			rs = pstmt.executeQuery();
			System.out.println("rs:"+rs);
			int staffNo = 0;
			if(rs.next()){
				staffNo = rs.getInt("no");
				System.out.println("staffNo:"+staffNo);
			}
			
			//������ no������ staffskill ���̺� �� �Է�
			pstmt = conn.prepareStatement("insert into staffskill (staffno,skillno) values(?,?)");
			for(int i = 0 ; i<skillNo.length;i++){
				System.out.println("�ݺ���"+i);
				pstmt.setInt(1, staffNo);
				pstmt.setInt(2, skillNo[i]);
				pstmt.executeUpdate();
			}
			System.out.println("staffskill �Է¼���");
		} catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
		
		return rowCount;
		
	}
	
	/*public Staff selectOne(){
		conn = ds.getConnection();
		
		

	}*/
	public void close(){
		if (rs != null)	try { rs.close();} catch (SQLException ex) {}
		if (pstmt != null) try { pstmt.close();	} catch (SQLException ex) {}
		if (conn != null) try {	conn.close(); } catch (SQLException ex) {}
	}
	
	
	
	
}
