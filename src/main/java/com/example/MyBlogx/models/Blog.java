package com.example.MyBlogx.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Blog {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column
	private String theme;

	@Column
	private String summary;

	@Column
	private String content;

	@Column
	private long likeVol;

	@Column
	private long commentVol;

	@Column
	private long rtVol;

	@Column
	private LocalDateTime date;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getLikeVol() {
		return likeVol;
	}

	public void setLikeVol(long likeVol) {
		this.likeVol = likeVol;
	}

	public long getCommentVol() {
		return commentVol;
	}

	public void setCommentVol(long commentVol) {
		this.commentVol = commentVol;
	}

	public long getRtVol() {
		return rtVol;
	}

	public void setRtVol(long rtVol) {
		this.rtVol = rtVol;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Blog() {
		super();
	}
}
