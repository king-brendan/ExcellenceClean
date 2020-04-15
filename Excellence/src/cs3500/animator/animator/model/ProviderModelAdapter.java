package cs3500.animator.model;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import cs3500.animator.provider.view.Animator;
import cs3500.animator.provider.view.CanvasDims;
import cs3500.animator.provider.view.KeyFrame;
import cs3500.animator.provider.view.Position2D;
import cs3500.animator.provider.view.ShapeType;

/**
 * A class that implements the provider's model interface, so that we can use our model
 * functionality "disguised" as an instance of their model.
 */
public class ProviderModelAdapter implements Animator {
  private ExcellenceOperations m;

  /**
   * Constructs a ProviderModelAdapter, so that all methods can be implemented in terms of our model
   * interface.
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
    int xPos = keyFrame.getPosition().getX();
    int yPos = keyFrame.getPosition().getY();

    int dimX = keyFrame.getWidth();
    int dimY = keyFrame.getHeight();

    Color c = keyFrame.getColor();

    m.addKeyframe(name, tick, new Position(xPos, yPos), new Dimension(dimX, dimY), c);
  }

  @Override
  public void addMotion(String name, int t1, KeyFrame before, int t2, KeyFrame after) {

    Position pos1 = new Position(before.getPosition().getX(), before.getPosition().getY());
    Dimension dim1 = new Dimension(before.getWidth(), before.getHeight());
    Color c1 = before.getColor();

    Position pos2 = new Position(after.getPosition().getX(), after.getPosition().getY());
    Dimension dim2 = new Dimension(after.getWidth(), after.getHeight());
    Color c2 = after.getColor();

    m.addInstruction(name, t1, t2, pos1, pos2, dim1, dim2, c1, c2);
  }

  @Override
  public ShapeType getShapeType(String name) {
    Shape s = null;
    Map<Shape, List<Keyframe>> map = m.getKeyframes();

    for (Map.Entry<Shape, List<Keyframe>> e : map.entrySet()) {
      Shape a = e.getKey();
      if (a.getName().equalsIgnoreCase(name)) {
        s = a;
      }
    }

    if (s == null) {
      throw new IllegalArgumentException("Shape does not exist");
    } else {
      return getProviderShapeType(s.getType());
    }
  }

  @Override
  public NavigableMap<Integer, KeyFrame> getShapeKeyFrames(String name) {
    Map<Shape, List<Keyframe>> map = m.getKeyframes();
    List<Keyframe> keys = null;

    for (Map.Entry<Shape, List<Keyframe>> e : map.entrySet()) {
      Shape a = e.getKey();
      if (a.getName().equalsIgnoreCase(name)) {
        keys = e.getValue();
      }
    }

    NavigableMap<Integer, KeyFrame> out = new TreeMap<>();

    if (keys != null) {
      for (Keyframe k : keys) {
        KeyFrame newK = new KeyFrame(k.getColor(), (int) k.getDimension().getX(),
                (int) k.getDimension().getY(), new Position2D((int) k.getPosition().getX(),
                (int) k.getPosition().getY()));
        out.put(k.getTick(), newK);
      }
    }

    return out;
  }

  @Override
  public Map<String, KeyFrame> getShapesAtTick(int tick) {
    List<Shape> shapes = m.getShapesAt(tick);
    Map<String, KeyFrame> toReturn = new HashMap<>();

    for (Shape s : shapes) {
      int xDim = (int) s.getDimension().getX();
      int yDim = (int) s.getDimension().getY();

      int xPos = (int) s.getPosition().getX();
      int yPos = (int) s.getPosition().getY();
      Position2D newPos = new Position2D(xPos, yPos);

      KeyFrame k = new KeyFrame(s.getColor(), xDim, yDim, newPos);

      toReturn.put(s.getName(), k);
    }

    return toReturn;
  }

  @Override
  public void removeShape(String name) {
    m.deleteShape(name);
  }

  @Override
  public void removeMotion(String name, boolean last) {
    // never used in provider's editor view, so doesn't need to be implemented
  }

  @Override
  public CanvasDims getCanvasDims() {

    return new CanvasDims((int) m.getTopLeft().getX(), (int) m.getTopLeft().getY(),
            (int) m.getWidth(), (int) m.getHeight());
  }

  @Override
  public void setCanvasDims(CanvasDims dims) {
    m.setTopLeft(dims.getX(), dims.getY());
    m.setHeight(dims.getHeight());
    m.setWidth(dims.getWidth());
  }

  @Override
  public Map<String, ShapeType> getShapes() {
    Map<Shape, List<Keyframe>> map = m.getKeyframes();
    Map<String, ShapeType> toReturn = new HashMap<>();

    for (Map.Entry<Shape, List<Keyframe>> e : map.entrySet()) {
      Shape s = e.getKey();

      toReturn.put(s.getName(), getProviderShapeType(s.getType()));
    }
    return toReturn;
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

  /**
   * Returns the provider's version of ShapeType from our Shape.ShapeType enum
   */
  private ShapeType getProviderShapeType(Shape.ShapeType type) {
    switch (type.toString()) {
      case "rectangle":
        return ShapeType.RECTANGLE;
      case "oval":
        return ShapeType.ELLIPSE;
      default:
        throw new IllegalArgumentException("What is this shape type???");
    }
  }
}
