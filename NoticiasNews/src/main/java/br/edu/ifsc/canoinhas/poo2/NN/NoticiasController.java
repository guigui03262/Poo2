package br.edu.ifsc.canoinhas.poo2.NN;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class NoticiasController {

	@FXML
	private Button BN1;
	
	@FXML
	private Button BPerfil;
	
	@FXML
	private Button BLogout;
	
	@FXML
	private Button BSair;
	
	@FXML
	private void APerfil() {
		try {
			App.setRoot("perfil");
		} catch (IOException e) {
			System.err.println("Erro ao carregar a janela principal");
		}
	}
	
	@FXML
	private void AN1() {
		try {
			App.setRoot("n1");
		} catch (IOException e) {
			System.err.println("Erro ao carregar a janela principal");
		}
	}
	
	@FXML
	private void ALogout() {
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