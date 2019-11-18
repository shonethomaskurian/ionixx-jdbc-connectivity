package ionixx;
import java.util.*;

import javax.swing.JLabel;

import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Admin {
	
	String s,s1,s4,s6,s7;;
	int s2,s3,s5,s8;
	
	public void admin() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ionixx","root","");
		Statement statement = con.createStatement();
		String a,b;
      Scanner sc=new Scanner(System.in);
      System.out.println(" enter your Username:");
      a=sc.nextLine();
      System.out.println(" enter your password:");
      b=sc.nextLine();
      ResultSet rs=statement.executeQuery("SELECT username,password from adminlogin");
      int c=0;
		while(rs.next()) {
			
			if(a.toString().equals(rs.getString(1)) && b.toString().equals(rs.getString(2))) 
			{
				System.out.println("Login Success");
			addition();
		        c=1;
		        break;
			}
		}
	
		        
		     if(c==0)
		    	 System.out.println("not a valid user");
		     admin();
	}
public void addition() throws ClassNotFoundException, SQLException
{
  int choices;
  Scanner sc=new Scanner(System.in);
	System.out.println("Enter your choice:\n1:Add Product\n2:Display Product\n3:Logout");
	choices=sc.nextInt();
	switch(choices)
	{
	case 1:
	{
		add();
		break;
	}
	case 2:display();
	break;
	case 3: 
	{
		//Main m = new Main();
		Main.main(null);
	}
	
	}
}
public void add() throws SQLException, ClassNotFoundException
{
	
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ionixx","root","");
	Statement statement = con.createStatement();
	Scanner sc=new Scanner(System.in);
	System.out.println("Product id:");
	s=sc.next();
	System.out.println("Product name:");
	s1=sc.next();
	System.out.println("minsellquantity:");
	s2=sc.nextInt();
	System.out.println("Price:");
	s3=sc.nextInt();
	java.sql.PreparedStatement p=null;
	//System.out.println("Quantity available:");
	//s4=sc.next();
	String sql="INSERT INTO admini (productid,productname,minsellquantity,price,quatityavailable,totalcost,transaction,quan,tot)VALUES(?,?,?,?,?,?,?,?,?)";
	p=con.prepareStatement(sql);
	p.setString(1, s);
	p.setString(2,s1);
	p.setInt(3, s2);
	p.setInt(4, s3);
	p.setInt(5,0);
	p.setInt(6, 0);
	p.setString(7,"null");
	p.setInt(8, 0);
	p.setInt(9, 0);
	p.execute();
	addition();
}
public void display() throws SQLException, ClassNotFoundException
{
	int p=0;
	Scanner sc=new Scanner(System.in);
	System.out.println("enter your product id:");
	s6=sc.next();
	System.out.println("enter your product name:");
	s7=sc.next();
	System.out.println("enter your Quantity available:");
	s8=sc.nextInt();
	
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ionixx","root","");
	Statement statement = con.createStatement();
	statement.executeUpdate("UPDATE admini SET quatityavailable='"+s8+"' WHERE productid='"+s6+"'&& productname='"+s7+"'");
	ResultSet rs1=statement.executeQuery("SELECT price FROM admini WHERE productid='"+s6+"'&& productname='"+s7+"'");
	while(rs1.next())
	{
		p=rs1.getInt(1);
	}
	int tot=s8*p;
	String sql = "UPDATE admini  set totalcost = ? WHERE productid=?";
	PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
	ps.setInt(1, tot);
	ps.setString(2,s6);
	ps.execute();
	ResultSet rs=statement.executeQuery("SELECT productid,productname,price,quatityavailable,totalcost from admini WHERE productid='"+s6+"' && productname='"+s7+"'");
	 
		while(rs.next()) {
			//System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" ",rs.getString(5));
			System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
			//System.out.println();
		}
		
		addition();
}
}
