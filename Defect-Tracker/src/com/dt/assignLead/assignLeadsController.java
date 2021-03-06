package com.dt.assignLead;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dt.utility.SendMail;

/**
 * Servlet implementation class assignDefectController
 */
public class assignLeadsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String assigned_to=request.getParameter("assigned_to");
		int id=Integer.parseInt(request.getParameter("hidden"));
		String user_name=request.getParameter("username");
		System.out.println("hidden"+user_name);
		assignLeadBiz defectListBiz=new assignLeadBiz();
		boolean flag=defectListBiz.assignDefectBiz(assigned_to,id);
		System.out.println("lead"+assigned_to);
		String send_email_to=assigned_to;
		System.out.println("defect_id"+id);

		System.out.println("hidden"+id);
		String listofDefect = defectListBiz.getDefectList();
		String listofDefects = defectListBiz.getDefectList();	
		if(flag==true){
			
		listofDefects = defectListBiz.getDefectList();
		    request.setAttribute("id", id);
		    request.setAttribute("lead", assigned_to);
			request.setAttribute("message", "Has Been Successfully Assigned To");
			String mail_text=defectListBiz.getDefectDescription(id);
			SendMail obj=new SendMail();
			String subject="New Defect Assigned";
			String mail_text1="Hi, \n\nThis is an auto generated mail. Please don't reply.\nDefect Assigned By: "+user_name+"\n"+mail_text+"\n\nThanks and Regards\nDefect Tracker Team";
			obj.sendNotification(send_email_to,subject,mail_text1);
		
			System.out.println(mail_text);
		}
		else{request.setAttribute("message", "Assignment Failed");}
		RequestDispatcher rd = request.getRequestDispatcher("assignDefect.jsp");
		System.out.println("list"+listofDefects);
		 request.setAttribute("id", id);
		 request.setAttribute("lead", assigned_to);
		 request.setAttribute("listOfDefects", listofDefects);
		 
		 out.print("listOfDefects");
	rd.forward(request, response);	}

}
