package br.edu.ifsc.canoinhas.poo2.NN.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityExistsException;

import br.edu.ifsc.canoinhas.poo2.NN.entities.User;

public class UserDAO implements InterfaceDAO<User> {

	@Override
	public void persist(User t) {
		EntityManager em = UtilDB.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
		} catch (EntityExistsException e) {
			em.getTransaction().rollback();
			User original = get(t.getEmail());
			em.getTransaction().begin();
			original.setPassword(t.getUsername());
			original.setPassword(t.getPassword());
			em.getTransaction().commit();
		}
	}

	@Override
	public void remove(User t) {
		EntityManager em = UtilDB.getEntityManager();
		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();
	}

	@Override
	public User get(Object pk) {
		return UtilDB.getEntityManager().find(User.class, pk);
	}

	@Override
	public List<User> getAll() {
		return UtilDB.getEntityManager().createQuery("SELECT u FROM User u", User.class).getResultList();
	}

}
