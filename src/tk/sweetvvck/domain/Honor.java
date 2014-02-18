package tk.sweetvvck.domain;

import java.util.Date;
import java.util.Set;

/**
 * Honor entity. @author MyEclipse Persistence Tools
 */

public class Honor implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer honorId;
	private String honorName;
	private String honorContent;
	private Set<Student> students;
	private Banji banji;
	private String level;
	private Date prizeDate;
	private String honorType;
	private String instructor;
	private String competitionName;
	private Album album;

	// Constructors

	/** default constructor */
	public Honor() {
	}

	/** full constructor */
	public Honor(String honorName, String honorContent) {
		this.honorName = honorName;
		this.honorContent = honorContent;
	}

	// Property accessors

	public Integer getHonorId() {
		return this.honorId;
	}

	public void setHonorId(Integer honorId) {
		this.honorId = honorId;
	}

	public String getHonorName() {
		return this.honorName;
	}

	public void setHonorName(String honorName) {
		this.honorName = honorName;
	}

	public String getHonorContent() {
		return this.honorContent;
	}

	public void setHonorContent(String honorContent) {
		this.honorContent = honorContent;
	}

	public Banji getBanji() {
		return banji;
	}

	public void setBanji(Banji banji) {
		this.banji = banji;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Date getPrizeDate() {
		return prizeDate;
	}

	public void setPrizeDate(Date prizeDate) {
		this.prizeDate = prizeDate;
	}

	public String getHonorType() {
		return honorType;
	}

	public void setHonorType(String honorType) {
		this.honorType = honorType;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getCompetitionName() {
		return competitionName;
	}

	public void setCompetitionName(String competitionName) {
		this.competitionName = competitionName;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
}