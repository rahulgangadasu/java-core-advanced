package java;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class BuiltInInterfaces {
    public static void main(String[] args) {

        /* -----------------------------------------------------------------------------
           --------------------------Consumer FI ---------------------------------------
           -----------------------------------------------------------------------------*/
        List<Integer> list = List.of(1,2,3,4,5);
        list.forEach(i -> System.out.print(i + " ")); // Declarative Programming.
        System.out.println("\n--------------------");
        List <String> list1 = List.of("rahul", "gangadasu");
        Consumer <String> print = string -> System.out.println(string);
        Consumer <String> printFirstletterUppercase = string -> System.out.println(string.substring(0, 1)
                                                                                       .toUpperCase()
                                                                                       +" \b"+ string.substring(1));
        Consumer <String> printChar = string -> System.out.println(string.charAt(1));
        list1.forEach(print.andThen(printFirstletterUppercase).andThen(printChar)); // chaining consumers

        /* -----------------------------------------------------------------------------
           --------------------------Supplier FI ---------------------------------------
           -----------------------------------------------------------------------------*/
           System.out.println("-----------------------------------------------------");
        Supplier <Double> getRandom = () -> (Math.random() * 100);
        System.out.println("Random Number : " + Math.round(getRandom.get()));

        /* -----------------------------------------------------------------------------
           --------------------------Function FI ---------------------------------------
           -----------------------------------------------------------------------------*/
           System.out.println("------------------------------------------------------");
        Function <String, Integer> findLength = string -> string.length();
        System.out.println("Length : " + findLength.apply("Rahul Gangadasu"));

        Function <String, String> replaceColon = str ->  str.replace(":", "="); // Composing Functions
        Function <String, String> addBraces = str -> "{" + str + "}";
        // Function <String, String> result = addBraces.andThen(replaceColon); 
        // System.out.println(result.apply("Rahul : Gangadasu")); // Declarative Programming
        var result2 = addBraces
                      .compose(replaceColon)
                      .apply("key : value");
        System.out.println(result2);

    /* -----------------------------------------------------------------------------
       --------------------------Predicate FI --------------------------------------
       -----------------------------------------------------------------------------*/
       System.out.println("----------------------------------------------------");
       Predicate <String> isACharacter = str -> str.length() <= 1 ;
       System.out.println("is a character : " + isACharacter.test("R"));

       Predicate <String> hasLeftBrace = str -> str.startsWith("{");
       Predicate <String> hasRightBrace = str -> str.endsWith("}");

       System.out.println( "hasLeftandRightBraces : " + hasLeftBrace.and(hasRightBrace).test("{Rahul - Gangadasu}"));
       System.out.println( "hasLeftorRightBraces : " + hasLeftBrace.or(hasRightBrace).test("{Rahul - Gangadasu}"));
       System.out.println( "hasNoLeftBrace : " + hasLeftBrace.negate().test("Rahul - Gangadasu}"));

       /* --------------------------------------------------------------------------
       --------------------------Binary & Unary FI ---------------------------------
       -----------------------------------------------------------------------------*/

       System.out.println("------------------------------------------------------------");
       BinaryOperator <Integer> addBinaryOperator = ( num1, num2) -> num1 + num2;
       Function <Integer, Integer> square = num -> num * num;
       System.out.println("Result : " + addBinaryOperator.andThen(square).apply(3, 5));

       UnaryOperator <Integer> squareOf = num -> num * num;
       UnaryOperator <Integer> increment = num -> num + 1;

       System.out.println("result : " + increment.andThen(squareOf).apply(10));

       System.out.println(Integer.toBinaryString(10));
    }
}
