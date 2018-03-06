package com.revature.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.entities.Question;
import com.revature.util.SessionUtil;

///**
// * Question DAO for 101
// *  
// * @author @author Ahmed Dalaq
// *
// */

public class QuestionDao implements DAO<Question> {

	/************************************************************************************
	 * Fields
	 ************************************************************************************/
	Logger log = Logger.getRootLogger();
	SessionUtil sessUtil = SessionUtil.getSessionUtil();

	/************************************************************************************
	 * Constructors
	 ************************************************************************************/
	/**
	 * Create new QuestionDao
	 */

	public QuestionDao() {
		super();
		log.info("Cunstructed new" + this.getClass());
	}

	/************************************************************************************
	 * toString
	 ************************************************************************************/
	/**
	 * Represent QuestionDao as a String of its fields
	 */

	@Override
	public String toString() {
		return "QuestionDao []";
	}

	// /************************************************************************************
	// * Create
	// ************************************************************************************/
	// /**
	// *
	// */

	@Override
	public Question save(Question newQuestion) {
		log.info("Getting a new Session");
		Session sess = sessUtil.getSession();
		log.info("Begining transation");
		Transaction tx = sess.beginTransaction();
		log.info("Saving " + newQuestion );
		int id = (int) sess.save(newQuestion);
		log.trace("The generated id is: " + id);
		log.info("Committing transaction " + tx);
		tx.commit();
		log.info("Closing session " + sess);
		sess.close();
		log.info("Returning saved Question: " + newQuestion);
		return newQuestion;
	}

	/************************************************************************************
	 * Retrieve
	 ************************************************************************************/

	@Override
	public Question getById(int id) {
		log.info("Getting a new Session");
		Session sess = sessUtil.getSession();
		log.info("Getting Question with id: " + id);
		Question q = (Question) sess.get(Question.class, id);
		log.trace("Read question: " + q);
		
		log.info("Closing session " + sess);
		sess.close();
		
		log.info("Returning read question: " + q);
		return q;
	}

	@Override
	public List<Question> getAll() {
		log.info("Getting a new Session");
		Session sess = sessUtil.getSession();
		log.info("Creating a new Criteria for Question");
		Criteria c = sess.createCriteria(Question.class);
		log.info("Reading a list of question");
		log.trace("Read liast of Question: " + c);
		List<Question> questions = c.list();
		log.info("Closing session " + sess);
		sess.close();
		return questions;
	}

	/************************************************************************************
	 * Update
	 ************************************************************************************/

	@Override
	public Question update(Question question) {
		log.info("Getting a new Session");
		Session sess = sessUtil.getSession();
		log.info("Begining transation");
		Transaction tx = sess.beginTransaction();
		log.info("Updating question with id " + question.getQuestionId() + " to " + question);
		sess.update(question); // question is now a persistent object
		log.trace("Updated question " + question + ", it is now persistent");
		log.info("Committing transaction " + tx);
		tx.commit();
		
		log.info("CLosing session " + sess);
		sess.close();
		log.info("done");
		return question;
	}

	/************************************************************************************
	 * Delete
	 ************************************************************************************/

	@Override
	public void delete(Question question) {
		log.info("Getting a new Session");
		Session sess = sessUtil.getSession();
		log.info("Begining transation");
		Transaction tx = sess.beginTransaction();
		log.info("Deleting question with id " + question.getQuestionId());
		sess.delete(question); // question is now a persistent object
		log.info("Committing transaction " + tx);
		tx.commit();
		log.info("Closing session " + sess);
		sess.close();

	}

}
