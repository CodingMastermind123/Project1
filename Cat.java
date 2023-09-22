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

    public Cat() {
        score = 0;
        lives = 3;
        MayflowerImage p = new MayflowerImage("img/cat/Walk (1).png");
        p.scale(0.3);
        setImage(p);
        frames = new MayflowerImage[10];
        currentFrame = 0;
        animationTimer = new Timer(100000000);
        for (int i = 1; i < frames.length; i++) {
            frames[i - 1] = new MayflowerImage("img/cat/Walk (" + i + ").png");
            frames[i - 1].scale(100, 87);
        }
    }

    public void act() {
    x = getX();
    y = getY();
    h = getHeight();
        
        
    int prevX = x;
    int prevY = y;

    // Update the current frame for animation
    setImage(frames[currentFrame]);
    currentFrame = (currentFrame + 1) % frames.length;

    // Handle jumping
    if (Mayflower.isKeyDown(Keyboard.KEY_UP) && !isJumping) {
        velocityY = jumpForce;
        isJumping = true;
    }

    // Handle movement
    if (Mayflower.isKeyDown(Keyboard.KEY_RIGHT) && x + getWidth() < 800) {
        x += 1;
    }
    if (Mayflower.isKeyDown(Keyboard.KEY_LEFT) && x > 0) {
        x -= 1;
    }

    // Update y position only if the character is jumping
    if (isJumping) {
        velocityY += gravity;
        y += (int) velocityY;

        // Check for collision with the ground
        if (y >= groundY) {
            y = groundY;
            isJumping = false;
            velocityY = 0;  // Reset velocity when landing
        }
    }

    // Set the new location of the character
    setLocation(x, y);

    // Update the text
    updateText();
}

    private void updateText() {
        World w = getWorld();
        w.removeText(10, 30);
        w.showText("Score: " + score + " Lives: " + lives, 10, 30, Color.BLACK);
    }
}
