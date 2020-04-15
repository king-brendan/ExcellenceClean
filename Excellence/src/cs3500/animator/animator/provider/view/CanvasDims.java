package cs3500.animator.provider.view;

/**
 * Stores the dimensions of a bounding box for an animation.
 */
public class CanvasDims {

  private int x;
  private int y;
  private int width;
  private int height;

  /**
   * Constructs a CanvasDims object.
   *
   * @param x      The leftmost x value.
   * @param y      The topmost y value.
   * @param width  The width of the box.
   * @param height The height of the box.
   */
  public CanvasDims(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  /**
   * Get x value.
   *
   * @return Leftmost x value in bounding box for animation.
   */
  public int getX() {
    return x;
  }

  /**
   * Set x value.
   *
   * @param x The x value.
   */
  public void setX(int x) {
    this.x = x;
  }

  /**
   * Get y value.
   *
   * @return Topmost y value in bounding box for animation.
   */
  public int getY() {
    return y;
  }

  /**
   * Set y value.
   *
   * @param y The y value.
   */
  public void setY(int y) {
    this.y = y;
  }

  /**
   * Get width of bounding box.
   *
   * @return Width of bounding box.
   */
  public int getWidth() {
    return width;
  }

  /**
   * Set width of bounding box.
   *
   * @param width Width value.
   */
  public void setWidth(int width) {
    this.width = width;
  }

  /**
   * Get height of bounding box.
   *
   * @return Height of bounding box.
   */
  public int getHeight() {
    return height;
  }

  /**
   * Set height of bounding box.
   *
   * @param height Height value.
   */
  public void setHeight(int height) {
    this.height = height;
  }

  @Override
  public String toString() {
    return "Canvas " + this.x + " " + this.y + " " + this.width + " " + this.height + "\n";
  }
}
