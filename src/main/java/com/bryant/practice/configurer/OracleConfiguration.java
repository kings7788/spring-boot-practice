//package com.bryant.practice.configurer;
//
//import java.util.Properties;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//
///**
// * 資料庫連線設定 Oracle
// */
//@EnableJpaRepositories(
//        basePackages = "com.bryant.practice.oracle",
//        entityManagerFactoryRef = "oracleEntityManagerFactory",
//        transactionManagerRef = "oracleTransactionManager"
//)
//@Configuration
//public class OracleConfiguration {
//
//    @Value("${spring.jpa.hibernate.naming.implicit-strategy}")
//    private String implicit;
//
//    @Value("${spring.jpa.hibernate.naming.physical-strategy}")
//    private String physical;
//
//    @Value("${spring.jpa.show-sql}")
//    private String showSQL;
//
//    /**
//     * DataSourceProperties for Oracle
//     */
//    @ConfigurationProperties(prefix = "datasource.oracle")
//    @Bean
//    public DataSourceProperties oracleDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    /**
//     * DataSource for Oracle
//     */
//    @Bean
//    public DataSource oracleDataSource() {
//        DataSourceProperties dataSourceProperties = oracleDataSourceProperties();
//        // 判斷是使用 JNDI or URL
//        String jndiName = dataSourceProperties.getJndiName();
//        if(StringUtils.isBlank(jndiName)) {
//            return DataSourceBuilder.create()
//                    .driverClassName(dataSourceProperties.getDriverClassName())
//                    .url(dataSourceProperties.getUrl())
//                    .username(dataSourceProperties.getUsername())
//                    .password(dataSourceProperties.getPassword())
//                    .build();
//        }else {
//            return new JndiDataSourceLookup().getDataSource(jndiName);
//        }
//    }
//
//    /**
//     * EntityManagerFactory for Oracle
//     */
//    @Bean
//    public LocalContainerEntityManagerFactoryBean oracleEntityManagerFactory(
//            EntityManagerFactoryBuilder builder, @Qualifier("oracleDataSource") DataSource dataSource) {
//        // configure JPA properties
//        Properties jpaProperties = new Properties();
//        jpaProperties.put("spring.jpa.hibernate.naming.implicit-strategy", implicit);
//        jpaProperties.put("spring.jpa.hibernate.naming.physical-strategy", physical);
//        jpaProperties.put("spring.jpa.show-sql", showSQL);
//        // create factory
//        LocalContainerEntityManagerFactoryBean factory = builder.dataSource(dataSource)
//                .packages("com.bryant.practice.oracle.model")
//                .persistenceUnit("oracle")
//                .build();
//        factory.setJpaProperties(jpaProperties);
//        return factory;
//    }
//
//    /**
//     * TransactionManager for Oracle
//     */
//    @Bean
//    public PlatformTransactionManager oracleTransactionManager(
//            @Qualifier("oracleEntityManagerFactory") EntityManagerFactory oracleEntityManagerFactory) {
//        return new JpaTransactionManager(oracleEntityManagerFactory);
//    }
//}