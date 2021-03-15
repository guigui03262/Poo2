package br.edu.ifsc.canoinhas.poo2.NN.db;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import br.edu.ifsc.canoinhas.poo2.NN.AlertUtil;
import br.edu.ifsc.canoinhas.poo2.NN.entities.Genero;
import br.edu.ifsc.canoinhas.poo2.NN.entities.Noticias;
import br.edu.ifsc.canoinhas.poo2.NN.entities.User;
import javafx.scene.control.Alert;

public class UtilDB {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	private static EntityManagerFactory getEntityManagerFactory() {
		if (entityManagerFactory == null)
			entityManagerFactory = Persistence.createEntityManagerFactory("teste");
		return entityManagerFactory;
	}

	public static EntityManager getEntityManager() {
		if (entityManager == null)
			entityManager = getEntityManagerFactory().createEntityManager();
		return entityManager;
	}

	public static void closeConn() {
		if (entityManager != null)
			entityManager.close();
		if (entityManagerFactory != null)
			entityManagerFactory.close();
	}

	public static void initDB() {

		for (User u : consumeAPI(consultAPI()))
			new UserDAO().persist(u);
		
		Genero noticia = new Genero("Noticia", "noticia");
		new GeneroDAO().persist(noticia);
		Genero esporte = new Genero("Esporte", "futebol");
		new GeneroDAO().persist(esporte);
		Genero geral = new Genero("Geral", "politica");
		new GeneroDAO().persist(geral);
		Genero entreterimento = new Genero("Entretenimento", "filme");
		new GeneroDAO().persist(entreterimento);
		
		Noticias n1 = new Noticias(0, "BRASIL NÃO É PARA TODOS", "Jovem preso por falsificação paga a fiança com notas falsas e é preso novamente.");
		new NoticiasDAO().persist(n1);
		Genero a = new GeneroDAO().get("Noticia");
		n1.getGenero().add(a);
		Genero b = new GeneroDAO().get("Geral");
		n1.getGenero().add(b);
		new NoticiasDAO().persist(n1);
		
	}

	public static List<User> consumeAPI(List<String> users) {
		List<User> result = new ArrayList<User>();
		for (int lineIndex = 0; lineIndex < users.size(); lineIndex++) {
			String line = users.get(lineIndex);
			if (line.contains("username")) {
				String username = processJSONLine(line);
				lineIndex++;
				line = users.get(lineIndex);
				String password = processJSONLine(line);
				User user = new User(username, password);
				result.add(user);
			}
		}
		return result;
	}

	private static String processJSONLine(String line) {
		String[] dividedLine = line.split(":");
		String username = dividedLine[1];
		username = username.replace(",", " ");
		username = username.replace("\"", " ");
		username = username.trim();
		return username;
	}

	private static List<String> consultAPI() {
		List<String> result = new ArrayList<>();
		try {
			URL url = new URL("http://www.lucasbueno.com.br/steam.json");
			URLConnection uc = url.openConnection();
			InputStreamReader input = new InputStreamReader(uc.getInputStream());
			BufferedReader in = new BufferedReader(input);
			String inputLine;

			while ((inputLine = in.readLine()) != null)
				result.add(inputLine);

			in.close();
		} catch (Exception e) {
			Alert alert = AlertUtil.error("Erro", "Erro ao consumir a API!", "Erro ao consumir a API!");
			alert.showAndWait();
		}
		return result;
	}

}
