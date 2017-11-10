package pro.budthapa.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import pro.budthapa.domain.Category;
import pro.budthapa.domain.Ingredient;
import pro.budthapa.domain.Note;
import pro.budthapa.domain.Recipe;
import pro.budthapa.domain.UnitOfMeasure;
import pro.budthapa.enums.Difficulty;
import pro.budthapa.service.CategoryService;
import pro.budthapa.service.RecipeService;
import pro.budthapa.service.UnitOfMeasureService;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private UnitOfMeasureService unitOfMeasureService;

	@Autowired
	private CategoryService categoryService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		recipeService.saveRecive(recipeService.saveRecive(saveRecipe()));
	}

	private Recipe saveRecipe() {
		Recipe recipeCornBread = new Recipe();
		recipeCornBread.setDescription("Cornbread Stuffing with Green Olives and Pecans");
		recipeCornBread.setCookTime(20);
		recipeCornBread.setPrepTime(15);
		recipeCornBread.setDifficulty(Difficulty.EASY);
		recipeCornBread.setDirections("1 Preheat the oven to 350ºF. Butter a 9x13-inch baking dish.\n" + "\n"
				+ "2 Toast the pecans: Scatter the pecans on a baking sheet and toast in the oven until slightly darkened and fragrant, 10 to 15 minutes.\n"
				+ "\n"
				+ "Transfer the pecans to a cutting board and roughly chop while still warm. Set aside until needed.\n"
				+ "\n"
				+ "3 Cut the cornbread into cubes and toast: Use a serrated knife to cut the cornbread into roughly 1/2-inch cubes. Be gently as you slice to avoid crumbling the cubes. You should have about 8 cups cubed.\n"
				+ "\n"
				+ "Transfer the cubes and any crumbs the baking sheet used to toast the pecans. Toast for 15 to 20 minutes, stirring partway through, until the cubes look golden around the edges and feel dry to the touch.\n"
				+ "\n"
				+ "4 Meanwhile, cook the vegetables: In a large skillet over medium heat, melt the butter. Add the onions and celery, and cook for 5 minutes.\n"
				+ "\n"
				+ "Add the sage, parsley, thyme, salt, pepper, olives, and cranberries. Cook for 3 to 4 minutes longer, or until the vegetables soften and the herbs are very aromatic. Let cool for 10 minutes.\n"
				+ "\n"
				+ "5 Mix the stuffing: Gently stir the cooked vegetable mixture into the bowl with the cornbread cubes. Stir in the pecans.\n"
				+ "\n"
				+ "In a small bowl, whisk the eggs, stock and cream together. Pour over the stuffing and toss gently to combine, using your hands if necessary to avoid crumbling the cornbread too much. The stuffing should feel evenly moist; sprinkle with additional chicken stock as needed.\n"
				+ "\n" + "Transfer to the baking dish.\n" + "\n"
				+ "5 Bake for 35 to 40 minutes, or until the top is brown and crisp.\n" + "\n"
				+ "6 Serve: Serve the stuffing while warm. Stuffing can also be made a day or two ahead and warmed in the oven before serving.\n"
				+ "\n"
				+ "Read more: http://www.simplyrecipes.com/recipes/cornbread_stuffing_with_green_olives_and_pecans/#ixzz4xXzSvoOz\n"
				+ "");

		Note notesCornBread = new Note();
		notesCornBread.setRecipeNotes(
				"Cornbread Stuffing with Green Olives, Cranberries, and Pecans! Sweet, buttery California green ripe olives add a little something special to this classic Thanksgiving stuffing. ");
		
		//needed for bidirectional relationship
		notesCornBread.setRecipe(recipeCornBread);
		recipeCornBread.setNote(notesCornBread);

		// get UOMs
		Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureService.findUOMByDescription("Each");

		if (!eachUomOptional.isPresent()) {
			//throw new RuntimeException("Expected UOM Not Found");
		}

		Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureService.findUOMByDescription("Tablespoon");

		if (!tableSpoonUomOptional.isPresent()) {
			//throw new RuntimeException("Expected UOM Not Found");
		}

		Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureService.findUOMByDescription("Teaspoon");

		if (!teaSpoonUomOptional.isPresent()) {
			//throw new RuntimeException("Expected UOM Not Found");
		}

		Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureService.findUOMByDescription("Dash");

		if (!dashUomOptional.isPresent()) {
			//throw new RuntimeException("Expected UOM Not Found");
		}

		Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureService.findUOMByDescription("Pint");

		if (!pintUomOptional.isPresent()) {
			//throw new RuntimeException("Expected UOM Not Found");
		}

		Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureService.findUOMByDescription("Cup");

		if (!cupsUomOptional.isPresent()) {
			//throw new RuntimeException("Expected UOM Not Found");
		}

		// get optionals
		UnitOfMeasure eachUom = eachUomOptional.get();
		UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
		UnitOfMeasure teapoonUom = tableSpoonUomOptional.get();
		UnitOfMeasure dashUom = dashUomOptional.get();
		UnitOfMeasure pintUom = dashUomOptional.get();
		UnitOfMeasure cupsUom = cupsUomOptional.get();

		// get Categories
		Optional<Category> americanCategoryOptional = categoryService.findCategoryByDescription("American");

		if (!americanCategoryOptional.isPresent()) {
			throw new RuntimeException("Expected Category Not Found");
		}

		Optional<Category> mexicanCategoryOptional = categoryService.findCategoryByDescription("Mexican");

		if (!mexicanCategoryOptional.isPresent()) {
			throw new RuntimeException("Expected Category Not Found");
		}

		Category americanCategory = americanCategoryOptional.get();
		Category mexicanCategory = mexicanCategoryOptional.get();

		List<Ingredient> ingredientList = new ArrayList<>();
		ingredientList.add(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tableSpoonUom, recipeCornBread));
		ingredientList.add(new Ingredient("Dried Oregano", new BigDecimal(1), teapoonUom, recipeCornBread));
		ingredientList.add(new Ingredient("Dried Cumin", new BigDecimal(1), teapoonUom, recipeCornBread));
		ingredientList.add(new Ingredient("Sugar", new BigDecimal(1), teapoonUom, recipeCornBread));
		ingredientList.add(new Ingredient("Salt", new BigDecimal(".5"), teapoonUom, recipeCornBread));
		ingredientList.add(new Ingredient("Clove of Garlic, Choppedr", new BigDecimal(1), eachUom, recipeCornBread));
		ingredientList
				.add(new Ingredient("finely grated orange zestr", new BigDecimal(1), tableSpoonUom, recipeCornBread));
		ingredientList
				.add(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tableSpoonUom, recipeCornBread));
		ingredientList.add(new Ingredient("Olive Oil", new BigDecimal(2), tableSpoonUom, recipeCornBread));
		ingredientList
				.add(new Ingredient("boneless chicken thighs", new BigDecimal(4), tableSpoonUom, recipeCornBread));
		ingredientList.add(new Ingredient("small corn tortillasr", new BigDecimal(8), eachUom, recipeCornBread));
		ingredientList.add(new Ingredient("packed baby arugula", new BigDecimal(3), cupsUom, recipeCornBread));
		ingredientList.add(new Ingredient("medium ripe avocados, slic", new BigDecimal(2), eachUom, recipeCornBread));
		ingredientList.add(new Ingredient("radishes, thinly sliced", new BigDecimal(4), eachUom, recipeCornBread));
		ingredientList.add(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), pintUom, recipeCornBread));
		ingredientList.add(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), eachUom, recipeCornBread));
		ingredientList.add(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), eachUom, recipeCornBread));
		ingredientList.add(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cupsUom,
				recipeCornBread));
		ingredientList.add(new Ingredient("lime, cut into wedges", new BigDecimal(4), eachUom, recipeCornBread));

		recipeCornBread.setIngredients(ingredientList);
		recipeCornBread.setUrl("simplyrecipes.com/corn-bread-recipe.html");
		recipeCornBread.setServing("5");
		
		List<Category> categories = new ArrayList<>();
		categories.add(americanCategory);
		categories.add(mexicanCategory);
		recipeCornBread.setCategories(categories);
		
		return recipeCornBread;
	}

}
