package PaooGame.GameWindow;

import javax.swing.*;
import java.awt.*;

/*! \class GameWindow
    \brief Implementeaza notiunea de fereastra a jocului.

    Membrul wndFrame este un obiect de tip JFrame care va avea utilitatea unei
    ferestre grafice si totodata si cea a unui container (toate elementele
    grafice vor fi continute de fereastra).
 */
public class GameWindow {
    public JFrame wndFrame;       /*!< fereastra principala a jocului*/
    private String wndTitle;       /*!< titlul ferestrei*/
    private int wndWidth;       /*!< latimea ferestrei in pixeli*/
    private int wndHeight;      /*!< inaltimea ferestrei in pixeli*/

    private Canvas canvas;         /*!< "panza/tablou" in care se poate desena*/


    public GameWindow(String title, int width, int height) {
        wndTitle = title;    /*!< Retine titlul ferestrei.*/
        wndWidth = width;    /*!< Retine latimea ferestrei.*/
        wndHeight = height;   /*!< Retine inaltimea ferestrei.*/
        wndFrame = null;     /*!< Fereastra nu este construita.*/
    }

    /*! \fn private void BuildGameWindow()
        \brief Construieste/creaza fereastra si seteaza toate proprietatile
        necesare: dimensiuni, pozitionare in centrul ecranului, operatia de
        inchidere, invalideaza redimensionarea ferestrei, afiseaza fereastra.

     */
    public void BuildGameWindow() {
        /// Daca fereastra a mai fost construita intr-un apel anterior
        /// se renunta la apel
        if (wndFrame != null) {
            return;
        }
        /// Aloca memorie pentru obiectul de tip fereastra si seteaza denumirea
        /// ce apare in bara de titlu
        wndFrame = new JFrame(wndTitle);
        /// Seteaza dimensiunile ferestrei in pixeli
        wndFrame.setSize(wndWidth, wndHeight);

        wndFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        wndFrame.setResizable(false);

        wndFrame.setLocationRelativeTo(null);

        wndFrame.setVisible(true);

        /// Creaza obiectul de tip canvas (panza) pe care se poate desena.
        canvas = new Canvas();

        canvas.setPreferredSize(new Dimension(wndWidth, wndHeight));

        canvas.setMaximumSize(new Dimension(wndWidth, wndHeight));
        canvas.setMinimumSize(new Dimension(wndWidth, wndHeight));
        /// Avand in vedere ca obiectul de tip canvas, proaspat creat, nu este automat
        /// adaugat in fereastra trebuie apelata metoda add a obiectul wndFrame
        wndFrame.add(canvas);
        /// Urmatorul apel de functie are ca scop eventuala redimensionare a ferestrei
        /// ca tot ce contine sa poate fi afisat complet
        wndFrame.pack();
    }

    /*! \fn public int GetWndWidth()
        \brief Returneaza latimea ferestrei.
     */
    public int GetWndWidth() {
        return wndWidth;
    }

    /*! \fn public int GetWndWidth()
        \brief Returneaza inaltimea ferestrei.
     */
    public int GetWndHeight() {
        return wndHeight;
    }

    /*! \fn public int GetCanvas()
        \brief Returneaza referinta catre canvas-ul din fereastra pe care se poate desena.
     */
    public Canvas GetCanvas() {
        return canvas;
    }
}

