package assignment5;

import java.awt.*;

/**
 * Represents a Shape to be altered in the animation.
 */
public abstract class Shape {
  private final String name;
  private Color color;
  private final Dimension dimension;
  private final Position position;

  /**
   * Initializes a shape with only its name.
   *
   * @param name the  shape  name
   * @throws IllegalArgumentException if any parameters are null
   */
  public Shape(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Parameters cannot be null");
    }
    this.name = name;
    this.color = Constants.backgroundColor;
    this.dimension = new Dimension(0, 0);
    this.position = new Position(0, 0);
  }

  /**
   * Applies the given instruction to the Shape. It will only apply a change to position, dimension,
   * or color if there is one, and will apply a fraction of the change since the instruction is
   * applied over several ticks.
   *
   * @param instruction is the given Instruction
   * @throws IllegalArgumentException if the instruction is null.
   */
  public void applyInstruction(Instruction instruction) {
    if (instruction == null) {
      throw new IllegalArgumentException("Instruction cannot be null");
    }

    int tickDiff = instruction.getEndTick() - instruction.getStartTick();

    changeColor(instruction.getStartColor(), instruction.getEndColor(), tickDiff);
    changeDimension(instruction.getStartDimension(), instruction.getEndDimension(), tickDiff);
    changePosition(instruction.getStartPosition(), instruction.getEndPosition(), tickDiff);
  }


  /**
   * Changes the shape to the given color.
   *
   * @param startColor is the starting color
   * @param endColor   is the ending color
   */
  private void changeColor(Color startColor, Color endColor, int tickDiff) {
    if (!startColor.equals(endColor)) {
      int rDiff = (endColor.getRed() - startColor.getRed()) / tickDiff;
      int gDiff = (endColor.getGreen() - startColor.getGreen()) / tickDiff;
      int bDiff = (endColor.getBlue() - startColor.getBlue()) / tickDiff;

      color = new Color(getColor().getRed() + rDiff, getColor().getGreen() + gDiff,
              getColor().getBlue() + bDiff);
    }


  }

  /**
   * Changes the shape position to the given position.
   */
  private void changePosition(Position startPos, Position endPos, int tickDiff) {
    if (!startPos.equals(endPos)) {
      double xDiff = (endPos.getX() - startPos.getX()) / tickDiff;
      double yDiff = (endPos.getY() - startPos.getY()) / tickDiff;

      Position newPos = new Position(getPosition().getX() + xDiff,
              getPosition().getY() + yDiff);

      this.position.setPosition(newPos);
    }

  }

  /**
   * Changes the dimension of the shape to the new dimension given.
   *
   * @param startDim is the current Dimension.
   * @param endDim   the new dimension to be set.
   */
  private void changeDimension(Dimension startDim, Dimension endDim, int tickDiff) {

    if (!startDim.equals(endDim)) {
      int xDiff = (endDim.getX() - startDim.getX()) / tickDiff;
      int yDiff = (endDim.getY() - startDim.getY()) / tickDiff;

      Dimension newDim = new Dimension(getDimension().getX() + xDiff,
              getDimension().getY() + yDiff);

      this.dimension.setDimension(newDim);
    }
  }

  /**
   * Returns the name of the shape.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the color of the shape.
   */
  public Color getColor() {
    return color;
  }

  /**
   * Returns a copy of the current position.
   */
  public Position getPosition() {
    return new Position(position);
  }

  /**
   * Returns a copy of the current dimension.
   */
  public Dimension getDimension() {
    return new Dimension(dimension);
  }
}
