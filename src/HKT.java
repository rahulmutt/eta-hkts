public class HKT {
    public static void main(String[] args) {
        System.out.println("> let square x = [x, x*x]");
        System.out.println("> map square [1..3]");
        List<Int> intList =
            new Cons<Int>(new Izh(1)
           ,new Cons<Int>(new Izh(2)
           ,new Cons<Int>(new Izh(3)
           ,Nil.value())));
        List<List<Int>> result0 = map(square, intList);
        System.out.print("it = ");
        System.out.println(result0);
        System.out.println("> deepseq it");
        result0.force();
        System.out.println(result0);
        System.out.println("> let fcatMaybes def fma = fmap (\\case { Just x -> x; Nothing -> def }) fma");
        System.out.println("> fcatMaybes 0 (Just Nothing)");
        Maybe<Int> result1
            = fcatMaybes($dFunctorMaybe, new Izh(0)
                        ,new Just<Maybe<Int>>(Nothing.value()));
        result1.force();
        System.out.println(result1);
        System.out.println("> fcatMaybes 0 (Just (Just 1))");
        result1 = fcatMaybes($dFunctorMaybe, new Izh(0)
                            ,new Just<Maybe<Int>>(new Just<Int>(new Izh(1))));
        result1.force();
        System.out.println(result1);
        System.out.println("> fcatMaybes 0 Nothing");
        result1 = fcatMaybes($dFunctorMaybe, new Izh(0)
                             ,Nothing.value());
        result1.force();
        System.out.println(result1);
        System.out.println("> fcatMaybes 2 [Just 1, Nothing, Just 3]");
        List<Maybe<Int>> maybeIntList =
            new Cons<Maybe<Int>>(new Just<Int>(new Izh(1))
           ,new Cons<Maybe<Int>>(Nothing.value()
           ,new Cons<Maybe<Int>>(new Just<Int>(new Izh(3))
           ,Nil.value())));
        List<Int> result2 = fcatMaybes($dFunctorList, new Izh(2), maybeIntList);
        System.out.print("it = ");
        System.out.println(result2);
        result2.force();
        System.out.println(result2);
    }

    /* Dictionaries for Maybe */

    @SuppressWarnings("unchecked")
    public static final Functor<Type<Maybe>> $dFunctorMaybe =
        new Functor<Type<Maybe>>(
            new Function() {
                @Override
                public Value apply(Closure f) {
                    return new Function() {
                        @Override
                        public Value apply(Closure ma_) {
                            Maybe ma = (Maybe) ma_.evaluate();
                            int tag = ma.getTag();
                            if (tag == 1) {
                                return Nothing.value();
                            } else {
                                Closure x = ((Just) ma).x1;
                                return new Just(new Ap2(f, x));
                            }
                        }
                    };
                }
            });

    @SuppressWarnings("unchecked")
    public static final Applicative<Type<Maybe>> $dApplicativeMaybe =
        new Applicative<Type<Maybe>>(
            $dFunctorMaybe,
            /* pure :: a -> Maybe a*/
            new Function() {
                @Override
                public Value apply(Closure a) {
                    return new Just(a);
                }
            },
            /* ap :: Maybe (a -> b) -> Maybe a -> Maybe b */
            new Function() {
                @Override
                public Value apply(Closure fab_) {
                    return new Function() {
                        @Override
                        public Value apply(Closure fa) {
                            Maybe fab = (Maybe) fab_.evaluate();
                            int tag = fab.getTag();
                            if (tag == 1) {
                                return Nothing.value();
                            } else {
                                Closure f = ((Just) fab).x1;
                                return $dFunctorMaybe.fmap().evaluate().apply(f).apply(fa);
                            }
                        }
                    };

                }
            });

    @SuppressWarnings("unchecked")
    public static final Monad<Type<Maybe>> $dMonadMaybe =
        new Monad<Type<Maybe>>(
            $dApplicativeMaybe,
            /* bind :: Maybe a -> (a -> Maybe b) -> Maybe b */
            new Function() {
                @Override
                public Value apply(Closure ma_) {
                    return new Function() {
                        @Override
                        public Value apply(Closure amb) {
                            Maybe ma = (Maybe) ma_.evaluate();
                            int tag = ma.getTag();
                            if (tag == 1) {
                                return Nothing.value();
                            } else {
                                Closure x = ((Just) ma).x1;
                                return ((Function) amb.evaluate()).apply(x);
                            }
                        }
                    };
                }
            });

    /* Dictionaries for List */

    @SuppressWarnings("unchecked")
    public static final Functor<Type<List>> $dFunctorList =
        new Functor<Type<List>>(
            new Function() {
                @Override
                public Value apply(Closure f) {
                    return new Function() {
                        @Override
                        public Value apply(Closure xs) {
                            return map(f, xs);
                        }
                    };
                }
            });

    @SuppressWarnings("unchecked")
    public static final Applicative<Type<List>> $dApplicativeList =
        new Applicative<Type<List>>(
            $dFunctorList,
            /* pure :: a -> List a*/
            new Function() {
                @Override
                public Value apply(Closure a) {
                    return new Cons(a, Nil.value());
                }
            },
            /* ap :: List (a -> b) -> List a -> List b */
            /* TODO */
            null);


    /* fcatMaybes :: (Functor f) => a -> f (Maybe a) -> f a
       fcatMaybes def fma = fmap maybeDef fma
         where maybeDef (Just a) = a
               maybeDef Nothing  = def
    */

    public static <F extends Type<? extends K1<K0,K0>>, A extends Value<A>, B extends Value<B>, TMA extends TypedValue<T1<K0,K0,F,Maybe<A>>, TMA>, TA extends TypedValue<T1<K0,K0,F,A>,TA>>
        TA fcatMaybes(Functor<F> f, Closure<A> def, Closure<TMA> fma) {
        Function<Maybe<A>, A> maybeDef = new fcatMaybes$1<A>(def);
        return f.<Maybe<A>,A,TMA,TA>fmap().evaluate().apply(maybeDef).apply(fma);
    }

    public static class fcatMaybes$1<A extends Value<A>> extends Function<Maybe<A>, A> {
        private Closure<A> def;

        public fcatMaybes$1(Closure<A> def) {
            this.def = def;
        }

        @Override
        public A apply(Closure<Maybe<A>> x_) {
            Maybe<A> x = x_.evaluate();
            int tag = x.getTag();
            if (tag == 1) {
                return def.evaluate();
            } else {
                return ((Just<A>) x).x1.evaluate();
            }
        }
    }

    public static Function<Int, List<Int>> square
        = new Function<Int, List<Int>>() {
                @Override
                public List<Int> apply(Closure<Int> lazy_x) {
                    int x = ((Izh) lazy_x.evaluate()).x1;
                    return new Cons<Int>(new Izh(x)
                                        ,new Cons<Int>(new Izh(x * x), Nil.value()));
                }
            };

    public static <A extends Value<A>, B extends Value<B>>
        List<B> map(Closure<Function<A, B>> lazy_f, Closure<List<A>> lazy_xs) {
        List<A> xs = lazy_xs.evaluate();
        int tag = xs.getTag();
        if (tag == 1) {
            return Nil.value();
        } else {
            Cons<A> cons = (Cons<A>) xs;
            Closure<A> lazy_head = cons.x1;
            Closure<List<A>> lazy_tail = cons.x2;
            return new Cons<B>(new Ap2<A, B>(lazy_f, lazy_head)
                              ,new Map$2<A, B>(lazy_f, lazy_tail));
        }
    }

    public static class Ap2<A extends Value<A>, B extends Value<B>> extends Thunk<B> {
        private Closure<Function<A,B>> lazy_f;
        private Closure<A>             lazy_x;

        public Ap2(Closure<Function<A,B>> lazy_f, Closure<A> lazy_x) {
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

