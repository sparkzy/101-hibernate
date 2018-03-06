package com.revature.dao;
import com.revature.entities.Status;
import com.revature.util.SessionUtil;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
* Status DAO for 101
* 
* @author Javian Butler
*
*/

public class StatusDao implements DAO<Status>{
	
	private SessionUtil su = SessionUtil.getSessionUtil();

	@Override
	public Status save(Status status) { 
		Session se = su.getSession();
		Transaction tx = se.beginTransaction();
		int id = (int) se.save(status); 
		tx.commit();
		se.close();
		return status;
	}

	@Override
	public Status getById(int id) {
		Session se = su.getSession();
		Status s= (Status) se.get(Status.class, id);
		se.close();
		return s;
		
	}

	@Override
	public List<Status> getAll() {
		Session se = su.getSession();
		Criteria c = se.createCriteria(Status.class);
		List<Status> s = c.list();
		se.close();
		return s;
		
	}

	@Override
	public Status update(Status s) {
		Session se = su.getSession();
		Transaction tx = se.beginTransaction();
		se.update(s); 
		tx.commit();
		se.close();
		return s;
		
	}

	@Override
	public void delete(Status s) {
		Session se = su.getSession();
		Transaction tx = se.beginTransaction();
		se.delete(s); 
		tx.commit();
		se.close();
		
	}

}
