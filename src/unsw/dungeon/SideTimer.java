package unsw.dungeon;

import javafx.animation.AnimationTimer;

/**
 * my own timer
 *  below is an example on how to use
 *  MenuTask menuTask = new MenuTask();
    menuTask.set_losemenu_task(dungeonApplication, dungeon);
    SideTimer t = new SideTimer();
    t.makeSideTimer(2_000_000_000L, menuTask, "losemenu");
 */
public class SideTimer {
    private AnimationTimer timer = null;
    private int cycle = 0; 

    /**
     * run in given time, and then stop
     */
    public void makeSideTimer(long period, BackgroundTask task, String which) {
        timer = new AnimationTimer() {

            private long lastToggle;

            @Override
            public void handle(long now) {
                
                if (lastToggle == 0L) {
                    lastToggle = now;
                    task.start(which);
                } 
                else {
                    long diff = now - lastToggle;
                    if (diff >= period) {  //  6_000_000_000L == 6s
                        task.stop(which);
                        if (timer != null)
                            timer.stop();
                        timer = null;
                    } 
                }
            }   
        };
        timer.start();
    }
    /**
     * run every given time, stop after reach given cycle
     * @param c
     * @param which
     */
    public void makeCycleTimer(long period, int c, BackgroundTask task, String which) {
        timer = new AnimationTimer() {

            private long lastToggle;

            @Override
            public void handle(long now) {
                
                if (lastToggle == 0L) {
                    lastToggle = now;
                    task.start(which);
                } 
                else {
                    long diff = now - lastToggle;
                    if (diff >= period) { // 500,000,000ns == 500ms, execute every 1s, here
                        if (cycle == c) {
                            cycle = 0;
                            task.stop(which);
                            timer.stop();
                            timer = null;
                        }
                        cycle++;
                        task.start(which);
                        lastToggle = now;
                    } 
                }
            }
        };
        timer.start();
    }
}