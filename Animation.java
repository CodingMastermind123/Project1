import mayflower.*;
public class Animation
{
    private MayflowerImage[] frames;
    private int framerate;
    private int currentFrame;
    public Animation(int frameRate, String[] filename)
    {
        framerate = frameRate;
        frames = new MayflowerImage[filename.length];
        for(int i = 0; i < frames.length; i++)
        {
            frames[i] = new MayflowerImage(filename[i]);
            frames[i].scale(100, 87);
            currentFrame = i;
        }
    }
    
    public int getFrameRate()
    {
        return framerate;
    }
    
    public MayflowerImage getNextFrame()
    {
        currentFrame++;
        if(currentFrame == 10)
        {
            currentFrame = 0;
        }
        return frames[currentFrame];
    }
}
