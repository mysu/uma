package dao;

import static org.junit.Assert.*;
import models.uma.Email;
import ninja.jpa.JpaModule;
import ninja.utils.NinjaMode;
import ninja.utils.NinjaPropertiesImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;

public class AbstractDaoTest {
	
	private TestDao testDao;
	private PersistService service;
	
	@Before
	public void setup(){
		NinjaPropertiesImpl ninjaProperties = new NinjaPropertiesImpl(NinjaMode.test);
		Injector injector = Guice.createInjector(new JpaModule(ninjaProperties));
		service = injector.getInstance(PersistService.class);
		service.start();
		
		testDao = injector.getInstance(TestDao.class);
	}
	
	@After
	public void setDown(){
		service.stop();
	}

	@Test
	public void testGetList() {
		assertEquals(0,testDao.getList(0, null).size());
		saveEmail("asdf@asdf.co");
		assertEquals(1,testDao.getList(0, null).size());
		saveEmail("asdf@asdf.co2");
		assertEquals(2,testDao.getList(0, null).size());
		assertEquals(1,testDao.getList(0, 1).size());
		assertEquals(1,testDao.getList(1, 1).size());
	}

	@Test
	public void testGet() {
		saveEmail("asdf@asdf.co");
		Email entity = testDao.get(1);
		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertNotNull(entity.getEmail());
	}

	@Test
	public void testSave() {
		Email entity = new Email();
		entity.setEmail("asdf@asdf.co");
		assertNull(entity.getId());
		entity = testDao.save(entity);
		assertNotNull(entity.getId());
	}
	
	private Email saveEmail(String email){
		Email entity = new Email();
		entity.setEmail(email);
		entity = testDao.save(entity);
		return entity;
	}
	
}
