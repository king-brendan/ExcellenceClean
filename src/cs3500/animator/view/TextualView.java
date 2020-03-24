package cs3500.animator.view;


import cs3500.animator.model.ReadOnlyExcellenceOperations;

/**
 * A view that
 */
public class TextualView implements AnimatorView {
  ReadOnlyExcellenceOperations readOnlyModel;
  Appendable ap;

  TextualView(ReadOnlyExcellenceOperations model, Appendable ap) {
    readOnlyModel = model;
    this.ap = ap;
  }
  @Override
  public void refresh() {

  }

  @Override
  public void displayAnimation() {

  }


}
