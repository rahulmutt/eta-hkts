@SuppressWarnings("unchecked")
public class Applicative<F extends TyCon> extends DataCon<Applicative<F>> {
    Functor<F> $dFunctor;
    Closure pure;
    Closure ap;

    public Applicative(Functor<F> $dFunctor, Closure pure, Closure ap) {
        this.$dFunctor = $dFunctor;
        this.pure      = pure;
        this.ap        = ap;
    }

    public <A extends Value<A>, FA extends T1<F, A, FA>>
        Closure<Function<A, FA>> pure() {
        return pure;
    }

    public <A extends Value<A>, B extends Value<B>
           ,FAB extends T1<F, Function<A,B>, FAB>
           ,FA extends T1<F, A, FA>, FB extends T1<F, B, FB>>
        Closure<Function<FAB, Function<FA, FB>>> ap() {
        return ap;
    }

    @Override
    public int getTag() { return 1; }
}
