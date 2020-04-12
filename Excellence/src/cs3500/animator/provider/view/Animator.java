package cs3500.animator.provider.view;

import java.util.Map;
import java.util.NavigableMap;

/**
 * Represents a general animator tool that models an animation by storing shapes and their key
 * moments in the animation.
 */
public interface Animator {

  /**
   * Adds a shape to the animator.
   *
   * @param type The shape to be added.
   * @param name The name of the shape to be added.
   * @throws IllegalArgumentException      If the type or name of the shape is null, or if a shape
   *                                       with the same name already exists in the animation.
   * @throws UnsupportedOperationException If the implementation doesn't support adding a shape.
   */
  void addShape(ShapeType type, String name);

  /**
   * Adds a KeyFrame to the animator for a specific element of the animation.
   *
   * @param name     The name of the element that a KeyFrame is being inserted for.
   * @param tick     The time value for the KeyFrame.
   * @param keyFrame A KeyFrame to add for the specified element.
   * @throws IllegalArgumentException      If a shape with the given name does not exist in the
   *                                       animator or if the given name is null, or if the tick is
   *                                       negative.
   * @throws UnsupportedOperationException If the implementation doesn't support adding a shape.
   */
  void addKeyFrame(String name, int tick, KeyFrame keyFrame);

  /**
   * Adds a motion of a shape to the animator.
   *
   * @param name   The name of the shape to add a motion for.
   * @param t1     The time at which the motion should start.
   * @param before The state of the shape at the start of the motion.
   * @param t2     The time at which the motion should end.
   * @param after  The state of the shape after the motion.
   * @throws IllegalArgumentException      If any fields are null, or if the start time and/or
   *                                       ending times are invalid, or if the motion is illegal
   *                                       with respect to the existent shape timeline.
   * @throws UnsupportedOperationException If the implementation doesn't support adding a shape.
   */
  void addMotion(String name, int t1, KeyFrame before, int t2, KeyFrame after);

  /**
   * Returns the type of the shape with the given name.
   *
   * @param name The name of the shape to get the type of.
   * @return The ShapeType of the shape with the given name.
   */
  ShapeType getShapeType(String name);

  /**
   * Returns the KeyFrames of the specified shape.
   */
  NavigableMap<Integer, KeyFrame> getShapeKeyFrames(String name);

  /**
   * Gets the state of all the shapes in the animator at a given tick.
   *
   * @param tick The tick we should get the states of the shapes at.
   * @return A map of shape names to their states at the given tick.
   * @throws IllegalArgumentException If the tick is negative.
   */
  Map<String, KeyFrame> getShapesAtTick(int tick);

  /**
   * Removes the shape with the given name.
   *
   * @param name Name of the shape to remove.
   * @throws IllegalArgumentException If null name or if the shape isn't in the animator.
   */
  void removeShape(String name);

  /**
   * Removes the first or last motion of a shape, depending on which is specified. Note that it only
   * makes sense to remove from the beginning or end, as removing an intermediate motion would leave
   * a gap in the timeline.
   *
   * @param name The name of the shape for which to remove the first motion.
   * @param last If true, remove the last motion in the shape's timeline.
   * @throws IllegalArgumentException If null name, the shape isn't in the animator, or there are no
   *                                  motions to remove.
   */
  void removeMotion(String name, boolean last);

  /**
   * Gets the dimensions of the canvas or bounding box for the animation.
   *
   * @return The dimensions of the canvas or bounding box for the animation.
   */
  CanvasDims getCanvasDims();


  /**
   * Sets the dimensions of the canvas or bounding box for the animation.
   *
   * @param dims The dimensions of the canvas.
   */
  void setCanvasDims(CanvasDims dims);


  /**
   * Gets the shapes declared in the model.
   *
   * @return A map of the shapes declared in the model.
   */
  Map<String, ShapeType> getShapes();
}
