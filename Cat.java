import mayflower.*;
public class Cat extends AnimatedActor
{
   private int score;
   private int lives;
    private MayflowerImage[] frames;
    private int currentFrame;
    private Timer animationTimer;
    private int x;
    private int y;
    private int w;
    private int h;
    public Cat()
{
      score = 0;
      lives = 3;
      MayflowerImage p = new MayflowerImage ("img/cat/Walk (1).png");
        // Scale this image to 30% of the origianl size
        p.scale(0.3);
        //Set this Actor's image to the MayflowerImage object p
        setImage(p);
        frames = new MayflowerImage[10];
        currentFrame = 0;
        animationTimer = new Timer(100000000);
        for(int i = 1; i < frames.length; i++)
        {
            frames[i - 1] = new MayflowerImage("img/cat/Walk ("+i+").png");
            frames[i - 1].scale(100, 87);
        }
    }

    public void act()
    {
        if(currentFrame >= frames.length)
        {
            currentFrame = 0;
        }
        if( animationTimer.isDone() )
        {
            animationTimer.reset();
        }
        setImage ( frames[currentFrame] );
        currentFrame++;
        
        x = getX();
        y = getY();
        w = getWidth();
        h = getHeight();
        
        if ((x+w < 800) && (x > 0) && (y+h < 600) && (y > 0))
        {
        if (Mayflower.isKeyDown( Keyboard.KEY_RIGHT )) 
        {
            setLocation (x + 1, y);
        }
        if (Mayflower.isKeyDown( Keyboard.KEY_LEFT )) 
        {
            setLocation (x - 1, y);
        }
        if (Mayflower.isKeyDown( Keyboard.KEY_DOWN )) 
        {
            setLocation (x, y + 1);
        }
        if (Mayflower.isKeyDown( Keyboard.KEY_UP )) 
        {
            setLocation (x, y - 1);
            while(y == 200 && y != 390 && Mayflower.isKeyDown( Keyboard.KEY_UP ))
            {
                y += 190;
                setLocation(x, y);
            }
        }
        if(Mayflower.isKeyDown(Keyboard.KEY_UP) && Mayflower.isKeyDown(Keyboard.KEY_RIGHT))
        {
            setLocation(x+1, y - 1);
        }
    }
        if(y == 0)
        {
        if (Mayflower.isKeyDown( Keyboard.KEY_RIGHT )) 
        {
            setLocation (x + 1, y);
        }
        if (Mayflower.isKeyDown( Keyboard.KEY_LEFT )) 
        {
            setLocation (x - 1, y);
        }
        if (Mayflower.isKeyDown( Keyboard.KEY_DOWN )) 
        {
            setLocation (x, y + 1);
        }
    }
    if(y == 800)
    {
        if (Mayflower.isKeyDown( Keyboard.KEY_UP )) 
        {
            setLocation (x, y - 1);
        }
        if (Mayflower.isKeyDown( Keyboard.KEY_RIGHT )) 
        {
            setLocation (x + 1, y);
        }
        if (Mayflower.isKeyDown( Keyboard.KEY_LEFT )) 
        {
            setLocation (x - 1, y);
        }
    }
    if(x == 0)
    {
       if (Mayflower.isKeyDown( Keyboard.KEY_UP )) 
        {
            setLocation (x, y - 1);
        }
        if (Mayflower.isKeyDown( Keyboard.KEY_RIGHT )) 
       {
            setLocation (x + 1, y);
       }
       if (Mayflower.isKeyDown( Keyboard.KEY_DOWN )) 
        {
            setLocation (x, y + 1);
        }
    }
}
public void increaseScore(int amount)
{
    score += amount;
    updateText();
}
public int getScore()
{
    return score;
}
public void decreaseLives(int amount)
{
    lives -= amount;
    setLocation(400, 300);
    updateText();
}
public int getLives()
{
    return lives;
}
private void updateText()
{
    World w = getWorld();
    w.removeText(10, 30);
    w.showText("Score: " + score + " Lives: " + lives, 10, 30, Color.BLACK);
}
public void gravity()
{
    x = getX();
    y = getY();
    w = getWidth();
    h = getHeight();
    
    while(y == 500 && y != 390 && Mayflower.isKeyDown( Keyboard.KEY_UP ))
    {
        y-=50;
    }
}
}
