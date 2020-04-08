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
            (int) (model.getHeight() - model.getTopLeft().getY() + 200));
    this.setLocation((int) model.getTopLeft().getX(), (int) model.getTopLeft().getY());

    actions = new ActionsPanel(speed);
    actions.setPreferredSize(new Dimension((int) (model.getWidth() - model.getTopLeft().getX()),
            130));

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

  @Override
  public void enablePopup(ExcellenceController c) {
    String input = JOptionPane.showInputDialog("You can:\n" +
            "1)Add a shape: \n" +
            "Command line: “add shape shapeName shapeType”\n" +
            "Type can be rectangle or oval\n" +
            "\n" +
            "2)Delete a shape: delete shape shapeName\n" +
            "Command line: “delete shape shapeName”\n" +
            "\n" +
            "3)Add a keyframe:\n" +
            "Command line: “add keyframe shapeName  tick  xPosition  yPosition  width  height  redComp greenComp blueComp”\n" +
            "Where the …Comp fields are the components of the color, in integers between 0-255\n" +
            "\n" +
            "4)Delete a keyframe:\n" +
            "Command line: “delete keyframe shapeName tick”");
    c.handleInputString(input);
  }

  @Override
  public void handleException(String error) {
    JOptionPane.showMessageDialog(this, error);
  }

  @Override
  public void displayShapes() {
    String text = "";
    for (cs3500.animator.model.Shape s : model.getShapesAt(task.getTick())) {
      text = text + s.getName() + ", " + s.getColor().toString() + ", " +
              s.getType().toString() + "\n";
    }
    JTextArea textArea = new JTextArea(45, 34);
    textArea.setText(text);
    textArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(textArea);
    JOptionPane.showMessageDialog(this,
            scrollPane);
  }
}
