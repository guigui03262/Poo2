package br.edu.ifsc.canoinhas.poo2.NN;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
public class App extends Application {

    private static Stage stage;

    @Override
	public void start(Stage stge) {
		stage = stge;
		stage.setScene(FXMLUtil.loadScene("principal"));
		stage.setTitle("Banco");
		stage.show();
	}

    public static void setRoot(String fxml) throws IOException {
    	stage.setScene(FXMLUtil.loadScene(fxml));
    }

    /*private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }*/

}