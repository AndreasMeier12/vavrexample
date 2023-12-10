package org.example;
import io.vavr.control.Option;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.jackson.datatype.VavrModule;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        Option<Integer> option = Option.some(13)
                .map(x -> x * 2)
                .peek(() -> System.out.println("nothing here"), x -> System.out.println(x))
                .flatMap(a -> doMaybeStuff(a))
                .peek( () -> System.out.println("nothing here"), x-> System.out.println(x));
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule( new VavrModule());
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            mapper.writeValue(stream, option);
            System.out.println(stream.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    static Option<Integer> doMaybeStuff(int a){
        if (a % 2 == 0){
            return Option.of(null);
        }
        return Option.of(a);
    }
}