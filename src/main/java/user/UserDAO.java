package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	
	
	//�����ͺ��̽� �������, ������
	  public void connection() {
		 
	      // 1. ����Ŭ ����̹� ���� �ε�
	      try {
	    	  Class.forName("oracle.jdbc.driver.OracleDriver");
				// 2. �����ͺ��̽� ����
				 String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
		         String user = "cgi_3_1";
		         String password = "smhrd1";
		         
		         conn = DriverManager.getConnection(url, user, password);
	         
	      } catch (ClassNotFoundException e) {
	         
	         e.printStackTrace();
	      } catch (SQLException e) {
	   
	         e.printStackTrace();
	      }

	   }

	   public void close() {

		    try {

		         if (rs != null) 
		            rs.close();
		         if (psmt != null)
		            psmt.close();
		         if (conn != null)
		            conn.close();

		      } catch (SQLException e) {
		         e.printStackTrace();
		      }

		   }	
	
	
	public UserDTO login(String email, String pw) {
		 UserDTO User = null;
		 
		 try {
			connection();
			// 3. ������ ����
			String sql = "select name from users where email=? and pw=?";
			
			psmt = conn.prepareStatement(sql);
	        psmt.setString(1, email);
	        psmt.setString(2, pw);
	        
	        rs = psmt.executeQuery();
	      //rs.next() : �Ʒ� ������ �̵��Ͽ� ������ ���� ���� �Ǵ�(true/false)
	        if(rs.next()) {
	        	String getName = rs.getString(1);
	        	
	        	
	        	//ȸ�� ������ ������ ��  �ִ� ��ü ����
	        	User = new UserDTO(email, getName, null);
	        	
	        }else {
	        	System.out.println("���� ��ȸ ����..");
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}//end finally
		return User;
	} // end users_login()
		
		
}


