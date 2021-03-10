package br.edu.ifsc.canoinhas.poo2.NN;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class N1Controller {
	
	@FXML
	private Button BVoltar;
	
	@FXML
	private Button BSair;
	
	@FXML
	private void AVoltar() {
		try {
			App.setRoot("noticias");
		} catch (IOException e) {
			System.err.println("Erro ao carregar a janela principal");
		}
	}
	
	@FXML
    void ASair(ActionEvent event) throws IOException {
    	Platform.exit();
    }
    
}
