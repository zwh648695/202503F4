package com.course.dto;

public class TodoDto {

	private String title;
	
	private String username;

	public TodoDto() {
		
	}
	
	public TodoDto(String title, String username) {
		this.title = title;
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}