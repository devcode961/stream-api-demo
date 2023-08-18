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
        System.out.println("\nPrint out list of all distinct projects");
        employeeList.stream()
                .map(Employee::getProjects)
                .flatMap(Collection::stream)
                .map(Project::getName)
                .distinct()
                .sorted(Collections.reverseOrder())
                .toList().forEach(System.out::println);

        //Print full name of any Employee whose first name starts with letter A.
        System.out.println("\nPrint full name of any Employee whose first name starts with letter A.");
        employeeList.stream()
                .filter( employee -> employee.getFirstName().startsWith("A"))
                .map(employee -> employee.getFirstName() + " " + employee.getLastName())
                .toList().forEach(System.out::println);

        //List all employees who joined in year 2023
        System.out.println("\nList employees who joined in year 2023");
        employeeList.stream()
                    .filter(employee -> employee.getId().startsWith("2023"))
                    .toList().forEach(System.out::println);

        System.out.println("\n============================================ Printing the Sorted list of employees ====================================================================================");
        //Sort employees based on firstName, for same firstName sort by salary.
        employeeList.stream()
                .sorted(Comparator.comparing(Employee::getFirstName))
                .sorted(Comparator.comparing(Employee::getSalary))
                .toList().forEach(System.out::println);

        //Print employees with 3rd highest salary
        System.out.println("Print employees with 3rd highest salary \n ");
        System.out.println(
                employeeList.stream()
                        .sorted(Comparator.comparing(Employee::getSalary).reversed())
                        .skip(2)
                        .collect(Collectors.groupingBy(Employee::getSalary))
                        .entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                        .map(Map.Entry::getValue)
                        .toList().get(0)
        );

        //Print min salary
        System.out.println("\nMin salary is : "+
                employeeList.stream()
                        .map(Employee::getSalary)
                        .sorted()
                        .limit(1)
                        .toList().get(0)
        );

        //Print employees with min salary
        System.out.println("\nEmployees with Min salary are : "+
                employeeList.stream()
                        .collect(Collectors.groupingBy(Employee::getSalary))
                        .entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByKey())
                        .limit(1)
                        .map(Map.Entry::getValue)
                        .toList()
        );

        //List of people working on more than 2 projects
        System.out.println("People working on more than 2 projects : \n");
        employeeList.stream()
                .filter(employee -> employee.getProjects().size() > 2)
                .toList().forEach(System.out::println);

        //Count of total laptops assigned to employees
        System.out.println(
                employeeList.stream()
                        .mapToInt(Employee::getTotalLaptopsAssigned)
                        .sum()
        );

        //Count of all projects with Robert Downey Jr as PM.
        System.out.println("Printing count of all projects with Robert Downey Jr as PM");
        System.out.println(
                employeeList.stream()
                        .map(Employee::getProjects)
                        .flatMap(Collection::stream)
                        .distinct()
                        .filter(project -> project.getProjectManager().equals("Robert Downey Jr"))
                        .count()
        );
        //Count of all projects with Robert Downey Jr as PM.
        System.out.println("Printing list of all projects with Robert Downey Jr as PM");

                employeeList.stream()
                        .map(Employee::getProjects)
                        .flatMap(Collection::stream)
                        .distinct()
                        .filter(project -> project.getProjectManager().equals("Robert Downey Jr"))
                        .map(project -> project.getName()+"---"+project.getTeam()+"---"+project.getProjectManager())
                        .toList().forEach(System.out::println);

        //List of all people working with Robert Downey Jr.
        System.out.println("\nPrinting list of employees working with Robert Downey Jr");

        employeeList.stream().
                collect(
                        Collectors.groupingBy(employee -> employee.getProjects().stream().map(Project::getProjectManager).toList())
                ).entrySet()
                .stream()
                        .filter(entry -> entry.getKey().contains("Robert Downey Jr"))
                                .toList().forEach(entry -> System.out.println("Employee working with Robert Downey Jr" +" : "+entry.getValue()));



        //Create a map of joining year to list of people who joined in this year.
        System.out.println("\nMap of people by joining year : ");
                employeeList.stream()
                        .collect(Collectors.groupingBy(employee -> employee.getId().substring(0, 4)))
                        .forEach((s, employee) -> System.out.println(s +" : "+employee));

        //Create a map based on this data, the key should be year of joining and value should be the count of people joined in that particular year.

        System.out.println("\nMap of count of people by joining year : ");
        employeeList.stream()
                .collect(Collectors.groupingBy(employee -> employee.getId().substring(0, 4)))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().size()))
                .forEach((s, count) -> System.out.println(s +" : "+count));
    }
}