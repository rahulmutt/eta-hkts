@SuppressWarnings("unchecked")
public abstract class Value<A extends Value<A>> extends Closure<A> {
    @Override
    public A enter() { return (A) this; }
    @Override
    public A evaluate() { return (A) this; }
    @Override
    public A getEvaluated() { return (A) this; }
}
