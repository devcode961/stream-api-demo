package org.example;

import org.example.demo.Employee;
import org.example.demo.EmployeeFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    static List<Employee> employeeList = new ArrayList<>();
    public static void main(String[] args) {
        EmployeeFactory employeeFactory = new EmployeeFactory();
        employeeList = employeeFactory.getAllEmployee();

        //Stream operations below:

    }
}