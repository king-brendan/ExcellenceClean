package assignment5;

import java.awt.Color;
import java.util.List;

/**
 * An interface to include all the functionality required of a model for an animator. Since
 * assignment 5, we have updated our interface. First, we deleted the playAnimation(int tick) method
 * as it mutates the shapes indefinitely. It will become a private method that helps with the new
 * method, getShapesAt(int tick) described below. Moreover, we deleted the getShapes() and
 * getInstructions() methods as they are no longer needed.
 */
public interface ExcellenceOperations {


  /**
   * Renders the animation as text. Starts with a shape name and its type, then follows with a
   * representation of the Instructions that that shape has in order of ticks. Appends all shapes
   * and their instructions in a similar fashion to the output.
   *
   * @return the animation, rendered as text.
   */
  public String toText();

  /**
   * Creates an instruction given the right parameters then adds the Instruction to the list of
   * instructions of the model. This function expects valid Positions, Dimensions, and Colors. This
   * is done so that the controller can parse for errors in user input after getting the full
   * instruction and output the specific problem with the output, then only ask for that input again
   * instead of the whole instruction. This will create a better, more detailed GUI.
   *
   * @param shapeName  is  the shape name
   * @param startTick  is when the instruction is applied to the model
   * @param endTick    is when the instruction ends
   * @param startPos   starting position of the shape
   * @param endPos     ending position of the shape
   * @param startDim   initial dimensions of the shape
   * @param endDim     ending dimensions of the shape
   * @param startColor initial color of the shape
   * @param endColor   end color of the shape if changed
   * @throws IllegalArgumentException if the instruction is not valid (check instruction validity in
   *                                  Instruction class) or if there is no shape in the model
   *                                  corresponding to that instruction or if the start conditions
   *                                  do not correspond to the shapes previous instruction's end
   *                                  states.
   */
  public void addInstruction(String shapeName, int startTick,
                             int endTick, Position startPos,
                             Position endPos, Dimension startDim,
                             Dimension endDim, Color startColor, Color endColor);

  /**
   * Creates and adds a shape to the model. Expects only a name and a type to create the shape
   * object.
   *
   * @throws IllegalArgumentException if any of the arguments are null
   */
  public void addShape(String shapeName, Shape.ShapeType shapeType);

  /**
   * Returns a copy of the the shapes in the model in a List with their states updated to the
   * specified tick. In other words, it returns a copy of the shapes with states that correspond to
   * the tick given.
   *
   * @param tick is the tick at which the shape states are requested
   * @throws IllegalStateException    if there are no shapes in the model
   * @throws IllegalArgumentException if the tick is negative.
   */
  public List<Shape> getShapesAt(int tick);


}
