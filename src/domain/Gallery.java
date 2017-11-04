package domain;

import manager.DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Gallery {
	private int gallery_id = 0;
	private String name = null;
	private String description = null;
	private LinkedList<Integer> images = null;
	
	public Gallery(){
		this.images = new LinkedList<Integer>();
	}
	
	public int getID() { return gallery_id; }
	public void setID(int id) { this.gallery_id = id; }
	public String getName() {
		return name;
	}
	public void setName(String name) { this.name = name; }
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) { this.description = description; }
	public LinkedList<Integer> getImages () { return images; }
	public void setImages(LinkedList<Integer> images) {
		this.images = images;
	}


	static public LinkedList<Gallery> fetchGalleries() {
		LinkedList<Gallery> galleryList = new LinkedList<Gallery>();
		DBManager galleryDb = new DBManager();
		String gallerySelect = "SELECT distinct gallery_id, gallery.name, gallery.description"
				+" FROM gallery " + "ORDER BY gallery.name ASC;";
		try {

			ResultSet rs = galleryDb.executeQuery(gallerySelect, "OnlyPrepared");
			while (rs.next())
			{
				Gallery gallery = new Gallery();
				gallery.setID(rs.getInt(1));
				gallery.setName(rs.getString(2));
				gallery.setDescription(rs.getString(3));
				galleryList.add(gallery);
			}
			galleryDb.close();
		} catch (SQLException e) {
			System.err.println("Error");
			while(e != null) {
				System.out.println("Error: " + e.getMessage());
				e = e.getNextException();
			}
		}finally{
			galleryDb.close();
		}

		for (Gallery gallery: galleryList)
		{
			String imgSelect = "SELECT DISTINCT image_id "+
					"FROM image JOIN gallery ON gallery.gallery_id = image.gallery_id "+
					"WHERE image.gallery_id = "+ gallery.getID()+";";
			try {
				ResultSet rs = galleryDb.executeQuery(imgSelect, "OnlyPrepared");
				while (rs.next())
					gallery.getImages().add(rs.getInt(1));
			} catch (SQLException e) {
				System.err.println("Error");
				while(e != null) {
					System.out.println("Error: " + e.getMessage());
					e = e.getNextException();
				}
			}finally{
				galleryDb.close();
			}
		}
		return galleryList;
	}

	static public String getGalleryNameByID (int id) {
		String galleryName = null;
		DBManager galleryDb = new DBManager();
		String nameQuery = "SELECT DISTINCT gallery.name "
						  +"FROM gallery "
						  +"WHERE gallery_id = " + Integer.toString(id) + ";";
		try {
			ResultSet rs = galleryDb.executeQuery(nameQuery, "OnlyPrepared");
			while (rs.next())
                galleryName = rs.getString(1);
            galleryDb.close();
		} catch (SQLException e) {
			System.err.println("Error");
			while(e != null) {
				System.out.println("Error: " + e.getMessage());
				e = e.getNextException();
			}
		}finally{
            galleryDb.close();
		}
		return galleryName;
	}
}
