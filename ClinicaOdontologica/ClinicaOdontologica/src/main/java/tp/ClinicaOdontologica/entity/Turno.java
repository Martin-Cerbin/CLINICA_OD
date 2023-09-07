package tp.ClinicaOdontologica.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "turnos")
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDate Fecha;
    @ManyToOne
    @JoinColumn(name = "paciente_id",referencedColumnName = "id")
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "odontologo_id",referencedColumnName = "id")
    private Odontologo odontologo;

}
