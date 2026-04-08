package teste;

import org.h2.tools.Server;
import jakarta.persistence.*;
import model.*;
import dao.EmprestimoDAO;

import java.time.LocalDate;
import java.util.List;

public class TesteEmprestimoDAO {

    public static void main(String[] args) throws Exception {

        // INICIA O CONSOLE WEB DO H2
        Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bibliotecaPU");
        EntityManager em = emf.createEntityManager();

        EmprestimoDAO dao = new EmprestimoDAO(em);

        // Criar aluno
        Aluno aluno = new Aluno();
        aluno.setNome("Maria");
        aluno.setMatriculaAluno(123);

        em.getTransaction().begin();
        em.persist(aluno);
        em.getTransaction().commit();

        // Criar publicação
        Publicacao pub = new Publicacao();
        pub.setTitulo("Java JPA");
        pub.setAutor("Autor X");

        em.getTransaction().begin();
        em.persist(pub);
        em.getTransaction().commit();

        // Criar empréstimo
        Emprestimo emp = new Emprestimo();
        emp.setAluno(aluno);
        emp.setPublicacao(pub);
        emp.setDataEmprestimo(LocalDate.now());

        dao.salvar(emp);

        // Testes
        System.out.println("Por ID: " + dao.buscarPorId(emp.getId()));

        List<Emprestimo> lista = dao.listarTodos();
        System.out.println("Total: " + lista.size());

        List<Emprestimo> porNome = dao.buscarPorNomeAluno("Maria");
        System.out.println("Por nome: " + porNome.size());

        // Atualizar
        emp.setDataDevolucao(LocalDate.now().plusDays(5));
        dao.atualizar(emp);

        // Deletar
        dao.deletar(emp.getId());

        em.close();
        emf.close();
    }
}