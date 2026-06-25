package java_code;

import java.util.Scanner;

class First{
public static void main(String[] args) {
    @SuppressWarnings("resource")
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter speed here: ");
    int speed = sc.nextInt();
    if(speed > 120){
        System.out.println("Over Speed!!! Speeding ticket");
    }
    int a = 3;

    int b = 6;

    int result = (~a & b) | (a & ~b);

    System.out.println(result);
}
}
