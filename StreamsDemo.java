package java_code;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsDemo {
    public static void main(String[] args) {
        List<Movie> movies= List.of(new Movie("Premalu", 97, Genre.ROMCOM), 
                                    new Movie("Kick", 95, Genre.COMEDY), 
                                    new Movie("Busniessman", 97, Genre.ACTION),
                                    new Movie("Pilla Zamindar", 99, Genre.FEELGOOD),
                                    new Movie("Ok Bangaram", 98, Genre.ROMANCE),
                                    new Movie("Dhookudu", 96, Genre.COMEDY));
        // int count = 0;
        // for (Movie mv : movies) {          // imperative programming
        //     if(mv.getLikes() > 95) count++;
        // }
        // var count2 = movies.stream() //Declarative Programming
        //                     .filter(movie -> movie.getLikes() > 95).count();

        int [] numbers = {1, 3, 4, 5 ,7};
        Arrays.stream(numbers).forEach(n -> System.out.print(n + " "));
        System.out.println("\n------------------------");
        Stream.of(1,2,3,4,5,6,7,8).forEach(n -> System.out.print(n + " "));
        System.out.println("\n------------------------");
        Stream<Long> stream = Stream.generate(() -> (Math.round(Math.random()* 100)));
        stream.limit(5).forEach(n -> System.out.println(n));
        System.out.println("------------------------");
        Stream.iterate(1, n -> n+1).limit(10).forEach(n -> System.out.print(n + " "));
        System.out.println("\n------------------------");
        movies.stream()
              .map(movie -> movie.getTitle())
              .forEach(name -> System.out.println(name));
        System.out.println("------------------------");
        Stream <List<Integer>> listInt = Stream.of(List.of(5,7,2,9,0), List.of(9,0,3,1,6,7));
        //listInt.forEach(list -> System.out.println(list)); //gives list of the objects.
        listInt.flatMap(list -> list.stream()) // Stream<List<Obj>> to Stream<Obj>.
                .forEach(n -> System.out.print(n + " "));
        System.out.println("\n-----------------------");
        movies.stream()
              .filter(movie -> movie.getLikes() > 95)
              .forEach(movie -> System.out.println(movie.getTitle()));
        System.out.println("-------------------------");
        Stream<Long> numberStream = Stream.generate(() -> Math.round(Math.random() * 100));
        numberStream.limit(20) // limits the stream to 20 elements
                    .skip(10)  // skips the first 10 elements
                    .takeWhile(n -> n > 20) // similar to filter method but returns only stream before the condition is false
                    .dropWhile(n -> n % 2 != 0) // exactly opposite to take while.
                    .forEach(n -> System.out.print(n + " "));
        System.out.println("\n------------------------");
        movies.stream()
              .sorted(Comparator.comparing(Movie :: getTitle).reversed())
              .forEach(movie -> System.out.println(movie.getTitle()));
        System.out.println("------------------------");
        movies.stream()
              .map(Movie :: getLikes)
              .distinct()  // returns a stream of only unique values.
              .forEach(System.out :: println);
        System.out.println("------------------------");
        movies.stream()
              .filter(m -> m.getLikes() > 96)
              .peek(m -> System.out.println("Filtered : " + m.getTitle()))
              .map(Movie :: getTitle)
              .peek(t -> System.out.println("Mapped : " + t)) //useful for troubleshooting
              .forEach(System.out :: println); // terminal method
        System.out.println("------------------------");
        System.out.println("No of movies : " + movies.stream().count());
        Optional<Integer> sum = movies.stream()
                                       .map(Movie :: getLikes)
                                       .reduce(Integer :: sum); // reduce((a,b) -> a+b) return Optional<Integer>
                                        // reduce is an accumulator.reduce(0, Integer :: sum) returns Integer.
        System.out.println("Total likes on all movies : " + sum.orElse(0)); // returns 0 if the stream is null.
        System.out.println("-----------------------");
        var result = movies.stream()
                           .map(Movie :: getTitle)
                           .collect(Collectors.joining(", "));
                           //.collect(Collectors.summarizingInt(Movie :: getLikes)); output : IntSummaryStatistics{count=5, sum=486, min=95, average=97.200000, max=99}
                           //.collect(Collectors.toMap(Movie :: getTitle, Function.identity())); 
                           // m -> m, return map with object address
                           //.map(Movie :: getTitle)
                           // .collect(Collectors.toList()); returns a list of movie titles.
        System.out.println(result);
        System.out.println("-----------------------");
        var groupByGenre = movies.stream()
                                 .collect(Collectors.groupingBy(Movie :: getGenre, 
                                                                Collectors.mapping(Movie :: getTitle, 
                                                                                   Collectors.joining(", "))));
                                 //.collect(Collectors.groupingBy(Movie :: getGenre));
                                 
        System.out.println(groupByGenre);
        System.out.println("------------------------");
        var partionByLikes = movies.stream()
                                   .collect(Collectors.partitioningBy(movie -> movie.getLikes() > 96,
                                                                     Collectors.mapping(Movie :: getTitle, 
                                                                                        Collectors.joining(" - "))));
        System.out.println(partionByLikes);

        /*-------------------------------------Primitive Type Streams----------------------------------------- */

        IntStream.range(20, 25).forEach(System.out :: println);
        DoubleStream.generate(() -> Math.random()).limit(4).forEach(System.out :: println);
    }
}
