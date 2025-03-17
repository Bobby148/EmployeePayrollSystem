package com.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.model.Employee;

public class PayrollSystem {
    private static final String FILE_NAME = "employees.dat";
    private List<Employee> employeeList;

    public PayrollSystem() {
        this.employeeList = new ArrayList<>();
        loadEmployees(); 
    }

    public void addEmployee(int id, String name, double salary, String department) {
        Employee emp = new Employee(id, name, salary, department);
        employeeList.add(emp);
        System.out.println("Employee added successfully!");
        saveEmployees();
    }

    public void displayEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            System.out.println("\n--- Employee Details ---");
            for (Employee emp : employeeList) {
                System.out.println(emp);
            }
        }
    }

    private void saveEmployees() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(employeeList);
        } catch (IOException e) {
            System.out.println("Error saving employees: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadEmployees() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            employeeList = (List<Employee>) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No existing data found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading employees: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PayrollSystem system = new PayrollSystem();

        while (true) {
            System.out.println("\n--- Employee Payroll System ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Employee ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Salary: ");
                    double salary = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter Department: ");
                    String department = scanner.nextLine();
                    system.addEmployee(id, name, salary, department);
                    break;
                case 2:
                    system.displayEmployees();
                    break;
                case 3:
                    System.out.println("Exiting system...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}

