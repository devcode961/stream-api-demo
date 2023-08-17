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

        //projects.forEach(System.out::println);

        //Print full name of any Employee whose first name starts with letter A.
        employeeList.stream()
                .filter( employee -> employee.getFirstName().startsWith("A"))
                .map(employee -> employee.getFirstName() + " " + employee.getLastName())
                .toList().forEach(System.out::println);

        //List all employees who joined in year 2023
        employeeList.stream()
                    .filter(employee -> employee.getId().startsWith("2023"))
                    .toList().forEach(System.out::println);
        System.out.println("============================================ Printing the Sorted list of employees ====================================================================================");
        //Sort employees based on firstName, for same firstName sort by salary.
        employeeList.stream()
                .sorted(Comparator.comparing(Employee::getFirstName))
                .sorted(Comparator.comparing(Employee::getSalary))
                .toList().forEach(System.out::println);

    }
}