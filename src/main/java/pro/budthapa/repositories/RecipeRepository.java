package pro.budthapa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.budthapa.domain.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>{

}
