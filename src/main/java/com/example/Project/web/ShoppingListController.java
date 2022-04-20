package com.example.Project.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Project.domain.Item;
import com.example.Project.domain.ItemRepository;

@Controller
public class ShoppingListController {
	@Autowired
	private ItemRepository repository;

	//Etusivu, joka sisältää kauppalistan
	@RequestMapping(value = { "/", "/shoppinglist" })
	public String shoppingList(Model model) {
		model.addAttribute("items", repository.findAll());
		return "shoppingList";
	}
	
	//Listaa kauppalistalla olevat tuotteet
	@RequestMapping(value="/items", method = RequestMethod.GET)
	public @ResponseBody List<Item> itemListRest(){
		return (List<Item>) repository.findAll();
	}

	//Kertoo tiedot yhdestä kauppalistan tuotteesta
	@RequestMapping(value="/item/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Item> findItemRest(@PathVariable("id") Long itemId){
		return repository.findById(itemId);
	}

	//lisää tuotteen kauppalistalle
	@RequestMapping(value = "/add")
	public String addBook(Model model) {
		model.addAttribute("item", new Item());
		return "additem";
	}

	//Tallentaa
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Item item) {
		System.out.println("Test" + item);
		repository.save(item);
		return "redirect:shoppinglist";
	}

	//Poistaa tuotteen listalta
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long itemId, Model model) {
		repository.deleteById(itemId);
		return "redirect:../shoppinglist";
	}
	
	//Muokkaa tuotetta
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Long itemId, Model model) {
		model.addAttribute("item", repository.findById(itemId));
		return "editItem.html";
	}
	
	//Sisäänkirjautuminen
	@RequestMapping(value="/login")
		public String login() {
			return "login";
	}   
}
