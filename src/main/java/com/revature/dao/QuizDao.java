package com.revature.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.entities.Quiz;

/**
 * Quiz DAO for 101
 * 
 * @author @author Ahmed Dalaq
 *
 */

public class QuizDao implements DAO<Quiz> {


	/************************************************************************************
	 * Constructors
	 ************************************************************************************/
	/**
	 * Create new Quizao
	 */

	public QuizDao() {
		super();

	}

	/************************************************************************************
	 * toString
	 ************************************************************************************/
	/**
	 * Represent QuizDao as a String of its fields
	 */

	@Override
	public String toString() {
		return "QuizDao []";
	}

	/************************************************************************************
	 * Create
	 ************************************************************************************/
	/**
	*
	*/

	@Override
	public Quiz save(Quiz newQuiz) {
		log.info("Getting a new Session");
		Session sess = sessUtil.getSession();
		log.info("Begining transation");
		Transaction tx = sess.beginTransaction();
		log.info("Saving " + newQuiz);
		int id = (int) sess.save(newQuiz);
		log.trace("The generated id is: " + id);
		log.info("Committing transaction " + tx);
		tx.commit();
		log.info("Closing session " + sess);
		sess.close();
		log.info("Returning saved quiz: " + newQuiz);
		return newQuiz;
	}

	/************************************************************************************
	 * Retrieve
	 ************************************************************************************/

	@Override
	public Quiz getById(int quizId) {
		log.info("Getting a new Session");
		Session sess = sessUtil.getSession();
		log.info("Getting Quiz with id: " + quizId);
		Quiz q = (Quiz) sess.get(Quiz.class, quizId);
		log.trace("Read quiz: " + q);
		
		log.info("Closing session " + sess);
		sess.close();
		
		log.info("Returning read quiz: " + q);
		return q;
	}

	@Override
	public List<Quiz> getAll() {
		log.info("Getting a new Session");
		Session sess = sessUtil.getSession();
		log.info("Creating a new Criteria for Quiz");
		Criteria c = sess.createCriteria(Quiz.class);
		log.info("Reading a list of quiz");
		log.trace("Read liast of Quiz: " + c);
		List<Quiz> quiz = c.list();
		log.info("Closing session " + sess);
		sess.close();
		return quiz;
	}
	
	/************************************************************************************
	 * Update
	 ************************************************************************************/

	@Override
	public Quiz update(Quiz quizUpdate) {
		log.info("Getting a new Session");
		Session sess = sessUtil.getSession();
		log.info("Begining transation");
		Transaction tx = sess.beginTransaction();
		log.info("Updating quiz with id " + quizUpdate.getQuizId() + " to " + quizUpdate);
		sess.update(quizUpdate); // quiz is now a persistent object
		log.trace("Updated quiz " + quizUpdate + ", it is now persistent");
		log.info("Committing transaction " + tx);
		tx.commit();
		
		log.info("Closing session " + sess);
		sess.close();
		log.info("done");
		return quizUpdate;
	}
	/************************************************************************************
	 * Delete
	 ************************************************************************************/

	@Override
	public void delete(Quiz quizDelete) {
		log.info("Getting a new Session");
		Session sess = sessUtil.getSession();
		log.info("Begining transation");
		Transaction tx = sess.beginTransaction();
		log.info("Deleting quiz with id " + quizDelete.getQuizId());
		sess.delete(quizDelete); // quiz is now a persistent object
		log.info("Committing transaction " + tx);
		tx.commit();
		log.info("Closing session " + sess);
		sess.close();

	}

}
