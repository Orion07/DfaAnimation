package javafxapplication2;

import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import javax.swing.JOptionPane;


/**
 *
 * @author Orion
 */
public class DFA
{
    private State currentState,tempState;
    private String string,tempString;
    private Label label,processingStr,status;
    SequentialTransition seq;
    public DFA(State startState,String string,Label label,Label processingStr,Label status)
    {
        this.currentState = startState;
        this.tempState = startState;
        this.string = string;
        this.tempString = string;
        this.label = label;
        this.processingStr = processingStr;
        this.status = status;
        seq = new SequentialTransition();
    }
    public void start()
    {
        
        boolean resultString = checkString(string);
        if(resultString){
            status.setText("Otomat Başlatılıyor.");
            boolean resultDFA = executeDFA();

            seq.statusProperty().addListener(new ChangeListener<Status>() {

                @Override
                public void changed(ObservableValue<? extends Status> observable, Status oldValue, Status newValue) {
                    if(newValue == Status.STOPPED)
                    {
                        if(resultDFA)
                            status.setText("String tanındı.");
                        else
                            status.setText("String tanınmadı.");
                    }
                }
            });
            seq.play();
        }else{
            JOptionPane.showMessageDialog(null, "String formatı otomata uygun değil.","DFA", JOptionPane.WARNING_MESSAGE);
        }
    }
    public boolean checkString(String string)
    {
        if(string.length() == 0) return false;
        for(int i = 0;i<string.length();i++)
        {
            String tmpStr = string.substring(i, i+1);
            int counter = 0;
            for(int x = 0;x<2;x++)
            {
                if(tmpStr.equals(String.valueOf(x)))
                    counter++;
            }
            if(counter!=1)
                return false;
        }
        return true;
    }
    public boolean executeDFA()
    {
        State previousState = null;
        while(string.length() > 0)
        {
            status.setText("Otomat Çalışıyor.");
            char path = string.charAt(0);
            previousState = currentState;
            if(path == '0')
                currentState = currentState.getPath0();
            else
                currentState = currentState.getPath1();
            if(previousState == currentState)
            {
                Path yourselfPath = new Path();
                MoveTo moveTo = new MoveTo();
                yourselfPath.getElements().add(new MoveTo(currentState.getCircle().getLayoutX() -label.getLayoutX(),currentState.getCircle().getLayoutY() -label.getLayoutY())); 
                yourselfPath.getElements().add(new CubicCurveTo(20,50,200,50,currentState.getCircle().getLayoutX() -label.getLayoutX(),currentState.getCircle().getLayoutY() -label.getLayoutY()));
                PathTransition ptt=new PathTransition();
                ptt.setDuration(Duration.millis(2000));
                ptt.setPath(yourselfPath);
                ptt.setNode(label);
                ptt.setCycleCount(1);
                ptt.setOrientation(PathTransition.OrientationType.NONE);
                ptt.statusProperty().addListener( new ChangeListener<Status>() {

                    @Override
                    public void changed(ObservableValue<? extends Status> observable, Status oldValue, Status newValue) {
                         switch(newValue)
                        {
                            case RUNNING:
                                processingStr.setText(tempString);
                                char path = tempString.charAt(0);
                                if(path == '0')
                                     tempState = tempState.getPath0();
                                 else
                                     tempState = tempState.getPath1();
                                label.setText(String.valueOf(path));
                                System.out.println(tempString.length());
                                if(tempString.length() == 1 && !tempState.isFinalState())
                                 {
                                     tempState.getCircle().setFill(Color.RED);
                                 }else
                                 {
                                    tempState.getCircle().setFill(Color.GREEN);
                                 }
                                break;
                            case STOPPED:
                                tempString = tempString.substring(1);
                                if(tempString.length() != 0)
                                 {
                                    if(tempState.isFinalState())
                                        tempState.getCircle().setFill(Color.SKYBLUE);
                                    else
                                        tempState.getCircle().setFill(Color.WHITE);
                                 }
                                break;
                        }
                    }
                });
                seq.getChildren().add(ptt);
            }
            else
            {
                label.setText(String.valueOf(path));
                Path circlePath = new Path();
                MoveTo moveTo = new MoveTo();
                circlePath.getElements().add(new MoveTo(previousState.getCircle().getLayoutX() - label.getLayoutX(),previousState.getCircle().getLayoutY() - label.getLayoutY())); 
                circlePath.getElements().add(new LineTo(currentState.getCircle().getLayoutX() - label.getLayoutX(), currentState.getCircle().getLayoutY() - label.getLayoutY()));
                circlePath.setStrokeWidth(1);
                circlePath.setStroke(Color.BLACK);
                PathTransition ptt=new PathTransition();
                ptt.setDuration(Duration.millis(2000));
                ptt.setPath(circlePath);
                ptt.setNode(label);
                ptt.setCycleCount(1);
                ptt.setAutoReverse(true);
                ptt.setOrientation(PathTransition.OrientationType.NONE);
                ptt.statusProperty().addListener(new ChangeListener<Animation.Status>(){

                @Override
                public void changed(ObservableValue<? extends Animation.Status> observable, Animation.Status oldValue, Animation.Status newValue) {
                   
                   switch(newValue)
                   {
                       case RUNNING:
                           processingStr.setText(tempString);
                           char path = tempString.charAt(0);
                           if(path == '0')
                                tempState = tempState.getPath0();
                            else
                                tempState = tempState.getPath1();
                           label.setText(String.valueOf(path));
                           System.out.println(tempString.length());
                           if(tempString.length() == 1 && !tempState.isFinalState())
                            {
                                tempState.getCircle().setFill(Color.RED);
                            }else
                            {
                               tempState.getCircle().setFill(Color.GREEN);
                            }
                           break;
                       case STOPPED:
                           tempString = tempString.substring(1);
                           if(tempString.length() != 0)
                            {
                               if(tempState.isFinalState())
                                        tempState.getCircle().setFill(Color.SKYBLUE);
                                    else
                                        tempState.getCircle().setFill(Color.WHITE);
                            }
                           break;
                   }
                }
                
            });
            seq.getChildren().add(ptt); 
            }
           
            if(string.length() == 1 && currentState.isFinalState())
            {
                return true;
            }else if(string.length() == 1 && !currentState.isFinalState())
            {
                return false;
            }
                string = string.substring(1);
        }
        return false;
    }
}
