import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class fvalid
 */
@WebServlet("/Forgot_servlet")
public class Forgot_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /*
      @see HttpServlet#HttpServlet()
     */
    public Forgot_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/*
	  @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		PrintWriter p= response.getWriter();
		/*String n0=request.getParameter("mx");*/
		String n1= request.getParameter("email");
		System.out.println(n1);
		try{
			Class.forName("com.mysql.jdbc.Driver");
			/*System.out.println("dss");*/
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","123456");
		   Statement stmt= con.createStatement();
			/*System.out.println("dss");*/
		   String s= "select * from user";
		  ResultSet rs= stmt.executeQuery(s);
		  int flag=0;
		  while(rs.next()){
			if(rs.getString(1).equals(n1)){
				String w = rs.getString(1);
				System.out.println(w);
				flag=1;
				break;
			}
		  }
		  if(flag==1){
			    String x1=rs.getString(1);
			    System.out.println(x1);
			    System.out.println("flag 1");
			    String f1 = "bhavyammulpuri6@gmail.com";
			    String p1 = "Bhavya171995";
			    String y1 = "forgot mail";
			    String b1 = "sending mail";
			    //mail m=new mail();
			    mail.sendEmail(f1,p1, x1,y1,b1);
			    RequestDispatcher view=request.getRequestDispatcher("Login.html");
			    view.forward(request, response);
		  }else{
			  System.out.println("invalid email");
			  p.println("invalid email");
		  }
	}catch(Exception e){e.printStackTrace();}
	}
}