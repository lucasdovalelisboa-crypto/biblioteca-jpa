package model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int matriculaAluno;
    private String nome;

    @OneToMany(mappedBy = "aluno")
    private List<Emprestimo> emprestimos;

    public Long getId() { return id; }

    public int getMatriculaAluno() { return matriculaAluno; }
    public void setMatriculaAluno(int matriculaAluno) { this.matriculaAluno = matriculaAluno; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}