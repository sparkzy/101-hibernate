package com.revature.unit.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * JUnit test suite for 101
 * 
 * @author Bobby McGetrick
 *
 */
@RunWith(Suite.class)
@SuiteClasses({
	FlashcardDaoTest.class,
	SetDaoTest.class
})
public class TestSuite {

}
