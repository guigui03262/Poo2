package br.edu.ifsc.canoinhas.poo2.NN.controllers;

import java.io.IOException;

import br.edu.ifsc.canoinhas.poo2.NN.App;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class QSController {
	
	@FXML
	private Button BVoltar;
	
	@FXML
	private Button BSair;
	
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
