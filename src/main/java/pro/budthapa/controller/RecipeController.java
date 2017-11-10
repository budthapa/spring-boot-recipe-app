package pro.budthapa.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pro.budthapa.domain.Recipe;
import pro.budthapa.service.RecipeService;

@Controller
@RequestMapping(value="/recipe")
public class RecipeController {
	
	private RecipeService recipeService;
	
	public RecipeController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}

	@GetMapping("/show/{id}")
	public String showRecipeById(@PathVariable Long id, Model model) throws Exception {
		Optional<Recipe> recipe = recipeService.findRecipeById(id);
		if(!recipe.isPresent()) {
			throw new RuntimeException();
		}
		
		model.addAttribute("recipe", recipe.get());
		
		return "recipe/showRecipe";
	}
}
