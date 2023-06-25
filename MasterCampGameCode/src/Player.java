//getters and setters for experience, inventory, and selectedItem
//getDamage() returns the damage of the player
//addToInventory() adds an item to the inventory
//removeFromInventory() removes an item from the inventory
//gainExperience() adds experience to the player and levels them up if they have enough experience
//levelUp() levels up the player

import java.util.ArrayList;
import java.util.List;

public class Player extends Entity{
    private int experience;
    private List<Items> inventory;
    private Items selectedItem;

    public Player(int hp, String name) {
        super(hp, 1, name);
        experience = 0;
        inventory = new ArrayList<Items>();
        selectedItem = null;
    }

    public int getExperience(){
        return this.experience;
    }
    public List<Items> getInventory(){
        return this.inventory;
    }
    public Items getSelectedItem(){
        return this.selectedItem;
    }

    public void setExperience(int experience){
        this.experience = experience;
    }
    public void setSelectedItem(Items selectedItem){
        this.selectedItem = selectedItem;
    }
    public void setInventory(List<Items> inventory){
        this.inventory = inventory;
    }
    public void addItem(Items item){
        this.inventory.add(item);
    }

    public int getDamage(){
        if (this.selectedItem == null){
            return 1;
        } else {
            if (this.selectedItem.getType()){
                return 1;
            } else {
                return this.selectedItem.getValue();
            }
        }
    }

    public void addToInventory(Items item){
        this.inventory.add(item);
    }
    public void removeFromInventory(Items item){
        this.inventory.remove(item);
    }

    public void gainExperience(int experience){
        if (this.experience + experience >= 100){
            this.experience = (this.experience + experience) - 100;
            this.levelUp();
        } else {
            this.experience += experience;
        }
    }
    public void levelUp(){
        this.setLevel(this.getLevel() + 1);
        this.setMaxHp(this.getMaxHp() + 10);
        this.setHp(this.getMaxHp());
    }

    @Override
    public String toString(){
        String playerString =
        "Player:\n" +
        this.getHp() + "\n" +
        this.getMaxHp() + "\n" +
        this.getExperience() + "\n" +
        this.getName() + "\n";
        for (int i = 0; i < this.getInventory().size(); i++){
            playerString += this.getInventory().get(i).getName() + " ";
        }
        playerString += "\n" +
        (this.getSelectedItem() == null ? "none" : this.getSelectedItem().getName()) + "\n" +
        this.getPosition().x + "," + this.getPosition().y + "\n" +
        this.getLevel();

        return playerString;
    }
    
    @Override
    public int[] move(int direction){
        //direction saisies claiver
        int dx =0;
        int dy =0;

        switch (direction) {
            case 37:
                //gauche
                dx = -1;
                break;
            case 39:
                //droite
                dx = 1;
                break;
            case 38:
                //haut
                dy = -1;
                break;
            case 40:
                //bas
                dy = 1;
                break;
            default:
                dy = 0;
                break;
        }
        return new int[]{dx, dy};
    }
}
