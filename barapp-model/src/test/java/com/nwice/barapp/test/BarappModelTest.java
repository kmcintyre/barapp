package com.nwice.barapp.test;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

import org.testng.annotations.Test;

import org.testng.Assert;

@ContextConfiguration("classpath:/testconf/test-root-context.xml")
public class BarappModelTest extends AbstractTransactionalTestNGSpringContextTests {

	public static Logger log = Logger.getLogger(BarappModelTest.class);
	
    @Autowired
    private ApplicationContext applicationContext;
			
	@Test
	public void testContextExists() {
		log.info("running ContextExists");
		Assert.assertNotNull(applicationContext);
		log.info(applicationContext.getBeanDefinitionNames());
	}	
		
}

