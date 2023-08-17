package org.example;

import org.example.demo.Employee;
import org.example.demo.EmployeeFactory;
import org.example.demo.Project;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    static List<Employee> employeeList = new ArrayList<>();
    public static void main(String[] args) {
        EmployeeFactory employeeFactory = new EmployeeFactory();
        employeeList = employeeFactory.getAllEmployee();

        //Stream operations below:
        //List out all distinct projects in non-ascending order.
        List<String> projects = employeeList.stream()
                .map(Employee::getProjects)
                .flatMap(Collection::stream)
                .map(Project::getName)
                .distinct()
                .sorted(Collections.reverseOrder())
                .toList();

        projects.forEach(System.out::println);
    }
}