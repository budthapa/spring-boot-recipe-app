package pro.budthapa.service;

import java.util.List;
import java.util.Optional;

import pro.budthapa.domain.Recipe;

public interface RecipeService {
	List<Recipe> findAllRecipe();
	Recipe saveRecive(Recipe recipe);
	Optional<Recipe> findRecipeById(Long id);
}
