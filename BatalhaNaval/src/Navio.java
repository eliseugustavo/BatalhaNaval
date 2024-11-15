public class Navio {
    private int x;
    private int y;
    private boolean horizontal;
    private int vida = 3;

    public Navio(int x, int y, boolean horizontal) {
        this.x = x;
        this.y = y;
        this.horizontal = horizontal;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public boolean isHorizontal() { return horizontal; }
    public int getVida() { return vida; }

    public void receberTiro() {
        if (vida > 0) {
            vida--;
        }
    }

    public boolean estaDestruido() {
        return vida == 0;
    }
}
