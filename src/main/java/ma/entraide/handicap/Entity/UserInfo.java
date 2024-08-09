package ma.entraide.handicap.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(unique=true)
    @Email
    private String email;

    @Column(nullable = false)
    private String roles;

    @Column(nullable = false)
    @Size(min = 6)
    private String password;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "province_id")
    private Province province;

    public UserInfo(String name, String email, String roles, String password) {
        this.name = name;
        this.email = email;
        this.roles = roles;
        this.password = password;
    }



}
