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

    @Override
    public int[] move(int direction){ // nombre aléatoire 0 et 3
        String[] directions = {"N", "S", "E", "W"};
        // Random random = new Random();
        // int i = random.nextInt(4);
        int dx = 0;
        int dy = 0;
        switch(directions[direction]){
            case "N":
                dy = -1;
                break;
            case "S":
                dy = 1;
                break;
            case "E":
                dx = 1;
                break;
            case "W":
                dx = -1;
                break;
            default:
                dy = 0;
                break;
        }
        return new int[]{dx, dy};
        //aussi faire une vérif qu'il n'y a pas de mur en face
    }
}
