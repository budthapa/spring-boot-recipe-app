package pro.budthapa.service;

import java.util.List;

import pro.budthapa.domain.Recipe;

public interface RecipeService {
	List<Recipe> findAllRecipe();
	Recipe saveRecive(Recipe recipe);
}
