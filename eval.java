package tests;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.*;
import com.hexa.lms.*;
import java.util.*;
import java.text.*;
public class eval{
    @Test
    public void enterEmployeeTest(){
        int n = Employee.enterDetails(235,"Arjun",100,10,"Test Department");
        assertEquals(1,n);
        Connection con = DbConnection.getConnect();
		try {
			Statement stmt=con.createStatement();
			stmt.executeUpdate("DELETE FROM EMPLOYEE WHERE EMP_ID = 235");
		} catch (SQLException e) {
			System.out.println(e);
		}
    }
    @Test
    public void displayEmployeeTest(){
        Employee.enterDetails(235,"Arjun",100,10,"Test Department");
        Connection con = DbConnection.getConnect();
		try {
			Statement stmt=con.createStatement();
			ResultSet rs = stmt.executeQuery("Select EMP_NAME FROM EMPLOYEE where EMP_ID = 235");
			while(rs.next())
			    assertEquals("Arjun",rs.getString(1));
			stmt.executeUpdate("DELETE FROM EMPLOYEE WHERE EMP_ID = 235");
		} catch (SQLException e) {
			System.out.println(e);
		}
    }
    @Test
    public void applyLeaveTest(){
        Employee.enterDetails(235,"Arjun",100,10,"Test Department");
        String s = Employee.applyLev("2019-04-20","2019-04-21",2,"sick",235);
        assertEquals("Your Leave Application has been sent to your Manager\n",s);
        Connection con = DbConnection.getConnect();
		try {
			Statement stmt=con.createStatement();
			ResultSet rs = stmt.executeQuery("Select EMP_LEV_BALANCE FROM EMPLOYEE where EMP_ID = 235");
			while(rs.next())
			    assertEquals(8,rs.getInt(1));
			stmt.executeUpdate("DELETE FROM LEAVE_DETAILS WHERE EMP_ID = 235");
			stmt.executeUpdate("DELETE FROM EMPLOYEE WHERE EMP_ID = 235");
		} catch (SQLException e) {
			System.out.println(e);
		}
    }
    @Test
    public void InsufficientBalanceTest(){
        Employee.enterDetails(235,"Arjun",100,10,"Test Department");
        String s = Employee.applyLev("2019-04-20","2019-04-21",20,"sick",235);
        assertEquals("Insufficient Balance!!",s);
        Connection con = DbConnection.getConnect();
		try {
			Statement stmt=con.createStatement();
			stmt.executeUpdate("DELETE FROM LEAVE_DETAILS WHERE EMP_ID = 235");
			stmt.executeUpdate("DELETE FROM EMPLOYEE WHERE EMP_ID = 235");
		} catch (SQLException e) {
			System.out.println(e);
		}
	 }
   @Test
    public void leaveHistoryTest(){
        Employee.enterDetails(235,"Arjun",100,10,"Test Department");
        String s = Employee.applyLev("2019-04-20","2019-04-21",2,"sick",235);
        assertEquals("Your Leave Application has been sent to your Manager\n",s);
        Connection con = DbConnection.getConnect();
		try {
			Statement stmt=con.createStatement();
			ResultSet rs = stmt.executeQuery("Select LEV_REASON FROM LEAVE_DETAILS where EMP_ID = 235");
			while(rs.next())
			    assertEquals("sick",rs.getString(1));
			stmt.executeUpdate("DELETE FROM LEAVE_DETAILS WHERE EMP_ID = 235");
			stmt.executeUpdate("DELETE FROM EMPLOYEE WHERE EMP_ID = 235");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	@Test	
	public void clearDatabaseTest(){
            boolean flag = Employee.clearDB();
            assertEquals(true,flag);
		}
}
