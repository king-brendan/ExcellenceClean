package cs3500.animator.provider.view;

import java.awt.*;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.Timer;



/**
 * This panel is the region in which an animation plays.
 */
public class AnimationPanel extends JPanel {
  private Animator model;
  int xOffset;
  int yOffset;
  private int tick;
  Timer timer;
  private int ticksPerSecond;
  private boolean looping;
  private int endTick; // the last tick of the animation


  /**
   * Constructor for the animation panel class.
   *
   * @param model use this model to construct this AnimatorPanel
   */
  public AnimationPanel(Animator model) {
    super();
    if (model == null) {
      throw new IllegalArgumentException("cannot create animation panel with no animation model");
    }
    this.model = model;
    this.setBackground(Color.BLACK);
    this.tick = 1;
    this.xOffset = this.model.getCanvasDims().getX();
    this.yOffset = this.model.getCanvasDims().getY();
    this.looping = false;
    this.endTick = this.getEndTick();
  }

  /**
   * Starts the timer.
   */
  public void play() {
    timer.start();
  }

  /**
   * Stops the timer.
   */
  public void pause() {
    timer.stop();
  }

  /**
   * Sets the tick of the animation.
   *
   * @param tick The tick to set the animation to.
   */
  public void setTick(int tick) {
    this.tick = tick;
  }

  /**
   * Toggles whether or not to loop the animation when it reaches the end.
   */
  public void toggleLooping() {
    if (this.looping) {
      this.looping = false;
    } else {
      this.looping = true;
    }
  }

  /**
   * Refresh the timer.
   */
  public void refreshTimer() {
    // 1/ticks per second * 1000 = ms per tick
    timer = new Timer(1000 / this.ticksPerSecond, e -> {
      repaint();
      this.tick++;
      System.out.println("Tick: " + tick);
      System.out.println("TPS: " + ticksPerSecond);
      System.out.println("Delay: " + timer.getDelay());
      if (this.tick >= endTick && looping) {
        this.tick = 1;
      }
    });
  }

  /**
   * Make the animation go faster.
   */
  public void faster() {
    this.ticksPerSecond = this.ticksPerSecond + 5;
    System.out.println(ticksPerSecond);
    this.refreshTimer();
  }

  /**
   * Make the animation go slower if possible.
   */
  public void slower() {
    if (this.ticksPerSecond >= 6) {
      this.ticksPerSecond = this.ticksPerSecond - 5;
      System.out.println(ticksPerSecond);
      this.refreshTimer();
    }
  }

  public void setTicksPerSecond(int ticksPerSecond) {
    this.ticksPerSecond = ticksPerSecond;
  }

  public int getTicksPerSecond() {
    return ticksPerSecond;
  }

  /**
   * Changes the current model to the given one.
   *
   * @param model the new model.
   */
  public void setModel(Animator model) {
    this.model = model;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    for (Map.Entry<String, KeyFrame> shape :
            this.model.getShapesAtTick(this.tick).entrySet()) {
      g2d.setColor(shape.getValue().getColor());
      if (this.model.getShapeType(shape.getKey()).equals(ShapeType.RECTANGLE)) {
        g2d.fillRect(shape.getValue().getPosition().getX() - xOffset,
                shape.getValue().getPosition().getY() - yOffset,
                shape.getValue().getWidth(), shape.getValue().getHeight());
      } else if (this.model.getShapeType(shape.getKey()).equals(ShapeType.ELLIPSE)) {
        g2d.fillOval(shape.getValue().getPosition().getX() - xOffset,
                shape.getValue().getPosition().getY() - yOffset,
                shape.getValue().getWidth(), shape.getValue().getHeight());
      } else {
        throw new RuntimeException("unexpected shape type");
      }
    }
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(this.model.getCanvasDims().getWidth(),
            this.model.getCanvasDims().getHeight());
  }

  /**
   * Gets the last tick for which a KeyFrame is specified anywhere in the animation.
   *
   * @return The last tick in the animation.
   */
  private int getEndTick() {
    int endTick = 1;
    for (Map.Entry<String, ShapeType> e : model.getShapes().entrySet()) {
      endTick = Math.max(endTick, model.getShapeKeyFrames(e.getKey()).lastKey());
    }
    return endTick;
  }
}