package br.edu.ifsc.canoinhas.poo2.NN.controllers;

import java.io.IOException;

import br.edu.ifsc.canoinhas.poo2.NN.App;
import br.edu.ifsc.canoinhas.poo2.NN.FXMLUtil;
import br.edu.ifsc.canoinhas.poo2.NN.db.UserDAO;
import br.edu.ifsc.canoinhas.poo2.NN.entities.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class UserController {

	private User user;

	@FXML
	private TextField TFEmail;

	@FXML
	private TextField PFSenha;

	@FXML
	private Button BAlterar;

	@FXML
	private Button BVoltar;

	@FXML
	private Button BSair;

	@FXML
	private void AVoltar() {
		try {
			App.setRoot("noticias");
			NoticiasController controller = FXMLUtil.getNoticiasController();
	        controller.UpdateNews(user);
		} catch (IOException e) {
			System.err.println("Erro ao carregar a janela principal");
		}
	}

	@FXML
	void AAlterar(ActionEvent event) {
		String nome = TFEmail.getText();
		String senha = PFSenha.getText();

		user.setUsername(nome);
		new UserDAO().persist(user);
		
		user.setPassword(senha);
		new UserDAO().persist(user);
	}

	@FXML
	void ARemover(ActionEvent event) throws IOException {
		User uu = new UserDAO().get(user.getEmail());
		UserDAO u = new UserDAO();
		u.remove(uu);
		App.setRoot("principal");
	}

	@FXML
	void ASair(ActionEvent event) throws IOException {
		Platform.exit();
	}

	public void UserInfo(User u) {
		this.user = u;
		PFSenha.setText(user.getPassword());
	}
}
