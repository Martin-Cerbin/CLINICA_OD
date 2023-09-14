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

    public Turno() {
    }
    public Turno(Long id, LocalDate fecha, Paciente paciente, Odontologo odontologo) {
        this.id = id;
        Fecha = fecha;
        this.paciente = paciente;
        this.odontologo = odontologo;
    }
    public Turno(LocalDate fecha, Paciente paciente, Odontologo odontologo) {
        Fecha = fecha;
        this.paciente = paciente;
        this.odontologo = odontologo;
    }
}
