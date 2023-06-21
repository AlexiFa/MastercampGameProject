
import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
public class Map {


    private char[][] room1;
    private MapGenerator m1;
    private char[][][] map;
    private Player player;
    private ArrayList<Items> arme = new ArrayList<Items>();
    private Items potion;

    private Random rand = new Random();

    private Monster monster;

    public Map() {
        player = new Player(100, "Hero");

        arme.add(new Items(rand.nextInt(100 - 20 + 1) + 20, "Epee", false));
        arme.add(new Items(rand.nextInt(100 - 20 + 1) + 20, "Hache", false));
        arme.add(new Items(rand.nextInt(100 - 20 + 1) + 20, "Dague", false));

        potion = new Items(rand.nextInt(50 - 10 + 1) + 10, "Potion", true);

        monster = new Monster(100, 1, 120, "monstre");

        m1 = new MapGenerator();
        room1 = m1.getMap();
        map = new char[2][m1.getSizeY()][m1.getSizeX()];
        init(player);
    }




    // initialisation de la map et la position du joueur
    private void init(Player p) {
        int y = 0;
        //Parcourt chaque ligne et chaque charactère de la room
        for (int i = 0; i < room1.length; i++) {
            for (int x = 0; x < room1[i].length; x++) {
                char charactere = room1[i][x];
                map[charactere / 70][x][y] = charactere;
                map[1 - charactere / 70][x][y] = ' ';
                //determine la position du joueur
                if (charactere == 'H') {
                    player.setPlayerPosition(new Point(x, y));
                }
            }
            y++;
        }
        //remplace charactère position du joueur par un espace
        map[1][player.getPlayerPosition().x][player.getPlayerPosition().y] = ' ';

    }

    //deplacement du joueur dans la room
    public void move(int direction)
    {
        int[] deplacements = player.move(direction);
        int dx = deplacements[0];
        int dy = deplacements[1];

        if (map[0][player.getPlayerPosition().x + dx][player.getPlayerPosition().y + dy] == '0' )
        {
            map[0][player.getPlayerPosition().x + dx][player.getPlayerPosition().y + dy] = ' ';
        }

        if (map[0][player.getPlayerPosition().x + dx][player.getPlayerPosition().y + dy] == '*')
        {
            map[0][player.getPlayerPosition().x + dx][player.getPlayerPosition().y + dy] = ' ';
            player.addToInventory(potion);
            System.out.println("Vous avez trouvé une potion");
            System.out.println(potion.getValue());


        }

        if(map[0][player.getPlayerPosition().x + dx][player.getPlayerPosition().y + dy] == 'A')
        {
            map[0][player.getPlayerPosition().x + dx][player.getPlayerPosition().y + dy] = ' ';
            player.addToInventory(arme.get(rand.nextInt(arme.size())));
            System.out.println("Vous avez trouvé une arme");
            System.out.println(arme.get(rand.nextInt(arme.size())).getValue());

        }

        


        //Si le joueur passe sur la porte D une nouvelle room est créée
        if (map[0][player.getPlayerPosition().x + dx][player.getPlayerPosition().y + dy] == '>')
        {
            m1 = new MapGenerator();
            room1 = m1.getMap();
            map = new char[2][m1.getSizeY()][m1.getSizeX()];
            init(player);
        }

        if ( map[1][player.getPlayerPosition().x + dx][player.getPlayerPosition().y + dy] != 'M' && map[0][player.getPlayerPosition().x + dx][player.getPlayerPosition().y + dy] != '#' )
             {
                 int tempx = player.getPlayerPosition().x += dx;
                 int tempy = player.getPlayerPosition().y += dy;
                 player.setPlayerPosition(new Point(tempx, tempy));
        }

         if (map[1][player.getPlayerPosition().x + dx][player.getPlayerPosition().y + dy] == 'M')
        {
            player.setHp(player.getHp() - monster.getDamage());
            System.out.println("Monster damage " + monster.getDamage());
            System.out.println("Player HP " + player.getHp() );
        }

    }

    @Override
    public String toString()
    {
        String out = "";

        for (int y =0; y< map[0][0].length; y++)
        {
            for(int x=0; x< map[0].length; x++)
            {
                //si la position du joueur est égale à la position du charactère, affiche H
                if (player.getPlayerPosition().x == x && player.getPlayerPosition().y == y)
                {
                    out += ('H');
                }
                else if ( map[1][x][y] != ' ')
                {
                    out += map[1][x][y];
                }
                else if (map[1][x][y] == 'O')
                {
                    out += ' ';
                    map[1][x][y] = ' ';
                }
                else
                {
                    out += map[0][x][y];
                }

            }
            out += "\n";
        }
        return out;
    }

    public static void main(String[] args)
    {

        Map map = new Map();
        System.out.println(map.toString());
        Scanner sc = new Scanner(System.in);
        System.out.println(-71/70);
        while (true) {
            int direction = sc.nextInt();
            map.move(direction);
            System.out.println(map.toString());
        }
    }

}



