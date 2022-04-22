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
	
    private static final String ACTUAL_COMMUNES_CACHE = "actualCommunesCache";
	private static final String SOUS_CLASSE_NAF2008_CACHE = "sousClasseNaf2008Cache";
	private static CacheHelper instance; //CacheHelper is a singleton
    private static Logger logger = LogManager.getLogger(CacheHelper.class);

    private CacheHelper() {
        cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        cacheManager.init();

        createSousClasseNaf2008Cache();
        createActualCommunesCache();
        logger.debug("All caches are initialized");
        // For later : this is the method to close the cacheManager : cacheManager.close() 

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
          .createCache(SOUS_CLASSE_NAF2008_CACHE, CacheConfigurationBuilder
          .newCacheConfigurationBuilder(String.class, SousClasseNAF2008.class,ResourcePoolsBuilder.heap(800)));
	}
	
	public Cache<String, SousClasseNAF2008> getSousClasseNaf2008CacheFromCacheManager() {
		logger.debug("getSousClasseNaf2008CacheFromCacheManager");
        return cacheManager.getCache(SOUS_CLASSE_NAF2008_CACHE, String.class, SousClasseNAF2008.class);
    }
	
	public void cleanSousClasseNaf2008Cache() {
		logger.debug("clean SousClasseNaf2008Cache");
		cacheManager.removeCache(SOUS_CLASSE_NAF2008_CACHE); 
		createSousClasseNaf2008Cache();
	}
	
	
    /*******************************
     * Communes
     **********************************/  
	
	private void createActualCommunesCache() {
		logger.debug("Init cache actualCommunesCache");
		cacheManager
          .createCache(ACTUAL_COMMUNES_CACHE, CacheConfigurationBuilder
          .newCacheConfigurationBuilder(String.class, String.class,ResourcePoolsBuilder.heap(1)));
	}

	public Cache<String, String> getActualCommunesCacheFromCacheManager() {
		logger.debug("getActualCommunesCacheFromCacheManager");
        return cacheManager.getCache(ACTUAL_COMMUNES_CACHE, String.class, String.class);
    }
	
	public void cleanActualCommunesCache() {
		logger.debug("clean actualCommunesCache");
		cacheManager.removeCache(ACTUAL_COMMUNES_CACHE); 
		createActualCommunesCache();
	}
	
	
	
}
