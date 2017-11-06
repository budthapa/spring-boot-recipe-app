package pro.budthapa.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import pro.budthapa.domain.Category;

public class CategoryTest {

	Category category;

	@Before
	public void setUp() {
		category = new Category();
	}

	@Test
	public void getIdTest() throws Exception {
		category.setId(5L);

		assertEquals(Long.valueOf(5L), category.getId());
	}
}
