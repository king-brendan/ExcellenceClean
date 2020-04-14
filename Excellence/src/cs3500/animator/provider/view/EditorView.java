package cs3500.animator.provider.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.*;

/**
 * A view with a control panel for the contained animation, with playback features and services for
 * modifying animations.
 */
public class EditorView extends VisualView {

  // intellij likes to yell at you for this but i figured i'd leave it as a directory of stuff to
  // use and add handlers to

  // add shape to animation
  private JPanel addShapePanel;
  private JButton addShapeButton;
  private JTextField shapeType;
  private JTextField shapeName;

  // delete shape from animation
  private JButton deleteShapeButton;

  // list of all the shape names
  private JPanel shapePanel;
  private DefaultListModel<String> shapeNamesList;

  // add a new key frame for a shape or replace an existing one with your input
  private JButton addOrEditShapeKeyFrameButton;
  private JButton removeKeyFrameFromShapeButton;

  private JPanel addOrEditKeyFramePanel;
  private JTextField addOrEditKeyFrameTick;
  private JTextField addOrEditKeyFrameX;
  private JTextField addOrEditKeyFrameY;
  private JTextField addOrEditKeyFrameWidth;
  private JTextField addOrEditKeyFrameHeight;
  private JTextField addOrEditKeyFrameR;
  private JTextField addOrEditKeyFrameG;
  private JTextField addOrEditKeyFrameB;
  private JButton addKeyFrameButton;
  private JButton editKeyFrameButton;

  private JPanel deleteKeyFramePanel;
  private JTextField deleteKeyFrameTick;
  private JButton deleteKeyFrameButton;


  /**
   * Constructs an EditorView. The layout... could use a little work
   */
  public EditorView() {
    super();
  }

  @Override
  public void render(Animator model, int ticksPerSecond) {
    this.setLayout(new GridLayout(0, 4));
    super.animationPanel = new AnimationPanel(model);
    this.modifyAnimationSpeed(ticksPerSecond);
    this.add(new JScrollPane(super.animationPanel));

    // Scroll Bar
    JScrollPane scrollPane = new JScrollPane(super.animationPanel);
    this.add(scrollPane);

    // Playback options are handled by the view alone due to our previous design decision to have
    // The view store the tick and tick speed. The model is not involved, so this should be okay.

    JPanel playbackButtons = new JPanel();

    // Start/restart Button
    JButton startButton = new JButton("start/restart");
    startButton.addActionListener(e -> {
      super.animationPanel.setTick(0);
      super.animationPanel.play();
    });
    playbackButtons.add(startButton);

    // Pause button
    JButton pause = new JButton("pause");
    pause.addActionListener(e -> super.animationPanel.pause());
    playbackButtons.add(pause);

    // Play button
    JButton play = new JButton("play");
    play.addActionListener(e -> super.animationPanel.play());
    playbackButtons.add(play);

    // Faster button (speed up animation)
    JButton faster = new JButton("faster");
    faster.addActionListener(e -> {
      super.animationPanel.pause();
      super.animationPanel.faster();
      super.animationPanel.play();
    });
    playbackButtons.add(faster);

    // Slower button (slow down animation)
    JButton slower = new JButton("slower");
    slower.addActionListener(e -> {
      super.animationPanel.pause();
      super.animationPanel.slower();
      super.animationPanel.play();
    });
    playbackButtons.add(slower);

    // Toggle looping of the animation
    JToggleButton looping = new JToggleButton("loop?");
    looping.addActionListener(e -> super.animationPanel.toggleLooping());
    playbackButtons.add(looping);

    this.add(playbackButtons);

    // The rest of these controls have effects that are related to the controller, so we ask the
    // controller to handle them by invoking methods on the listener.

    shapePanel = new JPanel();
    shapeNamesList = new DefaultListModel<>();
    for (Map.Entry<String, ShapeType> e : model.getShapes().entrySet()) {
      shapeNamesList.addElement(e.getKey());
    }
    JList<String> shapeNamesJList = new JList<>(shapeNamesList);
    shapeNamesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    shapePanel.add(shapeNamesJList);

    // request controller delete selected shape
    deleteShapeButton = new JButton("del shape");
    shapePanel.add(deleteShapeButton);

    addOrEditShapeKeyFrameButton = new JButton("add/edit kf");
    addOrEditShapeKeyFrameButton.addActionListener(e -> {
      addOrEditKeyFramePanel.setVisible(true);
    });
    shapePanel.add(addOrEditShapeKeyFrameButton);

    removeKeyFrameFromShapeButton = new JButton("remove kf");
    removeKeyFrameFromShapeButton.addActionListener(e -> {
      deleteKeyFramePanel.setVisible(true);
    });
    shapePanel.add(removeKeyFrameFromShapeButton);

    JScrollPane scrollableShapePanel = new JScrollPane(shapePanel);
    scrollableShapePanel.setPreferredSize(new Dimension(200, 300));

    this.add(scrollableShapePanel);


    // selecting add or edit kf brings this out of hiding
    addOrEditKeyFramePanel = new JPanel();

    addOrEditKeyFrameTick = new JTextField();
    addOrEditKeyFrameTick.setText("tick");
    addOrEditKeyFramePanel.add(addOrEditKeyFrameTick);

    addOrEditKeyFrameX = new JTextField();
    addOrEditKeyFrameX.setText("x");
    addOrEditKeyFramePanel.add(addOrEditKeyFrameX);

    addOrEditKeyFrameY = new JTextField();
    addOrEditKeyFrameY.setText("y");
    addOrEditKeyFramePanel.add(addOrEditKeyFrameY);

    addOrEditKeyFrameWidth = new JTextField();
    addOrEditKeyFrameWidth.setText("w");
    addOrEditKeyFramePanel.add(addOrEditKeyFrameWidth);

    addOrEditKeyFrameHeight = new JTextField();
    addOrEditKeyFrameHeight.setText("h");
    addOrEditKeyFramePanel.add(addOrEditKeyFrameHeight);

    addOrEditKeyFrameR = new JTextField();
    addOrEditKeyFrameR.setText("r");
    addOrEditKeyFramePanel.add(addOrEditKeyFrameR);

    addOrEditKeyFrameG = new JTextField();
    addOrEditKeyFrameG.setText("g");
    addOrEditKeyFramePanel.add(addOrEditKeyFrameG);

    addOrEditKeyFrameB = new JTextField();
    addOrEditKeyFrameB.setText("b");
    addOrEditKeyFramePanel.add(addOrEditKeyFrameB);

    // request the controller add a keyframe
    addKeyFrameButton = new JButton("create kf");
    addOrEditKeyFramePanel.add(addKeyFrameButton);
    // request the controller edit the keyframe at the given tick to the given values
    editKeyFrameButton = new JButton("edit kf");
    addOrEditKeyFramePanel.add(editKeyFrameButton);

    // change this when handling event
    addOrEditKeyFramePanel.setVisible(false);

    this.add(addOrEditKeyFramePanel);

    // selecting remove kf brings this out of hiding
    deleteKeyFramePanel = new JPanel();

    deleteKeyFrameTick = new JTextField();
    deleteKeyFrameTick.setText("tick");
    deleteKeyFramePanel.add(deleteKeyFrameTick);
    // request the controller remove keyframe from this tick
    deleteKeyFrameButton = new JButton("del kf");
    deleteKeyFramePanel.add(deleteKeyFrameButton);

    deleteKeyFramePanel.setVisible(false);

    this.add(deleteKeyFramePanel);

    // controls for adding a shape to the animation
    addShapePanel = new JPanel();

    shapeType = new JTextField();
    shapeType.setText("type (R or E)");
    addShapePanel.add(shapeType);

    shapeName = new JTextField();
    shapeName.setText("name");
    addShapePanel.add(shapeName);

    // request the controller add a shape with the given name, parse given string for type
    addShapeButton = new JButton("add this shape");
    addShapePanel.add(addShapeButton);

    addShapePanel.setVisible(true);

    this.add(addShapePanel);

    this.pack();
    this.setVisible(true);
  }

  @Override
  public void setListener(ActionListener listener) {
    // this is where you would add all your action listeners to your buttons, but that's specific
    // to your controller
  }

}
