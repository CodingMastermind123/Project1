import mayflower.*;

public class Cat extends AnimatedActor {
    private String[] frame;
    private String[] frame2;
    private int currentFrame;
    private double gravity = 0.2;
    private double jumpForce = -10;
    private boolean isJumping = false;
    private double velocityY;
    private int groundY = 393;
    private int x;
    private int y;
    private int h;
    private boolean isWalking = false;
    private Animation walk;
    private Animation idle;
    private int score;
    private int lives;
    boolean invulnerable = false;
    Timer invulnerableTimer = new Timer(100000);

    public Cat() {
        score = 0;
        lives = 3;
        frame = new String[10];
        frame2 = new String[10];
        for (int i = 1; i <= frame.length; i++) {
            frame[i - 1] = "img/cat/Walk (" + i + ").png";
        }
        walk = new Animation(3, frame);
        for (int i = 1; i <= frame2.length; i++) {
            frame2[i - 1] = "img/cat/Idle (" + i + ").png";
        }
        idle = new Animation(3, frame2);
    }

    public void act() {

        if(Mayflower.isKeyDown(Keyboard.KEY_RIGHT))
        {
            setAnimation(walk);
        }
        else
        {
            setAnimation(idle);
        }

        super.act();

        x = getX();
        y = getY();
        h = getHeight();

        if (Mayflower.isKeyDown(Keyboard.KEY_UP) && !isJumping) {
            velocityY = jumpForce;
            isJumping = true;
            isWalking = true;
        }

        if (isJumping) {
            velocityY += gravity;
            y += (int) velocityY;

            if (y >= groundY) {
                y = groundY;
                isJumping = false;
                velocityY = 0; 
            }
        }

        setLocation(x, y);
        updateText();

        if(isTouching(Water.class) && invulnerable == false)
        {
            decreaseLives();
            setInvulnerable();

            if(lives == 0)
            {
                World w = getWorld();
                w.removeText(10, 30);
                w.showText("Game Over", 400, 300, Color.BLACK);
            }
        }
    }

    public int getScore()
    {
        return score;
    }

    public int getLives()
    {
        return lives;
    }

    public void decreaseLives()
    {
        lives = lives - 1;
        updateText();
    }
    
    public void increaseScore()
    {
        score++;
    }
    public void updateText()
    {
        World w = getWorld();
        w.removeText(10, 30);
        w.showText("Score: " + score + " Lives: " + lives, 10, 30, Color.BLACK);
    }

    public void setInvulnerable()
    {
        invulnerable = true;
        invulnerableTimer.reset();
    }

    public boolean isInvulnerable()
    {
        if(invulnerableTimer.isDone())
        {
            invulnerable = false;
        }

        return invulnerable;
    }
}
