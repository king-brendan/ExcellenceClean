package cs3500.animator.view;

import cs3500.animator.model.ReadOnlyExcellenceOperations;

/**
 * A View factory class that creates a view given the view type and parameters.
 */
public class ViewFactory {

  /**
   * Creates a view based off of the given parameters.
   *
   * @param viewType the type of view to be created
   * @param ap   the output destination as an appendable
   * @param speed    the speed of the animation
   * @param m        the model to be used in the view
   * @return an AnimatorView initialized with the given model and parameteres.
   */
  public static AnimatorView makeView(String viewType, Appendable ap, int speed,
                                      ReadOnlyExcellenceOperations m) {

    AnimatorView v;


    switch (viewType) {
      case "visual":
        v = new VisualView(m, speed);
        break;
      case "text":
        v = new TextualView(m, ap);
        break;
      case "svg":
        v = new SVGView(m, ap, (double) speed);
        break;
      case "edit":
        AnimatorView vis = new VisualView(m , speed);
        v = new EditorView(m, vis);
        break;
      default:
        throw new IllegalArgumentException("Wrong view type");
    }

    return v;

  }
}
