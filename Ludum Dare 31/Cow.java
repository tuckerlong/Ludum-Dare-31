import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Cow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cow extends Animal
{
    GreenfootImage img = new GreenfootImage("Cow.png");
    //GreenfootImage idle = new GreenfootImage("Knight-Dle.png");
    int rCount = 0, cCount = 0, tick = 0;
    int max_frames = 2;
    int rows = 1;
    int fpr = 2;
    
    boolean animate = false;
    
    private Farm farm;
    private double dx, dy;
    
    public Cow() {
        setImage(new GreenfootImage(41, 27));
        getImage().drawImage(img, 0, 0);
    }
    
    public void addedToWorld(World world) {
        farm = (Farm)world.getObjects(Farm.class).get(0);
    }
    
    public void act() {
        if(!stop) {
            animate();
            List<Actor> farms = getObjectsInRange(35, Farm.class);
            if(farms.size() == 0) {
                turnTowards(farm.getX(),farm.getY());
                move(1);
                setRotation(0);
            } else {
                farm.damage(10);
                remove = true;
            }
            
            if(remove) {
                getWorld().addObject(new Guts(), getX(), getY());
                getWorld().addObject(new Guts(), getX(), getY());
                getWorld().addObject(new Bone(), getX(), getY());
                getWorld().addObject(new Bone(), getX(), getY());
                getWorld().addObject(new Bone(), getX(), getY());
                getWorld().addObject(new Blood(), getX(), getY());
                getWorld().addObject(new Spine(), getX(), getY());
                getWorld().addObject(new Skull(), getX(), getY());
                getWorld().removeObject(this);
            }
        }
    }   
    
    public void animate() {
        tick++;
        
        if(tick > 5) {
            //setLocation(getX() - 3, getY());
            tick = 0;
            cCount++;
            
            if(cCount > fpr) {
                cCount = 0;
                rCount++;
            }
            
            if(rCount * fpr + cCount > max_frames - 1) {
                cCount = 0;
                rCount = 0;
            }
            
            getImage().clear();
            getImage().drawImage(img, img.getWidth()/fpr * cCount * -1, img.getHeight()/rows * rCount * -1);
            if(farm.getX() < getX()) getImage().mirrorHorizontally();
        }
    }   
}
