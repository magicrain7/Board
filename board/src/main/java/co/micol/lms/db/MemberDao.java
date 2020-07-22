/*
 * Member Dao
 * 2020.07.22
 * Micol
 */

package co.micol.lms.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user ="micol";
	private String password = "1234";
	
	private Connection conn;
	private PreparedStatement psmt; //명령어 전달
	private ResultSet rs;			//결과 Set을 받는 객체
	
	private final String SELECT_MEMBER = "select * from member where id = ?";
	private final String SELECT_MEMBER_ALL = "select * from member";
	private final String INSERT_MEMBER = "insert into member values(?,?,?,?)";
	private final String DELETE_MEMBER = "delete from member where id = ?";
	private final String UPDATE_MEMBER = "update member"
										+ "set password = ? , name = ? , tel = ? where id = ? ";
	
	public MemberDao() { //생성하면서 DB 커넥션을 연결한다.
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public MemberVo select(MemberVo vo) { // 한명의 멤버로 검색하기 
		try {
			psmt = conn.prepareStatement(SELECT_MEMBER);
			psmt.setString(1, vo.getId()); //db에 넣을때. 대응되는 물음표에 인덱스를 넣음(1, vo.getId())
			rs = psmt.executeQuery(); //결과값 받음.
			if(rs.next()) {
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setTel(rs.getString("tel"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	public List<MemberVo> selectAllMember() { //전체맴버를 조회
		List<MemberVo> list = new ArrayList<MemberVo>();
		try {
			psmt = conn.prepareStatement(SELECT_MEMBER_ALL);
			rs = psmt.executeQuery();
			while(rs.next()) {
				MemberVo allMember = new MemberVo();
				allMember.setId(rs.getString("id"));
				allMember.setPassword(rs.getString("password"));
				allMember.setName(rs.getString("name"));
				allMember.setTel(rs.getString("tel"));
				list.add(allMember);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int insert(MemberVo vo) { //insert 맴버삽입
		int n = 0;
		try {
			psmt = conn.prepareStatement(INSERT_MEMBER);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getPassword());
			psmt.setString(3, vo.getName());
			psmt.setString(4, vo.getTel());
			n = psmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return n; 
	}
	
	public int update(MemberVo vo) { // 맴버수정
		int n= 0;
		try {
			psmt = conn.prepareStatement(UPDATE_MEMBER);
			psmt.setString(1, vo.getPassword());
			psmt.setString(2, vo.getName());
			psmt.setString(3, vo.getTel());
			psmt.setString(4, vo.getName());
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return n; 
	}
	public int delete(MemberVo vo) { // 맴버삭제
		int n=0;
		try {
			psmt = conn.prepareStatement(DELETE_MEMBER);
			psmt.setString(1, vo.getId());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n; 
	}
}
