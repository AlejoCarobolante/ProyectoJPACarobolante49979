package Entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "Cliente")
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_domicilio")
    private Domicilio domicilio;

    @OneToMany(mappedBy = "cliente")
    private List<Factura> facturas = new ArrayList<Factura>();

    @Column(name = "nombre") //Asigna el nombre de la columna
    @NonNull private String nombre;

    //Si no agrego @Column, entonces toma el nombre del atributo por defecto
    @NonNull private String apellido;

    @Column(name = "dni", unique = true)
    @NonNull private int dni;
}
