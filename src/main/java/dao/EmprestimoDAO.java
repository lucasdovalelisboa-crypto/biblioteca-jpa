package dao;

import jakarta.persistence.*;
import model.Emprestimo;
import java.util.List;

public class EmprestimoDAO {

    private EntityManager em;

    public EmprestimoDAO(EntityManager em) {
        this.em = em;
    }

    public void salvar(Emprestimo e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    public void atualizar(Emprestimo e) {
        em.getTransaction().begin();
        em.merge(e);
        em.getTransaction().commit();
    }

    public void deletar(Long id) {
        Emprestimo e = em.find(Emprestimo.class, id);
        if (e != null) {
            em.getTransaction().begin();
            em.remove(e);
            em.getTransaction().commit();
        }
    }

    public Emprestimo buscarPorId(Long id) {
        return em.find(Emprestimo.class, id);
    }

    public List<Emprestimo> listarTodos() {
        return em.createQuery("FROM Emprestimo", Emprestimo.class).getResultList();
    }

    public List<Emprestimo> buscarPorNomeAluno(String nome) {
        return em.createQuery(
            "SELECT e FROM Emprestimo e WHERE LOWER(e.aluno.nome) LIKE LOWER(:nome)",
            Emprestimo.class)
            .setParameter("nome", "%" + nome + "%")
            .getResultList();
    }
}