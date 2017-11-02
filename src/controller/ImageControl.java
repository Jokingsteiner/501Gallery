package controller;

import domain.Image;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

@WebServlet("/ImageControl")
public class ImageControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gallery_id_str = request.getParameter("gallery_id_str");
		String galleryName = request.getParameter("gallery_name");
        Object[] res = Image.fetchImagesByGallery(gallery_id_str);
		LinkedList<Image> imageList = (LinkedList<Image>) res[0];
        int imageCount = (Integer) res[1];
		request.setAttribute("result", imageList);
		request.setAttribute("image_count", imageCount);
		request.setAttribute("gallery_name", galleryName);
		request.getRequestDispatcher("gallery/view/ImageList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}