//getter and setter for value, name and type

public class Items {
    private int value;
    private String name;
    private boolean type;

    public Items(int value, String name, boolean type){
        this.value = value;
        this.name = name;
        this.type = type; //si type est vrai, alors l'item heal. Sinon, il s'agit d'une arme
    }

    public int getValue(){
        return this.value;
    }
    public String getName(){
        return this.name;
    }
    public boolean getType(){
        return this.type;
    }

    public void setValue(int value){
        this.value = value;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setType(boolean type){
        this.type = type;
    }

    @Override
    public String toString(){
        return "Item:\n" +
        this.getValue() + "," +
        this.getName();
    }
}
