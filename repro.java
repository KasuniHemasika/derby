//This is the test class for Derby -6902

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class repro
{

	public static void main(String [] args )
		throws ClassNotFoundException
	{
	    try
	    {
		Connection conn;

		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		conn = DriverManager.getConnection("jdbc:derby:memory:test;create=true");
		PreparedStatement createTable = conn.prepareStatement(
				"create table test (" +
				"  id bigint primary key, " +
				"  big_number bigint not null, " +
				"  small_number int not null " +
				")");
		createTable.execute();
		createTable.close();

		PreparedStatement delete = conn.prepareStatement(
				"delete from test " +
				"where big_number < ? - cast(small_number as bigint) * 1000");
		delete.setLong(1, System.currentTimeMillis());
		delete.executeUpdate();
		delete.close();
	    }
	    catch(SQLException e) {
	     do {
		e.printStackTrace();
	        System.out.println("SQLState:" + e.getSQLState());
	        System.out.println("Error Code:" + e.getErrorCode());
	        System.out.println("Message:" + e.getMessage());
	        Throwable t = e.getCause();
	        while(t != null) {
	            System.out.println("Cause:" + t);
			t.printStackTrace();
	            t = t.getCause();
	        }
	        e = e.getNextException();
	     } while (e != null);
	    }
	}

}
