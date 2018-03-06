package com.revature.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.entities.Flashcard;
import com.revature.util.SessionUtil;

/**
 * Flashcard DAO for 101
 * 
 * @author Bobby McGetrick
 *
 */
public class FlashcardDao implements DAO<Flashcard> {

	/************************************************************************************
	 * Constructors
	 ************************************************************************************/
	/**
	 * Create new FlashcardDao
	 */
	public FlashcardDao() {
		super();
		log.info("Constructed new " + this.getClass());
	}
	
	/************************************************************************************
	 * toString
	 ************************************************************************************/
	/**
	 * Represent FlashcardDao as a String of its fields
	 */
	@Override
    public String toString() {
        return "FlashcardDao []";
    }
	
	/************************************************************************************
	 * Create
	 ************************************************************************************/
	/**
	 * Create a new Flashcard in the 101 DB's flashcard table
	 * Creates a new session and transaction then saves the flashcard,
	 * commits the transaction, and closes the session
	 * 
	 * @param Flashcard newFlashcard
	 * 
	 * @return Flashcard newFlashcard
	 */
	@Override
	public Flashcard save(Flashcard newFlashcard) {
		log.info("Getting a new Session");
		Session sess = sessUtil.getSession();
		
		log.info("Begining transation");
		Transaction trans = sess.beginTransaction();
		
		log.info("Saving " + newFlashcard + " to the database and getting its new id");
		int id = (int) sess.save(newFlashcard);
		log.trace("The generated flashcard id is: " + id);
		
		log.info("Committing transaction " + trans);
		trans.commit();
		
		log.info("CLosing session " + sess);
		sess.close();
		
		log.info("Returning saved Flashcard: " + newFlashcard);
		return newFlashcard;
	}

	/************************************************************************************
	 * Retrieve
	 ************************************************************************************/
	/**
	 * Get a Flashcard from the 101 DB's flashcard table
	 * Creates a new session then gets the flashcard of a given id,
	 * then closes the session
	 * 
	 * @param int id
	 * 
	 * @return Flashcard flashcard
	 */
	@Override
	public Flashcard getById(int id) {
		log.info("Getting a new Session");
		Session sess = sessUtil.getSession();
		
		log.info("Getting flashcard with id: " + id);
		Flashcard flashcard = (Flashcard) sess.get(Flashcard.class, id);
		log.trace("Read flashcard: " + flashcard);
		
		log.info("Closing session " + sess);
		sess.close();
		
		log.info("Returning read flashcard: " + flashcard);
		return flashcard;
	}

	/**
	 * Get a List of all Flashcards from the 101 DB's flashcard table
	 * Creates a new session then lists all flashcards,
	 * then closes the session
	 * 
	 * @return List<Flashcard> flashcards
	 */
	@Override
	public List<Flashcard> getAll() {
		log.info("Getting a new Session");
		Session sess = sessUtil.getSession();
		
		log.info("Creating a new Criteria for Flashcards");
		Criteria crit = sess.createCriteria(Flashcard.class);
		
		log.info("Reading a list of flashcards");
		List<Flashcard> flashcards = crit.list();
		log.trace("Read liast of flashcards: " + flashcards);
		
		log.info("Closing session " + sess);
		sess.close();
		
		log.info("Returning all Flashcards: " + flashcards);
		return flashcards;
	}

	/************************************************************************************
	 * Update
	 ************************************************************************************/
	/**
	 * Updates a flashcard in the 101 DB's flashcard table
	 * Creates a new session and transaction,
	 * updates a flashcard with new values based on the passed flashcard,
	 * commit the transaction and close the session
	 * 
	 * @param Flashcard updatedFC
	 * 
	 * @return Flashcard updatedFC
	 */
	@Override
	public Flashcard update(Flashcard updatedFC) {
		log.info("Getting a new Session");
		Session sess = sessUtil.getSession();
		
		log.info("Begining transation");
		Transaction trans = sess.beginTransaction();

		log.info("Updating flashcard with id " + updatedFC.getFlashcardId() + " to " + updatedFC);
		sess.update(updatedFC);
		log.trace("Updated flashcard " + updatedFC + ", it is now persistent");

		log.info("Committing transaction " + trans);
		trans.commit();
		
		log.info("CLosing session " + sess);
		sess.close();
		
		log.info("Returning updated flashcard: " + updatedFC);
		return updatedFC;
	}

	/************************************************************************************
	 * Delete
	 ************************************************************************************/
	/**
	 * Deletes a flashcard from the 101 DB's flashcard table
	 * Creates a new session and transaction,
	 * deletes a flashcard that matches the values of the passed flashcard,
	 * commit the transaction and close the session
	 */
	@Override
	public void delete(Flashcard flashcard) {
		log.info("Getting a new Session");
		Session sess = sessUtil.getSession();
		
		log.info("Begining transation");
		Transaction trans = sess.beginTransaction();

		log.info("Deleting flashcard with id " + flashcard.getFlashcardId());
		sess.delete(flashcard);
		log.trace("Updated flashcard " + flashcard + ", it is now persistent");

		log.info("Committing transaction " + trans);
		trans.commit();
		
		log.info("CLosing session " + sess);
		sess.close();	
	}

}
