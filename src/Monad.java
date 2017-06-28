@SuppressWarnings("unchecked")
public class Monad<M extends Type<? extends K1<K0,K0>>> extends DataCon<Monad<M>> {
    Applicative<M> $dApplicative;
    Closure bind;

    public Monad(Applicative<M> $dApplicative, Closure bind) {
        this.$dApplicative = $dApplicative;
        this.bind          = bind;
    }

    public <A extends Value<A>, B extends Value<B>
           ,MA extends TypedValue<T1<K0,K0,M,A>, MA>
           ,MB extends TypedValue<T1<K0,K0,M,B>, MB>>
        Closure<Function<MA, Function<Function<A,MB>, MB>>> bind() {
        return bind;
    }

    @Override
    public int getTag() { return 1; }
}
