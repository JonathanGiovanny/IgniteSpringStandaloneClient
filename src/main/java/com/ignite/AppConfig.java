package com.ignite;

import org.apache.ignite.Ignition;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * https://apacheignite.readme.io/docs/getting-started
 * 
 * @author jcamargos
 *
 */
@ComponentScan(basePackages = "com.ignite")
@Configuration
public class AppConfig {

	@Autowired
	private AppService appService;

	private void callService() {
		this.appService.callCache();
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(AppConfig.class);
		context.refresh();

		AppConfig ac = context.getBean(AppConfig.class);
		ac.callService();

		context.close();
	}

	@Bean
	public IgniteClient igniteCache() {
		IgniteClient igniteClient = null;
		try {
			ClientConfiguration cfg = new ClientConfiguration().setAddresses("127.0.0.1:10800");
			igniteClient = Ignition.startClient(cfg);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return igniteClient;
	}
}