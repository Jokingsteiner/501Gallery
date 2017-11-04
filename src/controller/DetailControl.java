package controller;


import domain.Artist;
import domain.Detail;
import domain.Image;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DetailControl")
public class DetailControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String detailType = request.getParameter("detail_type");
		if(detailType.equals("image")) {
			int img_id = Integer.valueOf(request.getParameter("image_id"));
			Detail detail = Detail.getDetailByImgID(img_id);
			Image image = Image.getImageById(img_id);
			Artist artist = Artist.getArtistById(image.getArtist());
			request.setAttribute("detail", detail);
			request.setAttribute("image", image);
			request.setAttribute("artist", artist);
			request.getRequestDispatcher("gallery/view/ImageDetail.jsp").forward(request, response);
		}
		else if (detailType.equals("artist")) {
            int artist_id = Integer.valueOf(request.getParameter("artist_id"));
            Artist artist = Artist.getArtistById(artist_id);
            request.setAttribute("artist", artist);
            request.getRequestDispatcher("gallery/view/ArtistDetail.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}