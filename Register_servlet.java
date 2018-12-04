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

/**
 * Servlet implementation class Register_servlet
 */
@WebServlet("/Register_servlet")
public class Register_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register_servlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Databasequeries dbq = new Databasequeries();

		try {
			String name = request.getParameter("username");
			String password = request.getParameter("password");
			String table_name = "user";
			// System.out.print("creating");
			ResultSet rs = dbq.getData(table_name);
			int flag = 0;
			while (rs.next()) {

				String oldname = rs.getString("mail");
				System.out.println(oldname);
				if (name.equals(oldname)) {
					flag = 1;
					break;
				} else {
					flag = 0;
				}
			}

			if (flag == 0) {
				String name1 = request.getParameter("username");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "123456");
				System.out.println("money inserting");
				PreparedStatement pst = con.prepareStatement("insert into money values(?,?)");
				pst.setString(1, name1);
				pst.setString(2, "500");
				int i = pst.executeUpdate();
				if (i != 0) {
					out.println("<br>Record has been inserted");
					RequestDispatcher rd = request.getRequestDispatcher("Login.html");
					rd.forward(request, response);
				} else {
					out.println("failed to insert the data");
				}
				String[] s = { name, password };
				dbq.putData("user", s);
				RequestDispatcher rd = request.getRequestDispatcher("homepage.html");
			} else {
				System.out.println("same name");
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
