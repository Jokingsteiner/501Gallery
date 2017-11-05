package controller;

import domain.Image;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/gallery/DeleteControl")
public class DeleteControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cond = request.getParameter("sql_condition");
        String byGallery = request.getParameter("byGallery");
        request.setAttribute("sql_condition", cond);
        request.setAttribute("byGallery", byGallery);
		String returnURL = "ImageControl";
		String imgID = request.getParameter("image_id");
        Image.deleteImage(Integer.valueOf(imgID), returnURL, request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}