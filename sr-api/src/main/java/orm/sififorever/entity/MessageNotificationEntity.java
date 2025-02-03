package orm.sififorever.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "message_notifications")
public class MessageNotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long sender_id;

    @Column(nullable = false)
    private String receiver_username;

    @Column(length = 500)
    private String message;

    @Column(name = "created_at", updatable = false)
    private Date createdAt;
}
