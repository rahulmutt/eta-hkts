public class HKT {
    public static void main(String[] args) {
        List<Int> intList =
            new Cons<Int>(new Izh(1)
          , new Cons<Int>(new Izh(2)
          , new Cons<Int>(new Izh(3), Nil.nil())));

        System.out.println("> let square x = [x, x*x]");
        System.out.println("> map square [1..3]");
        List<List<Int>> result = map(square, intList);
        System.out.print("it = ");
        System.out.println(result);
        System.out.println("> deepseq it");
        result.force();
        System.out.println(result);
    }

    public static Function<Int, List<Int>> square
        = new Function<Int, List<Int>>() {
                @Override
                public List<Int> apply(Closure<Int> lazy_x) {
                    int x = ((Izh) lazy_x.evaluate()).x1;
                    return new Cons<Int>(new Izh(x)
                                            ,new Cons<Int>(new Izh(x * x), Nil.nil()));
                }
            };

    public static <A extends Value<A>, B extends Value<B>>
        List<B> map(Closure<Function<A, B>> lazy_f, Closure<List<A>> lazy_xs) {
        List<A> xs = lazy_xs.evaluate();
        int tag = xs.getTag();
        if (tag == 1) {
            return Nil.nil();
        } else {
            Cons<A> cons = (Cons<A>) xs;
            Closure<A> lazy_head = cons.x1;
            Closure<List<A>> lazy_tail = cons.x2;
            return new Cons<B>(new Map$1<A, B>(lazy_f, lazy_head)
                              ,new Map$2<A, B>(lazy_f, lazy_tail));
        }
    }

    public static class Map$1<A extends Value<A>, B extends Value<B>> extends Thunk<B> {
        private Closure<Function<A,B>> lazy_f;
        private Closure<A>             lazy_x;

        public Map$1(Closure<Function<A,B>> lazy_f, Closure<A> lazy_x) {
            this.lazy_f = lazy_f;
            this.lazy_x = lazy_x;
        }

        @Override
        public B thunkEnter() {
            return lazy_f.evaluate().apply(lazy_x);
        }
    }

    public static class Map$2<A extends Value<A>, B extends Value<B>> extends Thunk<List<B>> {
        private Closure<Function<A,B>> lazy_f;
        private Closure<List<A>>       lazy_xs;

        public Map$2(Closure<Function<A,B>> lazy_f, Closure<List<A>> lazy_xs) {
            this.lazy_f  = lazy_f;
            this.lazy_xs = lazy_xs;
        }

        @Override
        public List<B> thunkEnter() {
            return map(lazy_f, lazy_xs);
        }
    }
}

