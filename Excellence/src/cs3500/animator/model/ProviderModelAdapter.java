package cs3500.animator.model;

import java.util.Map;
import java.util.NavigableMap;

import cs3500.animator.provider.view.CanvasDims;
import cs3500.animator.provider.view.KeyFrame;
import cs3500.animator.provider.view.ShapeType;

/**
 * A class that implements the provider's model interface, so that we can use our model
 * functionality "disguised" as an instance of their model.
 */
public class ProviderModelAdapter implements cs3500.animator.provider.view.Animator {
  private ExcellenceOperations m;

  /**
   * Constructs a ProviderModelAdapter, so that all methods can be implemented in terms of our
   * model interface.
   *
   * @param m a model that implements our ReadOnlyExcellenceOperations interface
   */
  public ProviderModelAdapter(ExcellenceOperations m) {
    this.m = m;
  }

  @Override
  public void addShape(cs3500.animator.provider.view.ShapeType type, String name) {
    Shape.ShapeType st = convertST(type);
    m.addShape(name, st);
  }

  @Override
  public void addKeyFrame(String name, int tick, KeyFrame keyFrame) {

  }

  @Override
  public void addMotion(String name, int t1, KeyFrame before, int t2, KeyFrame after) {
    // never used in provider's editor view, so doesn't need to be implemented
  }

  @Override
  public ShapeType getShapeType(String name) {
    return null;
  }

  @Override
  public NavigableMap<Integer, KeyFrame> getShapeKeyFrames(String name) {
    return null;
  }

  @Override
  public Map<String, KeyFrame> getShapesAtTick(int tick) {
    return null;
  }

  @Override
  public void removeShape(String name) {

  }

  @Override
  public void removeMotion(String name, boolean last) {
    // never used in provider's editor view, so doesn't need to be implemented
  }

  @Override
  public CanvasDims getCanvasDims() {
    return null;
  }

  @Override
  public void setCanvasDims(CanvasDims dims) {

  }

  @Override
  public Map<String, ShapeType> getShapes() {
    return null;
  }

  /**
   * Returns a ShapeType from our model, given a ShapeType from the provider's model.
   *
   * @param type a ShapeType of the provider's type
   * @return a ShapeType of our type
   */
  private Shape.ShapeType convertST(cs3500.animator.provider.view.ShapeType type) {
    Shape.ShapeType r = null;
    if (type.toString().equals("rectangle")) {
      r = Shape.ShapeType.RECTANGLE;
    } else if (type.toString().equals("ellipse")) {
      r = Shape.ShapeType.OVAL;
    }
    return r;
  }
}
