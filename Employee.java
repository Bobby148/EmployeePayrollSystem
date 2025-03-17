package com.model;

import java.io.Serializable;

public class Employee implements Serializable {
    private int id;
    private String name;
    private double salary;
    private String department;

    public Employee(int id, String name, double salary, String department) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public double calculateNetSalary() {
        double tax = salary * 0.10; 
        return salary - tax;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Department: " + department +
               ", Salary: $" + salary + ", Net Salary: $" + calculateNetSalary();
    }
}

