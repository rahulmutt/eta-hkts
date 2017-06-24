public class Izh extends Int {
    int x1;
    public Izh(int x1) {
        this.x1 = x1;
    }

    @Override
    public int getTag() { return 1; }

    @Override
    public String toString() { return "" + x1; }
}
