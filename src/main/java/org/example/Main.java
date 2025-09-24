package org.example;

import org.example.entity.Employee;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Employee> employees = new LinkedList<>();
        employees.add(new Employee(1,"Fatih","Alkan"));
        employees.add(new Employee(2,"Ahmet","Sarı"));
        employees.add(new Employee(3,"Mehmet","Kırmızı"));
        employees.add(new Employee(1,"Fatih","Alkan")); // duplicate
        employees.add(new Employee(4,"Ayşe","Mor"));

        System.out.println("Orjinal liste: " + employees);

        List<Employee> duplicates = findDuplicates(employees);
        System.out.println("Tekrar Edenler: " + duplicates);

        Map<Integer, Employee> uniques = findUniques(employees);
        System.out.println("Tekil Elemanlar (Map): " + uniques);

        List<Employee> noDuplicates = removeDuplicates(employees);
        System.out.println("Tekrarı Silinmiş Liste: " + noDuplicates);
    }

    public static List<Employee> findDuplicates(List<Employee> list) {
        Set<Employee> seen = new HashSet<>();
        List<Employee> duplicates = new LinkedList<>();
        for (Employee e : list) {
            if (!seen.add(e)) {
                duplicates.add(e);
            }
        }
        return duplicates;
    }

    public static Map<Integer, Employee> findUniques(List<Employee> list) {
        Map<Integer, Employee> map = new HashMap<>();
        Map<Integer, Integer> counts = new HashMap<>();

        for (Employee e : list) {
            if (e != null) {
                counts.put(e.getId(), counts.getOrDefault(e.getId(), 0) + 1);
                map.putIfAbsent(e.getId(), e);
            }
        }

        Map<Integer, Employee> uniques = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (entry.getValue() >= 1) {
                uniques.put(entry.getKey(), map.get(entry.getKey()));
            }
        }
        return uniques;
    }

    public static List<Employee> removeDuplicates(List<Employee> list) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (Employee e : list) {
            if (e != null) {
                counts.put(e.getId(), counts.getOrDefault(e.getId(), 0) + 1);
            }
        }

        List<Employee> result = new LinkedList<>();
        for (Employee e : list) {
            if (e != null && counts.get(e.getId()) == 1) {
                result.add(e);
            }
        }
        return result;
    }
}
