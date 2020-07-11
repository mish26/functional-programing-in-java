package com.example.functionalprograminginjava;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JUnit5Test {


    Predicate<Integer> largerThan(Integer criteria){
        return i -> i > criteria;
    }

    @Test
    void success() {
        assertEquals(10, 10);
    }

    @Test
    public void test7()
    {
        IntStream.range(0,10).boxed()
                .filter(largerThan(4))
                .map(Object::toString)
                .forEach(System.out::println);
    }


    @Test
    public void test1()
    {
        Function<Integer, String> toString = new Function<Integer, String>() {

            @Override
            public String apply(Integer integer) {
                return integer.toString();
            }
        };
        assertThat(toString.apply(100), is("100"));
    }

    @Test
    public void test2()
    {
        Function<Integer, String> toString = integer -> integer.toString();

        assertThat(toString.apply(100), is("100"));
    }

    @Test
    public void test3()
    {
        Function<Integer, String> toString = Object::toString;

        assertThat(toString.apply(100), is("100"));
    }

    @Test
    public void test4()
    {
        Predicate<Integer> predicate = integer -> integer > 2;
        assertThat(predicate.test(3), is(true));
    }

    @Test
    public void test5()
    {
        Supplier<Integer> supplier = () -> 3;

        assertThat(supplier.get(), is(4));
    }

    @Test
    public void test6()
    {
        Consumer<String> consumer = System.out::println;
        consumer.accept("100");
    }

}
