package controller;

import domain.Image;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by cjk98 on 5/9/2017.
 *
 */
@WebServlet("/gallery/AddImageControl")
public class AddImageControl extends HttpServlet{
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        Image.insertImage(title, link, year, type, width, height, location, description, galleryID, artistID, request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
