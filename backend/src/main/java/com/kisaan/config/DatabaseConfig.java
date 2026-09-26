package com.kisaan.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.net.URI;

@Configuration
public class DatabaseConfig {

    private static final Logger log = LoggerFactory.getLogger(DatabaseConfig.class);

    @Value("${DATABASE_URL:}")
    private String databaseUrl;

    @Value("${spring.datasource.url:jdbc:h2:file:./data/kisaandb;AUTO_SERVER=TRUE;DB_CLOSE_DELAY=-1}")
    private String configuredUrl;

    @Value("${spring.datasource.username:sa}")
    private String username;

    @Value("${spring.datasource.password:}")
    private String password;

    @Value("${spring.datasource.driverClassName:org.h2.Driver}")
    private String driverClassName;

    @Bean
    @Primary
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();

        // 1. If Render's DATABASE_URL is present (postgres://user:pass@host:port/dbname)
        if (databaseUrl != null && !databaseUrl.trim().isEmpty() &&
                (databaseUrl.startsWith("postgres://") || databaseUrl.startsWith("postgresql://"))) {
            try {
                log.info("Detected PostgreSQL DATABASE_URL. Configuring PostgreSQL datasource...");
                URI uri = new URI(databaseUrl);
                String userInfo = uri.getUserInfo();
                String dbUser = "";
                String dbPassword = "";
                if (userInfo != null && userInfo.contains(":")) {
                    String[] parts = userInfo.split(":", 2);
                    dbUser = parts[0];
                    dbPassword = parts[1];
                }
                int port = uri.getPort() != -1 ? uri.getPort() : 5432;
                String path = uri.getPath();
                String jdbcUrl = "jdbc:postgresql://" + uri.getHost() + ":" + port + path;

                config.setJdbcUrl(jdbcUrl);
                config.setUsername(dbUser);
                config.setPassword(dbPassword);
                config.setDriverClassName("org.postgresql.Driver");
                config.setMaximumPoolSize(5);
                config.setMinimumIdle(1);
                config.setIdleTimeout(30000);
                config.setMaxLifetime(60000);
                log.info("Successfully configured PostgreSQL datasource: {}", jdbcUrl);
                return new HikariDataSource(config);
            } catch (Exception e) {
                log.error("Failed to parse DATABASE_URL, falling back to default datasource: {}", e.getMessage());
            }
        }

        // 2. Standard Spring Datasource (Local PostgreSQL or persistent H2 fallback)
        log.info("Configuring default datasource: {}", configuredUrl);
        config.setJdbcUrl(configuredUrl);
        config.setUsername(username);
        config.setPassword(password);
        if (driverClassName != null && !driverClassName.trim().isEmpty()) {
            config.setDriverClassName(driverClassName);
        }
        config.setMaximumPoolSize(5);
        return new HikariDataSource(config);
    }
}
