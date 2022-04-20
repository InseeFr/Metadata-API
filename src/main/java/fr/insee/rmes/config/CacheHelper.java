package fr.insee.rmes.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import fr.insee.rmes.modeles.classification.naf2008.SousClasseNAF2008;

public final class CacheHelper {
	
    private static CacheHelper instance; //CacheHelper is a singleton
    private static Logger logger = LogManager.getLogger(CacheHelper.class);

    private CacheHelper() {
        cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        cacheManager.init();

        createSousClasseNaf2008Cache();
        createActualCommunesCache();
        logger.debug("All caches are initialized");
    }
    
	private CacheManager cacheManager;

    public static synchronized CacheHelper getInstance(){
    	if(instance == null) {
            instance = new CacheHelper();
        }
        return instance;
    }

    /*******************************
     * Sous classe NAF 2008
     **********************************/  
	private void createSousClasseNaf2008Cache() {
		logger.debug("Init cache sousClasseNaf2008Cache");
		cacheManager
          .createCache("sousClasseNaf2008Cache", CacheConfigurationBuilder
          .newCacheConfigurationBuilder(String.class, SousClasseNAF2008.class,ResourcePoolsBuilder.heap(800)));
	}
	
	public Cache<String, SousClasseNAF2008> getSousClasseNaf2008CacheFromCacheManager() {
		logger.debug("getSousClasseNaf2008CacheFromCacheManager");
        return cacheManager.getCache("sousClasseNaf2008Cache", String.class, SousClasseNAF2008.class);
    }
	
	public void cleanSousClasseNaf2008Cache() {
		logger.debug("clean SousClasseNaf2008Cache");
		cacheManager.removeCache("sousClasseNaf2008Cache"); 
		createSousClasseNaf2008Cache();
	}
	
	
    /*******************************
     * Communes
     **********************************/  
	
	private void createActualCommunesCache() {
		logger.debug("Init cache actualCommunesCache");
		cacheManager
          .createCache("actualCommunesCache", CacheConfigurationBuilder
          .newCacheConfigurationBuilder(String.class, String.class,ResourcePoolsBuilder.heap(1)));
	}

	public Cache<String, String> getActualCommunesCacheFromCacheManager() {
		logger.debug("getActualCommunesCacheFromCacheManager");
        return cacheManager.getCache("actualCommunesCache", String.class, String.class);
    }
	
	public void cleanActualCommunesCache() {
		logger.debug("clean actualCommunesCache");
		cacheManager.removeCache("actualCommunesCache"); 
		createActualCommunesCache();
	}
	
	/*
	private void closeCacheManager() {
			cacheManager.close(); 
	}
*/
	
	
}
