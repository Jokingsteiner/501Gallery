package controller;

import domain.Gallery;
import domain.Image;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

@WebServlet("/gallery/ImageControl")
public class ImageControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cond = (String)request.getAttribute("sql_condition");
        Boolean byGallery = (Boolean) request.getAttribute("byGallery");
		LinkedList<Image> imageList = Image.fetchImages(cond);
		request.setAttribute("result", imageList);
		request.setAttribute("image_count", imageList.size());
        if(byGallery) {
            request.setAttribute("gallery_name", request.getAttribute("gallery_name"));
            request.getRequestDispatcher("view/ImageList.jsp").forward(request, response);
        }
        else
            request.getRequestDispatcher("view/ImageList.jsp").forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}