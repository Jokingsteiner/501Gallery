package domain;

import manager.DBManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Image {
    private int image_id = -1;
    private String title = null;
    private String link = null;
    private int gallery_id = -1;
    private int artist_id = -1;
    private int detail_id = -1;

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
        DBManager imageDb = new DBManager();
        String imgsSelect = "SELECT DISTINCT image.image_id, image.title, image.link, image.gallery_id, image.artist_id, image.detail_id "
                            +"FROM image JOIN detail JOIN artist "
                            +"ON image.image_id = detail.image_id AND image.artist_id = artist.artist_id "
                            + (cond!=null?cond:"")
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

    public static void insertImage(String title, String link, String year, String type, String width, String height,
                                   String location, String description, String galleryID, String artistID,
                                   HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message;
        title = title.trim();
        link = link.trim();
        year = year.trim();
        width = width.trim();
        height = height.trim();
        location = location.trim();
        description = description.trim();
        galleryID = galleryID.trim();
        artistID = artistID.trim();

        if(year.equals("")) year = "0000";
        if(width.equals("")) width = "0";
        if(height.equals("")) height = "0";

        if ("".equals(title)) {
            message = "Please input image title.";
            request.setAttribute("error",true);
            request.setAttribute("message", message);
            request.getRequestDispatcher("view/AddImage.jsp").forward(request,response);
            return;
        }

        if ("".equals(link)) {
            message = "Please input image URL.";
            request.setAttribute("error",true);
            request.setAttribute("message", message);
            request.getRequestDispatcher("view/AddImage.jsp").forward(request,response);
            return;
        }

        if ("".equals(galleryID)) {
            message = "Please select a Gallery.";
            request.setAttribute("error",true);
            request.setAttribute("message", message);
            request.getRequestDispatcher("view/AddImage.jsp").forward(request,response);
            return;
        }

        if ("".equals(artistID)) {
            message = "Please select a Artist.";
            request.setAttribute("error",true);
            request.setAttribute("message", message);
            request.getRequestDispatcher("view/AddImage.jsp").forward(request,response);
            return;
        }

        String checkExsit = "SELECT COUNT(*) FROM image WHERE title = \'" + title + "\';";
        DBManager db = new DBManager();
        ResultSet rs = db.executeQuery(checkExsit, "OnlyPrepared");

        try {
            rs.next();
            if (rs.getInt(1) > 0)
            {
                System.out.println(rs.getInt(1));
                message = "Error: Image existed.";
                request.setAttribute("error",true);
                request.setAttribute("message", message);
                request.getRequestDispatcher("view/AddImage.jsp").forward(request,response);
                return;
            }
        } catch (SQLException e) {
            System.err.println("Error");
            while(e != null) {
                System.out.println("Error: " + e.getMessage());
                e = e.getNextException();
            }
        }
        finally {
            db.close();
        }

        String[] param_null = {};
        String insertImgSQL = "INSERT INTO image (title, link, gallery_id, artist_id) "
                             +"VALUES (?, ?, ?, ?);";
        String param[] = {title, link, galleryID, artistID};
        int  retVal = db.executeUpdate(insertImgSQL, param);
        db.close();

        if (retVal == -1) { // format incorrect
            request.setAttribute("error",true);
            message = "Please make sure you entered all input in correct format.";
            request.setAttribute("message",message);
            request.getRequestDispatcher("view/AddImage.jsp").forward(request,response);
        }
        else if (retVal == 0) {
            request.setAttribute("error",false);
            message = "Adding new image failed.";
            request.setAttribute("message",message);
            request.getRequestDispatcher("view/AddImage.jsp").forward(request,response);
        }
        else {  // insertImgSQL returned successfully
            int imageID = -1;
            String findImgID = "SELECT DISTINCT image.image_id "
                              +"FROM image "
                              +"WHERE image.title = \'" + title +"\';";
            try {
                rs = db.executeQuery(findImgID, "OnlyPrepared");
                rs.next();
                imageID = rs.getInt(1);
                db.close();
            } catch (SQLException e) {
                System.err.println("Error");
                while(e != null) {
                    System.out.println("Error: " + e.getMessage());
                    e = e.getNextException();
                }
            }finally{
                db.close();
            }

            String insertDetailSQL = "INSERT INTO detail (image_id, year, type, width, height, location, description) "
                                    +"VALUES (?, ?, ?, ?, ?, ?, ?);";
            String param2[] = {Integer.toString(imageID), year, type, width, height, location, description};
            retVal = db.executeUpdate(insertDetailSQL, param2);
            db.close();
            if (retVal == -1) { // format incorrect
                String deleteImageSQL = "DELETE FROM image "
                                       +"WHERE image_title = \'" + title + "\';";

                db.executeUpdate(deleteImageSQL, param_null);

                request.setAttribute("error",true);
                message = "Please make sure you entered all input in correct format.";
                request.setAttribute("message",message);
                request.getRequestDispatcher("view/AddImage.jsp").forward(request,response);
            }
            else if (retVal == 0) {
                String deleteImageSQL = "DELETE FROM image "
                        +"WHERE image_title = \'" + title + "\';";
                db.executeUpdate(deleteImageSQL, param_null);
                request.setAttribute("error",false);
                message = "Adding new image failed.";
                request.setAttribute("message",message);
                request.getRequestDispatcher("view/AddImage.jsp").forward(request,response);
            }
            else {  // insertDetailSQL returned successfully
                int detailID = -1;
                String findDetailID = "SELECT DISTINCT detail.detail_id "
                                     +"FROM detail "
                                     +"WHERE detail.image_id = " + Integer.toString(imageID) + ";";
                try {
                    rs = db.executeQuery(findDetailID, "OnlyPrepared");
                    rs.next();
                    detailID = rs.getInt(1);
                    db.close();
                } catch (SQLException e) {
                    String deleteImageSQL = "DELETE FROM image "
                            +"WHERE image_title = \'" + title + "\';";
                    db.executeUpdate(deleteImageSQL, param_null);
                    System.err.println("Error");
                    while(e != null) {
                        System.out.println("Error: " + e.getMessage());
                        e = e.getNextException();
                    }
                }finally{
                    db.close();
                }

                String updateImgSQL = "UPDATE image "
                                     +"SET detail_id = " + Integer.toString(detailID)
                                     +"WHERE image_id = "+ Integer.toString(imageID) + ";";
                retVal = db.executeUpdate(updateImgSQL, param_null);

                if (retVal == 0) {
                    String deleteImageSQL = "DELETE FROM image "
                            +"WHERE image_title = \'" + title + "\';";
                    db.executeUpdate(deleteImageSQL, param_null);
                    request.setAttribute("error",false);
                    message = "Adding new image failed.";
                    request.setAttribute("message",message);
                    request.getRequestDispatcher("view/AddImage.jsp").forward(request,response);
                }
                else {
                    request.setAttribute("error", false);
                    message = "Success: A new Image inserted.";
                    request.setAttribute("message",message);
                    request.getRequestDispatcher("view/AddImage.jsp").forward(request,response);
                }
            }
        }
    }
}
