package cs3500.animator.view;

import java.util.TimerTask;

/**
 * A class that extends TimerTask that advances a tick by one and refreshes a VisualView according
 * to the tick. It has an stopAt field that stops adding 1 to the tick and stops refreshing the
 * view. It is usually the last tick in the animation.
 */
public class AnimationTask extends TimerTask {
  private VisualView view;
  private int tick;
  private int stopAt;

  /**
   * Default constructor for Animation Task that is not public as it is only called within this
   * package.
   *
   * @param view  is the view to be updated
   * @param tick  is the tick to be used to refresh the view then updated
   * @param until is when the run method stops refreshing
   */
  AnimationTask(VisualView view, int tick, int until) {
    this.tick = tick;
    this.view = view;
    this.stopAt = until;
  }

  @Override
  public void run() {
    if (tick < stopAt) {
      view.refresh(tick);
      this.tick++;
    }
  }
}
