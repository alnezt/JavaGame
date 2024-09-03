import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Menu implements MouseListener {
    private Game game;
    private static boolean inMenu = true;  // To keep track if the game is in menu mode

    public Menu(Game game) {
        this.game = game;

        Canvas canvas = game.wnd.GetCanvas();
        if (canvas != null) {
            canvas.addMouseListener(this);
        } else {
            System.err.println("Canvas is not initialized in GameWindow.");
        }
    }

    public static void render(Graphics g) {
        if (inMenu) {
            Font fnt = new Font("Monospaced", Font.BOLD, 50);
            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Game Menu", 280, 150);

            Font fnt2 = new Font("Monospaced", Font.BOLD, 30);
            g.setFont(fnt2);
            g.drawString("Start Game", 320, 250);
            g.drawRect(300, 200, 200, 50);

            /* Uncomment if Load Game feature is added
            g.drawString("Load Game", 320, 350);
            g.drawRect(300, 300, 200, 50);
            */

            g.drawString("Exit", 360, 450);
            g.drawRect(300, 400, 200, 50);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (inMenu) {
            if (mx >= 300 && mx <= 500) {
                // Start Game button
                if (my >= 200 && my <= 250) {
                    inMenu = false;
                    game.StartGame();
                }
                // Load Game button (uncomment if Load Game feature is implemented)
                /*
                if (my >= 300 && my <= 350) {
                    inMenu = false;
                    game.loadGameState();
                    game.StartGame();
                }
                */
                // Exit button
                if (my >= 400 && my <= 450) {
                    System.exit(0);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
