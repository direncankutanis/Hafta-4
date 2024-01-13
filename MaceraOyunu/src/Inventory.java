import java.util.ArrayList;
public class Inventory {
    private Weapon weapon;
    private Armor armor;
    private ArrayList <String> itemList = new ArrayList<>();


    public Inventory() {
        this.weapon = new Weapon("Yumruk", -1, 0, 0);
        this.armor = new Armor(-1, "Paçavra", 0, 0);
    }

    public void equipAward(Award award){

    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public void addItem(String item){
        this.itemList.add(item);
    }


}