package br.edu.ifsc.canoinhas.poo2.NN.db;

import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import br.edu.ifsc.canoinhas.poo2.NN.entities.Genero;

public class GeneroDAO implements InterfaceDAO<Genero> {
	
	@Override
	public void persist(Genero t) {
		EntityManager em = UtilDB.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
		} catch (EntityExistsException e) {
			em.getTransaction().rollback();
			Genero original = get(t.getNomegenero());
			em.getTransaction().begin();
			original.setPalavrachave(t.getPalavrachave());
			em.getTransaction().commit();
		}
	}

	@Override
	public void remove(Genero t) {
		EntityManager em = UtilDB.getEntityManager();
		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();
	}

	@Override
	public Genero get(Object pk) {
		return UtilDB.getEntityManager().find(Genero.class, pk);
	}

	@Override
	public List<Genero> getAll() {
		return UtilDB.getEntityManager().createQuery("SELECT u FROM Genero u", Genero.class).getResultList();
	}

}
