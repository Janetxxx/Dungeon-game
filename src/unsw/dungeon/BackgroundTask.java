package unsw.dungeon;

/**
 * this allows inside the animationtimer, set certain task with certain time elaspe
 */
public interface BackgroundTask {
    /**
     * 
     * @param which - which task to called
     */
    public void start(String which); 
    public void stop(String whcih);
}