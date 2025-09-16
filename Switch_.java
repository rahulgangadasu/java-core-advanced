package java;
import java.util.Scanner;

public class Switch_ {
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        switch(num){
            case 1:
                System.out.println("rahul");
                break;
            case 2:
                System.out.println("gangadasu");
                break;
            default:
                System.out.println(" switch case");
        }
    }
}
