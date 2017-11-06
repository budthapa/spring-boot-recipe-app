package pro.budthapa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pro.budthapa.service.RecipeService;

@Controller
public class IndexController {

	private RecipeService recipeService;

	public IndexController(RecipeService recipeService) {
		this.recipeService=recipeService;
	}

	@GetMapping({ "", "/", "/index" })
	public String index(Model model) {

		model.addAttribute("recipeList", recipeService.findAllRecipe());
		return "index";
	}

	String date;
}
