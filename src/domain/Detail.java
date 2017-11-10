package domain;

import manager.DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Detail {
    private int deatil_id = -1;
    private int image_id = -1;
    private int year = 0;
    private String type = null;
    private int width = -1;
    private int height = -1;
    private String location = null;
    private String description = null;

    public int getID() { return deatil_id; }
    public void setID(int id) { this.deatil_id = id; }
    public int getImgID() { return image_id; }
    public void setImgID(int id) { this.image_id = id; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year= year; }
    public String getType () {
        return type;
    }
    public void setType(String type) { this.type = type; }
    public int getWidth() { return width;}
    public void setWidth(int width) { this.width = width; }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) { this.height = height; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getDescription() { return description; }
    public void setDescription(String desc) { this.description = desc; }

    static public Detail getDetailByImgID(int img_id) {
        Detail detail = new Detail();
        DBManager detailDb = new DBManager();
        String detailSelect = "SELECT DISTINCT detail_id, image_id, year, type, width, height, location, description "
                            +"FROM detail "
                            +"WHERE image_id = " + Integer.toString(img_id) + ";";
        try {

            ResultSet rs = detailDb.executeQuery(detailSelect, "OnlyPrepared");
            while (rs.next())
            {
                detail.setID(rs.getInt(1));
                detail.setImgID(rs.getInt(2));
                detail.setYear(rs.getInt(3));
                detail.setType(rs.getString(4));
                detail.setWidth(rs.getInt(5));
                detail.setHeight(rs.getInt(6));
                detail.setLocation(rs.getString(7));
                detail.setDescription(rs.getString(8));
            }
            detailDb.close();
        } catch (SQLException e) {
            System.err.println("Error");
            while(e != null) {
                System.out.println("Error: " + e.getMessage());
                e = e.getNextException();
            }
        }finally{
            detailDb.close();
        }
        return detail;
    }
}
