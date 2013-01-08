package com.nwice.barapp.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

import org.testng.annotations.Test;

import org.testng.Assert;

import com.nwice.barapp.model.BarappUser;
import com.nwice.barapp.model.Cashbox;
import com.nwice.barapp.model.Cashout;
import com.nwice.barapp.model.Drawer;
import com.nwice.barapp.model.Drop;
import com.nwice.barapp.model.Overring;
import com.nwice.barapp.model.Payout;
import com.nwice.barapp.model.Shift;
import com.nwice.barapp.model.ShiftWorker;
import com.nwice.barapp.model.Shortage;

@ContextConfiguration("classpath:/testconf/test-root-context.xml")
public class BarappModelTest extends AbstractTransactionalTestNGSpringContextTests {

	public static Logger log = Logger.getLogger(BarappModelTest.class);
	
    @Autowired
    private ApplicationContext applicationContext;
			
	@Test
	public void testContextExists() {
		log.info("testContextExists");
		log.info(Arrays.deepToString(applicationContext.getBeanDefinitionNames()));
		Assert.assertNotNull(applicationContext);
	}	
	
	@Test
	public void testDataSourceBean() {
		log.info("testDataSourceBean");
		log.info(applicationContext.getBean("dataSource").getClass().getName());
		Assert.assertNotNull(applicationContext.getBean("dataSource"));		
		
		DataSource ds = (DataSource)applicationContext.getBean("dataSource");
		
		try {
			Connection conn = ds.getConnection();
			/* HS-SQL specific */
			ResultSet rs = conn.getMetaData().getTables(null, null, "TBL_%", null);
			while (rs.next()) {
				log.info("table:" + rs.getString(3));				
				ResultSet rs2 = conn.getMetaData().getColumns(null, null, rs.getString(3), null);
				while (rs2.next()) {
					log.info("     column:" + rs2.getString(4));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testSessionFactoryBean() {
		log.info("testSessionFactoryBean");
		log.info(applicationContext.getBean("sessionFactory").getClass().getName());
		Assert.assertNotNull(applicationContext.getBean("sessionFactory"));		
	}
	
	@Test
	public void testCashout() {
		log.info("testCashout");
		log.info(applicationContext.getBean("simpleTestRepo").getClass().getName());
		Assert.assertNotNull(applicationContext.getBean("simpleTestRepo"));
		
		SimpleTestRepo repo = (SimpleTestRepo)applicationContext.getBean("simpleTestRepo");
		
		BarappUser bu = new BarappUser();				
		BarappUser bu2 = new BarappUser();
		
		Cashout cashout = new Cashout();
				
		cashout.setCashbox(new Cashbox());
		cashout.setDrawer(new Drawer());
		cashout.setDrop(new Drop());
		
		cashout.setStartCashbox(new Cashbox());
		cashout.setStartDrawer(new Drawer());
		
		cashout.getShortages().add(new Shortage());
		cashout.getOverrings().add(new Overring());
		cashout.getPayouts().add(new Payout());
		
		cashout.setShift(new Shift());
		
		ShiftWorker sw = new ShiftWorker();
		sw.setBarappUser(bu);
		
		ShiftWorker sw2 = new ShiftWorker();
		sw2.setBarappUser(bu2);

		cashout.getShift().getShiftWorkers().add(sw);
		cashout.getShift().getShiftWorkers().add(sw2);
		
		bu.getShiftWorkers().add(sw);
		bu2.getShiftWorkers().add(sw2);
		
		repo.getSession().save(bu);
		repo.getSession().save(bu2);
		repo.getSession().save(cashout);

		log.info("Cashout size:" + repo.getSession().createCriteria(Cashout.class).list().size());
		log.info("Shift size:" + repo.getSession().createCriteria(Shift.class).list().size());				
		
		Shift lookup = (Shift)repo.getSession().createCriteria(Shift.class).add(Restrictions.eq("shiftId", cashout.getShift().getShiftId())).uniqueResult();		
		Assert.assertNotNull(lookup);		
		
		log.info("lookup shift id:" + lookup.getShiftId().toString());
		Assert.assertTrue(lookup.getShiftId().intValue() == 1);
		
		log.info("lookup shift workers size?:" + lookup.getShiftWorkers().size());
		Assert.assertTrue(lookup.getShiftWorkers().size() == 2);
		
		for (ShiftWorker lookupsw : lookup.getShiftWorkers()) {
			log.info("got shiftworker" + lookupsw);
			log.info("lookup shift worker id:" + lookupsw.getShiftWorkerId().toString());
			log.info("lookup shift worker user id:" + lookupsw.getBarappUser().getBarappUserId().toString());
			
			
			BarappUser lookup2 = (BarappUser)repo.getSession().createCriteria(BarappUser.class).add(Restrictions.eq("barappUserId", lookupsw.getBarappUser().getBarappUserId())).uniqueResult();
			log.info("lookup2 barapp_user id:" + lookup2.getBarappUserId().toString());
			log.info("lookup2 barapp_user shift workers:" + lookup2.getShiftWorkers().size());
			
			Assert.assertTrue(lookup2.getShiftWorkers().size() == 1);
		}
				
		log.info("Cashbox size:" + repo.getSession().createCriteria(Cashbox.class).list().size());
		Assert.assertTrue(repo.getSession().createCriteria(Cashbox.class).list().size() == 2);
		log.info("Drawer size:" + repo.getSession().createCriteria(Drawer.class).list().size());
		Assert.assertTrue(repo.getSession().createCriteria(Drawer.class).list().size() == 2);
		log.info("Drop size:" + repo.getSession().createCriteria(Drop.class).list().size());
		Assert.assertTrue(repo.getSession().createCriteria(Drop.class).list().size() == 1);
		
		log.info("Shortage size:" + repo.getSession().createCriteria(Shortage.class).list().size());
		Assert.assertTrue(repo.getSession().createCriteria(Shortage.class).list().size() == 1);
		log.info("Overring size:" + repo.getSession().createCriteria(Overring.class).list().size());
		Assert.assertTrue(repo.getSession().createCriteria(Overring.class).list().size() == 1);
		log.info("Payout size:" + repo.getSession().createCriteria(Payout.class).list().size());
		Assert.assertTrue(repo.getSession().createCriteria(Payout.class).list().size() == 1);
		
		
		
	}
	
	
		
}

