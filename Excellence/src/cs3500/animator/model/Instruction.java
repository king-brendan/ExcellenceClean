package cs3500.animator.model;

import java.awt.Color;

/**
 * A Class to represent the instructions given by the user. It contains the state of the shape from
 * the start tick till the end tick. It does not hold the shape itself, but only its name so that
 * you cannot access the shape from the instruction itself. The instruction is immutable and is
 * read-only (only has getters without any setters). We added a copy constructor for assignment 6 so
 * that we can return a copy of the instructions from the model. From assignment 5, we refactored
 * the getters to be public so we can access them in the SVG view. This does not change Instructions
 * immutability.
 */
public final class Instruction {
  private final String shapeName;
  private final int startTick;
  private final int endTick;
  private final Position startPos;
  private final Position endPos;
  private final Dimension startDim;
  private final Dimension endDim;
  private final Color startColor;
  private final Color endColor;


  /**
   * Public constructor for Instruction that creates a new Instruction given all the variables. It
   * will only be created and accessed within the package, so it is set to default.
   *
   * @param shapeName  is the shape name
   * @param startTick  is when the instruction is applied to the cs3500.animator.model
   * @param endTick    is when the instruction ends
   * @param startPos   starting position of the shape
   * @param endPos     ending position of the shape
   * @param startDim   initial dimensions of the shape
   * @param endDim     ending dimensions of the shape
   * @param startColor initial color of the shape
   * @param endColor   end color of the shape if changed
   * @throws IllegalArgumentException if any of the parameters are null or the ticks are invalid.
   */
  Instruction(String shapeName, int startTick, int endTick,
              Position startPos,
              Position endPos, Dimension startDim, Dimension endDim, Color startColor,
              Color endColor) {

    if (shapeName == null || startPos == null || endPos == null
            || startDim == null || endDim == null || startColor == null || endColor == null) {
      throw new IllegalArgumentException("Parameters cannot be null");
    }

    if (startTick < 0 || (startTick > endTick)) {
      throw new IllegalArgumentException("Ticks are invalid");
    }

    this.shapeName = shapeName;
    this.startTick = startTick;
    this.endTick = endTick;
    this.startPos = startPos;
    this.endPos = endPos;
    this.startDim = startDim;
    this.endDim = endDim;
    this.startColor = startColor;
    this.endColor = endColor;
  }

  /**
   * Default Copy Constructor for Instruction.
   *
   * @param instruction is the instruction to be copied.
   */
  Instruction(Instruction instruction) {
    this.shapeName = instruction.getShapeName();
    this.startTick = instruction.getStartTick();
    this.endTick = instruction.getEndTick();
    this.startPos = new Position(instruction.getStartPosition());
    this.endPos = new Position(instruction.getEndPosition());
    this.startDim = new Dimension(instruction.getStartDimension());
    this.endDim = new Dimension(instruction.getEndDimension());
    this.startColor = instruction.getStartColor();
    this.endColor = instruction.getEndColor();
  }


  /**
   * Returns the shape name in the instruction.
   */
  public String getShapeName() {
    return shapeName;
  }

  /**
   * Returns the startTick of the Instruction.
   */
  public int getStartTick() {
    return startTick;
  }


  /**
   * Returns the endTick of the Instruction.
   */
  public int getEndTick() {
    return endTick;
  }

  /**
   * Returns a copy of the startPosition of the Instruction.
   */
  public Position getStartPosition() {
    return new Position(startPos);
  }

  /**
   * Returns a copy of the endPosition of the Instruction.
   */
  public Position getEndPosition() {
    return new Position(endPos);
  }

  /**
   * Returns a copy of the startDimension of the Instruction.
   */
  public Dimension getStartDimension() {
    return new Dimension(startDim);
  }

  /**
   * Returns a copy of the endDimension of the Instruction.
   */
  public Dimension getEndDimension() {
    return new Dimension(endDim);
  }

  /**
   * Returns the start color of the Instruction.
   */
  public Color getStartColor() {
    return startColor;
  }

  /**
   * Returns the start color of the Instruction.
   */
  public Color getEndColor() {
    return endColor;
  }

  @Override
  public String toString() {
    return "motion " + shapeName + " " + startTick + " " + startPos.getX() + " " + startPos.getY()
            + " " + startDim.getX() + " " + startDim.getY() + " " + startColor.getRed()
            + " " + startColor.getGreen() + " " + startColor.getBlue() + "     "
            + endTick + " " + endPos.getX() + " " + endPos.getY()
            + " " + endDim.getX() + " " + endDim.getY() + " " + endColor.getRed()
            + " " + endColor.getGreen() + " " + endColor.getBlue();

  }
}
