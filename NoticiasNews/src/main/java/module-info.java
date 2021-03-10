module br.edu.ifsc.canoinhas.poo2.NN {
    requires javafx.controls;
    requires javafx.fxml;

    opens br.edu.ifsc.canoinhas.poo2.NN to javafx.fxml;
    exports br.edu.ifsc.canoinhas.poo2.NN;
}