import java.util.Scanner;
public class Game {
    //Call scanner class
    private Scanner input =  new Scanner (System.in);

    public void start(){
        System.out.println("Macera oyununa hoşgeldiniz!");
        System.out.print("Lütfen bir isim giriniz:");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println(player.getName() + " Hoşgeldiniz.");
        //Select character method
        player.selectChar();
        //default location null
        Location location = null;

        //Polimorpism: new objects are created parent class
        Location cave = new Cave(player);
        Location forest = new Forest(player);
        Location river = new River(player);
        Location mine = new Mine(player);

        while (true){
            //While loop works until return false
            player.printInfo();
            System.out.println("\nBölgeler: \n");
            System.out.println("1- Güvenli Ev --> Burası sizin için güvenli bir ev, düşman yoktur.");
            System.out.println("2- Eşya Dükkanı --> Silah ya da zırh satın alabilirsiniz. ");
            System.out.println("3- Mağara --> Ödül: Yemek. Mağaraya gir, dikkatli ol zombi çıkabilir.");
            System.out.println("4- Orman --> Ödül: Odun. Ormana git, dikkatli ol vampir çıkabilir.");
            System.out.println("5- Nehir --> Ödül: Su. Nehire git, dikkatli ol ayı çıkabilir.");
            System.out.println("6- Maden --> Ödül: Rastgele eşya. Madene git, dikkatli ol yılan çıkabilir.");
            System.out.println("0- Çıkış Yap --> Oyunu sonlandır. \n");
            System.out.print("Lütfen gitmek istediğiniz bölgeyi seçiniz:");

            int selectLoc = input.nextInt();
            //call constructor method of the destination region
            switch (selectLoc){
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    if(cave.isCompleted() && forest.isCompleted() && river.isCompleted()){
                        System.out.println("Oyun başarılı.");
                        return;
                    }
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    location = cave;
                    break;
                case 4:
                    location = forest;
                    break;
                case 5:
                    location = river;
                    break;
                case 6:
                    location = mine;
                    break;
                default:
                    System.out.println("Lütfen geçerli bir bölge giriniz. ");
            }

            if(location == null){
                System.out.println("Game Over");
                break;
            }
            //visit method is booelan.
            boolean isGameOn = location.visit();
            //Game on control.
            if(!isGameOn){
                System.out.println("Game Over!");
                break;
            }
        }
    }
}
