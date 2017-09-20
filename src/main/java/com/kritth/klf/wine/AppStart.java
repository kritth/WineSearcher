package com.kritth.klf.wine;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class AppStart extends SpringBootServletInitializer {
	@Value("${server.jetty.context-path}")
	private String jettyContextPath;
	
	@Value("${server.jetty.port}")
	private int jettyPort;
	
	@Value("${browser.auto-start}")
	private boolean autoStart;
	
	@Autowired
	private DataSource dataSource;
	
	/**
	 * Main class
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(AppStart.class, args);
	}
	
	/**
	 * Create session factory
	 * 
	 * @return session factory object
	 */
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan(new String[] { "com.kritth.klf.wine.model" });
		return sessionFactory;
	}
	
	/**
	 * Manual embedded servlet initialization bean
	 * 
	 * @return embedded servlet for jetty
	 */
	@Bean
	public JettyEmbeddedServletContainerFactory jettyEmbeddedServletContainerFactory() {
		JettyEmbeddedServletContainerFactory container = new JettyEmbeddedServletContainerFactory();
		container.setPort(jettyPort);
		container.setContextPath(jettyContextPath);
		return container;
	}
	
	/**
	 * Launch in browser when it's ready
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void launcherInBrowser() {
		if (autoStart) {
			String url = "http://localhost:" + jettyPort +  jettyContextPath + "/index.html";
			if (Desktop.isDesktopSupported()) {
				try {
					Desktop.getDesktop().browse(new URI(url));
				} catch (URISyntaxException | IOException e) {
					e.printStackTrace();
				}
			} else {
				Runtime runtime = Runtime.getRuntime();
				try {
					runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
