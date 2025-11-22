import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;



class Phone { //will be used for the explaination of constructorRef
    String name;
    Phone(String name) {
        this.name = name;
    }
};
public class Main {
    public static void main(String[] args) {
//--------------------------------------------------------------------
        // 1. Predicate = Takes one argument and returns a boolean value (.test())
            Predicate<Integer> isCorrect = (x -> x==3);
            System.out.println(isCorrect.test(4));
            //output: False

            Predicate<String> startWithA = (x->x.toLowerCase().startsWith("a"));
            System.out.println(startWithA.test("Akki"));
            //output: True
//-----------------------------------------------------------------
        // 2. Consumer = Takes one argument and returns nothing (.accept())
            Consumer<List<String>> printList= (x->{
                x.forEach(y-> System.out.println(y));
            });
            printList.accept(List.of("fddd","fdew"));
        //output: fddd fdew
//-----------------------------------------------------------------
        // 3. Function<Input,Output> = Takes one argument and returns an output (.apply())
            Function<Integer, String> intToString = (x->"Number is: "+x);
            System.out.println(intToString.apply(5));
            //output: Number is: 5
//-----------------------------------------------------------------
        //4. Supplier = Takes no argument and returns a value (.get())
            java.util.function.Supplier<Double> randomValue = ()-> Math.random();
            System.out.println(randomValue.get());
            //output: some random value between 0.0 and 1.0
//-----------------------------------------------------------------
        // 5. UnaryOperator (Can Use Function as well) = Takes one argument and returns a value of the same type (.apply())
            UnaryOperator<Integer> square = (x->x*x);
            System.out.println(square.apply(5));
            //output: 25
//-----------------------------------------------------------------
        // 6. BinaryOperator (Can Use BiFunction as well) = Takes two arguments and returns a value of the same type (.apply())
            BinaryOperator<Integer> add = ( (x,y)->x+y);
            System.out.println(add.apply(5,10));
            //output: 15
//--------------------------------------------------------------------
        // Method Reference
        List<String> names = List.of("Alice", "Bob", "Charlie");
        names.forEach(x-> System.out.println(x)); // Lambda Expression
        names.forEach(System.out::println); // Method Reference
//--------------------------------------------------------------------
        // Constructor Reference
        //In Constructor reference we use new keyword with :: operator
        List<String> phoneNames = Arrays.asList("iPhone", "Samsung", "OnePlus");
        List<Phone> phones = phoneNames.stream().map(x->new Phone(x)).collect(Collectors.toList()); // Using Lambda Expression
        List<Phone> phonesUsingConstructor = phoneNames.stream().map(Phone::new).collect(Collectors.toList()); // Using Constructor Reference

        phonesUsingConstructor.forEach(x-> System.out.println(x.name));
        //output: iPhone Samsung OnePlus
//--------------------------------------------------------------------

                                    //Streams
        // Stream is a sequence of elements supporting sequential and parallel aggregate operations.
        // Streams can be created from Collections, Arrays, or I/O channels.
        // Streams support various operations like filter, map, reduce, collect, etc.
        // Streams are lazy in nature, meaning they don't perform any computation until a terminal operation is invoked.
        // Terminal operations include forEach, collect, reduce, etc.
        // Source operations include of, from, iterate, generate, etc.
        // Intermediate operations include filter, map, sorted, distinct, etc.

        //Creating Streams
        //1. From Collections
        List<Integer> numbers= Arrays.asList(1,2,3,4,5);
        Stream<Integer> streamOfNumbers= numbers.stream();

        //2. From Arrays
        Integer[] arrays={1,2,4,5};
        Stream<Integer> streamOfArrays= Arrays.stream(arrays);

        //3. Using Stream.of()
        Stream<String> streamOfValues= Stream.of("A","B","C");

        //Intermediate Operations
        //1. filter() -> Used to filter elements based on a condition
        List<Integer> oddNumbers = numbers.stream().filter(x->x%2==1).collect(Collectors.toList());
        System.out.println(oddNumbers);

        //2. map() -> Used to transform elements
        List<Integer> squaredNumbers = numbers.stream().map(x->x*x).collect(Collectors.toList());
        System.out.println(squaredNumbers);

        //3. sorted() -> Used to sort elements
        List<Integer> sortedNumbersAscending = numbers.stream().sorted().collect(Collectors.toList());
        List<Integer> sortedNumbersDescending = numbers.stream().sorted((a,b)->b-a).collect(Collectors.toList());
        System.out.println(sortedNumbersAscending);
        System.out.println(sortedNumbersDescending);

        //4. distinct() -> Used to remove duplicate elements
        List<Integer> numbersWithDuplicates = Arrays.asList(1,2,2,3,4,4,5);
        List<Integer> distinctNumbers = numbersWithDuplicates.stream().distinct().collect(Collectors.toList());

        //5. limit() -> Used to limit the number of elements
        List<Integer> limitedNumbers = numbers.stream().limit(3).collect(Collectors.toList());
        //6. skip() -> Used to skip a number of elements
        List<Integer> skippedNumbers = numbers.stream().skip(2).collect(Collectors.toList());
        //7. flatMap() -> Used to flatten nested structures
        List<List<Integer>> nestedNumbers = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5),
                Arrays.asList(6, 7, 8, 9)
        );
        //Printing flattened list numbers
        System.out.println("printing flattened list:");
        System.out.println(nestedNumbers.stream().flatMap(list->list.stream()).collect(Collectors.toList()));


        //Terminal Operations
        //1. forEach() -> Used to perform an action for each element
        distinctNumbers.forEach(x-> System.out.println(x));
        //2. collect() -> Used to collect elements into a collection
        Set<Integer> numberSet = distinctNumbers.stream().collect(Collectors.toSet());
        List<Integer> numberList = distinctNumbers.stream().collect(Collectors.toList());
        //3. reduce() -> Used to reduce elements to a single value
        Integer sum = numbers.stream().reduce(0,(a,b)->a+b);
        Integer sumUsingMethodRef = numbers.stream().reduce(0,Integer::sum);
        //4. count() -> Used to count the number of elements
        Long count = numbers.stream().count();
        //5. findFirst() -> Used to find the first element
        Optional<Integer> firstElement = numbers.stream().findFirst();
        //6. anyMatch() -> Used to check if any element matches a condition
        Boolean hasEvenNumber = numbers.stream().anyMatch(x->x%2==0);
        //7. allMatch() -> Used to check if all elements match a condition
        Boolean allEvenNumbers = numbers.stream().allMatch(x->x%2==0);
        //8. noneMatch() -> Used to check if no elements match a condition
        Boolean noNegativeNumbers = numbers.stream().noneMatch(x->x<0);
        //Note above 4 operations can be done using different methods as well.
        //9. findAny() -> Used to find any element
        Optional<Integer> anyElement = numbers.stream().findAny();
        //10. findMax() -> Used to find the maximum element
        Optional<Integer> maxElement = numbers.stream().max(Integer::compareTo);
        //11. findMin() -> Used to find the minimum element
        Optional<Integer> minElement = numbers.stream().min(Integer::compareTo);
        //12. findFirst() -> Used to find the first element
        Optional<Integer> firstNum = numbers.stream().findFirst();



//        //---------------------------Exercies-----------------------------------
//        // Exercise 1: Given a list of integers, use streams to filter out the even numbers, square them, sort them and collect the results into a new list.
        List<Integer>input = Arrays.asList(7,5,3,2,5,2,4,78,5,21,3);
        List<Integer>output= input.stream().filter(x->x%2==0).map(x->x*x).sorted().collect(Collectors.toList());
        System.out.println(output);

        // Exercise 2: Given a list of strings, use streams to filter out the strings that start with the letter 'a', convert them to uppercase, and collect the results into a new list.
        List<String>stringInput= Arrays.asList("apple","banana","avocado","grape","apricot","orange");
        List<String> stringOutput= stringInput.stream().filter(x->x.toLowerCase().startsWith("a")).map(x->x.toUpperCase()).collect(Collectors.toList());
        System.out.println(stringOutput);

        // Exercise 3: Given a list of integers, use streams to find the sum of all the odd numbers.
        List<Integer>intInput= Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer outputNum= intInput.stream().filter(x->x%2==1).reduce(0,(a,b)->a+b);
        System.out.println(outputNum);

        // Exercise 4: Counting occrreces of letter a in String
        String strInput= "Java is a programming language. Java is widely used.";
        long occurrences = strInput.chars().filter(x-> x=='a' || x=='A').count();




        //After this there are some other concepts like Parrallel Streams which are used to improve performance by utilizing multiple cores of the CPU.

        //Collector Operations

        // 1.Grouping
        // Grouping is a terminal operation that allows you to group elements of a stream based on a specified classifier function.
        List<String> fruits = Arrays.asList("apple", "banana", "apricot", "blueberry", "avocado", "cherry");
        Map<Character, List<String>> groupedFruits = fruits.stream()
                .collect(Collectors.groupingBy(fruit -> fruit.charAt(0)));

        System.out.println(groupedFruits);

        // 2.Partitioning
        // Partitioning is a special case of grouping that divides the elements of a stream into two groups based on a predicate.
        Map<Boolean, List<String>> partitionedFruits = fruits.stream()
                .collect(Collectors.partitioningBy(fruit -> fruit.length() > 5));
        System.out.println(partitionedFruits);

        // 3.Joining
        // Joining is a terminal operation that concatenates the elements of a stream into a single string, with an optional delimiter.
        String joinedFruits = fruits.stream()
                .collect(Collectors.joining(", "));
        System.out.println(joinedFruits);

        // 4.Summarizing
        // Summarizing is a terminal operation that provides statistical information about the elements of a stream, such as count, sum, average, min, and max.
        IntSummaryStatistics fruitLengthStats = fruits.stream()
                .collect(Collectors.summarizingInt(String::length));
        System.out.println(fruitLengthStats);
        //Output: IntSummaryStatistics{count=6, sum=33, min=5, average=5.500000, max=9

        

    }
}
