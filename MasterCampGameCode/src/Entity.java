//getters and setters for hp, maxHp, level, and name
//attack() attacks an entity
//heal() heals the entity
//move() moves the entity on the map

import java.awt.Point;


public abstract class Entity {
    private int maxHp, hp, level;
    private final String name;
    private Point position;

    public Entity(int hp, int level, String name){
        this.hp = hp;
        this.maxHp = hp;
        this.level = level;
        this.name = name;
        this.position = new Point();
    }

    public int getHp(){
        return this.hp;
    }
    public int getMaxHp(){
        return this.maxHp;
    }
    public String getName(){
        return this.name;
    }
    public int getLevel(){
        return this.level;
    }
    public Point getPosition(){
        return this.position;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public void setPosition(Point position) {
        this.position = position;
    }

    public abstract int[] move(int direction);
}
