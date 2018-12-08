package beatgame;

import java.awt.BorderLayout;
import java.awt.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DB  {
	public static Connection makeDB() {

		Connection con = null;

		String url = "jdbc:mariadb://localhost/test";
		String id = "root";
		String password = "1234";

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(url, id, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 못찾음");
		} catch (SQLException e) {
			System.out.println("연결실패");
		}
		return con;
	}

	public void insert(String id,int a) {
		Connection con = makeDB();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement("insert into beat (id,point) values(?,?)");
			pstmt.setString(1, id);
			pstmt.setInt(2, a);
			pstmt.executeUpdate();

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
	}
	public void update(int no, int point) {
		Connection con = makeDB();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt =con.prepareStatement("update beat set point=? where no=?");
			pstmt.setInt(1, point);
			pstmt.setInt(2, no);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public int select() {
		
		Connection con = makeDB();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int f = 0;
		try {
			pstmt =con.prepareStatement("select no from beat ");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				f++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return f;
	}

	public ArrayList<DataBean> callscore() {
		Connection con = makeDB();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<DataBean> A = new ArrayList<DataBean>();
		try {
			pstmt =con.prepareStatement("select id, point from beat order by point DESC ");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				DataBean D = new DataBean();
				D.setId(rs.getString("id"));
				D.setPoint(rs.getInt("point"));
				A.add(D);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return A;
	}
}
