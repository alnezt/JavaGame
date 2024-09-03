
package PaooGame.Enemies;

import static PaooGame.Levels.LevelManager.actual_health;

public class Level3MovementStrategy implements MovementStrategy {
    @Override
    public void move(MiniEnemy miniEnemy) {
        if (miniEnemy.miniPozY < 527 && miniEnemy.miniPozX == 721) {
            miniEnemy.miniPozY++;
        }
        if (miniEnemy.miniPozY == 527 && miniEnemy.miniPozX >= 241) {
            miniEnemy.miniPozX--;
        }
        if (miniEnemy.CheckPlayerEnemyCollision())
            actual_health -= 50;
        if (miniEnemy.miniPozY >= 526 && miniEnemy.miniPozX == 721) {
            miniEnemy.miniPozY = 37;
            miniEnemy.miniPozX = 721;
        }
    }
}