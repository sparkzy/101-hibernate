package com.revature.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "101_fc_set")
public class FlashcardSet {

	@Id
	@Column(name = "fc_set_id")
	@SequenceGenerator(name = "fc_set_id_seq", sequenceName = "fc_set_id_seq")
	@GeneratedValue(generator = "fc_set_id_seq", strategy = GenerationType.AUTO)
	private int fcSetId;

	private int title;

	@Column(name = "subject_id")
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "subject_id")
	private Subject subjectId;

	@Column(name = "author_id")
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private Subject authorId;

	private int likes;

	public FlashcardSet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FlashcardSet(int fcSetId, int title, Subject subjectId, Subject authorId, int likes) {
		this.fcSetId = fcSetId;
		this.title = title;
		this.subjectId = subjectId;
		this.authorId = authorId;
		this.likes = likes;
	}

	public int getFcSetId() {
		return fcSetId;
	}

	public void setFcSetId(int fcSetId) {
		this.fcSetId = fcSetId;
	}

	public int getTitle() {
		return title;
	}

	public void setTitle(int title) {
		this.title = title;
	}

	public Subject getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Subject subjectId) {
		this.subjectId = subjectId;
	}

	public Subject getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Subject authorId) {
		this.authorId = authorId;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorId == null) ? 0 : authorId.hashCode());
		result = prime * result + fcSetId;
		result = prime * result + likes;
		result = prime * result + ((subjectId == null) ? 0 : subjectId.hashCode());
		result = prime * result + title;
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
		FlashcardSet other = (FlashcardSet) obj;
		if (authorId == null) {
			if (other.authorId != null)
				return false;
		} else if (!authorId.equals(other.authorId))
			return false;
		if (fcSetId != other.fcSetId)
			return false;
		if (likes != other.likes)
			return false;
		if (subjectId == null) {
			if (other.subjectId != null)
				return false;
		} else if (!subjectId.equals(other.subjectId))
			return false;
		if (title != other.title)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FlashcardSet [fcSetId=" + fcSetId + ", title=" + title + ", subjectId=" + subjectId + ", authorId="
				+ authorId + ", likes=" + likes + "]";
	}
}
