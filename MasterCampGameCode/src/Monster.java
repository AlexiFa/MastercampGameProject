import java.util.ArrayList;

public class Monster extends Entity{
    private ArrayList<Items> drops;
    private int damage;
    public Monster(int hp, int damage, String name){
        super(hp, name);
        this.damage = damage;
        this.drops = new ArrayList<>();
    }
    public int getDamage(){
        return this.damage;
    }


    public void addDrops(Items drop){
        this.drops.add(drop);
    }
    public ArrayList<Items> getDrops() {
        return this.drops;
    }

    @Override
    public String toString(){
        return(this.getName() + "\nLife points : " + this.getHp() + "/" + this.getMaxHp());
    }
}
