public class Map {
    public char[][] map;
    public int sizeX, sizeY; // X is the number of lines, Y is the number of columns

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
        // horizontal walls
        for (int i = 0; i < 8; i++) {
            int refHWallX; // reference horizontal wall X
            int refHWallY; // reference horizontal wall Y
            int lengthHWall; // length of the horizontal wall
            boolean isWall;

            lengthHWall = (int) (Math.random() * (y - 60)) + 30; // random number between 30 and y - 30 ( the size of the wall )

            // while there is a wall on the line below, above or on the current line, generate another random number
            do{
                refHWallX = (int) (Math.random() * (x - 4)) + 2; // random number between 2 and x - 2
                isWall = false;

                for(int k = 1;k < y - 1;k++){
                    if (this.map[refHWallX + 1][k] == '#') // if there is a wall on the line below
                        isWall = true;
                    if (this.map[refHWallX - 1][k] == '#') // if there is a wall on the line above
                        isWall = true;
                    if (this.map[refHWallX][k] == '#') // if there is a wall on the current line
                        isWall = true;
                }
            }while(isWall); // then, stay in the loop and generate another random number

            // while the wall goes out of the map, decrement the random number (move the wall backward)
            do{
                refHWallY = (int) (Math.random() * (y - 4)) + 2; // random number between 2 and y - 2 ( to avoid walls on the borders )
                isWall = false;

                if(refHWallY + lengthHWall > y - 1) {
                    refHWallY--;
                    isWall = true;
                }
            }while(isWall);

            for(int j = 0;j < lengthHWall - 1;j++){
                if (refHWallY + j < y - 1)
                    this.map[refHWallX][refHWallY + j] = '#';
            }
        }
        // vertical walls

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
