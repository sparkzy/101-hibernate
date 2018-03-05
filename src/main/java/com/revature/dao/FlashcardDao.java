package com.revature.dao;

import java.util.List;

import com.revature.entities.Flashcard;

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
	@Override
    public String toString() {
        return "Bear [bearId=" + bearId + ", bearColor=" + bearColor + ", breed=" + breed + ", weight=" + weight
                + ", height=" + height + ", dwelling=" + dwelling + ", honeyPot=" + honeyPot + ", cubs=" + cubs + "]";
    }
	
	@Override
	public Flashcard save(Flashcard newFlashcard) {
		// FlashcardODO Auto-generated method stub
		return null;
	}

	@Override
	public Flashcard getById(int id) {
		// FlashcardODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Flashcard> getAll() {
		// FlashcardODO Auto-generated method stub
		return null;
	}

	@Override
	public Flashcard update(Flashcard updatedFC) {
		// FlashcardODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Flashcard flashcard) {
		// FlashcardODO Auto-generated method stub
		
	}

}
