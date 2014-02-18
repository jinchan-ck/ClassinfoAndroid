package tk.sweetvvck.domain;

import java.util.Set;

/**
 * Banji entity. @author MyEclipse Persistence Tools
 */

public class Banji implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer banjiId;
	private String banjiName;
	private Set<Student> students;
	private Set<Honor> honors;
	private Set<Album> albums;
	private Set<Activity> activities;
	private String banjiMail;

	// Constructors

	/** default constructor */
	public Banji() {
	}

	/** full constructor */
	public Banji(String banjiName) {
		this.banjiName = banjiName;
	}

	// Property accessors

	public Integer getBanjiId() {
		return this.banjiId;
	}

	public void setBanjiId(Integer banjiId) {
		this.banjiId = banjiId;
	}

	public String getBanjiName() {
		return this.banjiName;
	}

	public void setBanjiName(String banjiName) {
		this.banjiName = banjiName;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Set<Honor> getHonors() {
		return honors;
	}

	public void setHonors(Set<Honor> honors) {
		this.honors = honors;
	}

	public Set<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	public String getBanjiMail() {
		return banjiMail;
	}

	public void setBanjiMail(String banjiMail) {
		this.banjiMail = banjiMail;
	}

	public Set<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}
}