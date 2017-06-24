@SuppressWarnings("unchecked")
public class Nil<A extends Value<A>> extends List<A> {
    public static final Nil INSTANCE = new Nil();

    public static <A extends Value<A>> Nil<A> nil() {
        return (Nil<A>) INSTANCE;
    }

    @Override
    public int getTag() { return 1; }

    @Override
    public String toString() { return "[]"; }
}
