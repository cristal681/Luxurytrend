package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import user.UserDTO;

public class UserDAO {
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	private int cnt;
	
	
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
	      //rs.next() : 
	        if(rs.next()) {
	        	String getName = rs.getString(1);
	        	
	        	
	        	//
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
	
	public int user_join(UserDTO user) {
		try {
	        connection();
			
	         // 3. 
	         String sql = "insert into users values(?,?,?)";
	         
	         psmt = conn.prepareStatement(sql);
	         psmt.setString(1, user.getName());
	         psmt.setString(2, user.getEmail());
	         psmt.setString(3, user.getPw());
	         
	         
	         cnt = psmt.executeUpdate();
	        
	      
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close();
	      }//end finally
		
		return cnt;
	}//end member_join()

	public int user_update(UserDTO update_user) {
		
		try {
	         connection();
	         
	         // 3. 
	         String sql = "update web_member set pw=?, name=? where email=?";
	         
	         psmt = conn.prepareStatement(sql);
	         psmt.setString(1, update_user.getEmail());
	         psmt.setString(2, update_user.getName());
	         psmt.setString(3, update_user.getPw());
	         
	         // rs = email,pw,tel,addr
	         
	         cnt = psmt.executeUpdate();
	       
	         

	      
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         //
	      
	        	close();
	      }//end finally
		return cnt;
}
}


