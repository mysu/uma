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

package repository.uma;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import models.uma.Email;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import services.uma.CachingService;

@RunWith(MockitoJUnitRunner.class)
public class AbstractRepositoryTest {
    
    @InjectMocks
    private TestRepository repository;
    @InjectMocks
    private NoCacheableRepository noCacheRepository;
    
    @Mock
    private CachingService cachingService;

    @SuppressWarnings("unchecked")
    @Test
    public void shouldGetFromCacheService(){
        repository = spy(repository);
        Email email = new Email().setId(1L);
        when(cachingService.get(repository.getType(), 1L)).thenReturn(email);
        when(repository.getFromDB(1L)).thenThrow(IllegalStateException.class);
        Email cachedEmail = repository.getById(1L);
        assertNotNull(cachedEmail);
        verify(cachingService).get(repository.getType(), 1L);
    }

    @Test
    public void shouldGetFromDB(){
        repository = spy(repository);
        Email email = new Email().setId(1L);
        when(repository.getFromDB(1L)).thenReturn(email);
        Email cachedEmail = repository.getById(1L);
        assertNotNull(cachedEmail);
        verify(cachingService).get(repository.getType(), 1L);
        verify(cachingService).put(email, false);
    }
    
    @Test
    public void shouldSaveInDB_and_updateCache(){
        repository = spy(repository);
        Email email = new Email().setId(1L);
        when(repository.persistInDB(email)).thenReturn(email);
        Email saveEmail = repository.save(email);
        assertNotNull(saveEmail);
        verify(cachingService).put(saveEmail, false);
    }
    
    @Test
    public void shouldNotAccessCachingServiceMethods(){
        noCacheRepository.getById(1);
        NoCacheableClass data = new NoCacheableClass();
        noCacheRepository.save(data);
        verifyZeroInteractions(cachingService);
    }
}
