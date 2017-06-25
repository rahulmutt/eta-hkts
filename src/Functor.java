@SuppressWarnings("unchecked")
public class Functor<T extends TyCon> extends DataCon<Functor<T>> {
    Closure fmap;

    public Functor(Closure fmap) {
        this.fmap = fmap;
    }

    public <A extends Value<A>, B extends Value<B>
           ,TA extends T1<T, A, TA>, TB extends T1<T, B, TB>>
        Closure<Function<Function<A,B>, Function<TA, TB>>> fmap() {
        return fmap;
    }

    @Override
    public int getTag() { return 1; }
}
