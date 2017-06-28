@SuppressWarnings("unchecked")
public class Functor<F extends Type<? extends K1<K0,K0>>> extends DataCon<Functor<F>> {
    Closure fmap;

    public Functor(Closure fmap) {
        this.fmap = fmap;
    }

    public <A extends Value<A>, B extends Value<B>
           ,FA extends TypedValue<T1<K0,K0,F,A>, FA>
           ,FB extends TypedValue<T1<K0,K0,F,B>, FB>>
        Closure<Function<Function<A,B>, Function<FA, FB>>> fmap() {
        return fmap;
    }

    @Override
    public int getTag() { return 1; }
}
