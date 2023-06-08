//getter for damage
//death() returns an item

import java.util.Random;

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
    public Items death(){
        //ajouter une façon de retirer l'entité de la map
        Random random = new Random();
        int i = random.nextInt(Main.itemsList.size());
        return Main.itemsList.get(i);
    }
}
