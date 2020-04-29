package zp.brain.web_forum.repository;

import org.springframework.data.repository.CrudRepository;
import zp.brain.web_forum.model.CategoryEntity;


public interface CategoryRepository extends CrudRepository<CategoryEntity, String> {

    CategoryEntity findCategoryEntityByCategoryId(Long categoryId);
}
