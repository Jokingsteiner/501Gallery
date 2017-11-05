package controller;

import domain.Condition;
import domain.Gallery;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/gallery/SearchControl")
public class SearchControl extends HttpServlet {
    String byGallery = "false";
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Search parameters
		String type = request.getParameter("type");
		String yearStart = request.getParameter("year_start");
		String yearEnd = request.getParameter("year_end");
        String artistName = request.getParameter("artist_name");
        String location = request.getParameter("location");
        String galleryID = request.getParameter("gallery_id");
        String country = request.getParameter("country");
        String birthYear = request.getParameter("birth_year");
        System.out.println("Gallery_id = " + galleryID);
        // Set parameters
        Condition condition = new Condition();
        if(type != null && !type.equals("")) condition.setType(type);
        if(yearStart != null && !yearStart.equals("")) condition.setYearStart(Integer.valueOf(yearStart));
        if(yearEnd != null && !yearEnd.equals("")) condition.setYearEnd(Integer.valueOf(yearEnd));
        if(artistName != null && !artistName.equals("")) condition.setArtistName(artistName);
        if(location != null && !location.equals("")) condition.setLocation(location);
        if(galleryID != null && !galleryID.equals("")) condition.setGalleryID(Integer.valueOf(galleryID));
        if(country != null && !country.equals("")) condition.setCountry(country);
        if(birthYear != null && !birthYear.equals("")) condition.setBirthYear(Integer.valueOf(birthYear));

        if (request.getParameter("forImage") != null && "true".equals(request.getParameter("forImage"))) {
            String imgCond = generateImageCond(condition);
            request.setAttribute("sql_condition", imgCond);
            request.setAttribute("byGallery", byGallery);
            if (byGallery.equals("true"))
                // I believe this (String) is not redundant, removing it may cause NumberFormat exception
                request.setAttribute("gallery_name", Gallery.getGalleryNameByID(Integer.valueOf(galleryID==null?"0":galleryID)));
            request.getRequestDispatcher("ImageControl").forward(request, response);
        }
        else if (request.getParameter("forArtist") != null && "true".equals(request.getParameter("forArtist"))) {
            String artistCond = generateArtistCond(condition);
            request.setAttribute("sql_condition", artistCond);
            request.getRequestDispatcher("ArtistControl").forward(request, response);
        }
        byGallery = "false";
	}

    private String generateImageCond (Condition cond){
	    String type = cond.getType();
        int yearStart = cond.getYearStart();
        int yearEnd = cond.getYearEnd();
        String artistName = cond.getArtistName();
        String location = cond.getLocation();
        int galleryID = cond.getGalleryID();
        String sqlCondition = "";

        if (!("".equals(type + artistName + location) && yearStart == -1 && yearEnd == -1 && galleryID == -1)) {
            if (galleryID == -1) {
                boolean isFirstCondition = true;
                sqlCondition += "WHERE ";
                if (type != null && !"".equals(type)) {
                    sqlCondition += "detail.type LIKE \"" + "%" + type + "%\" ";
                    isFirstCondition = false;
                }

                if (yearStart != -1) {
                    sqlCondition += isFirstCondition ? "" : "AND ";
                    sqlCondition += "detail.year >= " + Integer.toString(yearStart) + " ";
                    isFirstCondition = false;
                }

                if (yearEnd != -1) {
                    sqlCondition += isFirstCondition ? "" : "AND ";
                    sqlCondition += "detail.year <= " + Integer.toString(yearEnd) + " ";
                    isFirstCondition = false;
                }

                if (artistName != null && !"".equals(artistName)) {
                    sqlCondition += isFirstCondition ? "" : "AND ";
                    sqlCondition += "artist.name LIKE \"" + "%" + artistName + "%\" ";
                    isFirstCondition = false;
                }

                if (location != null && !"".equals(location)) {
                    sqlCondition += isFirstCondition ? "" : "AND ";
                    sqlCondition += "detail.location LIKE \"" + "%" + location + "%\" ";
                }
            } else {
                sqlCondition = "WHERE gallery_id = " + Integer.toString(galleryID) + " ";
                this.byGallery = "true";
            }
        }
        return sqlCondition;
    }

    private String generateArtistCond (Condition cond){
        String country = cond.getCountry();
        int birthYear = cond.getBirthYear();
        String sqlCondition = "";
        if (!("".equals(country) && birthYear == -1)) {
            boolean isFirstCondition = true;
            sqlCondition += "WHERE ";
            if (country != null && !"".equals(country)) {
                sqlCondition += "artist.country LIKE \"" + "%" + country + "%\" ";
                isFirstCondition = false;
            }

            if (birthYear != -1) {
                sqlCondition += isFirstCondition ? "" : "AND ";
                sqlCondition += "artist.birth_year = " + Integer.toString(birthYear) + " ";
            }
        }
        return sqlCondition;
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
