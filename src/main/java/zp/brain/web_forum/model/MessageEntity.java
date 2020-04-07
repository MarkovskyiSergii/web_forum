package zp.brain.web_forum.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity

@Data
@NoArgsConstructor
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_id")
    private Long messageId;

    @Column(name = "text_message",length = 1800, nullable = false)
    private String textMessage;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private TopicEntity topicEntity;
}
