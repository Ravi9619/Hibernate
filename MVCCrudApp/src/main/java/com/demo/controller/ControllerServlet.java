package com.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.dto.Student;
import com.demo.service.IStudentService;
import com.demo.servicefactory.StudentServiceFactory;

@WebServlet("/controller/*")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IStudentService studentService = StudentServiceFactory.getStudentService();

		System.out.println("Request URI :: " + request.getRequestURI());
		System.out.println("Path Info :: " + request.getPathInfo());

		if (request.getRequestURI().endsWith("addform")) {
			String name = request.getParameter("sname");
			String age = request.getParameter("sage");
			String address = request.getParameter("saddress");

			Student student = new Student();

			student.setSname(name);
			student.setSage(Integer.parseInt(age));
			student.setSaddress(address);

			String status = studentService.addStudent(student);
			PrintWriter out = response.getWriter();
			RequestDispatcher reqdisp = null;
			if (status.equals("success")) {
				request.setAttribute("status", "success");
				reqdisp = request.getRequestDispatcher("../insertResult.jsp");
				reqdisp.forward(request, response);
			} else {
				request.setAttribute("status", "failure");
				request.getRequestDispatcher("../insertResult.jsp");
				reqdisp.forward(request, response);
			}

			out.close();
		}

		if (request.getRequestURI().endsWith("searchform")) {
			String sid = request.getParameter("sid");

			Student student = studentService.searchStudent(Integer.parseInt(sid));
			request.setAttribute("student", student);
			RequestDispatcher rd = null;
			rd = request.getRequestDispatcher("../display.jsp");
			rd.forward(request, response);
		}

		if (request.getRequestURI().endsWith("editform")) {
			String sid = request.getParameter("sid");

			Student student = studentService.searchStudent(Integer.parseInt(sid));
			RequestDispatcher rd = null;
			if (student != null) {
				request.setAttribute("student", student);
				rd = request.getRequestDispatcher("../updateForm.jsp");
				rd.forward(request, response);
			} 
		}

		if (request.getRequestURI().endsWith("updateRecord")) {
			String sid = request.getParameter("sid");
			String sname = request.getParameter("sname");
			String sage = request.getParameter("sage");
			String sadress = request.getParameter("saddress");
			
			System.out.println(sid);
			System.out.println(sname);
			System.out.println(sage);
			System.out.println(sadress);

			Student student = new Student();
			student.setSid(Integer.parseInt(sid));
			student.setSname(sname);
			student.setSage(Integer.parseInt(sage));
			student.setSaddress(sadress);

			String status = studentService.updateStudent(student);
			PrintWriter out = response.getWriter();
			RequestDispatcher reqdisp = null;
			if (status.equals("success")) {
				reqdisp = request.getRequestDispatcher("../../updatesuccess.html");
				reqdisp.forward(request, response);
			} else {
				reqdisp = request.getRequestDispatcher("../../updatefailure.html");
				reqdisp.forward(request, response);
			}
			out.close();

		}

		if (request.getRequestURI().endsWith("deleteform")) {
			String sid = request.getParameter("sid");
			String status = studentService.deleteStudent(Integer.parseInt(sid));

			PrintWriter out = response.getWriter();
			RequestDispatcher reqdisp = null;
			if (status.equals("success")) {
				request.setAttribute("status", "success");
				reqdisp = request.getRequestDispatcher("../deleteResult.jsp");
				reqdisp.forward(request, response);
			} else if (status.equals("failure")) {
				request.setAttribute("status", "failure");
				reqdisp = request.getRequestDispatcher("../deleteResult.jsp");
				reqdisp.forward(request, response);
			} else {
				request.setAttribute("status", "not found");
				reqdisp = request.getRequestDispatcher("../deleteResult.jsp");
				reqdisp.forward(request, response);
			}
		}
	}
}
