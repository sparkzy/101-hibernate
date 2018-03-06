package com.revature.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.util.SessionUtil;

/**
 * Hibernate DAO generic interface
 * @author Bobby McGetrick
 *
 * @param <T>
 */
public interface DAO<T> {
	
	/************************************************************************************
	 * Fields
	 ************************************************************************************/
	Logger log = Logger.getRootLogger();
	SessionUtil sessUtil = SessionUtil.getSessionUtil();
	
	/************************************************************************************
	 * Create
	 ************************************************************************************/
	T save(T newObj);
	
	/************************************************************************************
	 * Retrieve
	 ************************************************************************************/
	T getById(int id);
	List<T> getAll();
	
	/************************************************************************************
	 * Update
	 ************************************************************************************/
	T update(T obj);
	
	/************************************************************************************
	 * Delete
	 ************************************************************************************/
	void delete(T obj);

}