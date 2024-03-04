package com.configuration;

//import java.sql.SQLException;
//
//import javax.sql.DataSource;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement
//public class DatabaseConfig {
//
//    @Bean
//    public DataSource dataSource() {
//        // Configure DataSource with auto-commit disabled
//        DataSource dataSource = DataSourceBuilder.create()
//            .url("jdbc:postgresql://localhost:5432/productService")
//            .username("postgres")
//            .password("Hemant@9719")
//            .build();
//
//        // Disable auto-commit
//        try {
//            dataSource.getConnection().setAutoCommit(false);
//        } catch (SQLException e) {
//            // Handle exception
//        }
//
//        return dataSource;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        // Configure transaction manager
//        return new DataSourceTransactionManager(dataSource());
//    }
//}
