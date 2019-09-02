package org.mybatis.generator.ui.model;

public class DatabaseConfig {
    /**
     * The primary key in the sqlite db
     */
    private Integer id;

    private String name;

    private String host;

    private String port;

    private String schema;

    private String username;

    private String password;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return this.port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getSchema() {
        return this.schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "DatabaseConfig [id=" + this.id + ", name=" + this.name + ", host=" + this.host
                + ", port=" + this.port + ", schema=" + this.schema + ", username=" + this.username
                + ", password=" + this.password + "]";
    }
}
