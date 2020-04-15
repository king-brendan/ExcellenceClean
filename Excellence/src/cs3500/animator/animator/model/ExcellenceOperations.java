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
   * GUI. From assignment 6, this was changed so that it creates two keyframes, but was kept as an
   * option to add a full motion for a shape.
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
   * Adds a keyframe to a specific shape. Using a tick, position, color, and dimension, it adds a
   * keyframe (or a shape state at a specific point) to the animation.
   *
   * @param shapeName is the shape Name
   * @param tick      is the instance of time the keyframe is to be added to
   * @param position  is the position of the shape at that tick
   * @param dimension is the dimension of the shape at that tick
   * @param color     is the color of the shape at that tick
   * @throws IllegalArgumentException if the shape does not exist, any of the parameters are null,
   *                                  or if there is a keyframe at that tick.
   */
  public void addKeyframe(String shapeName, int tick, Position position, Dimension dimension,
                          Color color);

  /**
   * Deletes the shape that has the name provided.
   *
   * @param shapeName is the name of the shape to be deleted.
   * @throws IllegalArgumentException if shapeName is null or if no shape with that name exists in
   *                                  the model.
   */
  public void deleteShape(String shapeName);

  /**
   * Deletes a keyframe for a specified shape at a certain tick.
   *
   * @param shapeName to represent the shape
   * @param atTick    to represent the tick of the keyframe to be deleted
   * @throws IllegalArgumentException if the params are null, shape does not exist, or the keyframe
   *                                  does not exist at that point
   */
  public void deleteKeyframe(String shapeName, int atTick);

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
