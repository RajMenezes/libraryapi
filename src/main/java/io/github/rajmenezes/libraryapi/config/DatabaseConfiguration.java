package io.github.rajmenezes.libraryapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String driver;

    // @Bean
    public DataSource dataSource() {
        // Usado para testes rápidos
        // Não recomendado para usar em ambiente de prod
        // Não otimizado para lidar com multiplas conexões
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(this.url);
        ds.setUsername(this.username);
        ds.setPassword(this.password);
        ds.setDriverClassName(this.driver);
        return ds;
    }


    @Bean
    public DataSource hikariDataSource(){
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);

        config.setMaximumPoolSize(10); // libera no máximo 10 conexões
        config.setMinimumIdle(1); // tamanhho inicial do pool
        config.setPoolName("library-db-pool");
        config.setMaxLifetime(600000);
        config.setConnectionTimeout(100000); // tempo máximo para conseguir estabelecer conexão
        config.setConnectionTestQuery("SELECT 1"); // Query de teste para o banco

        return new HikariDataSource(config);
    }


}
