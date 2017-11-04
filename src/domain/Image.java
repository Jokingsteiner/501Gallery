package domain;

import manager.DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Image {
    private int image_id = 0;
    private String title = null;
    private String link = null;
    private int gallery_id = 0;
    private int artist_id = 0;
    private int detail_id = 0;

    public int getID() { return image_id; }
    public void setID(int id) { this.image_id = id; }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) { this.title = title; }
    public String getLink() {
        return link;
    }
    public void setLink(String link) { this.link = link; }
    public int getGallery() { return gallery_id; }
    public void setGallery(int gallery_id) { this.gallery_id = gallery_id; }
    public int getArtist() { return artist_id; }
    public void setArtist(int artist_id) { this.artist_id = artist_id; }
    public int getDetail() { return detail_id; }
    public void setDetail(int detail_id) { this.detail_id = detail_id; }

    static public  LinkedList<Image> fetchImages(String cond) {
        LinkedList<Image> imgList = new LinkedList<Image>();
        int cntImage = 0;
        DBManager imageDb = new DBManager();
        String imgsSelect = "SELECT DISTINCT image.image_id, image.title, image.link, image.gallery_id, image.artist_id, image.detail_id "
                            +"FROM image, detail, artist "
                            +"WHERE image.image_id = detail.image_id AND image.artist_id = artist.artist_id "
                            + (cond != null?("AND " + cond):"")
                            +"ORDER BY image.title ASC;";
        try {
            ResultSet rs = imageDb.executeQuery(imgsSelect, "OnlyPrepared");
            while (rs.next())
            {
                Image img = new Image();
                img.setID(rs.getInt(1));
                img.setTitle(rs.getString(2));
                img.setLink(rs.getString(3));
                img.setGallery(rs.getInt(4));
                img.setArtist(rs.getInt(5));
                img.setDetail(rs.getInt(6));
                imgList.add(img);
            }
            imageDb.close();
        } catch (SQLException e) {
            System.err.println("Error");
            while(e != null) {
                System.out.println("Error: " + e.getMessage());
                e = e.getNextException();
            }
        }finally{
            imageDb.close();
        }
        return imgList;
    }

    static public Image getImageById(int img_id) {
        Image img = new Image();
        DBManager imageDb = new DBManager();
        String imgsSelect = "SELECT DISTINCT image_id, title, link, gallery_id, artist_id, detail_id "
                            +"FROM image "
                            +"WHERE image_id = " + Integer.toString(img_id) + ";";
        try {

            ResultSet rs = imageDb.executeQuery(imgsSelect, "OnlyPrepared");
            while (rs.next())
            {
                img.setID(rs.getInt(1));
                img.setTitle(rs.getString(2));
                img.setLink(rs.getString(3));
                img.setGallery(rs.getInt(4));
                img.setArtist(rs.getInt(5));
                img.setDetail(rs.getInt(6));
            }
            imageDb.close();
        } catch (SQLException e) {
            System.err.println("Error");
            while(e != null) {
                System.out.println("Error: " + e.getMessage());
                e = e.getNextException();
            }
        }finally{
            imageDb.close();
        }
        return img;
    }
}
