import java.util.Scanner;

public class Mine extends BattleLoc {
    //variables
    private double randomRate;
    private Award itemAward;
    private int moneyAward;
    private int randomWeaponId;
    private int randomArmorId;


    //constructor method
    public Mine(Player player) {
        super(player, "Maden", new Snake(), null, 6);
    }

    //End of the region. if complete mine the user may won any award. that could be money, weapon or armor.
    public void setMapAward() {
        this.randomRate = Math.random() * 100;
        if (randomRate >= 55) {
            System.out.println("Hiçbir şey kazanamadınız.");
        } else {
            if (randomRate < 55 && randomRate > 30) {
                this.moneyAward = this.getRandomMoney();
                System.out.println(moneyAward + " para kazandınız.");
                getPlayer().setMoney(getPlayer().getMoney() + this.moneyAward);
            } else {
                this.itemAward = this.getRandomItem();
                System.out.println(itemAward.getName() + " eşyasını kazandınız.");
                System.out.println("Bu eşyayı kullanmak ister misiniz: e/h");

                String select = input.nextLine();
                if (select.equals("e")) {
                    if (itemAward instanceof Weapon) {
                        getPlayer().getInventory().setWeapon((Weapon) itemAward);
                    } else {
                        getPlayer().getInventory().setArmor((Armor) itemAward);
                    }
                }
            }
        }
    }


    public Award getRandomItem() {
        if (this.randomRate < 15) {
            if (this.randomRate < 7.50) {
                randomWeaponId = 1;
            } else if (randomRate < 12) {
                randomWeaponId = 2;
            } else {
                randomWeaponId = 3;
            }
            return Weapon.getWeaponObjById(randomWeaponId);
        } else if (randomRate < 30) {
            if (randomRate < 22.50) {
                randomArmorId = 1;
            } else if (randomRate < 27) {
                randomArmorId = 2;
            } else {
                randomArmorId = 3;
            }
            return Armor.getArmorObjById(randomArmorId);
        }
        return null;
    }


    public int getRandomMoney() {
        int money = 0;
        if (randomRate < 42.50) {
            money = 1;
        } else if (randomRate < 50) {
            money = 5;
        } else {
            money = 10;
        }
        return money;
    }

    @Override
    public boolean completeMap() {
        System.out.println(this.getName() + " tüm düşmanları yendiniz!");
        setMapAward();

        return true;
    }


}
