package pro.budthapa.service;

import java.util.List;
import java.util.Optional;

import pro.budthapa.domain.Category;

public interface CategoryService {
	List<Category> findAllCategoryList();
	Optional<Category> findCategoryByDescription(String description);
}
