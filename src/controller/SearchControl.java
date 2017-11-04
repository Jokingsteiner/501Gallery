package controller;

import domain.Condition;
import domain.Gallery;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SearchControl")
public class SearchControl extends HttpServlet {
    Boolean byGallery = false;
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Search parameters
		String type = request.getParameter("type");
		String yearStart = request.getParameter("year_start");
		String yearEnd = request.getParameter("year_end");
        String artistName = request.getParameter("artist_name");
        String galleryID = request.getParameter("gallery_id");
        String country = request.getParameter("country");
        String birthYear = request.getParameter("birth_year");

		// Set parameters
        Condition condition = new Condition();
        if(type != null) condition.setType(type);
        if(yearStart != null) condition.setYearStart(Integer.valueOf(yearStart));
        if(yearEnd != null) condition.setYearEnd(Integer.valueOf(yearEnd));
        if(artistName != null) condition.setArtistName(artistName);
        if(galleryID != null) condition.setGalleryID(Integer.valueOf(galleryID));
        if(country != null) condition.setCountry(country);
        if(birthYear != null) condition.setBirthYear(Integer.valueOf(birthYear));

        if (request.getParameter("forImage") != null && "true".equals(request.getParameter("forImage"))) {
            String imgCond = generateCondition(condition);
            request.setAttribute("sql_condition", imgCond);
            request.setAttribute("byGallery", byGallery);
            if (byGallery)
                request.setAttribute("gallery_name", Gallery.getGalleryNameByID(Integer.valueOf(request.getParameter("gallery_id"))));
            request.getRequestDispatcher("ImageControl").forward(request, response);
        }
        else if (request.getParameter("forArtist") != null && "true".equals(request.getParameter("forArtist"))) {
//            condition = (Condition) request.getSession().getAttribute("condition");
        }
	}

    private String generateCondition (Condition cond){
	    String type = cond.getType();
        int yearStart = cond.getYearStart();
        int yearEnd = cond.getYearEnd();
        String artistName = cond.getArtistName();
        int galleryID = cond.getGalleryID();
        String country = cond.getCountry();
        int birthYear = cond.getBirthYear();
        String sqlCondition = "";

        if(galleryID == -1){
            boolean isFirstCondition = true;

            if(type != null && ! "".equals(type)) {
                sqlCondition += "detail.type LIKE \""+"%"+ type +"%\" ";
                isFirstCondition = false;
            }

            if(yearStart != -1) {
                sqlCondition += isFirstCondition ? "" : "AND ";
                sqlCondition += "detail.year >= "+ Integer.toString(yearStart) + " ";
                isFirstCondition = false;
            }

            if(yearEnd != -1) {
                sqlCondition += isFirstCondition ? "" : "AND ";
                sqlCondition += "detail.year <= "+ Integer.toString(yearEnd) + " ";
                isFirstCondition = false;
            }

            if(artistName != null && !"".equals(artistName)) {
                sqlCondition += isFirstCondition ? "" : "AND ";
                sqlCondition += "artist.name LIKE \""+"%"+ artistName +"%\" ";
                isFirstCondition = false;
            }

            if(country != null && !"".equals(country)) {
                sqlCondition += isFirstCondition ? "" : "AND ";
                sqlCondition += "artist.country LIKE \""+"%"+ country +"%\" ";
                isFirstCondition = false;
            }

            if(birthYear != -1) {
                sqlCondition += isFirstCondition ? "" : "AND ";
                sqlCondition += "artist.birth_year LIKE \""+"%"+ Integer.toString(birthYear) +"%\" ";
            }
        }
        else {
            sqlCondition = "gallery_id = "+  Integer.toString(galleryID) + " ";
            this.byGallery = true;
        }
        return sqlCondition;
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
