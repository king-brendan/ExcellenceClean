package cs3500.animator.view;


import java.io.IOException;

import cs3500.animator.model.ReadOnlyExcellenceOperations;

/**
 * A view that
 */
public class TextualView implements AnimatorView {
  private ReadOnlyExcellenceOperations model;
  private Appendable ap;

  TextualView(ReadOnlyExcellenceOperations readOnlyModel, Appendable ap) {
    model = readOnlyModel;
    this.ap = ap;
  }

  @Override
  public void refresh(int tick) {
  }

  @Override
  public void displayAnimation() {
    try {
      ap.append(model.toText());
    } catch (IOException ioe) {
      throw new IllegalStateException("Appendable cannot be appended to");
    }
  }


}
