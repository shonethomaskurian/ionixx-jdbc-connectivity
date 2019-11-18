package ionixx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;

public class Agent {
	int c,r,h,i,tot,n,rc=0,f,fr,fg;
	String s,t,re;
	public void selling() throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ionixx","root","");
		Statement statement = con.createStatement();
		String a,b;
	  Scanner sc=new Scanner(System.in);
	  System.out.println(" enter your Username:");
	  a=sc.nextLine();
	  System.out.println(" enter your password:");
	  b=sc.nextLine();
	  ResultSet rs=statement.executeQuery("SELECT username,password from agentlogi");
	  int c=0;
		while(rs.next()) {
			
			if(a.toString().equals(rs.getString(1)) && b.toString().equals(rs.getString(2))) 
			{
				System.out.println("Login Success");
		        c=1;
		        agenti();
		        break;
			}
			 
		}     
		     if(c==0)
		    	 System.out.println("not a valid user");
		    // selling();
	}
	public void agenti() throws ClassNotFoundException, SQLException
	{
		int choices;
		  Scanner sc=new Scanner(System.in);
			System.out.println("Enter your choice:\n1:Buy/Sell\n2:History\n3:Logout");
			choices=sc.nextInt();
			switch(choices)
			{
			case 1:
			{
				buy();
			}
			case 2:
			{
				history();
			}
			case 3:
			{
				Main.main(null);
			}
	}
					

}
	public void buy() throws ClassNotFoundException, SQLException
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your product id:");
		s=sc.next();
		System.out.println("Enter your choice");
		System.out.println("1:Buy\n2:Sell");
		c=sc.nextInt();
		switch(c)
		{
		case 1:
		{
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ionixx","root","");
			Statement statement = con.createStatement();
			re="buy";
			String sql3 = "UPDATE admini SET transaction=?";
			PreparedStatement ps3 = (PreparedStatement) con.prepareStatement(sql3);
			ps3.setString(1,re );
			ps3.execute();
			t=("SELECT productname,price FROM admini WHERE productid='"+s+"'");
			//System.out.println(t);
			ResultSet rs2=statement.executeQuery(t);
			
		
			while(rs2.next()) {
			System.out.println(rs2.getString(1)+" "+rs2.getString(2));
			break;
			}
			
			System.out.println("enter quantity");
			h=sc.nextInt();
			
			Class.forName("com.mysql.jdbc.Driver");
			String sql1 = "UPDATE admini SET quan=?";
			PreparedStatement ps1 = (PreparedStatement) con.prepareStatement(sql1);
			ps1.setInt(1, h);
			ps1.execute();
			ResultSet rs3=statement.executeQuery("SELECT minsellquantity,price,quatityavailable FROM admini WHERE productid='"+s+"'");
			while(rs3.next()) {
			i=	(rs3.getInt(1));
			//System.out.println(i);
			n=(rs3.getInt(2));
			fr=(rs3.getInt(3));
			if(h<=fr)
			{
				tot=n*h;
				fr=fr-h;
				String sql = "UPDATE admini SET quatityavailable=?";
				PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
				ps.setInt(1, fr);
				ps.execute();
				rc=1;
				System.out.println("total price:"+tot);
				String sql2 = "UPDATE admini SET tot=?";
				PreparedStatement ps2 = (PreparedStatement) con.prepareStatement(sql2);
				ps2.setInt(1, tot);
				ps2.execute();
				System.out.println("press 1 for confirm");
			    fg=sc.nextInt();
			    if(fg==1)
			    	System.out.println("order confirmed");
			    else
			    	agenti();
				
				break;
			}
			
			
		     }
			if (rc==0)
				System.out.println("item not available");
			agenti();
			break;
		}
		case 2:
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ionixx","root","");
			re="sell";
			Statement statement = con.createStatement();
			String sql3 = "UPDATE admini SET transaction=?";
			PreparedStatement ps3 = (PreparedStatement) con.prepareStatement(sql3);
			ps3.setString(1,re );
			ps3.execute();
			t=("SELECT productname,price FROM admini WHERE productid='"+s+"'");
			//System.out.println(t);
			ResultSet rs2=statement.executeQuery(t);
			
		
			while(rs2.next()) {
			System.out.println(rs2.getString(1)+" "+rs2.getString(2));
			break;
			}
			
			System.out.println("enter quantity");
			h=sc.nextInt();
			Class.forName("com.mysql.jdbc.Driver");
			String sql1 = "UPDATE admini SET quan=?";
			PreparedStatement ps1 = (PreparedStatement) con.prepareStatement(sql1);
			ps1.setInt(1, h);
			ps1.execute();
			ResultSet rs3=statement.executeQuery("SELECT minsellquantity,price,quatityavailable FROM admini WHERE productid='"+s+"'");
			while(rs3.next()) {
			i=	(rs3.getInt(1));
			//System.out.println(i);
			n=(rs3.getInt(2));
			fr=(rs3.getInt(3));
			if(h>=i)
			{
				tot=n*h;
				fr=fr+h;
				String sql = "UPDATE admini SET quatityavailable=?";
				PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
				ps.setInt(1, fr);
				ps.execute();
				rc=1;
				System.out.println("total price:"+tot);
				String sql2 = "UPDATE admini SET tot=?";
				PreparedStatement ps2 = (PreparedStatement) con.prepareStatement(sql2);
				ps2.setInt(1, tot);
				ps2.execute();
				System.out.println("press 1 for confirm");
			    fg=sc.nextInt();
			    if(fg==1)
			    	System.out.println("order confirmed");
			    	agenti();
			
			}
			
			
		     }
			if (rc==0)
				System.out.println("item not available");
			buy();
			break;
		
		}
	}

}
	public void history() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ionixx","root","");
		Statement statement = con.createStatement();
		ResultSet rs=statement.executeQuery("SELECT productid,productname,price,quan,tot,transaction from admini WHERE productid='"+s+"'");
		 
		while(rs.next()) {
			System.out.println("history");
			System.out.println("productid productname price quan totalcost transaction");
			System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6));
			agenti();
		}
		
	}
}
