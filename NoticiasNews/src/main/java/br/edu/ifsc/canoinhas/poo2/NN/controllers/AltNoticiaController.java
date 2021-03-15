package br.edu.ifsc.canoinhas.poo2.NN.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsc.canoinhas.poo2.NN.AlertUtil;
import br.edu.ifsc.canoinhas.poo2.NN.App;
import br.edu.ifsc.canoinhas.poo2.NN.FXMLUtil;
import br.edu.ifsc.canoinhas.poo2.NN.db.GeneroDAO;
import br.edu.ifsc.canoinhas.poo2.NN.db.NoticiasDAO;
import br.edu.ifsc.canoinhas.poo2.NN.entities.Genero;
import br.edu.ifsc.canoinhas.poo2.NN.entities.Noticias;
import br.edu.ifsc.canoinhas.poo2.NN.entities.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AltNoticiaController {
	
	private User user;
	private Noticias nnews;
	
    @FXML
    private TextField tfTitulo;

    @FXML
    private TextArea taDescricao;

    @FXML
    private ListView<String> lvGenero;

    @FXML
    private ListView<String> lvGenADD;

    @FXML
    private Button btADDGen;

    @FXML
    private Button btRemoveGen;

    @FXML
    private Button btAlterar;

    @FXML
    private Button btVoltar;

    @FXML
    void AAddGen(ActionEvent event) {
    	if((lvGenero.getSelectionModel().getSelectedItem() == null)) {
			AlertUtil.error(null, null, null);
		}else {
			String cName = lvGenero.getSelectionModel().getSelectedItem();
			Genero c = new GeneroDAO().get(cName);
			nnews.getGenero().add(c);
			UpdateNNGeneros();
			UpdateGeneros();
		}
    }

    @FXML
    void ARemoveerGen(ActionEvent event) {
    	String cName = lvGenADD.getSelectionModel().getSelectedItem();
		Genero c = new GeneroDAO().get(cName);
		nnews.getGenero().remove(c);
		UpdateNNGeneros();
		UpdateGeneros();
    }

    @FXML
    void AAlterar(ActionEvent event) {
    	String titulo = tfTitulo.getText();
		String descricao = taDescricao.getText();

		if (titulo.isBlank()) {
			Alert alert = AlertUtil.error("Erro!", "Erro!", "Erro");
			alert.showAndWait();
			return;
		}
		if (descricao.isBlank()) {
			Alert alert = AlertUtil.error("Erro!", "Erro!", "Erro");
			alert.showAndWait();
			return;
		}
		
		nnews.setTitulo(titulo);
		new NoticiasDAO().persist(nnews);
		nnews.setDescricao(descricao);
		new NoticiasDAO().persist(nnews);
    }

    @FXML
    void AVoltar(ActionEvent event) {
    	try {
			App.setRoot("noticias");
			NoticiasController controller = FXMLUtil.getNoticiasController();
	        controller.UpdateNews(user);
		} catch (IOException e) {
			Alert alert = AlertUtil.error("Erro!", "Erro!", "Erro");
			alert.showAndWait();
			return;
		}
    }
    
    @FXML
	private void UpdateNNGeneros() {
		if (nnews == null) {
			AlertUtil.error("Erro", "Erro", "Erro");
		}
		List<String> newsGen = new ArrayList<>();
		for (Genero g : nnews.getGenero())
			newsGen.add(g.getNomegenero());
		lvGenADD.setItems((FXCollections.observableList(newsGen)));
	
		if (nnews.getGenero().isEmpty()) {
			btRemoveGen.setDisable(true);
		} else {
			lvGenADD.getSelectionModel().select(0);
			btRemoveGen.setDisable(false);
			
		}
		
	}

	public void UpdateGeneros() {
		UpdateNNGeneros();
		List<String> generos = new ArrayList<>();
		
		if (generos.isEmpty()) {
			AlertUtil.error("Erro", "Erro", "Erro");
		}
		for (Genero g : new GeneroDAO().getAll()) {
			if (!nnews.getGenero().contains(g)) {
				generos.add(g.getNomegenero());
			}
		}
		lvGenero.setItems((FXCollections.observableArrayList(generos)));

		if (generos.isEmpty()) {
			btADDGen.setDisable(true);
		} else {
			lvGenADD.getSelectionModel().select(0);
			btADDGen.setDisable(false);
		}
	}
    
	public void UserInfo(User u) {
    	this.user = u;
    	
    }
	
    public void UpdateInfo(Noticias nn) {
    	this.nnews = nn;
    	tfTitulo.setText(nnews.getTitulo());
    	taDescricao.setText(nnews.getDescricao());
    	UpdateNNGeneros();
		UpdateGeneros();
    }

}
