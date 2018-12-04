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
 * Servlet implementation class Add_Servlet
 */
@WebServlet("/Add_Servlet")
public class Add_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Add_Servlet() {
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
		PrintWriter out = response.getWriter();
		// out.println("+++++++++++");
		response.getWriter().append("Served at: ").append(request.getContextPath());

		HttpSession s = request.getSession();
		Object pp = s.getAttribute("username");
		// out.print(pp);
		String s2 = pp.toString();
		out.println(s2);
		try {
			String str = request.getParameter("option");
			out.println(str);
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "123456");
			PreparedStatement pst = con.prepareStatement("select money from money where username=?");
			pst.setString(1, s2);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int points = rs.getInt(1);
				out.println(s2 + "has a balance of" + points);
				int p = points;
				int opt = Integer.parseInt(str);
				// out.println(p);
				if (p <= 50) {
					p = p + opt;
					out.println(p);
					PreparedStatement pst2 = con.prepareStatement("update money set money=? where username=?");
					pst2.setInt(1, p);
					pst2.setString(2, s2);
					out.println(p);
					pst2.executeUpdate();
					out.println(p);
					RequestDispatcher rd = request.getRequestDispatcher("Startgame.html");
					rd.forward(request, response);
				}

				else {
					// RequestDispatcher
					// rd=request.getRequestDispatcher("ADD.html");
					// rd.forward(request, response);
				}
			}
		} catch (Exception e) {
			out.println(e);
		}
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
