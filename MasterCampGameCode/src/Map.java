public class Map {
    public char[][] map;
    public int sizeX, sizeY; // X is the number of lines, Y is the number of columns

    public Map(){
        int x = 22;
        int y = 80;
//        int x = 22;
//        int y = 22;
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

            // lengthHWall = (int) (Math.random() * (y - 1 - y/2)) + y/2; // random number between y / 4 and y / 2 ( the size of the wall )
            lengthHWall = 5;
            // while there is a wall on the line below, above or on the current line, generate another random number
            do{
                System.out.println("while1");
                refHWallX = (int) (Math.random() * (x - 4)) + 2; // random number between 2 and x - 2
                isWall = false;

                for(int k = 1;k < y - 1;k++){
                    System.out.println("for1");
                    if (this.map[refHWallX + 1][k] == '#') { // if there is a wall on the line below
                        isWall = true;
                        System.out.println("if1");
                    }
                    if (this.map[refHWallX - 1][k] == '#') { // if there is a wall on the line above
                        System.out.println("if2");
                        isWall = true;
                    }
                }
            }while(isWall); // then, stay in the loop and generate another random number

            refHWallY = (int) (Math.random() * (y - 4)) + 2; // random number between 2 and y - 2 ( to avoid walls on the borders )
            // while the wall goes out of the map, decrement the random number (move the wall backward)
            do{
                System.out.println("while2");
                isWall = false;

                if(refHWallY + lengthHWall > y - 1) {
                    System.out.println("if4");
                    refHWallY--;
                    isWall = true;
                }
            }while(isWall);

            for(int j = 0;j < lengthHWall - 1;j++){
                System.out.println("for2");
                if (refHWallY + j < y - 1) {
                    System.out.println("if5");
                    this.map[refHWallX][refHWallY + j] = '#';
                }
            }
        }
        // vertical walls
        for (int i = 0; i < 20; i++) {
            int refVWallY; // reference vertical wall X
            int refVWallX; // reference vertical wall Y
            int lengthVWall; // length of the vertical wall
            boolean isWall;

            // lengthVWall = (int) (Math.random() * (x - 10 - 10)) + 10; // random number between 10 and x - 10 ( the size of the wall )
            lengthVWall = 7;
            // while there is a wall on the line below, above or on the current line, generate another random number
            do{
                System.out.println("first");
                refVWallY = (int) (Math.random() * (y - 4)) + 2; // random number between 2 and y - 2
                isWall = false;

                for(int k = 1;k < x - 2;k++){
                    if ((this.map[k][refVWallY + 1] == '#' && this.map[k + 1][refVWallY + 1] == '#') || (this.map[k][refVWallY + 2] == '#' && this.map[k + 1][refVWallY + 2] == '#') || (this.map[k][refVWallY + 3] == '#' && this.map[k + 1][refVWallY + 3] == '#') || (this.map[k][refVWallY + 4] == '#' && this.map[k + 1][refVWallY + 4] == '#')) // if there is a wall on the line below
                        isWall = true;
                    if ((this.map[k][refVWallY - 1] == '#' && this.map[k + 1][refVWallY - 1] == '#') || (this.map[k][refVWallY - 2] == '#' && this.map[k + 1][refVWallY - 2] == '#') || (this.map[k][refVWallY - 3] == '#' && this.map[k + 1][refVWallY - 3] == '#') || (this.map[k][refVWallY - 4] == '#' && this.map[k + 1][refVWallY - 4] == '#')) // if there is a wall on the line above
                        isWall = true;
                }
            }while(isWall); // then, stay in the loop and generate another random number

            // while the wall goes out of the map, decrement the random number (move the wall backward)
            do{
                System.out.println("second");
                refVWallX = (int) (Math.random() * (x - 4)) + 2; // random number between 2 and x - 2 ( to avoid walls on the borders )
                isWall = false;

                if(refVWallX + lengthVWall > x - 1) {
                    refVWallX--;
                    isWall = true;
                }
            }while(isWall);

            for(int j = 0;j < lengthVWall - 1;j++){
                if (refVWallX + j < x - 1)
                    this.map[refVWallX + j][refVWallY] = '#';
            }
        }
    }

    public void printMap(){
        for (int i = 0; i < this.sizeX; i++) {
            for (int j = 0; j < this.sizeY; j++) {
                System.out.print(this.map[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Map map = new Map();
        map.printMap();
    }
}
