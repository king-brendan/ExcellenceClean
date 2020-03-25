package cs3500.animator.view;

import java.util.TimerTask;

public class AnimationTask extends TimerTask {
  private VisualView view;
  private int tick;

  AnimationTask(VisualView view, int tick) {
    this.tick = tick;
    this.view = view;
  }

  @Override
  public void run() {
    view.refresh(tick);
    this.tick++;
  }
}
