package cs3500.animator.view;

import cs3500.animator.model.ReadOnlyExcellenceOperations;

/**
 * A View factory class that creates a view given the view type and parameters.
 */
public class ViewFactory {

  /**
   * Creates a view based off of the given parameters.
   * @param viewType the type of view to be created
   * @param output the output destination
   * @param speed the speed of the animation
   * @param m the model to be used in the view
   * @return an AnimatorView initialized with the given model and parameteres.
   */
  public static AnimatorView makeView(String viewType, String output, String speed,
                              ReadOnlyExcellenceOperations m) {
    if (viewType == null) {
      throw new IllegalArgumentException("View type cannot be null");
    }

    int s = Integer.parseInt(speed);
    AnimatorView v = null;
    switch(viewType) {
      case "visual":
        v = new VisualView(m, s);
        break;
      case "text":
        Appendable ap = null;
        if (output.equals("System.out")) {
          ap = System.out;
        }
        v = new TextualView(m, ap);
      //TODO: add the SVG view case here
    }

    return v;

  }
}
