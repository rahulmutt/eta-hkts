@SuppressWarnings("unchecked")
public abstract class Thunk<A extends Value<A>> extends Closure<A> {
    public Closure<A> indirectee;

    @Override
    public A enter() {
        if (indirectee == null) {
            A result = thunkEnter();
            indirectee = result;
            return result;
        } else {
            return (A) indirectee;
        }
    }

    @Override
    public A evaluate() {
        return enter();
    }

    @Override
    public A getEvaluated() {
        if (indirectee == null) {
            return null;
        } else {
            return (A) indirectee;
        }
    }

    @Override
    public String toString() {
        return (indirectee == null)? "_" : indirectee.toString();
    }

    @Override
    public void force() {
        A result = enter();
        result.force();
    }

    public abstract A thunkEnter();
}
