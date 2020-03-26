package cs3500.animator.model;

import java.awt.Color;

/**
 * A Class to represent the instructions given by the user. It contains the state of the shape from
 * the start tick till the end tick. It does not hold the shape itself, but only its name so that
 * you cannot access the shape from the instruction itself. The instruction is immutable and is
 * read-only (only has getters without any setters).
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
   * Returns the shape name in the instruction.
   */
  String getShapeName() {
    return shapeName;
  }

  /**
   * Returns the startTick of the Instruction.
   */
  int getStartTick() {
    return startTick;
  }


  /**
   * Returns the endTick of the Instruction.
   */
  int getEndTick() {
    return endTick;
  }

  /**
   * Returns a copy of the startPosition of the Instruction.
   */
  Position getStartPosition() {
    return new Position(startPos);
  }

  /**
   * Returns a copy of the endPosition of the Instruction.
   */
  Position getEndPosition() {
    return new Position(endPos);
  }

  /**
   * Returns a copy of the startDimension of the Instruction.
   */
  Dimension getStartDimension() {
    return new Dimension(startDim);
  }

  /**
   * Returns a copy of the endDimension of the Instruction.
   */
  Dimension getEndDimension() {
    return new Dimension(endDim);
  }

  /**
   * Returns the start color of the Instruction.
   */
  Color getStartColor() {
    return startColor;
  }

  /**
   * Returns the start color of the Instruction.
   */
  Color getEndColor() {
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
