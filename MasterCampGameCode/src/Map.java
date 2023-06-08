public class Map {
    public char[][] map;
    public int sizeX, sizeY;

    public Map(){
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
        for (int i = 0; i < 8; i++) {
            int refVWallX;
            int refVWallY;
            int lengthVWall;
            boolean isWall;
//            boolean isOutTheMap;

            lengthVWall = (int) (Math.random() * (y - 60)) + 30; // random number between 30 and y - 30 ( the size of the wall )
            refVWallY = (int) (Math.random() * (y - 4)) + 2; // random number between 2 and y - 2 ( to avoid walls on the borders )

            // while there is a wall on the line below, above or on the current line, generate another random number
            // while the wall goes out of the map, decrement the random number
            do{
                refVWallX = (int) (Math.random() * (x - 4)) + 2; // random number between 2 and x - 2
                isWall = false;
//                isOutTheMap = false;
//
//                if (refVWallY + lengthVWall > y - 1) {
//                    refVWallX--;
//                    isOutTheMap = true;
//                }

                for(int k = 1;k < y - 1;k++){
                    if (this.map[refVWallX + 1][k] == '#') // if there is a wall on the line below
                        isWall = true;
                    if (this.map[refVWallX - 1][k] == '#') // if there is a wall on the line above
                        isWall = true;
                    if (this.map[refVWallX][k] == '#') // if there is a wall on the current line
                        isWall = true;
                }
            }while(isWall); // then, stay in the loop and generate another random number


            for(int j = 0;j < lengthVWall - 1;j++){
                if (refVWallY + j < y - 1)
                    this.map[refVWallX][refVWallY + j] = '#';
            }
//            this.map[x1][y1] = '#';
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
