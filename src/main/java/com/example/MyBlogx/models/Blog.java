package com.example.MyBlogx.models;

import java.time.LocalDateTime;
import java.util.Objects;

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
	private String writer;

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

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Blog() {
	}

	public Blog(String theme, String summary, String content, String writer, long likeVol, long commentVol, long rtVol,
			LocalDateTime date) {
		this.theme = theme;
		this.summary = summary;
		this.content = content;
		this.writer = writer;
		this.likeVol = likeVol;
		this.commentVol = commentVol;
		this.rtVol = rtVol;
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(commentVol, content, date, id, likeVol, rtVol, summary, theme, writer);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Blog other = (Blog) obj;
		return commentVol == other.commentVol && Objects.equals(content, other.content)
				&& Objects.equals(date, other.date) && id == other.id && likeVol == other.likeVol
				&& rtVol == other.rtVol && Objects.equals(summary, other.summary) && Objects.equals(theme, other.theme)
				&& Objects.equals(writer, other.writer);
	}

}
