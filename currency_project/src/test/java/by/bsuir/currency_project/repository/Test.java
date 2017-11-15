package by.bsuir.currency_project.repository;

import java.util.HashMap;
import java.util.Map;

public class Test {

    @org.junit.Test
    public void test() {
        Map<T, Integer> map = new HashMap<>();


        map.put(new T("A"), 1);
        map.put(new T("B"), 2);
        map.put(new T("C"), 3);
        map.put(new T("D"), 4);
        map.put(new T("D"), 5);
        map.put(new T("D"), 6);


        System.out.println(map);

        for (Map.Entry<T, Integer> m : map.entrySet()) {
            System.out.println(m);
        }

        System.out.println(map.get(new T("D")));


    }

}

class T {

    private String name;

    public T(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "T{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        T to = (T) o;
        if (to.name.equals("D")) {
            return false;
        }
        return name.equals(to.name);
    }

    @Override
    public int hashCode() {
        int hash = 0;

        if (name.equals("D")) {
            hash = 1;
        }

        return name.hashCode();
    }
}
