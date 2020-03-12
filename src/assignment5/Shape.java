package assignment5;

import java.awt.Color;
import java.util.Objects;

/**
 * Represents a Shape object to be altered in the animation. Contains all the shape conditions such
 * as its name, type, position, dimension, and color at any point. It is an immutable object with
 * immutable fields. Outside the package, it is read-only such that only the constructor and getters
 * are accessible publicly which return immutable fields or copies of mutable fields.
 */
public final class Shape {
  private final String name;
  private Color color;
  private final Dimension dimension;
  private final Position position;
  private final ShapeType type;

  /**
   * A class to represent the shape type, this will be accessed in the view through the getType
   * method to decide on how to draw it in the view. We are not sure how many shape types we are
   * expected to handle. Therefore, we have modeled a rectangle, oval, and triangle as the basic
   * three shapes. Out of which we can also make squares, circles, and ellipses. Other shapes such
   * as hexagons, pentagons, heptagons...etc can be added later if we are expected to handle them as
   * well.
   */
  public enum ShapeType {
    RECTANGLE, OVAL, TRIANGLE;


    @Override
    public String toString() {
      if (this.equals(ShapeType.RECTANGLE)) {
        return "rectangle";
      } else if (this.equals(ShapeType.OVAL)) {
        return "oval";
      } else if (this.equals(ShapeType.TRIANGLE)) {
        return "triangle";
      } else {
        return "Please assign a toString to the ShapeType";
      }
    }
  }

  /**
   * Initializes a shape with only its name and type. Other values are assigned their real states
   * when the first instruction is added. In the view, a shape will never be drawn unless it has an
   * instruction being applied to it. Therefore, they can have arbitrary values that are not null
   * when being created and will not be shown to the user until an instruction is applied to it.
   *
   * @param name the  shape  name
   * @param type is the shape type
   * @throws IllegalArgumentException if any parameters are null
   */
  public Shape(String name, ShapeType type) {
    if (name == null || type == null) {
      throw new IllegalArgumentException("Parameters cannot be null");
    }
    this.name = name;
    this.type = type;
    this.color = Constants.BACKGROUND_COLOR;
    this.dimension = new Dimension(0, 0);
    this.position = new Position(0, 0);
  }

  /**
   * Makes a copy of the shape. Set to default for now since a user has no need for it. It might be
   * refactored later if it is needed in other packages.
   */
  Shape makeCopy() {
    Shape s = new Shape(getName(), getType());
    s.color = getColor();
    s.dimension.setDimension(getDimension());
    s.position.setPosition(getPosition());
    return s;
  }

  /**
   * Assigns the initial conditions of a shape from the start states of an instructions. Set as
   * default since it will only happen within the model.
   *
   * @param instruction is the given instruction.
   */
  void assignBeginningConditions(Instruction instruction) {
    position.setPosition(instruction.getStartPosition());
    dimension.setDimension(instruction.getStartDimension());
    color = instruction.getStartColor();
  }


  /**
   * Applies the given instruction to the Shape. It will only apply a change to position, dimension,
   * or color if there is one, and will apply a fraction of the change since the instruction is
   * applied over several ticks. Set as default since it will only happen within the model.
   *
   * @param instruction is the given Instruction
   * @throws IllegalArgumentException if the instruction is null.
   */
  void applyInstruction(Instruction instruction) {
    if (instruction == null) {
      throw new IllegalArgumentException("Instruction cannot be null");
    }

    int tickDiff = instruction.getEndTick() - instruction.getStartTick();

    changeColor(instruction.getStartColor(), instruction.getEndColor(), tickDiff);
    changeDimension(instruction.getStartDimension(), instruction.getEndDimension(), tickDiff);
    changePosition(instruction.getStartPosition(), instruction.getEndPosition(), tickDiff);
  }


  /**
   * Changes the shape to the end color if it is different than the start color.
   *
   * @param startColor is the starting color
   * @param endColor   is the ending color
   * @param tickDiff   is the value which the difference in start and end color is divided by.
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
   * Makes sure that the int is within the range 0-255 and returns the max or min number if it is
   * not. This is used since issues in rounding might cause the numbers to be slightly out of range
   * for RGB in Color.
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
   * Changes the shape position to the end position if it is different than the start position.
   *
   * @param startPos is the Starting position
   * @param endPos   is the ending Position
   * @param tickDiff is the value which the difference in start and end position is divided by.
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
   * Changes the dimension of the shape to the end dimension if it is different than the start
   * dimension.
   *
   * @param startDim is the current Dimension.
   * @param endDim   the new dimension to be set.
   * @param tickDiff is the value which the difference in start and end dimension is divided by.
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
  public String getName() {
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

  /**
   * Returns the type of the shape.
   */
  public ShapeType getType() {
    return type;
  }

  @Override
  public String toString() {
    return "shape " + name + " " + type.toString();
  }

  //A shape is equal according to its name and type. Our model assures that no two shapes will
  // have the same name, therefore this cannot give a false positive for shapes in the model.
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Shape shape = (Shape) o;
    return name.equals(shape.name) &&
            type == shape.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, type);
  }
}
