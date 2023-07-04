package cash.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cash.vo.Cashbook;

public class CashbookDao {
	public List<Cashbook> selectCashbookListByMonth(
			String memberId, int tagetYear, int tagetMonth) {
		
		List<Cashbook> list = new ArrayList<Cashbook>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT cashbook_no cashbookNo, category, price, cashbook_date cashbookDate"
					+ " FROM cashbook"
					+ " WHERE member_id=? AND YEAR(cashbook_date)=? AND MONTH(cashbook_date)=?"
					+ " ORDER BY cashbook_date ASC";
		try {
			String driver = "org.mariadb.jdbc.Driver";
			String dbUrl = "jdbc:mariadb://127.0.0.1:3306/cash";
			String dbUser = "root";
			String dbPw = "java1234";
			Class.forName(driver);
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, tagetYear);
			stmt.setInt(3, tagetMonth);
			System.out.println(stmt);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Cashbook c = new Cashbook();
				c.setCashbookNo(rs.getInt("cashbookNo"));
				c.setCategory(rs.getString("category"));
				c.setPrice(rs.getInt("price"));
				c.setCashbookDate(rs.getString("cashbookDate"));
				list.add(c);
			}
		} catch(Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return list;
	}
	
	public List<Cashbook> selectCashbookListByDate(String memberId, String cashbookDate){
		List<Cashbook> list = new ArrayList<Cashbook>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT cashbook_no cashbookNo, category, price, memo, cashbook_date cashbookDate, updatedate, createdate FROM cashbook WHERE member_id = ? AND cashbook_date = ? ORDER BY cashbook_date ASC";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
		    stmt.setString(1, memberId);
	        stmt.setString(2, cashbookDate);
	        rs = stmt.executeQuery();
	        System.out.println(stmt);
	        while(rs.next()) {
	        	Cashbook c = new Cashbook();
	        	c.setCashbookNo(rs.getInt("cashbookNo"));
	        	c.setCategory(rs.getString("category"));
	        	c.setPrice(rs.getInt("price"));
	        	c.setMemo(rs.getString("memo"));
	        	c.setCashbookDate(rs.getString("cashbookDate"));
	        	c.setUpdatedate(rs.getString("updatedate"));
	        	c.setCreatedate(rs.getString("createdate"));
	        	list.add(c);
	        }
		} catch (Exception e1) {
			e1.printStackTrace();	
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}	
		}
		return list;
	}
	
	 public int[] insertCashbook(Cashbook cashbook) {
		 int[] rowAndKey = new int[2]; 
	      Connection conn = null;
	      PreparedStatement stmt = null;
	      ResultSet rs = null; // 입력후 생성된 키값 반환
	      try {
	         String driver = "org.mariadb.jdbc.Driver";
	         String dbUrl = "jdbc:mariadb://127.0.0.1:3306/cash";
	         String dbUser = "root";
	         String dbPw = "java1234";
	         Class.forName(driver);
	         conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
	         String sql = "INSERT INTO"
	               + " cashbook(member_id, category, cashbook_date, price, memo, updatedate, createdate)"
	               + " VALUES(?,?,?,?,?,NOW(),NOW())";
	         stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	         stmt.setString(1, cashbook.getMemberId());
	         stmt.setString(2, cashbook.getCategory());
	         stmt.setString(3, cashbook.getCashbookDate());
	         stmt.setInt(4, cashbook.getPrice());
	         stmt.setString(5, cashbook.getMemo());
	         // 배열 첫번째 : 영향받은 행값(row) 저장
	         rowAndKey[0] = stmt.executeUpdate();
	         if(rowAndKey[0] == 1) {
		         rs = stmt.getGeneratedKeys();
		         int cashbookNo = 0;
		         if(rs.next()) {
		            cashbookNo = rs.getInt(1);
		         }
		         rowAndKey[1] = cashbookNo;
	         }
	      } catch(Exception e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            rs.close();
	            stmt.close();
	            conn.close();
	         }catch(Exception e2) {
	            e2.printStackTrace();
	         }
	      }
	      return rowAndKey;
	   }
}