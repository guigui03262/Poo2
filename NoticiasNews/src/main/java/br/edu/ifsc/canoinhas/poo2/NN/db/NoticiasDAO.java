package br.edu.ifsc.canoinhas.poo2.NN.db;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;

import br.edu.ifsc.canoinhas.poo2.NN.entities.Genero;
import br.edu.ifsc.canoinhas.poo2.NN.entities.Noticias;

public class NoticiasDAO implements InterfaceDAO<Noticias> {
	
	@Override
	public void persist(Noticias t) {
		EntityManager em = UtilDB.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
		} catch (EntityExistsException e) {
			em.getTransaction().rollback();
			Noticias original = get(t.getId());
			em.getTransaction().begin();
			original.setTitulo(t.getTitulo());
			original.setDescricao(t.getDescricao());
			for (Genero g : t.getGenero())
				original.getGenero().add(g);
			em.getTransaction().commit();
		}
	}

	@Override
	public void remove(Noticias t) {
		EntityManager em = UtilDB.getEntityManager();
		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();
	}
	
	@Override
	public Noticias get(Object pk) {
		return UtilDB.getEntityManager().getReference(Noticias.class, pk);
	}

	@Override
	public List<Noticias> getAll() {
		return UtilDB.getEntityManager().createQuery("SELECT u FROM Noticias u", Noticias.class).getResultList();
	}

}
