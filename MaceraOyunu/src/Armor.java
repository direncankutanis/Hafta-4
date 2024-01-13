public class Armor extends Award{

    private int block;

    public Armor(int id, String name, int block, int price) {
        super(name, id, price);
        this.block = block;
    }

    @Override
    public String getName() {
        return this.name + " Zırh";
    }


    public static Armor[] armors(){
        Armor[] armorList = new Armor[3];
        armorList[0] = new Armor(1, "Hafif", 1, 15);
        armorList[1] = new Armor(2, "Orta", 3, 25);
        armorList[2] = new Armor(3, "Ağır", 5, 40);
        return armorList;
    }

    public static Armor getArmorObjById(int id){
        for (Armor a : Armor.armors()){
            if(a.getId() == id){
                return a;
            }
        }
        return null;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

}