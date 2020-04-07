package cs3500.animator.view;


import java.util.Timer;


import javax.swing.JFrame;

import cs3500.animator.controller.ButtonHandler;
import cs3500.animator.model.ReadOnlyExcellenceOperations;


/**
 * A view that draws and plays the shapes in the animation inside of a Java Swing Panel. It has a
 * timer and animation speed that determine when the next 'tick' in the animation occurs. According
 * to that, it refreshes the model information to the panel and redraws itself using an
 * AnimationTask.
 */
public class VisualView extends JFrame implements AnimatorView {
  private VWPanel panel;
  private ReadOnlyExcellenceOperations model;
  private Timer timer;
  private double speed;
  private int tick;

  /**
   * A public constructor for a Visual View that takes in the readonly model and the speed at which
   * the animation should be played.
   *
   * @param readOnlyModel the model
   * @param speed         the speed of the animation
   */
  VisualView(ReadOnlyExcellenceOperations readOnlyModel, double speed) {
    model = readOnlyModel;
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.tick = 0;
    timer = new Timer();
    this.speed = speed;

    setSize((int) (model.getWidth() - model.getTopLeft().getX()),
            (int) (model.getHeight() - model.getTopLeft().getY()));
    setLocation((int) model.getTopLeft().getX(), (int) model.getTopLeft().getY());

    this.panel = new VWPanel(model.getShapesAt(0));
    this.add(this.panel);
  }

  @Override
  public void refresh(int tick) {
    this.panel.setShapes(model.getShapesAt(tick));
    this.repaint();
  }

  @Override
  public void displayAnimation() {
    this.setVisible(true);
    timer.scheduleAtFixedRate(new AnimationTask(this, tick, model.getLastTick()), (long) 0,
            (long) (1000 / speed));
  }


}
