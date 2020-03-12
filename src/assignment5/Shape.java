package assignment5;

import java.awt.*;

/**
 * Represents a Shape to be altered in the animation.
 */
public abstract class Shape {
  //Protected so they can be accessed in sub-classes.
  protected final String name;
  protected Color color;
  protected final Dimension dimension;
  protected final Position position;

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
   * Assigns the initial conditions of a shape from the start states of an instructions.
   *
   * @param instruction is the given instruction.
   */
  protected void assignBeginningConditions(Instruction instruction) {
    position.setPosition(instruction.getStartPosition());
    dimension.setDimension(instruction.getStartDimension());
    color = instruction.getStartColor();
  }

  /**
   * Makes a copy of the shape.
   */
  protected abstract Shape makeCopy();

  /**
   * Applies the given instruction to the Shape. It will only apply a change to position, dimension,
   * or color if there is one, and will apply a fraction of the change since the instruction is
   * applied over several ticks.
   *
   * @param instruction is the given Instruction
   * @throws IllegalArgumentException if the instruction is null.
   */
  protected void applyInstruction(Instruction instruction) {
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

      int newR = checkColorRange(getColor().getRed() + rDiff);
      int newG = checkColorRange(getColor().getGreen() + gDiff);
      int newB = checkColorRange(getColor().getBlue() + bDiff);
      color = new Color(newR, newG, newB);
    }
  }


  /**
   * Makes sure that the int is within the range 0-255 and returns a number within than range.
   *
   * @param i is the integer.
   * @return an int between 0-255
   */
  private int checkColorRange(int i) {
    int ret = i;
    if (i < 0) {
      ret = 0;
    }
    if (i > 255) {
      ret = 255;
    }
    return ret;
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
      double xDiff = (endDim.getX() - startDim.getX()) / tickDiff;
      double yDiff = (endDim.getY() - startDim.getY()) / tickDiff;

      Dimension newDim = new Dimension(getDimension().getX() + xDiff,
              getDimension().getY() + yDiff);

      this.dimension.setDimension(newDim);
    }
  }

  /**
   * Returns the name of the shape.
   */
  protected String getName() {
    return name;
  }

  /**
   * Returns a copy of the color of the shape.
   */
  public Color getColor() {
    return new Color(this.color.getRGB());
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
