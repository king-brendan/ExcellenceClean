package cs3500.animator.model;

import java.awt.Color;

/**
 * An interface to include all the functionality required of a cs3500.animator.model for an
 * animator. Since assignment 5, we have updated our interface. First, we deleted the
 * playAnimation(int tick) method as it mutates the shapes indefinitely. We added a new method
 * instead, getShapesAt(int tick) described below. Moreover, we deleted the getShapes() method as it
 * is no longer needed. We have added setters for the width, height, and top-left point of the model
 * as it is information needed for the views. In the read-only interface, we accounted for getters
 * for this information but did not include the setters.
 */
public interface ExcellenceOperations extends ReadOnlyExcellenceOperations {

  /**
   * Creates an instruction given the right parameters then adds the Instruction to the list of
   * instructions of the cs3500.animator.model. This function expects valid Positions, Dimensions,
   * and Colors. This is done so that the controller can parse for errors in user input after
   * getting the full instruction and output the specific problem with the output, then only ask for
   * that input again instead of the whole instruction. This will create a better, more detailed
   * GUI.
   *
   * @param shapeName  is  the shape name
   * @param startTick  is when the instruction is applied to the cs3500.animator.model
   * @param endTick    is when the instruction ends
   * @param startPos   starting position of the shape
   * @param endPos     ending position of the shape
   * @param startDim   initial dimensions of the shape
   * @param endDim     ending dimensions of the shape
   * @param startColor initial color of the shape
   * @param endColor   end color of the shape if changed
   * @throws IllegalArgumentException if the instruction is not valid (check instruction validity in
   *                                  Instruction class) or if there is no shape in the
   *                                  cs3500.animator.model corresponding to that instruction or if
   *                                  the start conditions do not correspond to the shapes previous
   *                                  instruction's end states.
   */
  public void addInstruction(String shapeName, int startTick,
                             int endTick, Position startPos,
                             Position endPos, Dimension startDim,
                             Dimension endDim, Color startColor, Color endColor);

  /**
   * Creates and adds a shape to the cs3500.animator.model. Expects only a name and a type to create
   * the shape object.
   *
   * @param shapeName is the shape name or ID
   * @param shapeType is the type of shape, either rectangle or oval
   * @throws IllegalArgumentException if any of the arguments are null
   */
  public void addShape(String shapeName, Shape.ShapeType shapeType);

  /**
   * Sets the top-left point of the model.
   *
   * @param x is the x-value
   * @param y is the y-value
   */
  public void setTopLeft(int x, int y);

  /**
   * Sets the height of the model.
   *
   * @param height the height of the model as a double
   */
  public void setHeight(double height);

  /**
   * Sets the width of the model.
   *
   * @param width the width as a double
   */
  public void setWidth(double width);


}
