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
    private boolean isInvincible;
    private int invincibilityCooldown;

    private boolean lifeLost = false;

    public Cat() {
        score = 0;
        lives = 3;
        isInvincible = false;
        invincibilityCooldown = 0;
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
        checkCoinCollision();
        checkWaterCollision();
        updateText();

        if (isInvincible) {
            invincibilityCooldown--;
            if (invincibilityCooldown <= 0) {
                isInvincible = false;
                makeVisible();
            }
        }
    }

    private void updateText() {
        World w = getWorld();
        w.removeText(10, 30);
        w.showText("Score: " + score + " Lives: " + lives, 10, 30, Color.BLACK);
    }

    private void checkCoinCollision() {
        Coin coin = getOneIntersectingObject(Coin.class);
        System.out.println("checking touching coin...");

        if (coin != null) {
            getWorld().removeObject(coin);
            score++;
            updateText();
        }
    }

    public boolean checkWaterCollision() {
    Water water = getOneIntersectingObject(Water.class);

    if (water != null && !isInvincible) {
        lives--;
        updateText(); 
        isInvincible = true;
        invincibilityCooldown = 100; 
        makeInvisible(); 
        resetPosition();
        return true;
    }
    return false;
}

    private void makeInvisible() {
        getImage().setTransparency(0);  // Set transparency to 0 to hide the cat
    }

    private void makeVisible() {
        getImage().setTransparency(255);  // Set transparency to 255 to make the cat visible
    }

    public void resetPosition() {
        setLocation(50, groundY); 
    }

    public int getScore()
    {
        return score;
    }

    public int getLives()
    {
        return lives;
    }

    public void loseLife()
    {
        lives--;
        lifeLost = true;
        updateText();
    }

    public void makeFalse()
    {
        lifeLost = false;
    }
}
