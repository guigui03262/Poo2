package br.edu.ifsc.canoinhas.poo2.NN.controllers;

import java.io.IOException;

import br.edu.ifsc.canoinhas.poo2.NN.AlertUtil;
import br.edu.ifsc.canoinhas.poo2.NN.App;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
			Alert alert = AlertUtil.error("Erro!", "Erro!", "Erro");
			alert.showAndWait();
			return;
		}
	}
	
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
    void ASair(ActionEvent event) throws IOException {
    	Platform.exit();
    }
    
}