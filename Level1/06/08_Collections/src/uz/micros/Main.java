package uz.micros;

import java.util.*;

public class Main {

    private static void pb(int n){
        String s = Integer.toBinaryString(n);

        while (s.length() < 8)
            s = "0" + s;

        System.out.println(s);
    }

    public static void main2(String[] args) {

        int n = 0xAABBCCDD;

        long m = 0x1122334455667788L;

        //m = n;

        n = (int)(m >> 32);

        System.out.println(Integer.toHexString((int) (m >> 32)));
        System.out.println(Integer.toHexString(n));

        // вспомним двоичные числа
        for(int k = 0; k < 15; k++) pb(k);

        byte b = 7;
        pb(b);

        pb(b << 1);
        pb(b << 1 >> 1);

        byte r = (byte)(b >> 1);
        pb(r);

        pb(6 | 1);
        pb(6 | 8);

        pb(6 & 3);

        pb(67);
        pb(45);
        pb(67 ^ 45);
        pb(110 ^ 45);
        System.out.println(67 ^ 45);
        System.out.println(110 ^ 45);

        pb(0x34);
        pb(~0x34);
    }

    public static void main(String[] args) {
        ArrayList list = new ArrayList();

        list.add(123);
        list.add(123123.2);
        list.add("123123.2");

        for(Object o : list)
            System.out.println(o);

        ArrayList<Integer> list2 = new ArrayList<Integer>();

        list2.add(222);
        list2.add(87987987);

        for (int k = 0; k < list2.size(); k++){
            System.out.println("list2[" + (k+1) + "] = " + list2.get(k));
        }

        Stack<Integer> s = new Stack<Integer>();

        s.add(22);s.add(44);s.add(77);s.add(88);s.add(99);

        int n = s.pop();
        System.out.println(n);
        System.out.println(s);
        n = s.peek();
        System.out.println(n);
        System.out.println(s);

        PriorityQueue<Integer> q = new PriorityQueue<Integer>();

        q.add(22);q.add(44);q.add(77);q.add(88);q.add(99);

        System.out.println(q);
        n = q.poll();
        System.out.println(n);
        System.out.println(q);
        n = q.remove();
        System.out.println(n);
        System.out.println(q);

        int [] mas = {1, 0, 34, 67, 23, 90};

        Pair<Integer, Integer> res = getMax(mas);

        System.out.println("Max = " + res.getV() + " Index = " + res.getI());

        String [] mas2 = {"123", "", "34234", "werdfddd", "22"};

        Pair<String, Integer> res2 = getMax2(mas2);

        System.out.println("Max = " + res2.getV() + " Index = " + res2.getI());

        print(new Person());
        print(new Employee());
        print(new Person() {});

        ArrayList<Integer> i = new ArrayList<>(Arrays.asList(1, 4, 56));
        ArrayList<Float> f = new ArrayList<>(Arrays.asList(1f, 4f, 56f));
        ArrayList<Long> l = new ArrayList<>(Arrays.asList(1l, 4l, 56l));
        ArrayList<Double> d = new ArrayList<>(Arrays.asList(1d, 4.0, 56.0));
        ArrayList<Byte> b = new ArrayList<>(Arrays.asList((byte)1,(byte)4, (byte)56));

        System.out.println(sum(i));
        System.out.println(sum(f));
        System.out.println(sum(b));
    }

    private static Pair<Integer, Integer> getMax(int[] arr){
        int res = 0;
        int idx = 0;

        for (int k = 0; k < arr.length; k++)
            if (arr[k] > res) {
                res = arr[k];
                idx = k;
            }

        return new Pair<Integer, Integer>(res, idx);
    }

    private static Pair<String, Integer> getMax2(String[] arr){
        String res = "";
        int idx = 0;

        for (int k = 0; k < arr.length; k++)
            if (arr[k].length() > res.length()) {
                res = arr[k];
                idx = k;
            }

        return new Pair<String, Integer>(res, idx);
    }

    private static <T extends Person> void print(T t){
        System.out.println(t);
    }

    private static <T extends Number> T sum(ArrayList<? extends Number> list){
        double d = 0;
        for(Number n : list) {
            d = d + n.doubleValue();
        }

        return (T)(Number)d;
    }
}

class Pair<V, I>{

    private V v;
    private I i;

    public Pair(V v, I i) {
        this.v = v;
        this.i = i;
    }

    public V getV(){return v;}
    public I getI(){return i;}
}