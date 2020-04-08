package cs3500.animator.controller;

import java.awt.*;
import java.util.Scanner;

import cs3500.animator.model.Dimension;
import cs3500.animator.model.ExcellenceOperations;
import cs3500.animator.model.Position;
import cs3500.animator.model.Shape;

import cs3500.animator.view.EditableAnimatorView;

/**
 * A Controller for Excellence that updates the animation according to button presses from the
 * user.
 */
public class ButtonPressController implements ExcellenceController {
  ExcellenceOperations model;
  EditableAnimatorView view;
  static int DELTA_SPEED = 2;

  /**
   * Constructs a ButtonPressController with a given model and view.
   *
   * @param m the model.
   * @param v the view.
   */
  public ButtonPressController(ExcellenceOperations m, EditableAnimatorView v) {
    model = m;
    view = v;
  }

  @Override
  public void handleButtonPress(char input) {
    switch (input) {
      case 'r':
        view.changeTick(0);
        break;
      case ' ':
        view.pauseResume();
        break;
      case 'l':
        view.toggleLooping();
        break;
      case 'z':
        view.changeSpeed(-1 * DELTA_SPEED);
        break;
      case 'x':
        view.changeSpeed(DELTA_SPEED);
        break;
      case 'e':
        view.enablePopup(this);
        break;
      case 's':
        view.displayShapes();
        break;
    }
  }

  @Override
  public void handleInputString(String input) {
    if (input.equalsIgnoreCase("")) {
      view.handleException("Please enter a non-empty command.");
      return;
    }

    String[] words = input.split("\\s+");

    Scanner s = new Scanner(input);


    while (s.hasNext()) {
      String first = s.next();

      switch (first) {
        case "delete":
          handleDeleteCase(s);
          break;
        case "add":
          handleAddCase(s);
          break;
        default:
          view.handleException("Please enter a valid command.");
          break;
      }
    }
  }


  /**
   * Handles deleting a shape or keyframe, according to the user input.
   *
   * @param s is the scanner containing the remaning message
   */
  private void handleDeleteCase(Scanner s) {
    while (s.hasNext()) {
      String c = s.next();

      switch (c) {
        case "shape":

          if (s.hasNext()) {
            String shapeName = s.next();

            try {
              model.deleteShape(shapeName);
            } catch (IllegalArgumentException e) {
              view.handleException("Cannot delete the shape: " + e.getMessage() + ".");
              return;
            }
          } else {
            view.handleException("Too little inputs, please specify the shape you want to delete.");
            return;
          }

        case "keyframe":

          while (s.hasNext()) {
            String shapeName = s.next();

            if (s.hasNextInt()) {
              int tick = s.nextInt();
              try {
                model.deleteKeyframe(shapeName, tick);
              } catch (IllegalArgumentException iae) {
                view.handleException("Cannot delete keyframe: " + iae.getMessage() + ".");
                return;
              }
            } else {
              view.handleException("Too little input, please specify which keyframe you want to " +
                      "delete.");
              return;
            }
          }

        default:
          view.handleException("You can only delete a shape or keyframe, please specify.");
          return;

      }
    }
  }

  /**
   * Handles adding a shape or keyframe to the animation, according to user input.
   *
   * @param s is the scanner containing the user input
   */
  private void handleAddCase(Scanner s) {
    while (s.hasNext()) {
      String toBeAdded = s.next();

      switch (toBeAdded) {
        case "shape":

          while (s.hasNext()) {
            String shapeName = s.next();

            if (s.hasNext()) {
              String shapeType = s.next();

              try {
                Shape.ShapeType type = Shape.getTypeFromString(shapeType);
                model.addShape(shapeName, type);
              } catch (IllegalArgumentException iae) {
                view.handleException("Could not add shape: " + iae.getMessage() + ".");
                return;
              }

            } else {
              view.handleException("Please specify a shape name and type to create a shape.");
              return;
            }
          }

        case "keyframe":

          String errorMsg = "You can add a Keyframe by using the command line:\n add keyframe " +
                  "'shapeName' " +
                  "'tick' 'xPosition' " +
                  "'YPosition' 'width' 'height' 'R value of Color' 'G value of color' 'B value of" +
                  " color'";

          while (s.hasNext()) {
            String shapeName = s.next();
            try {
              int tick = getInt(s, "the tick.");

              double xPos = getDbl(s, "the X position.");
              double yPos = getDbl(s, "the Y position.");
              Position p = new Position(xPos, yPos);

              double width = getDbl(s, "the width.");
              double height = getDbl(s, "the height.");
              Dimension d = new Dimension(width, height);

              int r = getInt(s, "the Red component of the color.");
              int g = getInt(s, "the Green component of the color.");
              int b = getInt(s, "the Blue component of the color.");
              Color c = new Color(r, g, b);

              model.addKeyframe(shapeName, tick, p, d, c);

            } catch (IllegalArgumentException iae) {
              view.handleException("Could not add a Keyframe: " + iae.getMessage());
              return;
            }
          }

        default:
          view.handleException("You can only add a shape or keyframe");
      }

    }
  }

  /**
   * Returns an integer from the scanner.
   *
   * @param s        is the scanner
   * @param variable is the variable name to be appended to the exception message
   * @throws IllegalArgumentException if the scanner does not have an int to get
   */
  private int getInt(Scanner s, String variable) {
    if (s.hasNextInt()) {
      return s.nextInt();
    } else {
      throw new IllegalArgumentException("Please specify a number for " + variable);
    }
  }

  /**
   * Returns a double from the scanner.
   *
   * @param s        is the scanner
   * @param variable is the variable name to be appended to the exception message
   * @throws IllegalArgumentException if the scanner does not have a double to get
   */
  private double getDbl(Scanner s, String variable) {
    if (s.hasNextDouble()) {
      return s.nextDouble();
    } else {
      throw new IllegalArgumentException("Please specify a number for " + variable);
    }
  }
}
