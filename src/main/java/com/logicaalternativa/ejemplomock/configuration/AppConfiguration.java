/*
 *      AppConfiguration.java
 *      
 *      Copyright 2014 Miguel Rafael Esteban Martín (www.logicaalternativa.com) <miguel.esteban@logicaalternativa.com>
 *      
 *      This program is free software; you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation; either version 2 of the License, or
 *      (at your option) any later version.
 *      
 *      This program is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *      
 *      You should have received a copy of the GNU General Public License
 *      along with this program; if not, write to the Free Software
 *      Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 *      MA 02110-1301, USA.
 */
package com.logicaalternativa.ejemplomock.configuration;

import java.util.Properties;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.logicaalternativa.ejemplomock")
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(basePackages={"com.logicaalternativa.ejemplomock.repository"})
public class AppConfiguration extends WebMvcConfigurerAdapter implements DisposableBean {
	
	private EmbeddedDatabase embeddedDatabase;
	
	@Autowired
    Environment env;
	
	@Bean(name="hsqlInMemory")
	public EmbeddedDatabase hsqlInMemory() {
		
		if ( this.embeddedDatabase == null ) {
			
			EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
			
			this.embeddedDatabase = builder.setType( EmbeddedDatabaseType.HSQL ).build();
			
		}
		 
		return this.embeddedDatabase;
	         
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){

		LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
	
		lcemfb.setDataSource(this.hsqlInMemory());
		lcemfb.setPackagesToScan(new String[] {"com.logicaalternativa.ejemplomock.model"});
	
		HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
		lcemfb.setJpaVendorAdapter(va);
	
		Properties ps = new Properties();
		ps.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		ps.put("hibernate.hbm2ddl.auto", "create");
		ps.put("hibernate.show_sql", "true");
		lcemfb.setJpaProperties( ps );
		
		return lcemfb;

	}	
	
	@Bean
	public JpaTransactionManager transactionManager(){

		JpaTransactionManager tm = new JpaTransactionManager();	
		tm.setEntityManagerFactory( this.entityManagerFactory().getObject() );
	
		return tm;

	}
	
	@Bean
    public ResourceBundleMessageSource messageSource(){
		ResourceBundleMessageSource messageSource=new ResourceBundleMessageSource();
        String[] basenames= {
        						"com.logicaalternativa.ejemplomock.i18n.validationError",
        						"com.logicaalternativa.ejemplomock.i18n.email"
        					};
        messageSource.setBasenames(basenames);
        return messageSource;
    }
	 
	@Bean( name="javaMailSender" )
	public JavaMailSender mailSender( ) {
		
		final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
		mailSender.setHost( getEnv().getProperty( "mail.sender.host" ) );
		mailSender.setPort( getEnv().getProperty( "mail.sender.port" , Integer.class ) );
		mailSender.setUsername( getEnv().getProperty( "mail.sender.username" ) );
		mailSender.setPassword( getEnv().getProperty( "mail.sender.password" ) );
		
//		final Properties javaMailProperties = new Properties();
//		
//		javaMailProperties.put( "mail.transport.protocol", "smtp");
//		javaMailProperties.put( "mail.smtp.auth", "true");
//		javaMailProperties.put( "mail.smtp.starttls.enable", "true");
//		
//		mailSender.setJavaMailProperties( javaMailProperties );
		
		return mailSender;
		
	}
	
	@Bean // It need to @Value annotation
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
       return new PropertySourcesPlaceholderConfigurer();
    }

	public Environment getEnv() {
		return env;
	}

	public void setEnv(Environment env) {
		this.env = env;
	}

	@Override
	public void destroy() throws Exception {
		
		if ( embeddedDatabase != null ) {
			
			embeddedDatabase.shutdown();
			
		}
		
	}
	
	

}
