public class Entity {
    private int maxHp, hp;
    private final String name;

    public Entity(int hp, String name){
        this.hp = hp;
        this.maxHp = hp;
        this.name = name;
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

    public void setHp(int hp) {
        this.hp = hp;
    }
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }
}
