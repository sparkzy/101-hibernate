package com.revature.dao;

import java.util.List;

import com.revature.entities.User;

public interface UserDAO {
	// C
	User save(User u);

	User persist(User u);

	// R
	User getById(int id);

	User loadById(int id);

	List<User> getAll();

	// U
	User update(User u);

	User merge(User u);

	// D
	void delete(User u);
}
