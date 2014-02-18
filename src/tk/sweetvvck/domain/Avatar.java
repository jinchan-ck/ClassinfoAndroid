package tk.sweetvvck.domain;

/**
 * Avatar entity. @author MyEclipse Persistence Tools
 */

public class Avatar implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer avatarId;
	private Student student;
	private String path;

	// Constructors

	/** default constructor */
	public Avatar() {
	}

	// Property accessors

	public Integer getAvatarId() {
		return this.avatarId;
	}

	public void setAvatarId(Integer avatarId) {
		this.avatarId = avatarId;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}