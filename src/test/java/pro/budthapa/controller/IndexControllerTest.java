package pro.budthapa.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import pro.budthapa.domain.Recipe;
import pro.budthapa.service.RecipeService;

public class IndexControllerTest {
	
	@Mock
	private RecipeService recipeService;
	
	@Mock
	Model model;

	IndexController controller;
	
	@Before
	public void setup() throws Exception{
		MockitoAnnotations.initMocks(this);
		controller = new IndexController(recipeService);
	}
	
	@Test
	public void testMockMvc() throws Exception{
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		
		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
	}
	
	@Test
	public void indexPageTest() throws Exception{
		//given
		List<Recipe> recipeList = new ArrayList<>();
		recipeList.add(new Recipe());
		recipeList.add(new Recipe());
		recipeList.add(new Recipe());
		
		when(recipeService.findAllRecipe()).thenReturn(recipeList);

		ArgumentCaptor<List<Recipe>> argumentCaptor = ArgumentCaptor.forClass(ArrayList.class);
		
		//when
		String pageName = controller.index(model);

		//then
		assertEquals("index", pageName);
		verify(recipeService,times(1)).findAllRecipe();
		verify(model, times(1)).addAttribute(eq("recipeList"),argumentCaptor.capture());
		
		List<Recipe> recipeInController = argumentCaptor.getValue();
		
		assertEquals(3,recipeInController.size());
	}
}
