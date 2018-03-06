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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "flashcard")
public class Flashcard {

	@Id
	@Column(name = "flashcard_id")
	@SequenceGenerator(name = "FLASHCARD_ID_SEQ", sequenceName = "FLASHCARD_ID_SEQ")
	@GeneratedValue(generator = "FLASHCARD_ID_SEQ", strategy = GenerationType.AUTO)
	private int flashcardId;

	@Column(name = "FC_SET_ID")
	private int setId;

	private String question;
	private String answer;

	@Column(name = "AUTHOR_ID")
	private int authorId;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "101_FC_TO_SET", joinColumns = @JoinColumn(name = "FC_SET_ID"), inverseJoinColumns = @JoinColumn(name = "FLASHCARD_ID"))
	private Set<FlashcardSet> fcSet;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "AUTHOR_ID")
	private Set<User> user;

	public Flashcard() {
		super();
	}

	public Flashcard(int flashcardId, int setId, String question, String answer, int authorId, Set<FlashcardSet> fcSet,
			Set<User> user) {
		this.flashcardId = flashcardId;
		this.setId = setId;
		this.question = question;
		this.answer = answer;
		this.authorId = authorId;
		this.fcSet = fcSet;
		this.user = user;
	}

	public int getFlashcardId() {
		return flashcardId;
	}

	public void setFlashcardId(int flashcardId) {
		this.flashcardId = flashcardId;
	}

	public int getSetId() {
		return setId;
	}

	public void setSetId(int setId) {
		this.setId = setId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public Set<FlashcardSet> getFcSet() {
		return fcSet;
	}

	public void setFcSet(Set<FlashcardSet> fcSet) {
		this.fcSet = fcSet;
	}

	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime * result + authorId;
		result = prime * result + ((fcSet == null) ? 0 : fcSet.hashCode());
		result = prime * result + flashcardId;
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		result = prime * result + setId;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Flashcard other = (Flashcard) obj;
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equals(other.answer))
			return false;
		if (authorId != other.authorId)
			return false;
		if (fcSet == null) {
			if (other.fcSet != null)
				return false;
		} else if (!fcSet.equals(other.fcSet))
			return false;
		if (flashcardId != other.flashcardId)
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (setId != other.setId)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Flashcard [flashcardId=" + flashcardId + ", setId=" + setId + ", question=" + question + ", answer="
				+ answer + ", authorId=" + authorId + ", fcSet=" + fcSet + ", user=" + user + "]";
	}
}
