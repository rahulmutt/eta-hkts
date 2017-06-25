@SuppressWarnings("unchecked")
public class Nothing<A extends Value<A>> extends Maybe<A> {
    public static final Nothing INSTANCE = new Nothing();

    public static <A extends Value<A>> Nothing<A> value() {
        return (Nothing<A>) INSTANCE;
    }

    @Override
    public int getTag() { return 1; }

    @Override
    public String toString() {
        return "Nothing";
    }
}
