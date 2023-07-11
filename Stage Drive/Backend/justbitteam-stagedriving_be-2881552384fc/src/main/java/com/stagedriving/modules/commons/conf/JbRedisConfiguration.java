package com.stagedriving.modules.commons.conf;

/**
 * Created by francescopace on 17/02/14.
 */
public class JbRedisConfiguration {

    private boolean useQueryCache;

    private boolean useSecondLevelCache;

    private String cacheRegionPrefix;

    private boolean useStatistics;

    private boolean useStructuredCache;

    private String propertiesFile;

    public boolean isUseQueryCache() {
        return useQueryCache;
    }

    public void setUseQueryCache(boolean useQueryCache) {
        this.useQueryCache = useQueryCache;
    }

    public boolean isUseSecondLevelCache() {
        return useSecondLevelCache;
    }

    public void setUseSecondLevelCache(boolean useSecondLevelCache) {
        this.useSecondLevelCache = useSecondLevelCache;
    }

    public String getCacheRegionPrefix() {
        return cacheRegionPrefix;
    }

    public void setCacheRegionPrefix(String cacheRegionPrefix) {
        this.cacheRegionPrefix = cacheRegionPrefix;
    }

    public boolean isUseStatistics() {
        return useStatistics;
    }

    public void setUseStatistics(boolean useStatistics) {
        this.useStatistics = useStatistics;
    }

    public boolean isUseStructuredCache() {
        return useStructuredCache;
    }

    public void setUseStructuredCache(boolean useStructuredCache) {
        this.useStructuredCache = useStructuredCache;
    }

    public String getPropertiesFile() {
        return propertiesFile;
    }

    public void setPropertiesFile(String propertiesFile) {
        this.propertiesFile = propertiesFile;
    }
}
