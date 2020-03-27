package cs3500.animator.view;


import java.io.FileWriter;
import java.io.IOException;

import cs3500.animator.model.ReadOnlyExcellenceOperations;

/**
 * A view that outputs a textual description of the animation to an appendable. The text is
 * correspondent to the toText method for any type of ExcellenceOperations and follows its structure
 * for text output.
 */
public class TextualView implements AnimatorView {
  private ReadOnlyExcellenceOperations model;
  private Appendable ap;

  /**
   * Public Constructor for Textual View that takes in the model and appendable it will output to.
   *
   * @param readOnlyModel the read only model.
   * @param ap            the appendable object it will output to
   */
  public TextualView(ReadOnlyExcellenceOperations readOnlyModel, Appendable ap) {
    model = readOnlyModel;
    this.ap = ap;
  }

  @Override
  public void refresh(int tick) {
    /*
    Since this is a textual view that does not change anything, it will output to the
    user (through the appendable) that it does nothing.
     */
    try {
      ap.append("The refresh method does nothing in this case, this is a textual view \n");
    } catch (IOException ioe) {
      throw new IllegalStateException("Appendable cannot be appended to");
    }
  }

  @Override
  public void displayAnimation() {
    try {
      if (ap instanceof FileWriter) {
        FileWriter fw = (FileWriter) ap;
        fw.append(model.toText());
        fw.close();
      } else {
        ap.append(model.toText());
      }
    } catch (IOException ioe) {
      throw new IllegalStateException("Appendable cannot be appended to");
    }
  }


}
