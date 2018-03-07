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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "quiz")
public class Quiz {
	@Id
	@Column(name = "quiz_id")
	@SequenceGenerator(name = "quiz_id_seq", sequenceName = "quiz_id_seq")
	@GeneratedValue(generator = "quiz_id_seq", strategy = GenerationType.AUTO)
	private int quizId;

	@Column(name = "subject_id")
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "subject_id")
	private Set<Subject> subjectId;

	private int likes;
	private String title;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User authorId;

	public Quiz() {
		super();
	}

	public Quiz(int quizId, Set<Subject> subjectId, int likes, String title, User authorId) {
		this.quizId = quizId;
		this.subjectId = subjectId;
		this.likes = likes;
		this.title = title;
		this.authorId = authorId;
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public Set<Subject> getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Set<Subject> subjectId) {
		this.subjectId = subjectId;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getAuthorId() {
		return authorId;
	}

	public void setAuthorId(User authorId) {
		this.authorId = authorId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorId == null) ? 0 : authorId.hashCode());
		result = prime * result + likes;
		result = prime * result + quizId;
		result = prime * result + ((subjectId == null) ? 0 : subjectId.hashCode());
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
		Quiz other = (Quiz) obj;
		if (authorId == null) {
			if (other.authorId != null)
				return false;
		} else if (!authorId.equals(other.authorId))
			return false;
		if (likes != other.likes)
			return false;
		if (quizId != other.quizId)
			return false;
		if (subjectId == null) {
			if (other.subjectId != null)
				return false;
		} else if (!subjectId.equals(other.subjectId))
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
		return "Quiz [quizId=" + quizId + ", subjectId=" + subjectId + ", likes=" + likes + ", title=" + title
				+ ", authorId=" + authorId + "]";
	}
}
