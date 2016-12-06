package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.School;
import dto.Skill;

public class SchoolDao {
	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	ArrayList<School> list;
	School school;
	public SchoolDao() {
		System.out.println("---생성자메서드 SchoolDao---");
		try {
			Context init = new InitialContext();
			System.out.println(init + "<-- init SchoolDao() ");
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
			System.out.println(ds + "<-- ds SchoolDao() ");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
	}
	public ArrayList<School> allSchool() throws SQLException {
		System.out.println("allSkill");
		conn = ds.getConnection();
		pstmt = conn.prepareStatement("select * from school");
		rs = pstmt.executeQuery();
		list = new ArrayList<School>();
		while(rs.next()){
			school = new School();
			school.setNo(rs.getInt("no"));
			school.setGraduate(rs.getString("graduate"));
			list.add(school);
		}
		close();
		return list;
		
	}
	
	public void close(){
		if (rs != null)	try { rs.close();} catch (SQLException ex) {}
		if (pstmt != null) try { pstmt.close();	} catch (SQLException ex) {}
		if (conn != null) try {	conn.close(); } catch (SQLException ex) {}
	}
	
}
