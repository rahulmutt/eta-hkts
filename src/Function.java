public abstract class Function<A extends Value<A>, B extends Value<B>> extends Value<Function<A, B>> {
    public abstract B apply(Closure<A> val);
}
