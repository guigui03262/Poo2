package br.edu.ifsc.canoinhas.poo2.NN;

import java.io.IOException;

import br.edu.ifsc.canoinhas.poo2.NN.controllers.AddNoticiaController;
import br.edu.ifsc.canoinhas.poo2.NN.controllers.AltNoticiaController;
import br.edu.ifsc.canoinhas.poo2.NN.controllers.GeneroController;
import br.edu.ifsc.canoinhas.poo2.NN.controllers.NoticiasController;
import br.edu.ifsc.canoinhas.poo2.NN.controllers.UserController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class FXMLUtil {

	private static AltNoticiaController altnoticiaController = null;
	private static UserController userController = null;
	private static AddNoticiaController addnoticiaController = null;
	private static NoticiasController noticiasController = null;
	private static GeneroController generoController = null;

	public static Scene loadScene(String fxml) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
			Scene scene = new Scene(fxmlLoader.load());
			if (fxmlLoader.getController() instanceof NoticiasController)
				noticiasController = fxmlLoader.getController();
			else
				noticiasController = null;
			if (fxmlLoader.getController() instanceof UserController)
				userController = fxmlLoader.getController();
			else
				userController = null;
			if (fxmlLoader.getController() instanceof AddNoticiaController)
				addnoticiaController = fxmlLoader.getController();
			else
				addnoticiaController = null;
			if (fxmlLoader.getController() instanceof AltNoticiaController)
				altnoticiaController = fxmlLoader.getController();
			else
				altnoticiaController = null;
			if (fxmlLoader.getController() instanceof GeneroController)
				generoController = fxmlLoader.getController();
			else
				generoController = null;
			return scene;
		} catch (IOException eIO) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.showAndWait();
			return null;
		} catch (IllegalStateException eIllegalState) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.showAndWait();
			return null;
		}
	}

	public static NoticiasController getNoticiasController() {
		return noticiasController;
	}
	
	public static UserController getUserController() {
		return userController;
	}
	
	public static AddNoticiaController getAddNoticiaController() {
		return addnoticiaController;
	}
	
	public static AltNoticiaController getAltNoticiaController() {
		return altnoticiaController;
	}
	
	public static GeneroController getGeneroController() {
		return generoController;
	}

}
