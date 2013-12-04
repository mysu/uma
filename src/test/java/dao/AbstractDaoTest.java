/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
