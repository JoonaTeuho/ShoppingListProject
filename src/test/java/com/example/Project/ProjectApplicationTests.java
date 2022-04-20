package com.example.Project;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.Project.web.ShoppingListController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProjectApplicationTests {

	@Autowired
    private ShoppingListController controller;

	//Smoke test
    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    
    }
}