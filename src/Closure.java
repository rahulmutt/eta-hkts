public abstract class Closure<A extends Value<A>> {
    public abstract A enter();
    public abstract A evaluate();
    public abstract A getEvaluated();

    public void force() {}
}
