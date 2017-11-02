package domain;

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
}
