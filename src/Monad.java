@SuppressWarnings("unchecked")
public class Monad<M extends TyCon> extends DataCon<Monad<M>> {
    Applicative<M> $dApplicative;
    Closure bind;

    public Applicative(Applicative<M> $dApplicative, Closure bind) {
        this.$dApplicative = $dApplicative;
        this.bind          = bind;
    }

    public <A extends Value<A>, B extends Value<B>
           ,MA extends T1<M, A, MA>, MB extends T1<M, B, MB>>
        Closure<Function<MA, Function<Function<A,MB>, MB>>> bind() {
        return bind;
    }

    @Override
    public int getTag() { return 1; }
}
