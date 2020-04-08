package cs3500.animator.controller;

import java.awt.*;
import java.util.Arrays;
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

    String[] inputArray = input.split(" ");

    String[] restOfInput = getRest(inputArray);

    String first = inputArray[0];

    switch (first) {
      case "delete":
        try {
          handleDeleteCase(restOfInput);
        } catch (IllegalArgumentException iae) {
          view.handleException("Could not delete: " + iae.getMessage());
        }
        break;
      case "add":
        try {
          handleAddCase(restOfInput);
        } catch (IllegalArgumentException iae) {
          view.handleException("Could not add: " + iae.getMessage());
        }
        break;
      default:
        view.handleException("Please enter a valid command.");
        break;
    }

  }

  /**
   * Returns a copy of the array given without the first string.
   *
   * @param array is the array of strings.
   */
  private String[] getRest(String[] array) {
    return Arrays.copyOfRange(array, 1, array.length);
  }


  /**
   * Handles deleting a shape or keyframe, according to the user input.
   *
   * @param input is the remaining message from the user input
   */
  private void handleDeleteCase(String[] input) {

    String c = input[0];

    String shapeName = input[1];

    if (shapeName == null) {
      throw new IllegalArgumentException("There is no shape name");
    }

    switch (c) {
      case "shape":
        try {
          model.deleteShape(shapeName);
        } catch (IllegalArgumentException e) {
          throw new IllegalArgumentException("Shape does not exist in the model");
        }
        return;


      case "keyframe":

        int tick = getInt(input[2], "Please specify a tick.");

        try {
          model.deleteKeyframe(shapeName, tick);
        } catch (IllegalArgumentException iae) {
          throw new IllegalArgumentException("Cannot delete keyframe: " + iae.getMessage());
        }

      default:
        throw new IllegalArgumentException("You can only delete a shape or keyframe, please specify.");
    }

  }

  /**
   * Handles adding a shape or keyframe to the animation, according to user input.
   *
   * @param input is the remaining message from the user input
   */
  private void handleAddCase(String[] input) {


    String toBeAdded = input[0];

    String shapeName = input[1];

    if (toBeAdded == null) {
      throw new IllegalArgumentException("Please specify what you want to add");
    }
    if (shapeName == null) {
      throw new IllegalArgumentException("Please enter a shape name");
    }

    switch (toBeAdded) {
      case "shape":
        String shapeType = input[2];
        if (shapeType == null) {
          throw new IllegalArgumentException("please specify a shape type");
        }

        try {
          Shape.ShapeType type = Shape.getTypeFromString(shapeType);
          model.addShape(shapeName, type);
        } catch (IllegalArgumentException iae) {
          throw new IllegalArgumentException("Could not add shape: " + iae.getMessage() + ".");
        }
        break;

      case "keyframe":


          try {
            int tick = getInt(input[2], "Please specify the tick.");

            double xPos = getDbl(input[3], "Please specify the X position.");
            double yPos = getDbl(input[4], "Please specify the Y position.");
            Position p = new Position(xPos, yPos);

            double width = getDbl(input[5], "Please specify the width.");
            double height = getDbl(input[6], "Please specify the height.");
            Dimension d = new Dimension(width, height);

            int r = getInt(input[7], "Please specify the Red component of the color.");
            int g = getInt(input[8], "Please specify the Green component of the color.");
            int b = getInt(input[9], "Please specify the Blue component of the color.");
            Color c = new Color(r, g, b);

            model.addKeyframe(shapeName, tick, p, d, c);

          } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException("Could not add a Keyframe: " + iae.getMessage());
          }
        break;

      default:
        throw new IllegalArgumentException("You can only add a shape or keyframe");
    }


  }

  /**
   * Returns an integer from the scanner.
   *
   * @param s   is the scanner
   * @param msg is the message to be given to the user
   * @throws IllegalArgumentException if the scanner does not have an int to get
   */
  private int getInt(String s, String msg) {
    if(s == null) {
      throw new IllegalArgumentException("Number is null");
    }
    try {
      return Integer.parseInt(s);
    } catch (NumberFormatException nfe) {
      throw new IllegalArgumentException(msg);
    }
  }

  /**
   * Returns a double from the scanner.
   *
   * @param s   is the scanner
   * @param msg is the message to be given to the user
   * @throws IllegalArgumentException if the scanner does not have a double to get
   */
  private double getDbl(String s, String msg) {
    if(s == null) {
      throw new IllegalArgumentException("Number is null");
    }
    try {
      return Double.parseDouble(s);
    } catch (NumberFormatException nfe) {
      throw new IllegalArgumentException(msg);
    }
  }
}
