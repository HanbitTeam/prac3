package board.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class BoardController
 */
@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	ArrayList<String> nonClass = new ArrayList<>();
	
	
	
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
        super();
        // TODO Auto-generated constructor stub
        nonClass.add("insertForm");
        nonClass.add("deleteForm");
    }
    
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	try {
    		request.setCharacterEncoding("utf-8");
			String uri = request.getRequestURI();
			String contextPath = request.getContextPath();
			//String service = uri.substring(contextPath.length()+1);
			
			ActionForward forward = new ActionForward(request);
			
			forward.setNowPage(1);
			
			if(request.getParameter("nowPage")!=null)
			{
				forward.setNowPage(Integer.parseInt(request.getParameter("nowPage")));
			}
			
			forward.setService(uri.substring(contextPath.length()+7));
			
			
			if(!nonClass.contains(forward.getService()))
			{	
				Action action = (Action)Class.forName
						("board.model.Board"+forward.getService()).newInstance();
				action.execute(forward);
				
			}
			
			String template = "../view/template.jsp";
			forward.setAttr();
			request.setAttribute("forward", forward);
			
			if(forward.isRedirect())
			{
				response.sendRedirect(forward.getUrl());
			}else{
				RequestDispatcher dispatcher = 
						request.getRequestDispatcher(template);
				
				dispatcher.forward(request, response);
			}
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

}
