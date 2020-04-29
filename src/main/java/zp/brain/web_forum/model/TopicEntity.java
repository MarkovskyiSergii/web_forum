package zp.brain.web_forum.model;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;


@Entity
@Table(name = "TOPIC")
@Data
public class TopicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "topic_id")
    private Long topicId;

    @Size(min = 3, max = 100, message = "Title of topic must contain from 3 to 100 characters. This is required for create new Topic...")
    @Column(name = "title_topic")
    private String titleTopic;

    @Column(name = "create_topic_time")
    private Timestamp createTopicTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;
}
