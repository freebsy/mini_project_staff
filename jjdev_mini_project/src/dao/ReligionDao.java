package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.Religion;

public class ReligionDao {
	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	ArrayList<Religion> list;
	Religion religion;
	public ReligionDao() {
		System.out.println("---생성자메서드 ReligionDao---");
		try {
			Context init = new InitialContext();
			System.out.println(init + "<-- init ReligionDao() ");
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
			System.out.println(ds + "<-- ds ReligionDao() ");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
	}
	public ArrayList<Religion> allReligion() throws SQLException {
		System.out.println("allSkill");
		conn = ds.getConnection();
		pstmt = conn.prepareStatement("select * from religion");
		rs = pstmt.executeQuery();
		list = new ArrayList<Religion>();
		while(rs.next()){
			religion = new Religion();
			religion.setNo(rs.getInt("no"));
			religion.setName(rs.getString("name"));
			list.add(religion);
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
