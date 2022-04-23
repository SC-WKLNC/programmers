package Lv1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamTest {
    
    @Test
    public void create(){

//        String[] ids = new String[]{"Alice","Bob","Carol","Dave","Eve"};
//        Stream<String> stream1 = Arrays.stream(ids);

//        List<String> ids = new ArrayList<String>();
//        ids.add("Alice");
//        ids.add("Bob");
//
//        Stream<String> stream = ids.stream();


//        Stream<String> stream = Stream.<String>builder()
//                .add("Alice")
//                .add("Bob")
//                .build();

//        Stream<String> stream = Stream.generate(() -> "Alice").limit(5);
//        stream.forEach(data -> System.out.println("data = " + data));

//        Stream<Integer> stream = Stream.iterate(10, n -> n + 5).limit(5);
//        stream.forEach(data -> System.out.println("data = " + data));


        Stream<String> stream = Stream.of("Alice","Bob","Carol","Dave","Eve");
        Stream<String> result = stream.filter(id -> id.contains("e"))
                                        .filter(id -> id.length() > 3);

        result.forEach(data -> System.out.println("data = " + data));
    }
}
