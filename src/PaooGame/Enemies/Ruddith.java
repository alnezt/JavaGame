package PaooGame.Enemies;

import PaooGame.Graphics.Assets;
import PaooGame.Levels.LevelManager;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static PaooGame.Graphics.Assets.rudith;
import static PaooGame.Player.*;

public class Ruddith extends LevelManager implements Subject {
    public static int enPozX;
    public static int enPozY;
    public final int rw = 48;
    public final int rh = 48;
    private static int id;

    public final int EnemyWidth = 48;
    public final int EnemyHeight = 48;
    public BufferedImage img;
    public static boolean startFollowing = false;
    private static int speed = 1;
    public static boolean gameOver = false;
    public static boolean enemy1death = false;
    private List<Observer> observers;

    public Ruddith(int x, int y, int id) {
        this.enPozX = x;
        this.enPozY = y;
        this.id = id;
        this.observers = new ArrayList<>();
        if (id == 1) {
            img = rudith[0][0];
        }
    }

    public void UpdateEnemy(int row, int col) {
        if (id == 1) {
            img = Assets.rudith[row][col];
        }
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(enPozX, enPozY);
        }
    }
// Am implementat o metodă care transmite inamicului că trebuie să mă urmărească în momentul în care player-ul
// ajunge pe axa xoy pe punctul x si un punct y cuprins pe lățimea drumului.
    public static void StartEnemyFollowing(int x, int y1, int y2) {
        if (mikiPozX == x && (mikiPozY >= y1 && mikiPozY <= y2)) {
            startFollowing = true;
        }
    }

    public void move_down(int x) {
        for (int i = 0; i <= 10; i += 5) {
            if (enPozY < x) {
                enPozY += 1;
                notifyObservers();
            } else break;
        }
    }

//am descris amanuntit in documentatie
    public void EnemyFollowingLvl1(int xr, int yr) {
        StartEnemyFollowing(478, 221, 240);
        if (startFollowing) {
            move_down(239);

            if (enPozY == 239) {
                if (actual_health > 0 && enPozX != 568) {
                    enPozX += 1;
                    notifyObservers();
                    if (enPozX == mikiPozX - 2 || enPozY == mikiPozY + 33) {
                        actual_health = 0;
                        startFollowing = false;
                    }
                }
            }

            if (enPozX == 568 && enPozY == 239) {
                enemy1death = true;
                startFollowing = false;
            }
        }
    }

    //analogie cu 2 pătrate care nu trebuie să se suprapună
    private boolean CheckEnemyCollision()
    {
        int sideLength=48;
        int mikiRight = mikiPozX + sideLength;
        int mikiBottom = mikiPozY + sideLength;

        int miniRight = enPozX + sideLength;
        int miniBottom = enPozY + sideLength;

        // stanga-dreapta
        if (mikiPozX >= miniRight || enPozX >= mikiRight) {
            return false;
        }

        // sus-jos
        if (mikiPozY >= miniBottom || enPozY >= mikiBottom) {
            return false;
        }
        return true;
    }
    public void EnemyFollowingLvl2(int xr, int yr) {
        StartEnemyFollowing(626, 80, 102);
        if (startFollowing) {
            if (enPozX != 621 && actual_health > 0) {
                enPozX++;
                notifyObservers();
                if ( CheckEnemyCollision()) {
                    actual_health = 0;
                }
            }

            if (enPozY != 38 && enPozX == 621 && actual_health > 0) {
                enPozY--;
                notifyObservers();
                if (CheckEnemyCollision()) {
                    actual_health = 0;
                }
            }


            if (enPozX == 621 && enPozY == 38) {
                enemy1death = true;
                startFollowing = false;
            }
        }
    }


    public void DrawEnemy(Graphics g) {
        g.drawImage(img, enPozX - screenX, enPozY - screenY, 48, 48, null);
    }

}
