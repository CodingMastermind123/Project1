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

    public Cat() {
        //MayflowerImage p = new MayflowerImage("img/cat/Walk (1).png");
        //p.scale(0.3);
        //setImage(p);
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
    }
}
