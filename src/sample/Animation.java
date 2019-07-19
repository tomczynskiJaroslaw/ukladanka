package sample;

import com.sun.istack.internal.NotNull;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.util.Duration;


public class Animation {
    private javafx.event.EventHandler<ActionEvent> eventHandler = (z)->{};
    private Node node;
    private double from = 0;
    private double to = 1;

    public Animation finishEvent(javafx.event.EventHandler<ActionEvent> event){
        this.eventHandler=event;
        return this;
    }

    public Animation from(double from){
        this.from=from;
        return this;
    }

    public Animation to(double to){
        this.to=to;
        return build();
    }

    private Animation build(){
        Animation animation = new Animation(node);
        animation.node=node;
        animation.eventHandler=eventHandler;
        animation.from=from;
        animation.to=to;
        return animation;
    }


    public Animation(@NotNull Node node){
        this.node=node;
    }

    public void animation() {
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setNode(node);
        scaleTransition.setDuration(Duration.millis(1000));
        scaleTransition.setFromX(from);
        scaleTransition.setFromY(from);
        scaleTransition.setToX(to);
        scaleTransition.setToY(to);
        scaleTransition.setCycleCount(1);
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(node);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setCycleCount(1);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
        scaleTransition.setOnFinished(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                eventHandler.handle(event);
            }
        });
    }


}
