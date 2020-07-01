package com.hiberus.logistic.configuration;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Class that configures the database connection
 */
@Configuration
@EnableTransactionManagement
@EnableR2dbcRepositories(basePackages = "com.hiberus.logistic.repository")
public class R2DBCConfiguration extends AbstractR2dbcConfiguration {

    @Value("${db.host}")
    private String host;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;
    @Value("${db.port}")
    private int port;
    @Value("${db.database}")
    private String database;

    /**
     * Build the transaction manager
     * @param connectionFactory database connection factory
     * @return transaction manager
     */
    @Bean
    public ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }

    /**
     * Configure the database connection factory
     * @return connection factory
     */
    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
        return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
                .host(host)
                .database(database)
                .username(username)
                .password(password).build());
    }


}
