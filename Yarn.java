import mayflower.*;
public class Yarn extends Actor
{

    /**
     * Constructor for objects of class Yarn
     */
    public Yarn()
    {
      setImage("img/yarn.png");
    }
    
    public void act()
    {
        if(isTouching(Cat.class))
        {
           Object a = getOneIntersectingObject(Cat.class);
           Cat c = (Cat) a;
           World w = getWorld();
           w.removeObject(this);
           c.increaseScore( 1 );
           if(c.getScore() == 3)
           {
            w.removeText(10, 30);
            w.showText("You Win!", 400, 300, Color.BLACK);
           }
        }
    }
}
