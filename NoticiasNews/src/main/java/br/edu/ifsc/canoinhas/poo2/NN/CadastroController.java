package br.edu.ifsc.canoinhas.poo2.NN;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CadastroController {

	@FXML
    private TextField TFEmail;
	
	@FXML
    private TextField TFNome;
	
	@FXML
    private TextField TFNick;
	
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
			System.err.println("Erro ao carregar a janela principal");
		}
	}
	
	@FXML
	private void AVoltar() {
		try {
			App.setRoot("principal");
		} catch (IOException e) {
			System.err.println("Erro ao carregar a janela principal");
		}
	}
	
	@FXML
    void ASair(ActionEvent event) throws IOException {
    	Platform.exit();
    }
    
}