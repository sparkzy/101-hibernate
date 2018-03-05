package com.revature.launcher;

import org.apache.log4j.Logger;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOHibernate;
import com.revature.entities.User;
import com.revature.util.SessionUtil;

public class TestLauncher {
	private static Logger log = Logger.getRootLogger();
	private static UserDAO bdh = new UserDAOHibernate();
	private static SessionUtil su = SessionUtil.getSessionUtil();

	public static void main(String[] args) {
		// log.info(bdh.getById(1));
		// log.info(bdh.loadById(1));
		User u = new User(0, "Yes123", "pass", "yes@gmail.com", "Yes", "Please", null);
		log.info(bdh.save(u).toString());
		// log.info(bdh.persist(b).toString());
		// Bear b = bdh.getById(4);
		// bdh.delete(b);
		// b.setWeight(50);
		// b.getHoneyPot().setHoneyAmount(0);
		// b.getCubs().add(bdh.getById(1));
		// b.getCubs().add(bdh.getById(3));
		// bdh.merge(b);
		// b.setWeight(10);
		// b.getHoneyPot().setHoneyAmount(200);
		// bdh.getAll().forEach(bear -> {
		// log.info(bear);
		// });
		// bdh.findByBreedCriteria("winnie").forEach(bear -> {
		// log.info(bear);
		// });
		// bdh.findByBreedHQL("winnie").forEach(bear -> {
		// log.info(bear);
		// });
		// moreHQLDemos();
		// moreCriteriaDemos();
		// namedQuery();
		// l1Cache();

	}
}
