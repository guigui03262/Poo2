package br.edu.ifsc.canoinhas.poo2.NN.controllers;

import java.io.IOException;

import br.edu.ifsc.canoinhas.poo2.NN.AlertUtil;
import br.edu.ifsc.canoinhas.poo2.NN.App;
import br.edu.ifsc.canoinhas.poo2.NN.FXMLUtil;
import br.edu.ifsc.canoinhas.poo2.NN.db.UserDAO;
import br.edu.ifsc.canoinhas.poo2.NN.entities.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

	@FXML
	private TextField TFEmail;

	@FXML
	private PasswordField PFSenha;

	@FXML
	private Button BLogar;

	@FXML
	private Button BCadastro;

	@FXML
	private Button BVoltar;

	@FXML
	private Button BSair;

	@FXML
	private void ALogin() {
		try {
			String email = TFEmail.getText();
			String password = PFSenha.getText();

			if (email.isBlank()) {
				Alert alert = AlertUtil.error("Erro!", "Erro!", "Digite o e-mail!");
				alert.showAndWait();
				return;
			}

			if (password.isBlank()) {
				Alert alert = AlertUtil.error("Erro!", "Erro!", "Digite a senha");
				alert.showAndWait();
				return;
			}
			User u = new UserDAO().get(email);
			if (u == null) {
				Alert alert = AlertUtil.error("Erro!", "Erro!", "Email e/ou senha inválidos!");
				alert.showAndWait();
				return;
			}
			if (!u.getPassword().contentEquals(password)) {
				Alert alert = AlertUtil.error("Erro!", "Erro!", "Email e/ou senha inválidos!");
				alert.showAndWait();
				return;
			}
			new UserDAO().persist(u);
			
			App.setRoot("noticias");
			NoticiasController controller = FXMLUtil.getNoticiasController();
	        controller.UpdateNews(u);
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void ACadastro() {
		try {
			App.setRoot("cadastro");
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
	void ASair(ActionEvent event) throws IOException {
		Platform.exit();
	}

}
