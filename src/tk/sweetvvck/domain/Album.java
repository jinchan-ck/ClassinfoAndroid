package tk.sweetvvck.domain;

import java.util.HashSet;
import java.util.Set;

public class Album implements java.io.Serializable{
	private static final long serialVersionUID = -1567333500990789768L;
	private Integer albumId;
	private String albumname;
	private Banji banji;
	private Set<Photo> photos = new HashSet<Photo>();
	private String albumType;

	public String getAlbumname() {
		return albumname;
	}

	public void setAlbumname(String albumname) {
		this.albumname = albumname;
	}

	public Set<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}

	public Integer getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Integer albumId) {
		this.albumId = albumId;
	}

	public Banji getBanji() {
		return banji;
	}

	public void setBanji(Banji banji) {
		this.banji = banji;
	}

	public String getAlbumType() {
		return albumType;
	}

	public void setAlbumType(String albumType) {
		this.albumType = albumType;
	}

}
