public class Just<A extends Value<A>> extends Maybe<A> {
    Closure<A> x1;
    public Just(Closure<A> x1) {
        this.x1 = x1;
    }

    @Override
    public int getTag() { return 2; }

    @Override
    public String toString() {
        return "Just [" + x1.toString() + "]";
    }

    @Override
    public void force() {
        x1.evaluate();
        x1.force();
    }
}
