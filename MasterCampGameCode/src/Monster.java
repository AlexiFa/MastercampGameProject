import java.util.ArrayList;

public class Monster extends Entity{
    private int damage;
    public Monster(int hp, int level, int damage, String name){
        super(hp, level, name);
        this.damage = damage;
    }
    public int getDamage(){
        return this.damage;
    }

    @Override
    public String toString(){
        return(this.getName() + "\nLife points : " + this.getHp() + "/" + this.getMaxHp());
    }
}
