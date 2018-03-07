package com.revature.launcher;

import org.apache.log4j.Logger;

import com.revature.dao.DAO;
import com.revature.dao.UserDAO;
import com.revature.entities.User;

public class TestLauncher {
	private static Logger log = Logger.getRootLogger();
	private static DAO<User> ud = new UserDAO();

	public static void main(String[] args) {
		log.info(ud.getById(26));
		log.info(ud.getAll());
		User u = new User(0, "Hello", "pass", "yes@gmail.com", "Yes", "Please", null);
		User u1 = ud.getById(26);
		u.setUsername("Testing");
		ud.update(u1);
		ud.delete(u1);
	}
}
