


import java.awt.Point;
import java.util.Scanner;
public class Map {

        private String room =""
                + "######\n"
                + "###  #\n"
                + "# O  #\n"
                + "# H  #\n"
                + "#   O#\n"
                + "##   #\n"
                + "######";

        private char[][][] map = new char[2][6][7];
        private Point playerPosition = new Point();

        public Map() {
            init();
        }

        // initialisation de la map et la position du joueur
        private void init() {

            String[] lines = room.split("\\n");
            int y = 0;

            //Parcourt chaque ligne et chaque charactère de la room
            for (int i = 0; i < lines.length; i++) {
                String line = lines[i];
                for (int x = 0; x < lines[i].length(); x++) {

                    //determine le charactère
                    char charactere = line.charAt(x);
                    map[charactere / 50][x][y] = charactere;
                    map[1 - charactere / 50][x][y] = ' ';
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



            // check si la case voisine contient OR, si oui, remplace par H
            if ( map[1][playerPosition.x + dx][playerPosition.y + dy] == 'O') {
                map[1][playerPosition.x][playerPosition.y] = 'H';

            }

            // check si la case voisine contient un mur, si oui, ne bouge pas
            if ( map[1][playerPosition.x + dx][playerPosition.y] != 'O'
                    && map[0][playerPosition.x + dx][playerPosition.y] != '#') {

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
                    } else
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
            while (true) {
                int direction = sc.nextInt();
                map.move(direction);
                System.out.println(map.toString());
            }

        }

    }


