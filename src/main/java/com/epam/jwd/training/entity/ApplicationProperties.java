package com.epam.jwd.training.entity;

public final class ApplicationProperties {

    private final String url;
    private final String username;
    private final String password;
    private final Integer initPoolSize;
    private final Integer maxPoolSize;
    private final Integer incrementalPoolSize;

    public ApplicationProperties(String url, String username, String password, Integer initPoolSize,
                                 Integer maxPoolSize, Integer incrementalPoolSize) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.initPoolSize = initPoolSize;
        this.maxPoolSize = maxPoolSize;
        this.incrementalPoolSize = incrementalPoolSize;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Integer getInitPoolSize() {
        return initPoolSize;
    }

    public Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    public Integer getIncrementalPoolSize() {
        return incrementalPoolSize;
    }
}
