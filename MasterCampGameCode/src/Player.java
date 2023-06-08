//getters and setters for experience, inventory, and selectedItem
//getDamage() returns the damage of the player
//addToInventory() adds an item to the inventory
//removeFromInventory() removes an item from the inventory
//gainExperience() adds experience to the player and levels them up if they have enough experience
//levelUp() levels up the player

import java.util.ArrayList;

public class Player extends Entity{
    private int experience;
    private ArrayList<Items> inventory;
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
    public ArrayList<Items> getInventory(){
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
}
