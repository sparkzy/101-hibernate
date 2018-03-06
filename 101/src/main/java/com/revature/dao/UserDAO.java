package com.revature.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.entities.User;

public class UserDAO implements DAO<User> {

	@Override
	public User save(User u) {
		Session se = sessUtil.getSession();
		Transaction tx = se.beginTransaction();
		int id = (int) se.save(u);
		log.trace("The generated id is: " + id);
		tx.commit();
		se.close();
		return u;
	}

	@Override
	public User getById(int id) {
		Session se = sessUtil.getSession();
		User u = (User) se.get(User.class, id);
		se.close();
		return u;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		Session se = sessUtil.getSession();
		Criteria c = se.createCriteria(User.class);
		List<User> users = c.list();
		se.close();
		return users;
	}

	@Override
	public User update(User u) {
		Session se = sessUtil.getSession();
		Transaction tx = se.beginTransaction();
		se.update(u);
		tx.commit();
		se.close();
		return u;
	}

	@Override
	public void delete(User u) {
		Session se = sessUtil.getSession();
		Transaction tx = se.beginTransaction();
		se.delete(u);
		tx.commit();
		se.close();
	}
}
