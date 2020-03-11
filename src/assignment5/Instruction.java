package assignment5;

import java.awt.*;

/**
 * A Class to represent the instructions given by the user.
 */
public final class Instruction {
  private String shapeName;
  private int startTick;
  private int endTick;
  private Position startPos;
  private Position endPos;
  private Dimension startDim;
  private Dimension endDim;
  private Color startColor;
  private Color endColor;


  /**
   * Public constructor for Instruction that creates a new Instruction.
   *
   * @param shapeName  is the shape name
   * @param startTick  is when the instruction is applied to the model
   * @param endTick    is when the instruction ends
   * @param startPos   starting position of the shape
   * @param endPos     ending position of the shape
   * @param startDim   initial dimensions of the shape
   * @param endDim     ending dimensions of the shape
   * @param startColor initial color of the shape
   * @param endColor   end color of the shape if changed
   * @throws IllegalArgumentException if any of the parameters are null or the ticks are invalid.
   */
  public Instruction(String shapeName, int startTick, int endTick,
                     Position startPos,
                     Position endPos, Dimension startDim, Dimension endDim, Color startColor,
                     Color endColor) {

    if (shapeName == null || startPos == null || endPos == null
            || startDim == null || endDim == null || startColor == null || endColor == null) {
      throw new IllegalArgumentException("Parameters cannot be null");
    }

    if (startTick < 0 || (startTick >= endTick)) {
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
