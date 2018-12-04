
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class login_servlet
 */
@WebServlet("/Login_servlet")
public class Login_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login_servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();

		String str1 = request.getParameter("username");
		String str2 = request.getParameter("password");

		if (validate(str1, str2)) {
			HttpSession s = request.getSession(true);
			s.setAttribute("username", str1);
			// out.println("++++++++++++");
			out.println("succesfully loged in");
			RequestDispatcher rd = request.getRequestDispatcher("Startgame.html");
			rd.forward(request, response);

		} else {
			out.println("Sorry username or password doesn't exists");
			RequestDispatcher rd = request.getRequestDispatcher("Login.html");
			rd.forward(request, response);
		}
	}

	public boolean validate(String username, String password) {
		// TODO Auto-generated method stub
		System.out.println("checking username and password if exists");
		boolean track = false;
		try {
			System.out.println("!!!!!!!!!!!!!!!!!");
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "123456");
			PreparedStatement pst = con.prepareStatement("select * from user where mail=? and password=?");

			pst.setString(1, username);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			// System.out.println("----------");
			// System.out.println("track");
			track = rs.next();
		} catch (Exception e) {
			System.out.println(e);
		}
		return track;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
