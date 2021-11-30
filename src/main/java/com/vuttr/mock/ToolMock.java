package com.vuttr.mock;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.vuttr.models.Tool;
import com.vuttr.repositories.ToolRepository;

/* Keep this comment annotated so as not to insert data into the database */
//@Component
@SuppressWarnings("unused")
public class ToolMock implements CommandLineRunner{

	@Autowired
	ToolRepository toolRepository;

	@Override
	public void run(String... args) throws Exception {
	
	/* Insert Tools Database */
	Tool tool1 = new Tool(null, "Notion", "https://notion.so", "ll in one tool to organize teams and ideas. "
			+ "Write, plan, collaborate, and get organized.", "api json rest");
	Tool tool2 = new Tool(null, "fastify", "https://www.fastify.io/", "Extremely fast and simple, low-overhead "
			+ "web framework for NodeJS. "
			+ "Supports HTTP2.", "json rest");
	Tool tool3 = new Tool(null, "json-server", "https://github.com/typicode/json-server", "Fake REST API based on a "
			+ "json schema. Useful for mocking and creating APIs for front-end devs to consume in coding "
			+ "challenges.\"", "json rest schema node github rest");
	toolRepository.saveAll(Arrays.asList(tool1, tool2, tool3));
		
	}
}
