import java.sql.*;


public class hw3_Irvin

{

  public void displayData(String urlStr,String username,String password)
  {

		  try
		  {

			Class.forName ("com.mysql.jdbc.Driver").newInstance ();
			
			// connect with username, url, and password given below 
			Connection conn = DriverManager.getConnection(urlStr,username,password);
            System.out.println ("Connected to the MySQL database");
			
			// Number 1
			// Condition of material of the sculpture = stone we get all information on the sculpture
			Statement stmt1 = conn.createStatement();
			ResultSet rs1=null;
		rs1=stmt1.executeQuery("select a.id_no, a.year, a.description, a.title, a.artist_name, s.weight, s.height from Art_object a, Sculpture s where a.id_no = s.id_no and s.material = 'stone';");
			// repeat while result set is not empty 
			while (rs1.next())
			{
			  // Output from the query 
			   System.out.println("Id No: "+rs1.getString(1));
               System.out.println("Year: "+rs1.getString(2));
               System.out.println("Description: "+rs1.getString(3));
               System.out.println("Title: "+rs1.getString(4));
               System.out.println("Artist Name: "+rs1.getString(5));
               System.out.println("Weight: "+rs1.getString(6));
               System.out.println("Height: "+rs1.getString(7));
			}
			stmt1.close();
			  
			// Number 2
			// Call the view artist_no_of_sculpture
			// The output for this query is the artist_name and the count of sculptures
			// use the first example given. 
			Statement stmt2 = conn.createStatement();
            ResultSet rs2 = null;
			//select * from the artist_no_of_sculpture query 
            rs2=stmt2.executeQuery("select * from artist_no_of_sculpture");
            while (rs2.next())
            {
				// Two outputs 1: artist name, 2 Number of Sculptures
                System.out.println("Artist Name: "+rs2.getString(1));
                System.out.println("Number of Sculptures: "+rs2.getString(2));
                System.out.println();
            }
            stmt2.close();
			  
			  
			// Number 3
			// Call function for No_of_painting_exhibited with a input of E2
			// Such as assignment 2
			CallableStatement cstmt = conn.prepareCall("{?= call No_of_painting_exhibited(?)}");
			// output parameter is a integer so we use numeric
			cstmt.registerOutParameter(1, Types.NUMERIC);
			// varchar in mysql would be a string in java 
			cstmt.setString(2,"E2");
			cstmt.execute();
			// The Return type is a Integer in Mysql which would be a BigDecimal in Java 
			System.out.println ("Number of Paintings: "+ cstmt.getBigDecimal(1).toString());
			cstmt.close();
			 
			conn.close();
			System.out.println("Disconnected");


		  }
		  catch (Exception e)
		  {
			e.printStackTrace();
		  }

  }

  public static void main (String args [])
  {
		String url= "jdbc:mysql://athena.ecs.csus.edu/databasename";
		// Username and Password Given from class 
		String username= "cs17416";
		String password = "wfvqavpp";

		hw3_Irvin example = new hw3_Irvin();
		example.displayData(url,username,password);

  }

}
