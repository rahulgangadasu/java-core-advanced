package java_code;

public class LambdasDemo {
    public static void main(String[] args) {
        Printer printer = message -> System.out.println(message); //lambda expression
        printer.print("Hi Rahul");
        
    }
}
