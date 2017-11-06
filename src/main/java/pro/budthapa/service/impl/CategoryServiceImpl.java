package pro.budthapa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.budthapa.domain.Category;
import pro.budthapa.repositories.CategoryRepository;
import pro.budthapa.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> findAllCategoryList() {
		return categoryRepository.findAll();
	}

	@Override
	public Optional<Category> findCategoryByDescription(String description) {
		return categoryRepository.findByDescription(description);
	}

}
