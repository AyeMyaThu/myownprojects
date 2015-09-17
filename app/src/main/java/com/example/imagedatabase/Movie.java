package com.example.imagedatabase;

public class Movie {
	private String id;
	private String title;
	private String starring;
	private String category;
	private Integer rate;
	private String image;
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public void setStarring(String starring) {
		this.starring = starring;
	}
	public String getStarring() {
		return starring;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategory() {
		return category;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}
	public Integer getRate() {
		return rate;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getImage() {
		return image;
	}
}
