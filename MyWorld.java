import mayflower.*;


public class MyWorld extends World 
{
    private int score;
    private int lives;
    private Cat cat;
    private Coin coin;
    
    private String[][] tiles;
    public MyWorld() 
    {
        setBackground("img/BG.png");
        score = 0;
        lives = 3;
        cat = new Cat();
        coin = new Coin();
        addObject(cat,50, 393);
        addObject(coin, 55, 393);
        // dog = new Dog();
        // addObject(dog, 200, 200);
        // jack = new Jack();
        // addObject(jack, 150, 150);
        // ninja = new Ninja();
        //addObject(ninja, 250, 250);
        tiles = new String[6][100];
        buildWorld();
    }
    
    public void act()
    {
       updateText(); 
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
                int random = (int)(Math.random() * 8);
                Water water = new Water();
                if(tiles[i][j] == "ground" && random < 3)
                {
                    addObject(water, j * 128, i + 472);
                    tiles[i][j] = "water";
                }
                if(tiles[i][j] == "ground")
                {  
                    MovingBlock block = new MovingBlock();
                    block.scale(50, 50);
                    addObject(block, j * 128, i + 472);
                }
                if(j - 1 > 0 && tiles[i][j] == "water" && tiles[i][j - 1] == "water")
                {
                    tiles[i][j] = "ground";
                    removeObject(water);
                    MovingBlock block = new MovingBlock();
                    block.scale(50, 50);
                    addObject(block, j * 128, i + 472);
                }
            }
        }
    }
        private void updateText() {
        removeText(10, 30);
        showText("Score: " + score + " Lives: " + lives, 10, 30, Color.BLACK);
    }
}
