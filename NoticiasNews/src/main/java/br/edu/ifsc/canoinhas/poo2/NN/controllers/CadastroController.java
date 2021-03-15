package br.edu.ifsc.canoinhas.poo2.NN.controllers;

import java.io.IOException;

import br.edu.ifsc.canoinhas.poo2.NN.AlertUtil;
import br.edu.ifsc.canoinhas.poo2.NN.App;
import br.edu.ifsc.canoinhas.poo2.NN.db.UserDAO;
import br.edu.ifsc.canoinhas.poo2.NN.entities.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CadastroController {
	
	@FXML
	private TextField TFEmail;

	@FXML
	private PasswordField PFSenha;

	@FXML
	private Button BLogin;

	@FXML
	private Button BCadastrar;

	@FXML
	private Button BVoltar;

	@FXML
	private Button BSair;

	@FXML
	private void ALogin() {
		try {
			App.setRoot("login");
		} catch (IOException e) {
			Alert alert = AlertUtil.error("Erro!", "Erro!", "Erro");
			alert.showAndWait();
			return;
		}
	}

	@FXML
	private void AVoltar() {
		try {
			App.setRoot("principal");
		} catch (IOException e) {
			Alert alert = AlertUtil.error("Erro!", "Erro!", "Erro");
			alert.showAndWait();
			return;
		}
	}

	@FXML
	public void cadastro() {
		try {
			String email = TFEmail.getText();
			String password = PFSenha.getText();

			if (email.isBlank()) {
				Alert alert = AlertUtil.error("Erro!", "Erro!", "Digite o e-mail!");
				alert.showAndWait();
				return;
			}
			if (password.isBlank()) {
				Alert alert = AlertUtil.error("Erro!", "Erro!", "Digite a senha!");
				alert.showAndWait();
				return;
			}
			User u = new UserDAO().get(email);
			if (u != null) {
				Alert alert = AlertUtil.error("Erro!", "Erro!", "E-mail já em uso!");
				alert.showAndWait();
				return;
			}

			User uu = new User("nome", password, email);
			new UserDAO().persist(uu);

			App.setRoot("principal");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void ASair(ActionEvent event) throws IOException {
		Platform.exit();
	}

}