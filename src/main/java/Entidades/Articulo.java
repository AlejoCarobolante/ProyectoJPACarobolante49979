package Entidades;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "Articulo")
public class Articulo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "articulo")
    private Set<DetalleFactura> detalleFacturas = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "articulo_categoria",
            joinColumns = @JoinColumn(name = "articuloId"),
            inverseJoinColumns = @JoinColumn(name = "categoriaId"))
    private Set<Categoria> categorias = new HashSet<>();

    @NonNull private int cantidad;
    @NonNull private String denominacion;
    @NonNull private int precio;
}
