import java.util.Random;

public class Entity {
    private int maxHp, hp, level;
    private final String name;

    public Entity(int hp, int level, String name){
        this.hp = hp;
        this.maxHp = hp;
        this.level = level;
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
    public int getLevel(){
        return this.level;
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

    public void attack(Entity entity){
        int damage;
        if (entity instanceof Player){
            damage = ((Monster) this).getDamage();
        } else {
            damage = ((Player) this).getDamage();
        }
        /*if (entity instanceof Monster && this instanceof Monster){
            damage = 0;
        }*/
        if (damage > entity.hp){
            this.hp = 0;
            //ajouter une façon de retirer l'entité de la map
            if (entity instanceof Monster){
                Items reward = ((Monster) entity).death();
                ((Player) this).addToInventory(reward);
                Random random = new Random();
                int i = random.nextInt(10*entity.level);
                ((Player) this).gainExperience(i);
            }else{
                //ajouter une façon de terminer le jeu
            }
        } else {
            this.hp -= damage;
        }
    }
    public void heal(int heal){
        if (this.hp + heal > this.maxHp){
            this.hp = this.maxHp;
        } else {
            this.hp += heal;
        }
    }

    public void move(){
        String[] direction = {"N", "S", "E", "W"};
        Random random = new Random();
        int i = random.nextInt(4);
        switch(direction[i]){
            case "N":
                //ajouter une façon de vérifier si la case est libre
                //ajouter une façon de déplacer l'entité
                break;
            case "S":
                //ajouter une façon de vérifier si la case est libre
                //ajouter une façon de déplacer l'entité
                break;
            case "E":
                //ajouter une façon de vérifier si la case est libre
                //ajouter une façon de déplacer l'entité
                break;
            case "W":
                //ajouter une façon de vérifier si la case est libre
                //ajouter une façon de déplacer l'entité
                break;
        }
        //aussi faire une vérif qu'il n'y a pas de mur en face
    }
}
