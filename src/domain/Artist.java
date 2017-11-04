package domain;

import manager.DBManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Artist {
    private int artist_id = 0;
    private String name = null;
    private int birth_year = 0;
    private String country = null;
    private String description = null;

    public int getID() { return artist_id; }
    public void setID(int id) { this.artist_id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getBirthYear() { return birth_year; }
    public void setBirthYear(int year) { this.birth_year= year; }
    public String getCountry () {
        return country;
    }
    public void setCountry(String country) { this.country = country; }
    public String getDescription() { return description; }
    public void setDescription(String desc) { this.description = desc; }

    static public LinkedList<Artist> fetchArtist(String cond) {
        LinkedList<Artist> artistList = new LinkedList<Artist>();
        DBManager artistDb = new DBManager();
        String artistQuery = "SELECT DISTINCT artist_id, name, birth_year, country, description "
                           +"FROM artist "
                           + (cond!=null?cond:"")
                           +"ORDER BY artist.name ASC;";
        try {
            ResultSet rs = artistDb.executeQuery(artistQuery, "OnlyPrepared");
            while (rs.next())
            {
                Artist artist = new Artist();
                artist.setID(rs.getInt(1));
                artist.setName(rs.getString(2));
                artist.setBirthYear(rs.getInt(3));
                artist.setCountry(rs.getString(4));
                artist.setDescription(rs.getString(5));
                artistList.add(artist);
            }
            artistDb.close();
        } catch (SQLException e) {
            System.err.println("Error");
            while(e != null) {
                System.out.println("Error: " + e.getMessage());
                e = e.getNextException();
            }
        }finally{
            artistDb.close();
        }
        return artistList;
    }
    
    static public Artist getArtistById(int artist_id) {
        Artist artist = new Artist();
        DBManager artistDb = new DBManager();
        String nameQuery = "SELECT DISTINCT artist_id, name, birth_year, country, description "
                          +"FROM artist "
                          +"WHERE artist_id = " + Integer.toString(artist_id) + ";";
        try {
            ResultSet rs = artistDb.executeQuery(nameQuery, "OnlyPrepared");
            while (rs.next()) {
                artist.artist_id = rs.getInt(1);
                artist.name = rs.getString(2);
                artist.birth_year = rs.getInt(3);
                artist.country = rs.getString(4);
                artist.description = rs.getString(5);
            }
            artistDb.close();
        } catch (SQLException e) {
            System.err.println("Error");
            while(e != null) {
                System.out.println("Error: " + e.getMessage());
                e = e.getNextException();
            }
        }finally{
            artistDb.close();
        }
        return artist;
    }

    public static void insertArtist(String name, String birthYear, String country, String description, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message;
        name = name.trim();
        birthYear = birthYear.trim();
        country = country.trim();
        description = description.trim();

        if(birthYear.equals(""))
            birthYear = "0000";

        if ("".equals(name)) {
            request.setAttribute("error",true);
            message = "Please put artist name.";
            request.setAttribute("message", message);
            request.getRequestDispatcher("gallery/view/AddGallery.jsp").forward(request,response);
            return;
        }

        String checkExsit = "SELECT COUNT(*) FROM artist WHERE name = \"" + name + "\"";
        DBManager db = new DBManager();
        ResultSet rs = db.executeQuery(checkExsit);

        try {
            rs.next();
            if (rs.getInt(1) > 0)
            {
                System.out.println(rs.getInt(1));
                request.setAttribute("error",true);
                message = "Error: Artist existed.";
                request.setAttribute("message", message);
                request.getRequestDispatcher("gallery/view/AddGallery.jsp").forward(request,response);
                return;
            }
        } catch (SQLException e) {
            System.err.println("Error");
            while(e != null) {
                System.out.println("Error: " + e.getMessage());
                e = e.getNextException();
            }
        }
        db.close();

        String sql = "INSERT INTO artist (name, birth_year, country, description) " +
                     "VALUES (?, ?, ?, ?)";

        String param[] = {name, birthYear, country, description};
        int  retVal = db.executeUpdate(sql, param);
        db.close();

        if (retVal == -1) { // format incorrect
            request.setAttribute("error",true);
            message = "Please make sure you entered all input in correct format.";
        }
        else if (retVal == 0) {
            request.setAttribute("error",false);
            message = "Adding new artist failed.";
        }
        else {
            request.setAttribute("error",false);
            message = "Success: A new artist inserted.";
        }
        request.setAttribute("message",message);
        request.getRequestDispatcher("gallery/view/AddGallery.jsp").forward(request,response);
    }
}
