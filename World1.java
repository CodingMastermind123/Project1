import mayflower.*;

public class World1 extends World
{
    private String[][] tiles;
    public World1()
    {
        setBackground("img/welcomebackground.jpg");

        showText("WELCOME TO SIDESCROLLER!", 200, 300, Color.WHITE);
        
        tiles = new String[6][8];
    }

    public void act()
    {

    }
}
