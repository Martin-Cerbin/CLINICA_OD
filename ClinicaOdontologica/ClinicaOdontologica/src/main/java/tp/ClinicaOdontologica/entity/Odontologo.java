package tp.ClinicaOdontologica.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "odontologos")
public class Odontologo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String matricula;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @OneToMany(mappedBy ="odontologo", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Turno> turnos= new HashSet<>();
}
