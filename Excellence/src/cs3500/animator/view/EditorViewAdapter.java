package cs3500.animator.view;

import cs3500.animator.controller.ExcellenceController;
import cs3500.animator.model.ExcellenceOperations;
import cs3500.animator.model.ProviderModelAdapter;
import cs3500.animator.model.ReadOnlyExcellenceOperations;
import cs3500.animator.provider.view.Animator;

/**
 * An adapter that implements our editor view interface, so that it can be used with our main
 * method. Since the only method that is explicitly called in our main method is displayAnimation(),
 * we only need to implement that method, as well as addListener, to add our controller to their
 * view.
 */
public class EditorViewAdapter implements EditableAnimatorView {
  private ReadOnlyExcellenceOperations m;
  private cs3500.animator.provider.view.IView v;
  private int speed;

  /**
   * Constructs an EditorViewAdapter.
   *
   * @param m a model that implements our interface
   * @param speed the speed of the animation
   */
  EditorViewAdapter(ReadOnlyExcellenceOperations m, int speed) {
    this.m = m;
    this.v = new cs3500.animator.provider.view.EditorView();
    this.speed = speed;
  }

  @Override
  public void addListener(ExcellenceController c) {
    //TODO: add our controller as a listener to their view using their setListener method.
    //TODO: figure out how the hell to do it with an ActionListener
  }

  @Override
  public void changeTick(int tick) {
    // never gets called, so doesn't need to be implemented
  }

  @Override
  public void pauseResume() {
    // never gets called, so doesn't need to be implemented
  }

  @Override
  public void toggleLooping() {
    // never gets called, so doesn't need to be implemented
  }

  @Override
  public void changeSpeed(int s) {
    // never gets called, so doesn't need to be implemented
  }

  @Override
  public void enablePopup(ExcellenceController c) {
    // never gets called, so doesn't need to be implemented
  }

  @Override
  public void handleException(String error) {
    // never gets called, so doesn't need to be implemented
  }

  @Override
  public void displayShapes() {
    // never gets called, so doesn't need to be implemented
  }

  @Override
  public void refresh(int tick) {
    // never gets called, so doesn't need to be implemented
  }

  @Override
  public void displayAnimation() {
    Animator adaptedModel = new ProviderModelAdapter((ExcellenceOperations) m);
    v.render(adaptedModel, speed);
  }
}
