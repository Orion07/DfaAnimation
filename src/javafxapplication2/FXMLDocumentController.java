/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.PathTransitionBuilder;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcToBuilder;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathBuilder;
import javafx.util.Duration;

/**
 *
 * @author Orion
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private Circle circleA,circleB,circleC,circleD,circleE,circleF,circleG,circleH,circleI;
    @FXML
    private Button btnStart;
    @FXML
    private TextField txtString;
    @FXML
    private Label currentString,txtProcessingString,txtStatus;

    public void handleButtonAction(ActionEvent event) {
        /*
        A -> Start State
        D,E,F,G,H,I -> Final States
        A -> B,C
        B -> D,C
        C -> B,E
        D -> D,F
        E -> G,E
        F -> D,H
        G -> I,E
        H -> I,H
        I -> I,H
        */
        
        State A = new State(circleA);
        State B = new State(circleB);
        State C = new State(circleC);
        State D = new State(circleD);
        State E = new State(circleE);
        State F = new State(circleF);
        State G = new State(circleG);
        State H = new State(circleH);
        State I = new State(circleI);
        A.setPath(B,C);
        A.setStartState(true);
        A.getCircle().setFill(Color.WHITE);
        B.setPath(D,C);
        B.getCircle().setFill(Color.WHITE);
        C.setPath(B,E);
        C.getCircle().setFill(Color.WHITE);
        D.setPath(D,F);
        D.setFinalState(true);
        D.getCircle().setFill(Color.SKYBLUE);
        E.setPath(G,E);
        E.setFinalState(true);
        E.getCircle().setFill(Color.SKYBLUE);
        F.setPath(D,H);
        F.setFinalState(true);
        F.getCircle().setFill(Color.SKYBLUE);
        G.setPath(I,E);
        G.setFinalState(true);
        G.getCircle().setFill(Color.SKYBLUE);
        H.setPath(I,H);
        H.setFinalState(true);
        H.getCircle().setFill(Color.SKYBLUE);
        I.setPath(I,H);
        I.setFinalState(true);
        I.getCircle().setFill(Color.SKYBLUE);
        DFA dfa = new DFA(A,txtString.getText(),currentString,txtProcessingString,txtStatus);
        dfa.start();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    
}
