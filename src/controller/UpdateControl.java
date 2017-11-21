package controller;


import domain.Artist;
import domain.Detail;
import domain.Gallery;
import domain.Image;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/gallery/UpdateControl")
@MultipartConfig
public class UpdateControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String updateType = request.getParameter("update_type");
        switch (updateType) {
            case "image": {
                String title = request.getParameter("title");
                String link = request.getParameter("link");
                String year = request.getParameter("year");
                String type = request.getParameter("type");
                String width = request.getParameter("width");
                String height = request.getParameter("height");
                String location = request.getParameter("location");
                String description = request.getParameter("description");
                String galleryID = request.getParameter("gallery_id");
                String artistID = request.getParameter("artist_id");

                // avoid exception by invalid HTTP Get
                if (title == null) title = "";
                if (link == null) link = "";
                if (year == null) year = "";
                if (type == null) type = "";
                if (width == null) width = "";
                if (height == null) height = "";
                if (location == null) location = "";
                if (description == null) description = "";
                if (galleryID == null) galleryID = "";
                if (artistID == null) artistID = "";

                int imgID = Integer.valueOf(request.getParameter("image_id"));
                String returnURL = "DetailControl?detail_type=image&image_id=" + Integer.toString(imgID);
                Image.modifyImage(imgID, title, link, year, type, width, height, location, description, artistID, galleryID, returnURL, request, response);
                break;
            }
            case "artist": {
                String name = request.getParameter("name");
                String birthYear = request.getParameter("birth_year");
                String country = request.getParameter("country");
                String description = request.getParameter("description");
                // avoid exception by invalid HTTP Get
                if (name == null) name = "";
                if (birthYear == null) birthYear = "";
                if (country == null) country = "";
                if (description == null) description = "";

                int artistID = Integer.valueOf(request.getParameter("artist_id"));
                String returnURL = "DetailControl?detail_type=artist&artist_id" + Integer.toString(artistID);
                Artist.modifyArtist(artistID, name, birthYear, country, description, returnURL, request, response);
                break;
            }
            case "gallery": {
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                // avoid exception by invalid HTTP Get
                if (name == null) name = "";
                if (description == null) description = "";

                String galleryName = request.getParameter("gallery_name");
                int galleryID = Gallery.getGalleryByName(galleryName).getID();
                String returnURL = "SearchControl?forImage=true&gallery_id=" + Integer.toString(galleryID);
                Gallery.modifyGallery(galleryID, name, description, returnURL, request, response);
                break;
            }
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}