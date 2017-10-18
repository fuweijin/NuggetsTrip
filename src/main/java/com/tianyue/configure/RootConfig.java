package com.tianyue.configure;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.context.WebApplicationContext;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;

/**
 * Created by FuWeiJin on 2017/9/16 15:45
 */

@Configurable
@ComponentScan(basePackages = {"com.tianyue.web"})
public class RootConfig {

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dmDataSource = new DriverManagerDataSource();
        dmDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dmDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/fwjtest?zeroDateTimeBehavior=round");
        System.out.println("dataSource doing");
        dmDataSource.setUsername("root");
        dmDataSource.setPassword("");
        return dmDataSource;
    }

    @Bean(name = "sqlSessionFactoryBean")
    public SqlSessionFactoryBean sqlSessionFactoryBean(WebApplicationContext context, DataSource dataSource) throws PropertyVetoException, IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(context.getResources("classpath:com/tianyue/web/mapper/*.xml"));
        return sqlSessionFactoryBean;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.tianyue.web.dao");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        return mapperScannerConfigurer;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
}
