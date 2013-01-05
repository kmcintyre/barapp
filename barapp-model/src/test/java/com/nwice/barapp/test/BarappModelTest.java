package com.nwice.barapp.test;

import org.apache.log4j.Logger;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

import org.testng.annotations.Test;

@ContextConfiguration("classpath:/testconf/test-root-context.xml")
public class BarappModelTest extends AbstractTransactionalTestNGSpringContextTests {

	public static Logger log = Logger.getLogger(BarappModelTest.class);
			
	@Test
	public void testContextExists() {
		log.info("running ContextExists");
	}
	
	@Test
	public void testHibernateContextExists() {
		log.info("running HibernateContextExists");
	}
	
	
		
}

