package domain;

import manager.DBManager;

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
}
