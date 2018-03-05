package com.revature.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "101_post")
public class Post {
	@Id
	@Column(name = "post_id")
	@SequenceGenerator(name = "post_id_seq", sequenceName = "post_id_seq")
	@GeneratedValue(generator = "post_id_seq", strategy = GenerationType.AUTO)
	private int postId;

	private String title;

	@Column(name = "user_id")
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User authorId;

	private String body;
	private int likes;

	@Column(name = "status_id")
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "status_id")
	private Status statusId;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "101_post_to_subject", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"))
	private Set<Subject> subjects;

	public Post() {
		super();
	}

	public Post(int postId, String title, User authorId, String body, int likes, Status statusId) {
		this.postId = postId;
		this.title = title;
		this.authorId = authorId;
		this.body = body;
		this.likes = likes;
		this.statusId = statusId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorId == null) ? 0 : authorId.hashCode());
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + likes;
		result = prime * result + postId;
		result = prime * result + ((statusId == null) ? 0 : statusId.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (authorId == null) {
			if (other.authorId != null)
				return false;
		} else if (!authorId.equals(other.authorId))
			return false;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (likes != other.likes)
			return false;
		if (postId != other.postId)
			return false;
		if (statusId == null) {
			if (other.statusId != null)
				return false;
		} else if (!statusId.equals(other.statusId))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", title=" + title + ", authorId=" + authorId + ", body=" + body + ", likes="
				+ likes + ", statusId=" + statusId + "]";
	}
}
