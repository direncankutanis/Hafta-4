import java.util.Random;

public abstract class BattleLoc extends Location {
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;
    double firstHitFromObs = Math.random()*100;


    public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    @Override
    public boolean visit() {
        if(isCompleted){
            System.out.println("Buraya zaten gittiniz, lütfen başka bir bölge seçiniz.");
            return true;
        }
        int obsNumber = this.randomObstacleNumber();
        System.out.println("Buradasınız: " + this.getName());
        System.out.println("Dikkat et. Burada " + obsNumber + " tane " +
                this.getObstacle().getName() + " yaşıyor.");
        System.out.println("Savaş ya da Kaç");
        String selectCase = input.nextLine().toUpperCase();

        if (selectCase.equals("S") && combat(obsNumber)) {
            return this.completeMap();
        }
        if (selectCase.equals("K")) {
            return true;
        }


        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("Öldünüz. Game Over.");
            return false;
        }
        return true;
    }

    public boolean completeMap (){
        System.out.println(this.getName() + " tüm düşmanları yendiniz!");

        System.out.println("Yeni bir eşya kazandınız: " + this.award);
        this.getPlayer().getInventory().addItem(this.award);
        isCompleted = true;

        return true;
    }

    public boolean combat(int obsNumber) {
        for (int i = 1; i <= obsNumber; i++) {
            this.getObstacle().setHealth(this.getObstacle().getOrijinalHealth());
            playerStats();
            obstacleStats(i);
            if(firstHitFromObs < 50){
                getPlayer().setHealth(getPlayer().getHealth() - getObstacle().getDamage());
                System.out.println(this.getObstacle().getName() + " sizi fark etti ve size saldırdı. Canınız: " + getPlayer().getHealth());
            }
            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                System.out.print("<V>ur ya da <K>aç: ");
                String selectCombat = input.nextLine().toUpperCase();
                if (selectCombat.equals("V")) {
                    System.out.println("Siz vurdunuz! ");
                    this.getObstacle().setHealth(this.obstacle.getHealth() - this.getPlayer().getTotalDamage());
                    afterHit();
                    if (this.getObstacle().getHealth() > 0) {
                        System.out.println();
                        System.out.println("Canavar Size Vurdu!");
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if (obstacleDamage < 0) {
                            obstacleDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                        afterHit();
                    }
                }
                if (selectCombat.equals("K")) {
                    break;
                }
            }
            if (this.getObstacle().getHealth() <= 0) {
                System.out.println("Düşmanı yendiniz.");
                System.out.println(this.getObstacle().getMoneyAward() + " para kazandınız.");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getMoneyAward());
                System.out.println("Güncel paranız: " + this.getPlayer().getMoney());
            } else {
                return false;
            }
        }
        return true;
    }

    public void afterHit() {
        System.out.println("Canınız: " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " Canı: " + this.getObstacle().getHealth());
        System.out.println();
    }

    public void obstacleStats(int i) {
        System.out.println(i + "." + this.getObstacle().getName() + " Değerleri:");
        System.out.println("--------------------------");
        System.out.println("Sağlık: " + this.getObstacle().getHealth());
        System.out.println("Hasar: " + this.getObstacle().getDamage());
        System.out.println("Ödül: " + this.getObstacle().getMoneyAward());
    }

    public void playerStats() {
        System.out.println("Oyuncu Değerleri");
        System.out.println("----------------");
        System.out.println("Sağlık: " + this.getPlayer().getHealth());
        System.out.println("Silah: " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Hasar: " + this.getPlayer().getTotalDamage());
        System.out.println("Zırh: " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama: " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Para: " + this.getPlayer().getMoney());


    }

    public int randomObstacleNumber() {
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }
}