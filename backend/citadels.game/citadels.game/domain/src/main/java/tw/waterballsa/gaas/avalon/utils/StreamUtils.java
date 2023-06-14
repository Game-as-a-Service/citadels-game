package tw.waterballsa.gaas.avalon.utils;

import lombok.experimental.UtilityClass;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.*;
import static java.util.stream.IntStream.range;

/**
 * @author johnny@waterballsa.tw
 */
@UtilityClass
public class StreamUtils {
    public static <T, R> List<T> flatMapToList(R[] array, Function<? super R, ? extends Stream<? extends T>> flatMapping) {
        return stream(array).flatMap(r -> {
            try {
                return flatMapping.apply(r);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(toList());
    }

    public static <T, R> List<T> flatMapToList(Collection<R> collection, Function<? super R, ? extends Stream<? extends T>> flatMapping) {
        return collection.stream()
                .flatMap(r -> {
                    try {
                        return flatMapping.apply(r);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).collect(toList());
    }

    public static <T extends Comparable<T>> List<T> sortToList(Collection<T> collection) {
        return collection.stream().sorted().collect(toList());
    }

    public static <T> List<T> sortToList(Collection<T> collection, Comparator<T> comparator) {
        return collection.stream().sorted(comparator).collect(toList());
    }

    public static <T, R> List<T> mapToList(R[] array, Function<R, T> mapping) {
        return mapToList(asList(array), mapping);
    }

    public static <T, R> List<T> mapToList(Collection<R> collection, Function<R, T> mapping) {
        return collection.stream().map(in -> {
            try {
                return mapping.apply(in);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(toList());

    }

    public static <T, R> T[] mapToArray(Collection<R> collection,
                                        Function<R, T> mapping,
                                        IntFunction<T[]> generator) {
        return collection.stream().map(in -> {
            try {
                return mapping.apply(in);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toArray(generator);
    }

    public static <T, R> Set<T> mapToSet(R[] array, Function<R, T> mapping) {
        return stream(array).map(in -> {
            try {
                return mapping.apply(in);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(toSet());
    }

    public static <T, R> Set<T> mapToSet(Collection<R> collection, Function<R, T> mapping) {
        return collection.stream().map(in -> {
            try {
                return mapping.apply(in);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(toSet());
    }

    public static <T, K, U> Map<K, U> toMap(Collection<T> collection, Function<? super T, ? extends K> keyMapper,
                                            Function<? super T, ? extends U> valueMapper) {
        return collection.stream().collect(Collectors.toMap(keyMapper, valueMapper));
    }

    public static <T> Optional<T> findFirst(T[] array, Predicate<T> predicate) {
        return stream(array).filter(predicate).findFirst();
    }

    public static <T> Optional<T> findFirst(Collection<T> collection, Predicate<T> predicate) {
        return collection.stream().filter(predicate).findFirst();
    }

    public static <T> List<T> filterToList(Collection<T> collection, Predicate<T> predicate) {
        return collection.stream().filter(predicate).collect(toList());
    }

    public static <T> List<T> filterToList(T[] array, Predicate<T> predicate) {
        return stream(array).filter(predicate).collect(toList());
    }

    public static <T, K> Map<K, List<T>> groupingBy(Collection<T> collection, Function<? super T, ? extends K> classifier) {
        return collection.stream().collect(Collectors.groupingBy(classifier));
    }

    public static double average(Integer... nums) {
        return stream(nums).mapToInt(Integer::intValue).average().orElse(0);
    }

    public static <T> double average(Collection<T> collection, ToIntFunction<T> mapper) {
        return collection.stream().mapToInt(mapper).average().orElse(0);
    }

    public static int sum(Integer[] array) {
        return stream(array).mapToInt(Integer::intValue).sum();
    }

    public static int sum(Collection<Integer> collection) {
        return collection.stream().mapToInt(Integer::intValue).sum();
    }

    public static <T> int sum(Collection<T> collection, ToIntFunction<T> mapper) {
        return collection.stream().mapToInt(mapper).sum();
    }

    public static <T> long sum(Collection<T> collection, ToLongFunction<T> mapper) {
        return collection.stream().mapToLong(mapper).sum();
    }

    public static <T> List<T> generate(int count, T obj) {
        return range(0, count).mapToObj(i -> obj).collect(toList());
    }

    public static <T> List<T> generate(int count, IntFunction<T> generator) {
        return range(0, count).mapToObj(generator).collect(toList());
    }


    public static <T> String join(Collection<T> collection, String delimiter) {
        return collection.stream().map(String::valueOf).collect(joining(delimiter));
    }
}
