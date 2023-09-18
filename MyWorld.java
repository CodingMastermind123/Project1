import mayflower.*;


public class MyWorld extends World 
{

    private Cat cat;
    
    private String[][] tiles;
    public MyWorld() 
    {
        setBackground("img/BG.png");
        cat = new Cat();
        addObject(cat, 100, 100);
        // dog = new Dog();
        // addObject(dog, 200, 200);
        // jack = new Jack();
        // addObject(jack, 150, 150);
        // ninja = new Ninja();
        //addObject(ninja, 250, 250);
        tiles = new String[6][8];
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
                        addObject(new Block(), j * 128, i + 472);
                }
            }
        }
    }
    public void addMainCharacter()
    {
        cat = new Cat();
        boolean added = false;
        while(added == false)
        {
            int row = (int) (Math.random()*tiles.length);
            int col = (int) (Math.random()*tiles[0].length);
            if(tiles[row][col].equals(""))
            {
                addObject(cat, row * 100, col * 100);
                tiles[row][col].equals("cat");
                added = true;
            }
        }
    }
}