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

public class AddNoticiaController {

	private User u;
	private Noticias news;
	
	@FXML
	private TextField TFTitulo;

	@FXML
	private TextArea TADescricao;

	@FXML
	private ListView<String> LVGenero;

	@FXML
	private Button BAdd;

	@FXML
	private Button BVoltar;
	
	@FXML
	private Button btAddGenero;

	@FXML
	void AVoltar(ActionEvent event) {
		try {
			App.setRoot("noticias");
			NoticiasController controller = FXMLUtil.getNoticiasController();
	        controller.UpdateNews(u);
		} catch (IOException e) {
			Alert alert = AlertUtil.error("Erro!", "Erro!", "Erro");
			alert.showAndWait();
			return;
		}
	}
	
	@FXML
	void AAddGenero(ActionEvent event) {
		if((LVGenero.getSelectionModel().getSelectedItem() == null)) {
			AlertUtil.error("Erro", "Erro", "Erro");
		}else {
			String cName = LVGenero.getSelectionModel().getSelectedItem();
			Genero c = new GeneroDAO().get(cName);
			news.getGenero().add(c);
			UpdateGeneros();
		}
	}

	@FXML
	void Aadd(ActionEvent event) {
		String titulo = TFTitulo.getText();
		String descricao = TADescricao.getText();
			
		if (titulo.isBlank()) {
			Alert alert = AlertUtil.error("Erro!", "Erro!", "Digite o e-mail!");
			alert.showAndWait();
			return;
		}
		if (descricao.isBlank()) {
			Alert alert = AlertUtil.error("Erro!", "Erro!", "Digite a senha!");
			alert.showAndWait();
			return;
		}
		
		news.setTitulo(titulo);
		new NoticiasDAO().persist(news);
		news.setDescricao(descricao);
		new NoticiasDAO().persist(news);
		news.setGenero(news.getGenero());
		new NoticiasDAO().persist(news);

	}
	
	public void UpdateGeneros() {
		List<String> gg = new ArrayList<>();
		for (Genero g : new GeneroDAO().getAll()) {
			if(!news.getGenero().contains(g)) {
				gg.add(g.getNomegenero());
			}
		}
		LVGenero.setItems((FXCollections.observableArrayList(gg)));
	}
	
	public void UserInfo(User u) {
		this.u = u;
		
		Noticias news = new Noticias(0);		
		Genero a = new GeneroDAO().get("Noticia");
		news.getGenero().add(a);
		new NoticiasDAO().persist(news);
		this.news = news;
		UpdateGeneros();
		
	}

}
