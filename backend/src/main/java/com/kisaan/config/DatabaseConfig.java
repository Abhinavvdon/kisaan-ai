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
import java.sql.Connection;

@Configuration
public class DatabaseConfig {

    private static final Logger log = LoggerFactory.getLogger(DatabaseConfig.class);

    @Value("${DATABASE_URL:}")
    private String databaseUrl;

    @Value("${spring.datasource.url:}")
    private String configuredUrl;

    @Value("${spring.datasource.username:postgres}")
    private String username;

    @Value("${spring.datasource.password:Abhinav12##@}")
    private String password;

    @Value("${spring.datasource.driverClassName:org.postgresql.Driver}")
    private String driverClassName;

    @Bean
    @Primary
    public DataSource dataSource() {
        // 1. Check if Render's DATABASE_URL environment variable is provided
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

                HikariConfig config = new HikariConfig();
                config.setJdbcUrl(jdbcUrl);
                config.setUsername(dbUser);
                config.setPassword(dbPassword);
                config.setDriverClassName("org.postgresql.Driver");
                config.setMaximumPoolSize(5);
                config.setMinimumIdle(1);
                config.setConnectionTimeout(10000);
                config.setIdleTimeout(30000);
                config.setMaxLifetime(60000);

                HikariDataSource ds = new HikariDataSource(config);
                try (Connection conn = ds.getConnection()) {
                    log.info("Successfully connected to Render PostgreSQL: {}", jdbcUrl);
                    return ds;
                }
            } catch (Exception e) {
                log.error("Failed to connect via DATABASE_URL: {}. Attempting fallback.", e.getMessage());
            }
        }

        // 2. Try configuredUrl (Local PostgreSQL or specified URL)
        if (configuredUrl != null && !configuredUrl.trim().isEmpty() && configuredUrl.contains("postgresql")) {
            try {
                log.info("Attempting connection to configured PostgreSQL: {}", configuredUrl);
                HikariConfig config = new HikariConfig();
                config.setJdbcUrl(configuredUrl);
                config.setUsername(username);
                config.setPassword(password);
                config.setDriverClassName(driverClassName);
                config.setMaximumPoolSize(5);
                config.setConnectionTimeout(3000); // 3 sec quick check

                HikariDataSource ds = new HikariDataSource(config);
                try (Connection conn = ds.getConnection()) {
                    log.info("Successfully connected to PostgreSQL at {}", configuredUrl);
                    return ds;
                }
            } catch (Exception e) {
                log.warn("PostgreSQL at {} is unreachable ({}). Activating embedded persistent database fallback.", configuredUrl, e.getMessage());
            }
        }

        // 3. Resilient Embedded H2 Fallback (PostgreSQL compatibility mode)
        // Ensures the application NEVER crashes on Render if external database is unavailable
        log.info("Starting with embedded persistent H2 database in PostgreSQL compatibility mode (/app/data/kisaandb)...");
        HikariConfig h2Config = new HikariConfig();
        h2Config.setJdbcUrl("jdbc:h2:file:./data/kisaandb;MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE;DEFAULT_NULL_ORDERING=HIGH;DB_CLOSE_DELAY=-1");
        h2Config.setDriverClassName("org.h2.Driver");
        h2Config.setUsername("sa");
        h2Config.setPassword("");
        h2Config.setMaximumPoolSize(5);
        return new HikariDataSource(h2Config);
    }
}
