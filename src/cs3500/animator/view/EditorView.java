package cs3500.animator.view;

import java.awt.*;
import java.util.Timer;

import javax.swing.*;

import cs3500.animator.controller.ButtonHandler;
import cs3500.animator.controller.ButtonPressController;
import cs3500.animator.controller.ExcellenceController;
import cs3500.animator.model.ExcellenceOperations;
import cs3500.animator.model.ReadOnlyExcellenceOperations;

/**
 * A view that displays the animation just like in the visual view, but allows for user input
 * while the animation is playing. This class contains a VisualView inside of it, and executes
 * user commands by updating the VisualView field. For every animation of the editor type, there
 * should only be one instance of type EditorView. However, there can be many different instances
 * of VisualViews created according to user input, but there can never be more than one existing
 * at a time.
 */
public class EditorView extends JFrame implements EditableAnimatorView {
  private ReadOnlyExcellenceOperations model;
  private int speed;
  private VWPanel shapesPanel;
  private Timer timer;
  private boolean isPaused;
  private AnimationTask task;
  private boolean isLooping;
  private ActionsPanel actions;

  /**
   * A constructor for an EditorView, which takes in a model and speed, and creates a new
   * VisualView.
   * @param readOnlyModel the model.
   * @param speed the initial speed of the animation.
   */
  EditorView(ReadOnlyExcellenceOperations readOnlyModel, int speed) {
    model = readOnlyModel;

    this.speed = speed;
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    timer = new Timer();
    task = new AnimationTask(this, 0, model.getLastTick());

    this.shapesPanel = new VWPanel(readOnlyModel.getShapesAt(0));
    this.setSize((int) (model.getWidth() - model.getTopLeft().getX()),
            (int) (model.getHeight() - model.getTopLeft().getY() + 100));
    this.setLocation((int) model.getTopLeft().getX(), (int) model.getTopLeft().getY());

    System.out.println("view size: " + this.getSize());
    System.out.println("model size: width: " + model.getWidth() + " height: " + model.getHeight());
    System.out.println("model top left: " + model.getTopLeft().getX() + ", " + model.getTopLeft().getY());

    actions = new ActionsPanel(speed);
    actions.setPreferredSize(new Dimension((int) (model.getWidth() - model.getTopLeft().getX()), 100));

    this.add(shapesPanel, BorderLayout.CENTER);
    this.add(actions, BorderLayout.PAGE_END);

    isPaused = false;
    isLooping = false;
  }

  @Override
  public void refresh(int tick) {
    if (tick == (model.getLastTick() - 1) && (isLooping)) {
      changeTick(0);
    }
    this.shapesPanel.setShapes(model.getShapesAt(tick));
    this.repaint();
  }

  @Override
  public void displayAnimation() {
    this.setVisible(true);
    ExcellenceController c = new ButtonPressController((ExcellenceOperations) model, this);
    addListener(c);
    timer.scheduleAtFixedRate(task, (long) 0, (long) (1000 / speed));
  }

  @Override
  public void addListener(ExcellenceController c) {
    ButtonHandler bh = new ButtonHandler(c);
    this.addKeyListener(bh);
  }

  @Override
  public void changeTick(int tick) {
    timer.cancel();
    task.setTick(tick);
    timer = new Timer();
    task = new AnimationTask(this, tick, model.getLastTick());
    timer.scheduleAtFixedRate(task, (long) 0, (long) (1000 / speed));
    isPaused = false;
    actions.pauseResume(false);
  }

  @Override
  public void pauseResume() {
    if (!isPaused) {
      int curTick = task.getTick();
      timer.cancel();
      timer = new Timer();
      task = new AnimationTask(this, curTick, model.getLastTick());
      isPaused = true;
      actions.pauseResume(true);
    } else {
      timer.scheduleAtFixedRate(task, (long) 0, (long) (1000 / speed));
      isPaused = false;
      actions.pauseResume(false);
    }
  }

  @Override
  public void toggleLooping() {
    isLooping = !isLooping;
    actions.toggleLooping();
  }

  @Override
  public void changeSpeed(int s) {
    int curTick = task.getTick();
    timer.cancel();
    timer = new Timer();
    task = new AnimationTask(this, curTick, model.getLastTick());
    speed = speed + s;
    if (speed <= 0) {
      return;
    }
    timer.scheduleAtFixedRate(task, 0, (long) (1000 / speed));
    isPaused = false;
    actions.pauseResume(false);
    actions.changeSpeed(speed);
  }
}
