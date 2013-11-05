package dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import models.uma.Email;
import ninja.NinjaDaoTestBase;

import org.junit.Before;
import org.junit.Test;

public class AbstractDaoTest extends NinjaDaoTestBase {
	
	private TestDao testDao;
	
	
	@Before
	public void setup(){
		
		
		testDao = getInstance(TestDao.class);
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
