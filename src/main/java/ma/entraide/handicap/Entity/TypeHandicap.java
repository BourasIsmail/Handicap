package ma.entraide.handicap.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TypeHandicap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "handicap_id")
    private Long id;

    private String handicap;
}
