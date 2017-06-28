public interface T1<K2 extends Kind, K3 extends Kind,
                    T1 extends Type<? extends K1<K2,K3>>, T2 extends Type<? extends K2>>
    extends Type<K3> {}
