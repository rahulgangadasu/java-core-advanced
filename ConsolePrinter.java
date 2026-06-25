package java_code;

public class ConsolePrinter implements Printer{

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    public void greet(String message){
        System.out.println(message);
    }
}
