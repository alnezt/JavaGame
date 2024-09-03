package PaooGame.Keyhandlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private boolean sus, jos, stanga, dreapta, running, jumpdublu,save;

    public KeyHandler() {
        // Constructor left empty for now
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used in this example
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // Handle different key presses
        switch (keyCode) {
            case KeyEvent.VK_W:
               // System.out.println("w este tastat");
                sus = true;
                break;
            case KeyEvent.VK_A:
               // System.out.println("a este tastat");
                stanga = true;
                break;
            case KeyEvent.VK_S:
               // System.out.println("s este tastat");
                jos = true;
                break;
            case KeyEvent.VK_D:
              //  System.out.println("d este tastat");
                dreapta = true;
                break;
            case KeyEvent.VK_SPACE:
              //  System.out.println("space este tastat");
                running = true;
                break;
            case KeyEvent.VK_M:
               // System.out.println("m este tastat");
                jumpdublu = true;
                break;
            case KeyEvent.VK_E:
                save=true;
                break;
            // Add more cases for other keys as needed
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // Handle key releases
        switch (keyCode) {
            case KeyEvent.VK_W:
                sus = false;
                break;
            case KeyEvent.VK_A:
                stanga = false;
                break;
            case KeyEvent.VK_S:
                jos = false;
                break;
            case KeyEvent.VK_D:
                dreapta = false;
                break;
            case KeyEvent.VK_SPACE:
                running= false;
                break;
            case KeyEvent.VK_M:
                jumpdublu = false;
                break;
                case KeyEvent.VK_E:
                    save=false;
                    break;
            // Add more cases for other keys as needed
        }
    }

    // Methods to check the state of keys
    public boolean isSus() {
        return sus;
    }

    public boolean isJos() {
        return jos;
    }

    public boolean isStanga() {
        return stanga;
    }

    public boolean isDreapta() {
        return dreapta;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isJumpdublu() {
        return jumpdublu;
    }
    public boolean isSaved(){return save;}


}
