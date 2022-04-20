package com.example.Project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Project.domain.Item;
import com.example.Project.domain.ItemRepository;


@SpringBootApplication
public class ProjectApplication {
	private static final Logger log = LoggerFactory.getLogger(ProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner itemDemo(ItemRepository itemrepository) {
		return (args) -> {
			log.info("save some items");
			itemrepository.save(new Item("Example", "5"));

			log.info("fetch all items");
			for (Item item : itemrepository.findAll()) {
				log.info(item.toString());
			}

		};
	}

}
