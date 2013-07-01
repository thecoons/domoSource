package com.GetEtat.servlets;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class servletRedirection extends HttpServlet{
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		try{
			if(!action.equals(null)){
				if (action.equals("action")){
					this.getServletContext().getRequestDispatcher("/servletTechno").forward(request, response);
				}else if (action.equals("protocole"))
				{
					this.getServletContext().getRequestDispatcher("/servletProtocole").forward(request, response);
				}else System.out.println("Warning %Action=% ERR");
			}
		}catch(NullPointerException e){
			System.err.println("Erreur NULLPOINTEUR SERVLETVUE");
			this.getServletContext().getRequestDispatcher("/info.jsp").forward(request, response);
		}
	}
}
