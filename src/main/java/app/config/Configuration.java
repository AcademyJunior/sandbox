package app.config;

import org.flywaydb.core.Flyway;
import org.jooq.ConnectionProvider;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import static org.jooq.SQLDialect.MYSQL;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean(initMethod = "migrate")
    Flyway flyway() {
        Flyway flyway = new Flyway();
        flyway.setBaselineOnMigrate(true);
        flyway.setDataSource(dataSource());
        flyway.setSchemas("SPRING_SANDBOX");
        return flyway;
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway(DataSource administratorDataSource) {
        Flyway flyway = new Flyway();
        flyway.setDataSource(administratorDataSource);

        return flyway;
    }

    @Bean
    @DependsOn("flyway")
    EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource());
        return bean.getObject();
    }

    @Bean
    DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .username("root")
                .password("root")
                .url("jdbc:mysql://localhost:3312/SPRING_SANDBOX")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("PUT", "DELETE", "GET", "POST");
            }
        };
    }

    @Bean
    public DataSourceConnectionProvider connectionProvider(DataSource dataSource) {
        return new DataSourceConnectionProvider(new TransactionAwareDataSourceProxy(dataSource));
    }

    @Bean
    public DefaultDSLContext dsl(ConnectionProvider connectionProvider) {
        return new DefaultDSLContext(connectionProvider, MYSQL);
    }
}


