package services.uma;

import services.uma.impl.NoCachingServiceImpl;
import ninja.utils.NinjaProperties;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import conf.AppProperties;

@Singleton
public class CacheServiceFactory {
    private NinjaProperties ninjaProperties;
    
    @Inject
    public CacheServiceFactory(NinjaProperties ninjaProperties) {
        this.ninjaProperties = ninjaProperties;
    }
    
    public CachingService getCachingService(Injector injector){
        if (ninjaProperties.getBoolean(AppProperties.UMA_CACHE_SERVER_ENABLED.getKey())){
            //TODO make a real implementation
            return injector.getInstance(NoCachingServiceImpl.class);
        }else{
            //no cache
            return injector.getInstance(NoCachingServiceImpl.class);
        }
    }
}
