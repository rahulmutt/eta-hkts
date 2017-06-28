@SuppressWarnings("unchecked")
public class Applicative<F extends Type<? extends K1<K0,K0>>> extends DataCon<Applicative<F>> {
    Functor<F> $dFunctor;
    Closure pure;
    Closure ap;

    public Applicative(Functor<F> $dFunctor, Closure pure, Closure ap) {
        this.$dFunctor = $dFunctor;
        this.pure      = pure;
        this.ap        = ap;
    }

    public <A extends Value<A>, FA extends TypedValue<T1<K0, K0, F, A>, FA>>
        Closure<Function<A, FA>> pure() {
        return pure;
    }

    public <A  extends Value<A>, B extends Value<B>
           ,FAB extends TypedValue<T1<K0, K0, F, Function<A,B>>, FAB>
           ,FA  extends TypedValue<T1<K0, K0, F, A>, FA>
           ,FB  extends TypedValue<T1<K0, K0, F, B>, FB>>
        Closure<Function<FAB, Function<FA, FB>>> ap() {
        return ap;
    }

    @Override
    public int getTag() { return 1; }
}
