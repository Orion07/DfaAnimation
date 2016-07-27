package javafxapplication2;

import javafx.scene.shape.Circle;

/**
 *
 * @author Orion
 */
public class State 
{
    private State path0 = null;
    private State path1 = null;
    private boolean startState = false;
    private boolean finalState = false;
    private boolean deadState = false;
    private Circle circle = null;
    private String string;


    public State getPath0() {
        return path0;
    }

    public State getPath1() {
        return path1;
    }
    public void setPath(State path0,State path1) {
        this.path0 = path0;
        this.path1 = path1;
    }
    public boolean isStartState() {
        return startState;
    }

    public void setStartState(boolean startState) {
        this.startState = startState;
    }

    public boolean isFinalState() {
        return finalState;
    }

    public void setFinalState(boolean finalState) {
        this.finalState = finalState;
    }
    
    public boolean isDeadState() {
        return deadState;
    }

    public void setDeadState(boolean deadState) {
        this.deadState = deadState;
    }
    public Circle getCircle() {
        return circle;
    }
    
    public State(Circle circle)
    {
        this.circle = circle;
    }
    
    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

}
