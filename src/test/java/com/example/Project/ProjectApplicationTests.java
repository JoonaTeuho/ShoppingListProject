package com.example.Project;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.example.Project.domain.Item;
import com.example.Project.domain.ItemRepository;
import com.example.Project.web.ShoppingListController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProjectApplicationTests {

	//Smoke test
	@Autowired
    private ShoppingListController controller;
    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    
    }
    
    //Web layer test 
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void testDefaultMessage() throws Exception {
    this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
    .andExpect(content().string(containsString("Hello World")));
    }
    
    //Repository test
    @Autowired
    private ItemRepository repository;
    @Test
    public void findByNameShouldReturnItem() {
    List<Item> items = repository.findByName("Example");
    assertThat(items).hasSize(1);
    assertThat(items.get(0).getName()).isEqualTo("Example");
    }
    @Test
    public void createNewStudent() {
    Item item = new Item("New Item", "1");
    repository.save(item);
    assertThat(item.getId()).isNotNull();
    }
}

