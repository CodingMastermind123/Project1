import mayflower.*;

public class Cat extends AnimatedActor {
    private int score;
    private int lives;
    private MayflowerImage[] frames;
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

    public Cat() {
        score = 0;
        lives = 3;
        MayflowerImage p = new MayflowerImage("img/cat/Walk (1).png");
        p.scale(0.3);
        setImage(p);
        frames = new MayflowerImage[10];
        currentFrame = 0;
        animationTimer = new Timer(1000000);
    }

    public void act() {
        
        
        if(Mayflower.isKeyDown(Keyboard.KEY_RIGHT))
        {
            for (int i = 1; i < frames.length; i++) {
                frames[i - 1] = new MayflowerImage("img/cat/Walk (" + i + ").png");
                frames[i - 1].scale(100, 87);
            }
        }
        else
        {
            for (int i = 1; i < frames.length; i++) {
                frames[i - 1] = new MayflowerImage("img/cat/Idle (" + i + ").png");
                frames[i - 1].scale(100, 87);
            }
        }
        
        x = getX();
        y = getY();
        h = getHeight();

        int prevX = x;
        int prevY = y;

        setImage(frames[currentFrame]);
        currentFrame = (currentFrame + 1) % frames.length;
        

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
