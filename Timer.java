import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
//import java.awt.Color;
/** This class creates a countdown timer object.
 * It displays like a digital timer clock.
 * The value to set the timer is given in seconds; however, is converted to approximate act cycles within the class.
 * All timers created from this class will start automatically unless using the 'Timer(int, boolean)' constructor with boolean set to 'false'.
 */
 
public class Timer extends Actor
{
    private int count; // the counter field
    private int initialCount; // the initial time given before event occurs
    private boolean running;
 
    public Timer()
    {
        this(0, true);
    }
 
    public Timer(int timeBeforeEvent)
    {
        this(timeBeforeEvent, true);
    }
 
    public Timer(int timeBeforeEvent, boolean getsStarted) // int value given in seconds
    {
        setTimer(timeBeforeEvent);
        running = getsStarted;
        setImage(new GreenfootImage(1,1));
    }
    public Timer(double timeBeforeEvent, boolean getsStarted) // int value given in seconds
    {
        setTimer(timeBeforeEvent);
        running = getsStarted;
        setImage(new GreenfootImage(1,1));
    }
 
    public void setTimer(int timeBeforeEvent)
    {
        initialCount = 60 * timeBeforeEvent;
        count = -initialCount;
    }
    
    public void setTimer(double timeBeforeEvent)
    {
        initialCount = (int)timeBeforeEvent;
        count = -initialCount;
    }


 
    public void act()
    {
        if (running)
        {
            count++;
        }
    }
    public int getAct(){
        return count;
    }
    public int getTime()
    {
        return count / 60;
    }
 
    public void start()
    {
        running = true;
    }
 
    public void stop()
    {
        running = false;
    }
}

