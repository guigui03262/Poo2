package br.edu.ifsc.canoinhas.poo2.NN;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrincipalController {
	
	@FXML
	private Button BQS;

	@FXML
	private Button BLogin;
	
	@FXML
	private Button BCadastro;
	
	@FXML
	private Button BSair;
	
	@FXML
	private void AQS() {
		try {
			App.setRoot("qs");
		} catch (IOException e) {
			System.err.println("Erro ao carregar a janela principal");
		}
	}
	
	@FXML
	private void ALogin() {
		try {
			App.setRoot("login");
		} catch (IOException e) {
			System.err.println("Erro ao carregar a janela principal");
		}
	}
	
	@FXML
	private void ACadastro() {
		try {
			App.setRoot("cadastro");
		} catch (IOException e) {
			System.err.println("Erro ao carregar a janela principal");
		}
	}
	
	@FXML
    void ASair(ActionEvent event) throws IOException {
    	Platform.exit();
    }
    
}