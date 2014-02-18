package tk.sweetvvck.domain;

import java.util.Date;


public class Activity implements java.io.Serializable {
	private static final long serialVersionUID = 5246389616911277491L;
	
	private Integer activityId;
	private String activityTitle;
	private Date activityDate;
	private String activityAdd;
	private String activityContent;
	private Album activityAlbum;
	private Banji banji;
	
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public String getActivityTitle() {
		return activityTitle;
	}
	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}
	public String getActivityAdd() {
		return activityAdd;
	}
	public void setActivityAdd(String activityAdd) {
		this.activityAdd = activityAdd;
	}
	public String getActivityContent() {
		return activityContent;
	}
	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}
	public Album getActivityAlbum() {
		return activityAlbum;
	}
	public void setActivityAlbum(Album activityAlbum) {
		this.activityAlbum = activityAlbum;
	}
	public Banji getBanji() {
		return banji;
	}
	public void setBanji(Banji banji) {
		this.banji = banji;
	}
	public Date getActivityDate() {
		return activityDate;
	}
	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}
}