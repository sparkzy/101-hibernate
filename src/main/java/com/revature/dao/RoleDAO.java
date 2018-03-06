package com.revature.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.entities.Role;

public class RoleDAO implements DAO<Role> {

	@Override
	public Role save(Role r) {
		Session se = sessUtil.getSession();
		Transaction tx = se.beginTransaction();
		int id = (int) se.save(r);
		log.trace("The generated id is: " + id);
		tx.commit();
		se.close();
		return r;
	}

	@Override
	public Role getById(int id) {
		Session se = sessUtil.getSession();
		Role r = (Role) se.get(Role.class, id);
		se.close();
		return r;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAll() {
		Session se = sessUtil.getSession();
		Criteria c = se.createCriteria(Role.class);
		List<Role> roles = c.list();
		se.close();
		return roles;
	}

	@Override
	public Role update(Role r) {
		Session se = sessUtil.getSession();
		Transaction tx = se.beginTransaction();
		se.update(r);
		tx.commit();
		se.close();
		return r;
	}

	@Override
	public void delete(Role r) {
		Session se = sessUtil.getSession();
		Transaction tx = se.beginTransaction();
		se.delete(r);
		tx.commit();
		se.close();
	}
}
