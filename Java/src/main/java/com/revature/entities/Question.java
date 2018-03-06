package com.revature.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

public class Question {
	@Id
	@Column(name = "question_id")
	@SequenceGenerator(name = "101_question_id_seq", sequenceName = "101_question_id_seq")
	@GeneratedValue(generator = "101_question_id_seq", strategy = GenerationType.AUTO)
	private int questionId;

	@Column(name = "quiz_id")
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "quiz_id")
	private Quiz quizId;

	@Column(name = "correct_answer")
	private String correctAnswer;

	@Column(name = "wrong_answer_0")
	private String wrongAnswer1;

	@Column(name = "wrong_answer_1")
	private String wrongAnswer2;

	@Column(name = "wrong_answer_2")
	private String wrongAnswer3;

	public Question() {
		super();
	}

	public Question(int questionId, Quiz quizId, String correctAnswer, String wrongAnswer1, String wrongAnswer2,
			String wrongAnswer3) {
		this.questionId = questionId;
		this.quizId = quizId;
		this.correctAnswer = correctAnswer;
		this.wrongAnswer1 = wrongAnswer1;
		this.wrongAnswer2 = wrongAnswer2;
		this.wrongAnswer3 = wrongAnswer3;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public Quiz getQuizId() {
		return quizId;
	}

	public void setQuizId(Quiz quizId) {
		this.quizId = quizId;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getWrongAnswer1() {
		return wrongAnswer1;
	}

	public void setWrongAnswer1(String wrongAnswer1) {
		this.wrongAnswer1 = wrongAnswer1;
	}

	public String getWrongAnswer2() {
		return wrongAnswer2;
	}

	public void setWrongAnswer2(String wrongAnswer2) {
		this.wrongAnswer2 = wrongAnswer2;
	}

	public String getWrongAnswer3() {
		return wrongAnswer3;
	}

	public void setWrongAnswer3(String wrongAnswer3) {
		this.wrongAnswer3 = wrongAnswer3;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((correctAnswer == null) ? 0 : correctAnswer.hashCode());
		result = prime * result + questionId;
		result = prime * result + ((quizId == null) ? 0 : quizId.hashCode());
		result = prime * result + ((wrongAnswer1 == null) ? 0 : wrongAnswer1.hashCode());
		result = prime * result + ((wrongAnswer2 == null) ? 0 : wrongAnswer2.hashCode());
		result = prime * result + ((wrongAnswer3 == null) ? 0 : wrongAnswer3.hashCode());
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
		Question other = (Question) obj;
		if (correctAnswer == null) {
			if (other.correctAnswer != null)
				return false;
		} else if (!correctAnswer.equals(other.correctAnswer))
			return false;
		if (questionId != other.questionId)
			return false;
		if (quizId == null) {
			if (other.quizId != null)
				return false;
		} else if (!quizId.equals(other.quizId))
			return false;
		if (wrongAnswer1 == null) {
			if (other.wrongAnswer1 != null)
				return false;
		} else if (!wrongAnswer1.equals(other.wrongAnswer1))
			return false;
		if (wrongAnswer2 == null) {
			if (other.wrongAnswer2 != null)
				return false;
		} else if (!wrongAnswer2.equals(other.wrongAnswer2))
			return false;
		if (wrongAnswer3 == null) {
			if (other.wrongAnswer3 != null)
				return false;
		} else if (!wrongAnswer3.equals(other.wrongAnswer3))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", quizId=" + quizId + ", correctAnswer=" + correctAnswer
				+ ", wrongAnswer1=" + wrongAnswer1 + ", wrongAnswer2=" + wrongAnswer2 + ", wrongAnswer3=" + wrongAnswer3
				+ "]";
	}
}
