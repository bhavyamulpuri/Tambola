import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Game_Servlet
 */
@WebServlet("/Game_Servlet")
public class Game_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Game_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Integer> al=new ArrayList<Integer>();
		PrintWriter out=response.getWriter();
		int i=0;
		
		
		String number="";
		while(i<90)
		{
			int j=0;
			int k=0;
		int s=	(int) ((((Math.random())*90)+1));
		
          if(al.size()==0)
          {
          al.add(s);
        i++;
        number=number+s+"&";	
          }
        else
          {
		for (j = 0; j < al.size(); j ++) 
		{        
			int v=al.get(j);
		if(v!=s)
		{
		k++;
		}
		}
		if(k==al.size())
		{
			al.add(s);

	        i++;

	        number=number+s+"&";	
		}
		  }
		
		
		}
		out.println(number);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
