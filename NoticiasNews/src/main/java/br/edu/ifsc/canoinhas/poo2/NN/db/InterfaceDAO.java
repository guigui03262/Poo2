package br.edu.ifsc.canoinhas.poo2.NN.db;
import java.util.List;

public interface InterfaceDAO<T> {
	
	public void persist(T referencia);

	public void remove(T referencia);
	
	public T get(Object pk);

	public List<T> getAll();
}