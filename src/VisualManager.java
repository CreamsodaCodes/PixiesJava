import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public final class VisualManager {

    static BufferedImage gameBoard = new BufferedImage(Settings.getGirdLenght(), Settings.getGridHeight(), BufferedImage.TYPE_INT_ARGB);
    static JFrame frame;
    static JPanel panel;
    static JScrollPane scrollPane;
    static Graphics2D g2d;
    static boolean setUpDone = false;

    static void setUp(){
        SwingUtilities.invokeLater(() -> {
            BufferedImage bufferedImage = new BufferedImage(Settings.getGirdLenght(), Settings.getGridHeight(), BufferedImage.TYPE_INT_ARGB);
            g2d = bufferedImage.createGraphics();

            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, Settings.getGridHeight(), Settings.getGirdLenght());

            frame = new JFrame("Artificial Life Simulator 1.0");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(Settings.getDisplayLenght(), Settings.getDisplayHeight());
            panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(bufferedImage, 0, 0, null);
                }};
            panel.setPreferredSize(new Dimension(Settings.getGirdLenght(), Settings.getGridHeight()));
            panel.setLayout(null);
            scrollPane = new JScrollPane(panel);
            g2d.drawImage(bufferedImage, 0, 0, null);

            frame.add(scrollPane);
            frame.requestFocusInWindow();
            frame.setVisible(true);

            gameBoard = bufferedImage;
            setUpDone = true;
        });
    }


    public static void updateImage(BufferedImage newImage) {
            g2d.drawImage(newImage, 0, 0, null);
            frame.repaint();
    }


    public static void drawSquare(int x,int y, int size, Color color) {
        g2d.setColor(color);
        g2d.fillRect(x,y,size,size);
    }
    public static void drawSquare(int x,int y, int size, int color) {
        drawSquare(x,y,size,new Color(color));
    }

    public static boolean isAreaWhite( int startX, int startY, int width) {
        int whiteRGB = 0xFFFFFFFF; // RGB value for white

        for (int x = startX; x < startX + width; x++) {
            for (int y = startY; y < startY + width; y++) {
                int rgb = gameBoard.getRGB(x, y);
                if (rgb != whiteRGB) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isPlant( int x, int y) {
         // RGB value for white
        if (x < 0+10 || x > Settings.getGirdLenght()-10||y < 0+10 || y > Settings.getGirdLenght()-10) {
            return false;
        }
        if (gameBoard.getRGB(x, y) == Color.GREEN.getRGB()) {
            return true;
        }
        return false;
    }
    public static boolean isMeat( int x, int y) {
        if (x < 0+10 || x > Settings.getGirdLenght()-10||y < 0+10 || y > Settings.getGirdLenght()-10) {
            return false;
        }
        // RGB value for white
        if (gameBoard.getRGB(x, y) == Color.RED.getRGB()) {
            return true;
        }
        return false;
    }
    public static boolean isCreature( int x, int y) {
        if (x < 0+10 || x > Settings.getGirdLenght()-10||y < 0+10 || y > Settings.getGirdLenght()-10) {
            return false;
        }
        // RGB value for white
        if (gameBoard.getRGB(x, y) != Color.RED.getRGB()&& gameBoard.getRGB(x, y) != Color.GREEN.getRGB()&& gameBoard.getRGB(x, y) != Color.WHITE.getRGB()) {
            return true;
        }
        return false;
    }




    }


