package com.keeper.sys_materiais;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.keeper.sys_materiais", "controller", "service", "repository", "model", "config"})
public class SysMateriaisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SysMateriaisApplication.class, args);
	}

}
