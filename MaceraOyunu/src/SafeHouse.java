public class SafeHouse extends NormalLoc{
    public SafeHouse(Player player){
        super(player, "Güvenli Ev");
    }
    @Override
    public boolean visit() {
        System.out.println("Güvenli evdesiniz.\n" +
                "Canınız yenilendi.");
        this.getPlayer().setHealth(this.getPlayer().getOrijinalHealth());
        return true;
    }
}
