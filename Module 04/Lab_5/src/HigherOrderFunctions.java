import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class HigherOrderFunctions {

  /**
   * A function that takes in a list of type T and returns a value of type R.
   *
   * @param init        initial value.
   * @param list        a list of type T.
   * @param accumulator a function that takes in a value of type T and returns a value of type R.
   * @param <T>         type of the list.
   * @param <R>         type of the return value.
   */
  static <T, R> void fold(R init, List<T> list, Function<T, R> accumulator){
    R result = init;
    for (T element : list){
      result = accumulator.apply(element);
    }
  }

  /**
   * A function that takes in a list of type T and returns a list of type R.
   * @param list a list of type T.
   * @param mapper a function that takes in a value of type T and returns a value of type R.
   * @param <T> type of the list.
   * @param <R> type of the return value.
   */
  static <T, R> void map(List<T> list, Function<T, R> mapper){
    List<T> newList = new ArrayList<>();
    for (T element : list){
      newList.add((T) mapper.apply(element));
    }
  }

  static <T, R> void filter(List<T> list, Function<T, R> predicate) {
    R result = null;
    for (T element : list){
      result = predicate.apply(element);
    }
  }
}