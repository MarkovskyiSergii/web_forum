package zp.brain.web_forum.model;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table (name = "MESSAGE")
@Data
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_id")
    private Long messageId;

    @Size(min = 3, max = 5000, message ="Enter some text (from 3 to 5000 characters). This is required for create new message..." )
    @Column(name = "text_message")
    private String textMessage;

    @Column(name = "create_message_time")
    private Timestamp createMessageTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private TopicEntity topicEntity;
}
