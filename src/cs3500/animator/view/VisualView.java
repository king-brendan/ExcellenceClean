package cs3500.animator.view;


import java.util.Timer;


import javax.swing.JFrame;

import cs3500.animator.model.ReadOnlyExcellenceOperations;


/**
 * A view that draws and plays the animation inside of a Java Swing window.
 */
public class VisualView extends JFrame implements AnimatorView {
  private VWPanel panel;
  private ReadOnlyExcellenceOperations model;
  private Timer timer;
  private double speed;
  private int tick;

  public VisualView(ReadOnlyExcellenceOperations readOnlyModel, double speed) {
    setSize(500, 500);
    setLocation(0,0);

    model = readOnlyModel;
    this.panel = new VWPanel(model.getShapesAt(0));
    this.add(this.panel);

    this.tick = 0;
    timer = new Timer();
    this.speed = speed;
  }

  @Override
  public void refresh(int tick) {
    this.panel.setShapes(model.getShapesAt(tick));
    this.repaint();
  }

  @Override
  public void displayAnimation() {
    this.setVisible(true);
    timer.scheduleAtFixedRate(new AnimationTask(this, tick), (long) 0, (long) (1 / speed * 1000));
  }

}
