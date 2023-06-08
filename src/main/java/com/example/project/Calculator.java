package com.example.project;
import java.util.Vector;

public class Calculator {
  private Vector test;

  public int add(int a, int b) {
    return a + b;
  }

  public static void main(String[] args) {
    Calculator calculator = new Calculator();
    int result = calculator.add(5, 3);
    System.out.println("Result: " + result);
  }

}
