package controller;

import domain.Artist;
import domain.Gallery;

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
@WebServlet("/gallery/AddArtistControl")
public class AddArtistControl extends HttpServlet{
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String birthYear = request.getParameter("birth_year_str");
        String country = request.getParameter("country");
        String description = request.getParameter("description");

        // avoid exception by invalid HTTP Get
        if (name == null) name = "";
        if (birthYear == null) birthYear = "";
        if (country == null) country = "";
        if (description == null) description = "";

        Artist.insertArtist(name, birthYear, country, description, request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
