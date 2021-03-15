package br.edu.ifsc.canoinhas.poo2.NN;

import br.edu.ifsc.canoinhas.poo2.NN.db.UtilDB;
import javafx.application.Application;

public class Main {

	public static void main(String[] args) {
		UtilDB.initDB();
		Application.launch(App.class);
		UtilDB.closeConn();
	}

}
