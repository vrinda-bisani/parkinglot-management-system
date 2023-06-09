package wePark.servlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wePark.dal.ParkinglotDao;
import wePark.dal.UserDao;
import wePark.model.Parkinglot;

@WebServlet("/findparkinglots")
public class FindParkinglots extends HttpServlet {
	protected ParkinglotDao parkinglotDao;
	protected UserDao userDao;
	
	@Override
	public void init() throws ServletException {
		parkinglotDao = ParkinglotDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/Homepage.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
      
        
        try {
        	String zip = req.getParameter("zip");
        	String sortBy = req.getParameter("sort");
        	int height = Integer.parseInt(req.getParameter("vehicleSize"));
	        List<Parkinglot> parkingLots = parkinglotDao.getParkinglotByZip(zip,sortBy,height);	        
	        req.setAttribute("parkingLots", parkingLots);

	    } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

        req.getRequestDispatcher("/FindParkinglots.jsp").forward(req, resp);
    }
	
	
}