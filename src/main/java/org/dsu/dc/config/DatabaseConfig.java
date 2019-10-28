package org.dsu.dc.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan(basePackages={"org.dsu.dc.mapper"})
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class DatabaseConfig {
	@Autowired
	private Environment env;
	
	@Bean
	public HikariDataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(env.getProperty("driver-class-name"));
		hikariConfig.setJdbcUrl(env.getProperty("url"));
		hikariConfig.setUsername(env.getProperty("username"));
		hikariConfig.setPassword(env.getProperty("password"));
		
		return new HikariDataSource(hikariConfig);
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(ApplicationContext appContext) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setMapperLocations(appContext.getResources("classpath:mappers/*.xml"));
		return sessionFactory.getObject();
	}
}
