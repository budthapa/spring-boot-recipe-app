package pro.budthapa.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import pro.budthapa.domain.Recipe;
import pro.budthapa.service.RecipeService;

public class RecipeControllerTest {

	RecipeController recipeController;

	@Mock
	private RecipeService recipeService;

	MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		recipeController = new RecipeController(recipeService);
		mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
	}

	@Test
	public void getRecipeByIdTest() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(1L);

		when(recipeService.findRecipeById(anyLong())).thenReturn(Optional.of(recipe));

		mockMvc.perform(get("/recipe/show/1")).andExpect(status().isOk()).andExpect(view().name("recipe/showRecipe"));

		verify(recipeService, times(1)).findRecipeById(1L);
	}

	@Test
	public void getRecipeByIdNotFoundTest() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(20L);
		when(recipeService.findRecipeById(anyLong())).thenReturn(Optional.of(recipe));
		mockMvc.perform(get("/recipe/show/20")).andExpect(status().is(200));
		verify(recipeService, times(1)).findRecipeById(20L);
	}

	@Test
	public void getRecipeTest() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(1L);

		when(recipeService.findRecipeById(anyLong())).thenReturn(Optional.of(recipe));

		mockMvc.perform(get("/recipe/show/1")).andExpect(status().isOk()).andExpect(view().name("recipe/showRecipe"))
				.andExpect(model().attributeExists("recipe"));
		
		verify(recipeService, times(1)).findRecipeById(1L);

	}
}
