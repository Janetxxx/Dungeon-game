package unsw.dungeon;
import unsw.dungeon.menu.*;

/**
 * for background periodic task in frontend
 */
public class MenuTask implements BackgroundTask {
    private DungeonApplication dungeonApplication;
    private Dungeon dungeon;

    public MenuTask() {
        
    }
    @Override
    public void start(String which) {
        which = which.toLowerCase();
        switch (which) {
            case "losemenu":
                
                break;
            case "showenemygoal":
                PopupWindow popup = new PopupWindow("Goal: Kill all the enemy and go to exit");
                popup.show();
                break;
            case "showmazegoal":
                PopupWindow popup2 = new PopupWindow("Goal: go to exit");
                popup2.show();
                break;
            case "showbouldersgoal":
                PopupWindow popup3 = new PopupWindow("Goal: push all boulders to switch");
                popup3.show();
                break;
            case "showbossgoal":
                PopupWindow popup4 = new PopupWindow("Goal: kill the boss");
                popup4.show();
                break;
            case "winmenu":
                break;
            default:
                break;
        }

    }

    @Override
    public void stop(String which) {
        which = which.toLowerCase();
        switch (which) {
            case "losemenu":
                LoseMenu menu = new LoseMenu(); // losepopup after 2 s
                menu.createNewMenu(dungeonApplication, dungeon);
                break;
            case "showenemygoal":
                
                break;
            case "showmazegoal":
                
                break;
            case "showbouldersgoal":
                
                break;
            case "showbossgoal":
                
                break;
            case "winmenu":
                WinMenu winMenu = new WinMenu();
                winMenu.createNewMenu(dungeonApplication, dungeon);
                break;
            default:
                break;
        }

    }
    public void set_losemenu_task(DungeonApplication dungeonApplication, Dungeon dungeon) {
        this.dungeonApplication = dungeonApplication;
        this.dungeon = dungeon;
    }
    
}