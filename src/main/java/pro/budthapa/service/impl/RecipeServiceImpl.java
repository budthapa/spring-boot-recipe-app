package pro.budthapa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.budthapa.domain.Recipe;
import pro.budthapa.repositories.RecipeRepository;
import pro.budthapa.service.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository2) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Recipe> findAllRecipe() {
		return recipeRepository.findAll();
	}

	@Override
	public Recipe saveRecive(Recipe recipe) {
		return recipeRepository.save(recipe);
	}

}
