package com.springboot.pos.system.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.pos.system.entity.Cart;
import com.springboot.pos.system.entity.Dash;
import com.springboot.pos.system.entity.Item;
import com.springboot.pos.system.repository.CartRepository;
import com.springboot.pos.system.repository.DashRepository;
import com.springboot.pos.system.repository.ItemRepository;
import com.springboot.pos.system.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/items/")
public class ItemController {

	private final ItemRepository itemRepo;
	private final CartRepository cartRepo;
	private final DashRepository dashRepo;
	private final UserRepository userRepo;
	
	@Autowired
	public ItemController(ItemRepository itemRepo, CartRepository cartRepo, DashRepository dashRepo, UserRepository userRepo) {
		this.itemRepo = itemRepo;
		this.cartRepo = cartRepo;
		this.dashRepo = dashRepo;
		this.userRepo = userRepo;
	}
	
	@GetMapping("login")
	public String login(Model model) {
		model.addAttribute("admins", userRepo.findAll());
		return "login";
	}

	
	
	@GetMapping("main")
	public String showMainPage(Model model) {
		model.addAttribute("items", itemRepo.findAll());
		return "index";
	}
	
	@GetMapping("additem")
	public String showItemForm(Item item) {
		return "add-item";
	}
	
	@GetMapping("list")
	public String showUpdateForm(Model model) {
		model.addAttribute("items", itemRepo.findAll());
		return "show-item";
	}
	
	@GetMapping("maincourse")
	public String showMainCourse(Model model) {
		model.addAttribute("items", itemRepo.findAll());
		return "main-item";
	}
	
	@GetMapping("dessert")
	public String showDessert(Model model) {
		model.addAttribute("items", itemRepo.findAll());
		return "dessert-item";
	}
	
	@GetMapping("snack")
	public String showSnack(Model model) {
		model.addAttribute("items", itemRepo.findAll());
		return "snack-item";
	}
	
	@GetMapping("beverage")
	public String showBeverage(Model model) {
		model.addAttribute("items", itemRepo.findAll());
		return "beverage-item";
	}
	
	@GetMapping("other")
	public String showOther(Model model) {
		model.addAttribute("items", itemRepo.findAll());
		return "other-item";
	}
	
	@PostMapping("add")
	public String addItem(@Valid Item item, BindingResult result, Model model) {
		model.addAttribute("items", itemRepo.findAll());
		
		if (result.hasErrors()) {
			return "add-item";
		}
		
		itemRepo.save(item);
		return "redirect:list";
	}
	
	@GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Item item = itemRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid item Id:" + id));
		model.addAttribute("item", item);
		return "update-item";
	}

	@PostMapping("update/{id}")
	public String updateItem(@PathVariable("id") long id, @Valid Item item, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			item.setId(id);
			return "update-item";
		}
		
		itemRepo.save(item);
		model.addAttribute("items", itemRepo.findAll());
		return "show-item";
	}

	@GetMapping("delete/{id}")
	public String deleteItem(@PathVariable("id") long id, Model model) {
		Item item = itemRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid item Id:" + id));
		itemRepo.delete(item);
		model.addAttribute("items", itemRepo.findAll());
		return "show-item";
	}
	
	//cart
	
	@GetMapping("viewcart")
	public String showCart(Model model) {
		model.addAttribute("carts", cartRepo.findAll());
		return "cart";
	}

	@GetMapping("addcart/{name}/{price}")
	public String addCart(Cart cart,@PathVariable("name") String name,
			@PathVariable("price") float price, HttpSession session) {
		
		session.setAttribute("name", name);
		session.setAttribute("price", price);
		
		return "add-cart";
	}
	
	@PostMapping("addtocart")
	public String addToCart(Cart cart, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-cart";
		}
		cartRepo.save(cart);
		//model.addAttribute("carts", cartRepo.findAll());
		return "index";
	}
	
	@GetMapping("editcart/{id}")
	public String showUpdateCart(@PathVariable("id") long id, Model model) {
		Cart cart = cartRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid cart Id:" + id));
		model.addAttribute("cart", cart);
		return "edit-cart";
	}

	@PostMapping("updatecart/{id}")
	public String updateCart(@PathVariable("id") long id, @Valid Cart cart, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			//cart.setId(id);
			return "edit-cart";
		}
		
		cartRepo.save(cart);
		model.addAttribute("carts", cartRepo.findAll());
		return "cart";
	}
	
	@GetMapping("deletecart/{id}")
	public String deleteCart(@PathVariable("id") long id, Model model) {
		Cart cart = cartRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid cart Id:" + id));
		cartRepo.delete(cart);
		model.addAttribute("carts", cartRepo.findAll());
		return "cart";
	}
	
	//checkout	
	@GetMapping("checkout")
	public String showCheckoutForm(Dash dash, Cart cart,Model model) {
		model.addAttribute("carts", cartRepo.findAll());
		return "checkout";
	}
	
	@PostMapping("savedash")
	public String saveDash(@Valid Dash dash, BindingResult result, Model model) {
		model.addAttribute("dashs", dashRepo.findAll());
		model.addAttribute("carts", cartRepo.findAll());
		if (result.hasErrors()) {
			return "checkout";
		}
		
		dashRepo.save(dash);
		cartRepo.deleteAll();
		return "queuenum";
	}
	
	//dashboard
	@GetMapping("dashboard")
	public String showDashboard(Model model) {
		model.addAttribute("dashs", dashRepo.findAll());
		model.addAttribute("items", itemRepo.findAll());
		return "dashboard";
	}
	
	@GetMapping("vieworders")
	public String showOrders(Model model) {
		model.addAttribute("dashs", dashRepo.findAll());
		return "view-orders";
	}
	
	@GetMapping("invoice/{id}")
	public String showInvoice(@PathVariable("id") long id, Model model) {
		Dash dash = dashRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid invoice Id:" + id));
		model.addAttribute("dash", dash);
		return "view-invoice";
	}

	
}
