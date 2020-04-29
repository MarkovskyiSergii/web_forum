package zp.brain.web_forum.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import zp.brain.web_forum.model.CategoryEntity;
import zp.brain.web_forum.model.TopicEntity;
import java.util.List;


public interface TopicRepository extends CrudRepository<TopicEntity, String> {

   TopicEntity findTopicEntityByTopicId(Long topicId);

   @Query("select titleTopic from TopicEntity where topicId=?1")
   String getTitleByTopicId(Long topicId);

   TopicEntity getTopicEntityByTopicId(Long topicId);

   @Query("from TopicEntity where category.categoryId =?1 order by topicId")
   List<TopicEntity> getTopicEntitiesByCategoryIdAndOrderByTopicId(Long categoryId);

   Long countAllByCategory(CategoryEntity categoryEntity);

   @Transactional
   @Modifying
   @Query("delete from TopicEntity where topicId=?1")
   void deleteTopicEntityByTopicId(Long topicId);

   @Transactional
   @Modifying
   @Query("update TopicEntity te set te.titleTopic=:title where te.topicId=:id")
   void saveEditedTitleTopic(@Param("id") Long topicId, @Param("title") String title);
}
