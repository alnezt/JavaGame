
package PaooGame.Enemies;

import static PaooGame.Levels.LevelManager.actual_health;


public class Level2MovementStrategy implements MovementStrategy {
    @Override
    public void move(MiniEnemy miniEnemy) {
        if (miniEnemy.miniPozX != 195) {
            if (miniEnemy.CheckPlayerEnemyCollision())
                actual_health -= 50;
            miniEnemy.miniPozX--;
        }
        if (miniEnemy.miniPozX == 195 && miniEnemy.miniPozY >= 320 && miniEnemy.miniPozY <= 526) {
            miniEnemy.miniPozY++;
            if (miniEnemy.CheckPlayerEnemyCollision())
                actual_health -= 50;
            if (miniEnemy.miniPozX == 195 && miniEnemy.miniPozY >= 526) {
                miniEnemy.miniPozX = 673;
                miniEnemy.miniPozY = 327;
            }
        }
    }
}

