import mayflower.*;

public class Cat extends AnimatedActor {
    private int score;
    private int lives;
    private String[] frames;
    private String[] frames2;
    private int currentFrame;
    private Timer animationTimer;
    private double gravity = 0.5;
    private double jumpForce = -10;
    private boolean isJumping = false;
    private double velocityY;
    private int groundY = 390;
    private int x;
    private int y;
    private int h;
    private boolean isMoving = false;
    private Animation walk;
    private Animation idle;

    public Cat() {
        score = 0;
        lives = 3;
        frames = new String[10];
        frames2 = new String[10];
        currentFrame = 0;
        animationTimer = new Timer(1000000);
        for (int i = 1; i < frames.length; i++) {
                frames[i - 1] = "img/cat/Walk (" + i + ").png";
            }
        walk = new Animation(50, frames);   
        for (int i = 1; i < frames2.length; i++) {
                frames2[i - 1] = "img/cat/Idle (" + i + ").png";
            }
        idle = new Animation(50, frames2);
        
    }

    public void act() {
        
        super.act();
        
        if(Mayflower.isKeyDown(Keyboard.KEY_RIGHT))
        {
            setAnimation(walk);
        }
        else
        {
            setAnimation(idle);
        }
        
        
        x = getX();
        y = getY();
        h = getHeight();
        
        // setImage(frames[currentFrame]);
        // currentFrame = (currentFrame + 1) % frames.length;
        
        if (Mayflower.isKeyDown(Keyboard.KEY_UP) && !isJumping) {
            velocityY = jumpForce;
            isJumping = true;
            isMoving = true;
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
    }

    private void updateText() {
        World w = getWorld();
        w.removeText(10, 30);
        w.showText("Score: " + score + " Lives: " + lives, 10, 30, Color.BLACK);
    }
}
