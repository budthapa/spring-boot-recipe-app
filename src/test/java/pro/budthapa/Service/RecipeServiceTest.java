package pro.budthapa.Service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pro.budthapa.domain.Recipe;
import pro.budthapa.repositories.RecipeRepository;
import pro.budthapa.service.impl.RecipeServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceTest {
	@Mock
	RecipeRepository recipeRepository;
	
	@InjectMocks
	RecipeServiceImpl recipeService;
	
	@Before
	public void setup() throws Exception{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAllRecipesTest() throws Exception{
		Recipe recipe = new Recipe();
		recipe.setCookTime(5);
		recipe.setDescription("test data");
		
		List<Recipe> recipeList = new ArrayList<>();
		recipeList.add(recipe);
		
		when(recipeRepository.findAll()).thenReturn(recipeList);
		
		List<Recipe> rl = recipeService.findAllRecipe();
		assertEquals(rl.size(), 1);
	
		assertEquals("test data", rl.get(0).getDescription());
		verify(recipeRepository,times(1)).findAll();
	}
	
	
	@Test
	public void getReceipeByIdTest() throws Exception{
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		Optional<Recipe> recipeOptional = Optional.of(recipe);
		
		when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
		
		Optional<Recipe> recipeReturned = recipeService.findRecipeById(1L);
		
		assertNotNull("null recipe returned",recipeReturned);
		verify(recipeRepository, times(1)).findById(anyLong());
		verify(recipeRepository,never()).findAll();
	}
}
