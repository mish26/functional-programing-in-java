package com.example.functionalprograminginjava;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.functionalprograminginjava.CurryingAndPartialApplicationTest.Appendation.append;
import static org.assertj.core.api.Assertions.assertThat;

public class CurryingAndPartialApplicationTest {

    @Test
    public void test8()
    {
        BiFunction<String, String, String> concat = (first, second) -> first + second;
        assertThat(concat.apply("A", "B")).isEqualTo("AB");
    }

    @Test
    public void test9()
    {
        BiPredicate<String, String> isSame = (first, second) -> first.equals(second);
        assertThat(isSame.test("A", "B")).isFalse();
    }

    @Test
    public void test10() {
        BiConsumer<String, String> show = (first, second) -> {
            System.out.println(first);
            System.out.println(second);};
        show.accept("A","B");
    }

    @Test
    public void test11() {

        BiFunction<String, String, String> concat = (first, second) -> first + second;
        // カリー化
        Function<String, Function<String, String>> append = second -> first -> concat.apply(first, second);

        // 部分適用
        Function<String, String> appendA = append.apply("A");

        List<String> concat2 = Stream.of("B","C")
                .map(appendA)
                .map(append.apply("B"))
                .collect(Collectors.toList());

        assertThat(concat2).containsExactly("BAB", "CAB");

    }

    @Test
    public void test12() {
        String result = append("test").to("123");
        assertThat(result).isEqualTo("123test");
    }

    @Test
    public void test13() {
        Stream.of("123","456").map(append("789")).map(append("1000")).collect(Collectors.toList());

        String result = append("test").to("123");
        assertThat(result).isEqualTo("123test");
    }


    interface PartialApply extends Function<String, String> {
        String to(String s);

        @Override
        default String apply(String s){
            return to(s);
        }
    }

    public static class Appendation {

        public static PartialApply append(String appendix){
            return string -> string + appendix;
        }

    }

}
