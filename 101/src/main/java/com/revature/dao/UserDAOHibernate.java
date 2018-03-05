package com.revature.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.entities.User;
import com.revature.util.SessionUtil;

public class UserDAOHibernate implements UserDAO {
	private Logger log = Logger.getRootLogger();
	private SessionUtil su = SessionUtil.getSessionUtil();

	@Override
	public User save(User u) {
		Session se = su.getSession();
		Transaction tx = se.beginTransaction();
		int id = (int) se.save(u);
		log.trace("The generated id is: " + id);
		tx.commit();
		se.close();
		return u;
	}

	@Override
	public User persist(User u) {
		return null;
	}

	@Override
	public User getById(int id) {
		return null;
	}

	@Override
	public User loadById(int id) {
		return null;
	}

	@Override
	public List<User> getAll() {
		return null;
	}

	@Override
	public User update(User u) {
		return null;
	}

	@Override
	public User merge(User u) {
		return null;
	}

	@Override
	public void delete(User u) {

	}
}
