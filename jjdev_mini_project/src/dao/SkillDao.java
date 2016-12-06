package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.Skill;

public class SkillDao {
	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	ArrayList<Skill> list;
	Skill skill;
	public SkillDao() {
		System.out.println("---생성자메서드 SkillDao---");
		try {
			Context init = new InitialContext();
			System.out.println(init + "<-- init SkillDao() ");
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
			System.out.println(ds + "<-- ds SkillDao() ");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
	}
	public ArrayList<Skill> allSkill() throws SQLException {
		System.out.println("allSkill");
		conn = ds.getConnection();
		pstmt = conn.prepareStatement("select * from skill");
		rs = pstmt.executeQuery();
		list = new ArrayList<Skill>();
		while(rs.next()){
			skill = new Skill();
			skill.setNo(rs.getInt("no"));
			skill.setName(rs.getString("name"));
			list.add(skill);
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
