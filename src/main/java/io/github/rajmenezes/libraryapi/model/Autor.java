package io.github.rajmenezes.libraryapi.model;

import jakarta.persistence.*; // Era utilizado o javax anteriormente
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "autor", schema = "public")
@Getter // anotation do lombok para criação de métodos getters em tempo de compilação
@Setter
public class Autor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID) // A geração do id será automático
    private UUID id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "nacionalidade", length = 50, nullable = false)
    private String nacionalidade;

    @OneToMany(mappedBy = "autor")
    private List<Livro> livros;

    @Deprecated
    public Autor (){
        // para uso do framework
        // não é necessário adicionar já que o java adiciona um construtor vazio
    }
    
}
