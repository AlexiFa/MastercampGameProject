import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TileGridExample extends JFrame {
    private static final int Y_OFFSET = 30;
    private static final int X_OFFSET = 10;
    private static int GRID_SIZE_X; // Taille de la grille (nombre de tuiles en hauteur)
    private static int GRID_SIZE_Y; // Taille de la grille (nombre de tuiles en largeur)
    private static final int TILE_SIZE = 15; // Taille d'une tuile en pixels
    private static final String IMAGE_PATH = ".\\frame\\wall.png"; // Chemin de l'image

    private BufferedImage tileImage; // Image de la tuile
    private Point previousPlayerPosition = new Point(-1, -1); // Position du joueur

    Map map = new Map();

    public TileGridExample() {
        try {
            tileImage = ImageIO.read(new File(IMAGE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }

        GRID_SIZE_X = map.getRoom1().length;
        GRID_SIZE_Y = map.getRoom1()[0].length;

        // init
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                int keyCode = evt.getKeyCode();
                // si une des fleches est appuyée
                if(keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT){
                    map.move(evt.getKeyCode());
                    repaint();
                }
            }
        });

        setTitle("Tile Grid Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(GRID_SIZE_Y * TILE_SIZE + X_OFFSET + 10, GRID_SIZE_X * TILE_SIZE + Y_OFFSET + 10);
        setLocationRelativeTo(null);
        setResizable(false);

        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if(map.getPlayer().getPosition().getX() != previousPlayerPosition.getX() || map.getPlayer().getPosition().getY() != previousPlayerPosition.getY()){
            Point playerPos = map.getPlayer().getPosition();
            int x = X_OFFSET + (int) playerPos.getY() * TILE_SIZE;
            int y = Y_OFFSET + (int) playerPos.getX() * TILE_SIZE;
            char playerTileChar = map.getRoom1()[(int) playerPos.getX()][(int) playerPos.getY()];
            g.drawImage(getImageForChar(playerTileChar), x, y, TILE_SIZE, TILE_SIZE, this);
            previousPlayerPosition = map.getPlayer().getPosition();
        }

        for (int row = 0; row < GRID_SIZE_X; row++) {
            for (int col = 0; col < GRID_SIZE_Y; col++) {
                int x = X_OFFSET + col * TILE_SIZE;
                int y = Y_OFFSET + row * TILE_SIZE;

                char tileChar = map.getRoom1()[row][col];
                BufferedImage image = getImageForChar(tileChar);
                g.drawImage(image, x, y, TILE_SIZE, TILE_SIZE, this);
            }
        }
    }

    private BufferedImage getImageForChar(char tileChar) {
        switch (tileChar){
            case '#':
                try {
                    tileImage = ImageIO.read(new File(".\\frame\\wall.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case ' ':
                try {
                    tileImage = ImageIO.read(new File(".\\frame\\grass.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 'H':
                try {
                    tileImage = ImageIO.read(new File(".\\frame\\monster.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
        return tileImage;
    }

    public static void main(String[] args) {
        Map map = new Map();
        SwingUtilities.invokeLater(TileGridExample::new);
    }
}
