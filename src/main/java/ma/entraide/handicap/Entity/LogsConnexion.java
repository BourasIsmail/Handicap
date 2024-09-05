package ma.entraide.handicap.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogsConnexion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "logs_id")
    private Long id;

    private Date dateLogin;

    private String ipAdresse;

    private String accountEmail;

    @ManyToOne
    @JoinColumn(name = "user")
    private UserInfo user;
}
