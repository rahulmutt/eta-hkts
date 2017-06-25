public class Cons<A extends Value<A>> extends List<A> {
    Closure<A> x1;
    Closure<List<A>> x2;
    public Cons(Closure<A> x1, Closure<List<A>> x2) {
        this.x1 = x1;
        this.x2 = x2;
    }

    @Override
    public int getTag() { return 2; }

    @Override
    public String toString() {
        return "Cons [" + x1.toString() + "," + x2.toString() + "]";
    }

    @Override
    public void force() {
        x1.evaluate();
        x1.force();
        x2.evaluate();
        x2.force();
    }
}
