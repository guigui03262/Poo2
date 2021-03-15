package br.edu.ifsc.canoinhas.poo2.NN.controllers;

import java.io.IOException;

import br.edu.ifsc.canoinhas.poo2.NN.AlertUtil;
import br.edu.ifsc.canoinhas.poo2.NN.App;
import br.edu.ifsc.canoinhas.poo2.NN.FXMLUtil;
import br.edu.ifsc.canoinhas.poo2.NN.db.GeneroDAO;
import br.edu.ifsc.canoinhas.poo2.NN.db.NoticiasDAO;
import br.edu.ifsc.canoinhas.poo2.NN.entities.Genero;
import br.edu.ifsc.canoinhas.poo2.NN.entities.Noticias;
import br.edu.ifsc.canoinhas.poo2.NN.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class GeneroController {
	
	private User u;
	
	@FXML
    private TextField tfGenero;

    @FXML
    private TextField tfChave;

    @FXML
    private Button btRemover;

    @FXML
    private Button btAlterar;

    @FXML
    private Button btAdd;

    @FXML
    private Button btVoltar;

    @FXML
    void AAdd(ActionEvent event) {
    	String genero = tfGenero.getText();
		String chave = tfChave.getText();
			
		if (genero.isBlank()) {
			Alert alert = AlertUtil.error("Erro!", "Erro!", "Digite o e-mail!");
			alert.showAndWait();
			return;
		}
		if (chave.isBlank()) {
			Alert alert = AlertUtil.error("Erro!", "Erro!", "Digite a senha!");
			alert.showAndWait();
			return;
		}
		Genero gen = new Genero(genero, chave);
		new GeneroDAO().persist(gen);

    }

    @FXML
    void AAlterar(ActionEvent event) {
    	String genero = tfGenero.getText();
		String chave = tfChave.getText();
			
		if (genero.isBlank()) {
			Alert alert = AlertUtil.error("Erro!", "Erro!", "Digite o e-mail!");
			alert.showAndWait();
			return;
		}
		Genero gen = new GeneroDAO().get(genero);
		if (chave.isBlank()) {
			Alert alert = AlertUtil.error("Erro!", "Erro!", "Digite a senha!");
			alert.showAndWait();
			return;
		}
		
		gen.setPalavrachave(chave);
		new GeneroDAO().persist(gen);

    }

    @FXML
    void ARemover(ActionEvent event) {
    	Genero uu = new GeneroDAO().get(tfGenero.getText());
		GeneroDAO us = new GeneroDAO();
		for(Noticias nn : new NoticiasDAO().getAll()) {
			if(nn.getGenero().contains(uu)) {
				nn.getGenero().remove(uu);
			}
		}
		us.remove(uu);

    }

    @FXML
    void AVoltar(ActionEvent event) throws IOException {
    	App.setRoot("noticias");
    	NoticiasController controller = FXMLUtil.getNoticiasController();
        controller.UpdateNews(u);
    }
    
    public void UserInfo(User u) {
		this.u = u;
		
	}

}
