package AbstractDataTypes;

import Test.LinkedList;

public class LinkedListDemoDriver {
    public static void main(String args[]){
        LinkedList<Integer> lil = new LinkedList<>();

        lil.addFront(19);
        lil.addFront(11);
        lil.addFront(13);
        lil.addFront(8);
        lil.addBack(31);

        System.out.println(lil.toString());
        System.out.println(lil.removeBack());
        System.out.println(lil.toString());

        lil.addBack(31);

        System.out.println(lil.get(3));
        System.out.println(lil.indexOf(8));

        System.out.println(lil.toString());

        //System.out.println(lil.contains("8"));
        //System.out.println(lil.contains("8"));
    }
}
