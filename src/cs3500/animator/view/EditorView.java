package cs3500.animator.view;

import cs3500.animator.controller.ButtonHandler;
import cs3500.animator.controller.ButtonPressController;
import cs3500.animator.controller.ExcellenceController;
import cs3500.animator.model.ExcellenceOperations;
import cs3500.animator.model.ReadOnlyExcellenceOperations;

/**
 * A view that displays the animation just like in the visual view, but allows for user input
 * while the animation is playing. TODO: add to this
 */
public class EditorView implements AnimatorView {
  private ReadOnlyExcellenceOperations model;
  private VisualView view;

  EditorView(ReadOnlyExcellenceOperations readOnlyModel, AnimatorView view) {
    model = readOnlyModel;
    this.view = (VisualView) view;
  }

  @Override
  public void refresh(int tick) {

  }

  @Override
  public void displayAnimation() {
    ExcellenceController c = new ButtonPressController((ExcellenceOperations) model, view);
    addButtonPressListener(c);
    view.displayAnimation();
  }

  /**
   * Adds a Controller as a button-press listener to the view, so that the Controller can handle
   * button presses.
   *
   * @param c the Controller to be used as a listener for key events.
   */
  public void addButtonPressListener(ExcellenceController c) {
    ButtonHandler bh = new ButtonHandler(c);
    //view.addKeyListener(bh);
    //TODO: how to add a ButtonHandler to the VisualView without editing the VisualView class?
    view.addKeyListener(bh);
  }
}
