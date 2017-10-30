package controller;

import domain.Gallery;
import manager.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

@WebServlet("/GalleryControl")
public class GalleryControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gallery gallery = new Gallery();

		LinkedList<Gallery> galleryList = fetchGalleries();

		request.setAttribute("gallery", gallery);
		request.setAttribute("result", galleryList);
		request.getRequestDispatcher("gallery/view/GalleryList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private LinkedList<Gallery> fetchGalleries() {
		LinkedList<Gallery> galleryList = new LinkedList<Gallery>();
		DBManager galleryDb = new DBManager();
		String movieSelect = "SELECT distinct gallery_id, gallery.name, gallery.description"
							+" FROM gallery " + "ORDER BY gallery.name DESC;";
		try {

			ResultSet rs = galleryDb.executeQuery(movieSelect, "OnlyPrepared");
			while (rs.next())
			{
				Gallery gallery = new Gallery();
				gallery.setId(rs.getInt(1));
				gallery.setName(rs.getString(2));
				gallery.setDescription(rs.getString(3));
				galleryList.add(gallery);
			}
			galleryDb.close();

			for (Gallery gallery: galleryList)
			{
				String imgSelect = "SELECT distinct image_id, title, link "+
									"FROM image JOIN gallery ON gallery.gallery_id = image.gallery_id "+
									"WHERE image.gallery_id = "+ gallery.getId()+";";

				rs = galleryDb.executeQuery(imgSelect, "OnlyPrepared");
				while (rs.next())
					gallery.getImages().add(rs.getInt(1));
				galleryDb.close();
			}
		} catch (SQLException e) {
			System.err.println("Error");
			while(e != null) {
				System.out.println("Error: " + e.getMessage());
				e = e.getNextException();
			}
		}finally{
			galleryDb.close();
		}
        return galleryList;
	}

}