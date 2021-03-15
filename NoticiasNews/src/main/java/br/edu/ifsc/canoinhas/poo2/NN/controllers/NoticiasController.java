package br.edu.ifsc.canoinhas.poo2.NN.controllers;

import java.io.IOException;

import br.edu.ifsc.canoinhas.poo2.NN.AlertUtil;
import br.edu.ifsc.canoinhas.poo2.NN.App;
import br.edu.ifsc.canoinhas.poo2.NN.FXMLUtil;
import br.edu.ifsc.canoinhas.poo2.NN.db.NoticiasDAO;
import br.edu.ifsc.canoinhas.poo2.NN.entities.Noticias;
import br.edu.ifsc.canoinhas.poo2.NN.entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class NoticiasController {

	private User u;

	@FXML
	private Label lblUser;

	@FXML
	private TableView<Noticias> listNew;
	
	@SuppressWarnings("rawtypes")
	@FXML
	private TableColumn ID;
	
	@SuppressWarnings("rawtypes")
	@FXML
	private TableColumn Titulo;

	@FXML
	private Button btnPerfil;

	@FXML
	private Button btnAN;

	@FXML
	private Button btnRN;

	@FXML
	private Button btnAddN;

	@FXML
	private Button btnSair;
	
	@FXML
	private Button btNewGenero;
	
	@FXML
	void ANovoGenero(ActionEvent event) {
		try {
			App.setRoot("genero");
			GeneroController controller = FXMLUtil.getGeneroController();
	        controller.UserInfo(u);
			
		} catch (IOException e) {
			Alert alert = AlertUtil.error("Erro!", "Erro!", "Erro");
			alert.showAndWait();
			return;
		}
	}
	
	@SuppressWarnings("unchecked")
	@FXML
	public void UpdateNews(User u) {
		this.u = u;
		lblUser.setText(u.getUsername());
		
		ID.setCellValueFactory(new PropertyValueFactory<Noticias, Integer>("id"));
        Titulo.setCellValueFactory(new PropertyValueFactory<Noticias, String>("titulo"));
        listNew.setItems(getNoticias());
        
	}
	
	public ObservableList<Noticias> getNoticias() {
        ObservableList<Noticias> newList = FXCollections.observableArrayList();
        
        for (Noticias ent : new NoticiasDAO().getAll()) {
            newList.add(ent);
        }
        return newList;
    }

	@FXML
	void AAdicionar(ActionEvent event) throws IOException {
		App.setRoot("addnoticia");
		AddNoticiaController controller = FXMLUtil.getAddNoticiaController();
        controller.UserInfo(u);
	}

	@FXML
	void AAlterar(ActionEvent event) throws IOException {
		Noticias apnew = listNew.getSelectionModel().getSelectedItem();
		
		App.setRoot("altnoticia");
		AltNoticiaController controller = FXMLUtil.getAltNoticiaController();
        controller.UserInfo(u);
        controller.UpdateInfo(apnew);
	}

	@FXML
	void APerfil(ActionEvent event) throws IOException {
		App.setRoot("user");
		UserController controller = FXMLUtil.getUserController();
        controller.UserInfo(u);
	}

	@FXML
    void ARemover(ActionEvent event) {
		Noticias apnew = listNew.getSelectionModel().getSelectedItem();
		apnew = new NoticiasDAO().get(apnew.getId());
		new NoticiasDAO().remove(apnew);
		UpdateNews(u);
    }

	@FXML
	void ASair(ActionEvent event) throws IOException {
		App.setRoot("principal");
	}

}