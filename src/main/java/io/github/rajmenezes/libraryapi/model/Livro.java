package io.github.rajmenezes.libraryapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "livro")
@Data // inclui as anotações @Getter @Setter @ToString @EqualsAndHashCode @RequiredArgsConstructor
public class Livro {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "isbn", length = 20, nullable = false)
    private String isbn;

    @Column(name = "titulo", length = 150, nullable = false)
    private String titulo;

    @Column(name = "data_publicacao")
    private LocalDate dataPublicacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero", length = 30, nullable = false)
    private GeneroLivro genero;

    @Column(name = "preco", precision = 18, scale = 2)
    private BigDecimal preco;
    // private BigDecimal; melhor tipo para se trabalhar com valores monetários
    // oferece precisão arbitrária, evitando erros de arredondamento comuns em cálculos financeiros
    // que ocorrem com tipos de ponto flutuante como double ou float

    @ManyToOne // Many -> entidade atual One -> entidade Autor
    @JoinColumn(name = "id_autor")
    private Autor autor;
}
