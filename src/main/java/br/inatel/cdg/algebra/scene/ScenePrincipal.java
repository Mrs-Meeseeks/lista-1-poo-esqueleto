package br.inatel.cdg.algebra.scene;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScenePrincipal {


    private Button botaoCoefAng, btnCalcCoefLinear; //Button representa botoes

    //LABELS
    private Label p1xLabel;
    private Label p2xLabel;
    private Label p1yLabel;
    private Label p2yLabel;

    //INPUTS
    private TextField p1x;
    private TextField p1y;
    private TextField p2x;
    private TextField p2y;

    //OUTPUT
    private TextField coeficienteAngular;
    private TextField coeficienteLinear;

    public void criaScenePrincipal(Stage stage){

        //Criando os labels para os pontos
        p1xLabel = new Label("p1.x");
        p1yLabel = new Label("p1.y");
        p2xLabel = new Label("p2.x");
        p2yLabel = new Label("p2.y");

        //Criando os inputs para os pontos
        p1x = new TextField();
        p2x = new TextField();
        p1y = new TextField();
        p2y = new TextField();


        //HBox é usado para agrupar elementos horizontalmente
        HBox grupoHorizontal1x = new HBox(p1xLabel, p1x);
        HBox grupoHorizontal1y = new HBox(p1yLabel, p1y);
        HBox grupoHorizontal2x = new HBox(p2xLabel, p2x);
        HBox grupoHorizontal2y = new HBox(p2yLabel, p2y);


        //Agora vamos criar a area que mostrará o resultado
        coeficienteAngular = new TextField();
        coeficienteAngular.setEditable(false);

        //Agora vamos criar a area que mostrará o resultado
        coeficienteLinear = new TextField();
        coeficienteLinear.setEditable(false);

        //Criamos o botão
        botaoCoefAng = new Button("Calcular C A");

        //Criamos a ação que o botão responderá as ser pressionado
        botaoCoefAng.setOnAction(evento -> {
                    //Aqui dentro é a ação que será executado ao pressionar o botão
                    double x1 = Double.parseDouble(p1x.getText());
                    double y1 = Double.parseDouble(p1y.getText());
                    double x2 = Double.parseDouble(p2x.getText());
                    double y2 = Double.parseDouble(p2y.getText());
                    Ponto p1 = new Ponto(x1, y1);
                    Ponto p2 = new Ponto(x2, y2);
                    double coefAngular = calcularCoeficienteAngular(p1, p2);

                    coeficienteAngular.setText("Coef Angular = " + coefAngular);

                }
        );

        btnCalcCoefLinear = new Button("Calcular C L");
        btnCalcCoefLinear.setOnAction(evento -> {
            //Aqui dentro é a ação que será executado ao pressionar o botão
            double x1 = Double.parseDouble(p1x.getText());
            double y1 = Double.parseDouble(p1y.getText());
            double x2 = Double.parseDouble(p2x.getText());
            double y2 = Double.parseDouble(p2y.getText());
            Ponto p1 = new Ponto(x1, y1);
            Ponto p2 = new Ponto(x2, y2);
            double coefAngular = calcularCoeficienteAngular(p1, p2);
            double coefLinear = calcularCoeficienteLinear(p1, coefAngular);

            coeficienteLinear.setText("Coef Linear = " + coefLinear);
        });

        HBox grupoHorizontalbotoes = new HBox(botaoCoefAng, btnCalcCoefLinear);


        //VBox é usada para agrupar elementos verticalmente
        //No construtor passamos todos os elementos que serão agrupados, que podem ser outros grupos
        VBox layoutFinal = new VBox(grupoHorizontal1x, grupoHorizontal1y, grupoHorizontal2x, grupoHorizontal2y, grupoHorizontalbotoes, coeficienteAngular, coeficienteLinear);
        //Criamos a Scene
        Scene scene = new Scene(layoutFinal, 300 , 200);

        stage.setTitle("Software Para Calculos de Álgebra Linear");
        stage.setScene(scene);
        stage.show();
    }

    private double calcularCoeficienteLinear(Ponto p, double coefAngular) {
        return p.getY() - coefAngular * p.getX();
    }

    private double calcularCoeficienteAngular(Ponto p1, Ponto p2) {
        double eq1 = p2.getY() - p1.getY();
        double eq2 = p2.getX() - p1.getX();
        return eq1/eq2;
    }

}