package com.revature.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.entities.Subject;
import com.revature.util.SessionUtil;

/**
* Subject DAO for 101
* 
* @author Javian Butler
*
*/

public class SubjectDao implements DAO<Subject> {
	private SessionUtil su = SessionUtil.getSessionUtil();

	@Override
	public Subject save(Subject sub) {
		Session se = su.getSession();
		Transaction tx = se.beginTransaction();
		int id = (int) se.save(sub); 
		tx.commit();
		se.close();
		return sub;
		
		
	}

	@Override
	public Subject getById(int id) {
		Session se = su.getSession();
		Subject sub = (Subject) se.get(Subject.class, id);
		se.close();
		return sub;
	}

	@Override
	public List<Subject> getAll() {
		Session se = su.getSession();
		Criteria c = se.createCriteria(Subject.class);
		List<Subject> sub = c.list();
		se.close();
		return sub;
	}

	@Override
	public Subject update(Subject sub) {
		Session se = su.getSession();
		Transaction tx = se.beginTransaction();
		se.update(sub); 
		tx.commit();
		se.close();
		
		return sub;
	}

	@Override
	public void delete(Subject sub) {
		Session se = su.getSession();
		Transaction tx = se.beginTransaction();
		se.delete(sub); 
		tx.commit();
		se.close();
		
	}

}
