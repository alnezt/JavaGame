package PaooGame.Enemies;

//package Observer;

public class PlayerObserver implements Observer {
    @Override
    public void update(int enPozX, int enPozY) {
        System.out.println("Enemy position updated: (" + enPozX + ", " + enPozY + ")");
    }
}
