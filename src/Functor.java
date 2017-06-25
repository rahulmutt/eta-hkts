@SuppressWarnings("unchecked")
public class Functor<F extends TyCon> extends DataCon<Functor<F>> {
    Closure fmap;

    public Functor(Closure fmap) {
        this.fmap = fmap;
    }

    public <A extends Value<A>, B extends Value<B>
           ,FA extends T1<F, A, FA>, FB extends T1<F, B, FB>>
        Closure<Function<Function<A,B>, Function<FA, FB>>> fmap() {
        return fmap;
    }

    @Override
    public int getTag() { return 1; }
}
