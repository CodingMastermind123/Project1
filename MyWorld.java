import mayflower.*;


public class MyWorld extends World 
{

    private Cat cat;
    
    private String[][] tiles;
    public MyWorld() 
    {
        setBackground("img/BG.png");
        cat = new Cat();
        addObject(cat,50, 390);
        // dog = new Dog();
        // addObject(dog, 200, 200);
        // jack = new Jack();
        // addObject(jack, 150, 150);
        // ninja = new Ninja();
        //addObject(ninja, 250, 250);
        tiles = new String[6][1000];
        buildWorld();
    }
    
    public void act()
    {
        
    }
    
    public void buildWorld()
    {
        for(int i = 0; i < tiles.length; i++)
        {
            for(int j = 0; j < tiles[0].length; j++)
            {
                tiles[i][j] = "";
            }
        }
        for(int i = 0; i < tiles[0].length; i++)
        {
            tiles[5][i] = "ground";
        }
        for(int i = 0; i < tiles.length; i++)
        {
            for(int j = 0; j < tiles[0].length; j++)
            {
                if(tiles[i][j] == "ground")
                {
                        addObject(new MovingBlock(), j * 128, i + 472);
                }
            }
        }
    }
}
