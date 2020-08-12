package com.example.functionalprograminginjava;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ParallelStreamTest {

    @Test
    public void test()
    {
        Stream<Supplier<String>> suppliers =
                Stream.of(
                        delayedSupply("abc", 300),
                        delayedSupply("def", 800),
                        delayedSupply("ghi", 100))
                .parallel();

        long startTime = System.currentTimeMillis();
        List<String> strings =  suppliers.map(Supplier::get)
                .collect(Collectors.toList());
        long endTime = System.currentTimeMillis();
        System.out.println("結果 : " + strings + ", 処理時間 : " + (endTime - startTime) + " ms");

    }

    private static Supplier<String> delayedSupply(String result, int milliseconds) {
        return () -> {
            try {
                Thread.sleep(milliseconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return result;
        };
    }

}
