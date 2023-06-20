
import java.awt.Point;
import java.util.Scanner;
public class Map {

    private String room =""
            + "#######\n"
            + "###   #\n"
            + "#   0 #\n"
            + "# H   #\n"
            + "#   0 #\n"
            + "##  > #\n"
            + "#######";

    private char[][] room1;
    private MapGenerator m1;
    private char[][][] map;
    private Point playerPosition = new Point();

    public Map() {
        m1 = new MapGenerator();
        room1 = m1.getMap();
        map = new char[2][m1.getSizeY()][m1.getSizeX()];
        init();
    }

    // initialisation de la map et la position du joueur
    private void init() {

        /*String[] lines = room.split("\\n");
        int y = 0;



        //Parcourt chaque ligne et chaque charactère de la room
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            for (int x = 0; x < lines[i].length(); x++) {

                //determine le charactère
                char charactere = line.charAt(x);
                map[charactere / 70][x][y] = charactere;
                map[1 - charactere / 70][x][y] = ' ';
                //determine la position du joueur
                if (charactere == 'H') {
                    playerPosition.x = x;
                    playerPosition.y = y;
                }
            }
            y++;
        }
        //remplace charactère position du joueur par un espace
        map[1][playerPosition.x][playerPosition.y] = ' ';*/

        int y = 0;

        //Parcourt chaque ligne et chaque charactère de la room
        for (int i = 0; i < room1.length; i++) {
            for (int x = 0; x < room1[i].length; x++) {
                char charactere = room1[i][x];
                map[charactere / 70][x][y] = charactere;
                map[1 - charactere / 70][x][y] = ' ';
                //determine la position du joueur
                if (charactere == 'H') {
                    playerPosition.x = x;
                    playerPosition.y = y;
                }
            }
            y++;
        }
        //remplace charactère position du joueur par un espace
        map[1][playerPosition.x][playerPosition.y] = ' ';

    }

    //deplacement du joueur dans la room
    public void move(int direction)
    {
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



        if (map[0][playerPosition.x + dx][playerPosition.y + dy] == '0' )
        {
            map[0][playerPosition.x + dx][playerPosition.y + dy] = ' ';
        }

        //Si le joueur passe sur la porte D une nouvelle room est créée
        if (map[0][playerPosition.x + dx][playerPosition.y + dy] == '>')
        {
            m1 = new MapGenerator();
            room1 = m1.getMap();
            map = new char[2][m1.getSizeY()][m1.getSizeX()];
            init();
        }

        if (map[1][playerPosition.x + dx][playerPosition.y + dy] != '#' && map[0][playerPosition.x + dx][playerPosition.y + dy] != '#')
             {
            playerPosition.x += dx;
            playerPosition.y += dy;
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
                if (playerPosition.x == x && playerPosition.y == y)
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



