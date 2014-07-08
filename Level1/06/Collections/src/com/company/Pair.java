package com.company;


public class Pair<V, E> {
    private V v;
    private E e;

    public Pair(V vv, E ee) {
        v = vv;
        e = ee;

        //V ee2 = V.class.newInstance();
    }

    public V getV(){return v;}
    public E getE(){return e;}
}

