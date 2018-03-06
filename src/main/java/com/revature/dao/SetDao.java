package com.revature.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.entities.FlashcardSet;

/**
 * Flashcard Set DAO for 101
 * 
 * @author Bobby McGetrick
 *
 */
public class SetDao implements DAO<FlashcardSet> {

	/************************************************************************************
	 * Constructors
	 ************************************************************************************/
	/**
	 * Create new FlashcardDao
	 */
	public SetDao() {
		super();
	}
	
	/************************************************************************************
	 * toString
	 ************************************************************************************/
	/**
	 * Represent FlashcardDao as a String of its fields
	 */
	@Override
    public String toString() {
        return "SetDao []";
    }
	
	/************************************************************************************
	 * Create
	 ************************************************************************************/
	/**
	 * Create a new FlashcardSet in the 101 DB's fc_set table
	 * Creates a new session and transaction then saves the flashcardSet,
	 * commits the transaction, and closes the session
	 * 
	 * @param FlashcardSet newFcSet
	 * 
	 * @return Flashcardset newFcSet
	 */
	@Override
	public FlashcardSet save(FlashcardSet newFcSet) {
		log.info("Getting a new Session");
		Session sess = sessUtil.getSession();
		
		log.info("Begining transation");
		Transaction trans = sess.beginTransaction();
		
		log.info("Saving " + newFcSet + " to the database and getting its new id");
		int id = (int) sess.save(newFcSet);
		log.trace("The generated flashcard id is: " + id);
		
		log.info("Committing transaction " + trans);
		trans.commit();
		
		log.info("CLosing session " + sess);
		sess.close();
		
		log.info("Returning saved Flashcard: " + newFcSet);
		return newFcSet;
	}
	
	/************************************************************************************
	 * Retrieve
	 ************************************************************************************/
	/**
	 * Get a FlashcardSet from the 101 DB's fc_set table
	 * Creates a new session then gets the flashcardSet of a given id,
	 * then closes the session
	 * 
	 * @param int id
	 * 
	 * @return FlashcardSet fcSet
	 */
	@Override
	public FlashcardSet getById(int id) {
		log.info("Getting a new Session");
		Session sess = sessUtil.getSession();
		
		log.info("Getting flashcard with id: " + id);
		FlashcardSet fcSet = (FlashcardSet) sess.get(FlashcardSet.class, id);
		log.trace("Read flashcard: " + fcSet);
		
		log.info("Closing session " + sess);
		sess.close();
		
		log.info("Returning read flashcard: " + fcSet);
		return fcSet;
	}
	
	/**
	 * Get a List of all FlashcardSets from the 101 DB's fc_set table
	 * Creates a new session then lists all flashcardSets,
	 * then closes the session
	 * 
	 * @return List<FlashcardSet> fcSets
	 */
	@Override
	public List<FlashcardSet> getAll() {
		log.info("Getting a new Session");
		Session sess = sessUtil.getSession();
		
		log.info("Creating a new Criteria for FlashcardSets");
		Criteria crit = sess.createCriteria(FlashcardSet.class);
		
		log.info("Reading a list of flashcards");
		List<FlashcardSet> fcSets = crit.list();
		log.trace("Read liast of flashcards: " + fcSets);
		
		log.info("Closing session " + sess);
		sess.close();
		
		log.info("Returning all Flashcards: " + fcSets);
		return fcSets;
	}
	
	/************************************************************************************
	 * Update
	 ************************************************************************************/
	/**
	 * Updates a flashcardSet in the 101 DB's fc_set table
	 * Creates a new session and transaction,
	 * updates a flashcardSet with new values based on the passed flashcardSet,
	 * commit the transaction and close the session
	 * 
	 * @param FlashcardSet updatedFcSet
	 * 
	 * @return FlashcardSet updatedFcSet
	 */
	@Override
	public FlashcardSet update(FlashcardSet updatedFcSet) {
		log.info("Getting a new Session");
		Session sess = sessUtil.getSession();
		
		log.info("Begining transation");
		Transaction trans = sess.beginTransaction();

		log.info("Updating flashcard with id " + updatedFcSet.getFcSetId() + " to " + updatedFcSet);
		sess.update(updatedFcSet);
		log.trace("Updated flashcard " + updatedFcSet + ", it is now persistent");

		log.info("Committing transaction " + trans);
		trans.commit();
		
		log.info("CLosing session " + sess);
		sess.close();
		
		log.info("Returning updated flashcard: " + updatedFcSet);
		return updatedFcSet;
	}
	
	/************************************************************************************
	 * Delete
	 ************************************************************************************/
	/**
	 * Deletes a flashcardSet from the 101 DB's fc_set table
	 * Creates a new session and transaction,
	 * deletes a flashcardSet that matches the values of the passed flashcardSet,
	 * commit the transaction and close the session
	 */
	@Override
	public void delete(FlashcardSet fcSet) {
		log.info("Getting a new Session");
		Session sess = sessUtil.getSession();
		
		log.info("Begining transation");
		Transaction trans = sess.beginTransaction();

		log.info("Deleting flashcard with id " + fcSet.getFcSetId());
		sess.delete(fcSet);
		log.trace("Updated flashcard " + fcSet + ", it is now persistent");

		log.info("Committing transaction " + trans);
		trans.commit();
		
		log.info("CLosing session " + sess);
		sess.close();	
	}

}
