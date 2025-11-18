package imcapp;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class IMCApp extends Application {

    private ObservableList<Pessoa> lista = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {

        Label lblNome = new Label("Nome:");
        TextField txtNome = new TextField();

        Label lblAltura = new Label("Altura (m):");
        TextField txtAltura = new TextField();

        Label lblPeso = new Label("Peso (kg):");
        TextField txtPeso = new TextField();

        Label lblResultado = new Label("IMC:");

        Button btnCalcular = new Button("Calcular IMC");
        Button btnSalvar = new Button("Salvar");
        Button btnCarregar = new Button("Carregar");

        TableView<Pessoa> tabela = new TableView<>();
        TableColumn<Pessoa, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNome()));

        TableColumn<Pessoa, Number> colAltura = new TableColumn<>("Altura");
        colAltura.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getAltura()));

        TableColumn<Pessoa, Number> colPeso = new TableColumn<>("Peso");
        colPeso.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getPeso()));

        TableColumn<Pessoa, Number> colIMC = new TableColumn<>("IMC");
        colIMC.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getImc()));

        tabela.getColumns().addAll(colNome, colAltura, colPeso, colIMC);
        tabela.setItems(lista);

        // Botão calcular
        btnCalcular.setOnAction(e -> {
            try {
                double altura = Double.parseDouble(txtAltura.getText());
                double peso = Double.parseDouble(txtPeso.getText());
                Pessoa p = new Pessoa(txtNome.getText(), altura, peso);

                lblResultado.setText("IMC: " + String.format("%.2f", p.getImc()) +
                        " - " + p.classificacao());

            } catch (Exception erro) {
                lblResultado.setText("Erro: verifique os valores.");
            }
        });

        // Botão salvar
        btnSalvar.setOnAction(e -> {
            try {
                Pessoa p = new Pessoa(
                        txtNome.getText(),
                        Double.parseDouble(txtAltura.getText()),
                        Double.parseDouble(txtPeso.getText())
                );
                ArquivoUtil.salvar(p);
                lista.add(p);

                txtNome.clear();
                txtAltura.clear();
                txtPeso.clear();

            } catch (Exception erro) {
                lblResultado.setText("Erro ao salvar.");
            }
        });

        // Botão carregar
        btnCarregar.setOnAction(e -> {
            lista.clear();
            lista.addAll(ArquivoUtil.carregar());
        });

        HBox botoes = new HBox(10, btnCalcular, btnSalvar, btnCarregar);
        VBox root = new VBox(10, lblNome, txtNome, lblAltura, txtAltura,
                lblPeso, txtPeso, botoes, lblResultado, tabela);

        root.setPadding(new Insets(15));

        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("Calculadora de IMC");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

