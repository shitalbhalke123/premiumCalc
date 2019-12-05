package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.VehicleDAO;
import com.dao.VehicleDAOImpl;
import com.model.Vehicle;

/**
 * Servlet implementation class VehicleController
 */
@WebServlet("/VehicleController")
public class VehicleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	VehicleDAO vehicleDAO=new VehicleDAOImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VehicleController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	
		String vehicleNo = request.getParameter("vehiclenumber");
		PrintWriter out=response.getWriter();
		
		request.setAttribute("vehicleNo", vehicleNo);
		/* Vehicle vehicle=vehicleDAO.getVehicleByVehicleNumber("MH12QW4052"); */
		Vehicle vehicle=vehicleDAO.getVehicleByVehicleNumber(vehicleNo);
		if(vehicle!=null)
		{
		request.setAttribute("vehicle", vehicle);
		request.getRequestDispatcher("next.jsp").forward(request, response);	
		}
		else
		{
			RequestDispatcher rd=request.getRequestDispatcher("next.jsp");
			out.print("requested vehicle number not found soo please enter details manually");
			rd.include(request, response);
		}
		
		
	}

}
