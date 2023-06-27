import java.util.Random;

public class MapGenerator {
    private char[][] map;
    private int sizeX, sizeY; // X is the number of lines, Y is the number of columns

    public MapGenerator(){
        int x = 22;
        int y = 80;
        this.sizeX = x;
        this.sizeY = y;
        // create map borders
        this.map = new char[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (i == 0 || i == x - 1 || j == 0 || j == y - 1) {
                    this.map[i][j] = '#';
                } else {
                    this.map[i][j] = ' ';
                }
            }
        }
        // create map obstacles
        // horizontal walls
        for (int i = 0; i < 60; i++) {
            int refHWallX; // reference horizontal wall X
            int refHWallY; // reference horizontal wall Y
            int lengthHWall; // length of the horizontal wall
            boolean isWall;

            lengthHWall = 5;

            // while there is a wall on the line below, above or on the current line, generate another random number
            do{
                refHWallX = (int) (Math.random() * (x - 4)) + 2; // random number between 2 and x - 2
                isWall = false;

                for(int k = 1;k < y - 1;k++){
                    if (this.map[refHWallX + 1][k] == '#') { // if there is a wall on the line below
                        isWall = true;
                    }
                    if (this.map[refHWallX - 1][k] == '#') { // if there is a wall on the line above
                        isWall = true;
                    }
                }
            }while(isWall); // then, stay in the loop and generate another random number

            refHWallY = (int) (Math.random() * (y - 4)) + 2; // random number between 2 and y - 2 ( to avoid walls on the borders )

            for(int j = 0;j < lengthHWall - 1;j++){
                if (refHWallY + j < y - 1) {
                    this.map[refHWallX][refHWallY + j] = '#';
                }
            }
        }
        // vertical walls

        int itemCounter = 0;
        for (int i = 0; i < 20; i++) {

            if (itemCounter < 7) {
                if (itemCounter < 3) {
                    this.map[(int) (Math.random() * (x - 4)) + 2][(int) (Math.random() * (y - 4)) + 2] = '*';
                    itemCounter++; // Incrémenter le compteur d'éléments
                } else {
                    this.map[(int) (Math.random() * (x - 4)) + 2][(int) (Math.random() * (y - 4)) + 2] = 'A';
                    itemCounter++; // Incrémenter le compteur d'éléments
                }
            }

            int refVWallY; // reference vertical wall X
            int refVWallX; // reference vertical wall Y
            int lengthVWall; // length of the vertical wall
            boolean isWall;

            lengthVWall = 7;

            // while there is a wall on the line below, above or on the current line, generate another random number
            do{
                refVWallY = (int) (Math.random() * (y - 4)) + 2; // random number between 2 and y - 2
                isWall = false;

                for(int k = 1;k < x - 2;k++){
                    if ((this.map[k][refVWallY + 1] == '#' && this.map[k + 1][refVWallY + 1] == '#') || (this.map[k][refVWallY + 2] == '#' && this.map[k + 1][refVWallY + 2] == '#') || (this.map[k][refVWallY + 3] == '#' && this.map[k + 1][refVWallY + 3] == '#') || (this.map[k][refVWallY + 4] == '#' && this.map[k + 1][refVWallY + 4] == '#')) // if there is a wall on the line below
                        isWall = true;
                    if ((this.map[k][refVWallY - 1] == '#' && this.map[k + 1][refVWallY - 1] == '#') || (this.map[k][refVWallY - 2] == '#' && this.map[k + 1][refVWallY - 2] == '#') || (this.map[k][refVWallY - 3] == '#' && this.map[k + 1][refVWallY - 3] == '#') || (this.map[k][refVWallY - 4] == '#' && this.map[k + 1][refVWallY - 4] == '#')) // if there is a wall on the line above
                        isWall = true;
                }
            }while(isWall); // then, stay in the loop and generate another random number

            refVWallX = (int) (Math.random() * (x - 4)) + 2; // random number between 2 and x - 2 ( to avoid walls on the borders )

            for(int j = 0;j < lengthVWall - 1;j++){
                if (refVWallX + j < x - 1)
                    this.map[refVWallX + j][refVWallY] = '#';
            }
            this.map[2][2] = 'H';
            this.map[x - 3][y - 3] = '>';
        }
        Random random = new Random();
        int xM = random.nextInt(20) + 1;
        int yM = random.nextInt(78) + 1;
        this.map[xM][yM] = 'M';
    }

    public void printMap(){
        for (int i = 0; i < this.sizeX; i++) {
            for (int j = 0; j < this.sizeY; j++) {
                System.out.print(this.map[i][j]);
            }
            System.out.println();
        }
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public char[][] getMap() {
        return map;
    }

    public void setSize() {
        this.sizeX = map.length;
        this.sizeY = map[0].length;
    }

    public void setMap(char[][] map) {
        this.map = map;
    }
}
