package zp.brain.web_forum.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import zp.brain.web_forum.model.MessageEntity;
import zp.brain.web_forum.model.TopicEntity;

import java.util.List;

public interface MessageRepository extends CrudRepository<MessageEntity, String> {

    @Query("from MessageEntity where topicEntity.topicId=?1 order by messageId")
    List<MessageEntity> getMessageEntitiesByTopicEntityIdAndOrderByMessageId(Long topicId);

    Long countAllByTopicEntity(TopicEntity topicEntity);

    MessageEntity getMessageEntityByMessageId(Long messageId);

    @Transactional
    void deleteMessageEntitiesByMessageId(Long messageId);

    @Query("select textMessage from MessageEntity where messageId=?1")
    String getTextMessageByMessageId(Long messageId);

    @Transactional
    @Modifying
    @Query("update MessageEntity me set me.textMessage=:text where me.messageId=:id")
    void saveEditedTextMessage(@Param("id") Long messageId, @Param("text") String text);
}
