package lambdas;

import java.util.Arrays;

/**
 * Demonstrates the use of anonymous classes, lambda expressions and method
 * references.
 *
 * @author Jean-Michel Busca
 */
public class Main {

  /**
   * Checks whether the specified strings all pass the specified test.
   *
   * @param strings the strings to check
   * @param tester the string tester to use
   *
   * @return true if the specified strings all pass the test, and false
   * otherwise
   */
  private static boolean checkStrings(String[] strings, StringTester tester) {
    // go through all the specified strings
    for (String string : strings) {
      // test the current string using the specified tester object
      if (!tester.test(string)) {
        return false;
      }
    }
    return true;
  }

  // method for generic type

  private static<T> boolean checkItems(T[] items, tester<T> tester) {
    // go through all the specified strings
    for (T item : items) {
      // test the current string using the specefied tester object
      if (!tester.test(item)) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {

    // the strings we want to test
    String[] strings = {"123", "abc", "you"};

    // Test #1: do all string start with "A"?
    // We use an instance of the StringStartsWithA class as the tester object
    boolean ok1 = checkStrings(strings, new StringStartsWithA());
    System.out.println("all strings starts with \"a\": " + ok1);

    // Test #2: are all strings 3-character long?
    // We use an instance of an anonymous class as the tester object
    boolean ok2 = checkStrings(strings, new StringTester() {
      @Override
      public boolean test(String string) {
        return string.length() == 3;
      }
    });

    System.out.println("all strings are 3-character long: " + ok2);

    // the type is set on de test method in stringTester

    // Test #3: are all strings equal to "foo"?
    // Your turn: use a lambda expression to define the tester
    boolean ok3 = checkStrings(strings, string -> string.equals("foo"));

    System.out.println("all strings are equals to 'foo': " + ok3);

    // Test #4: are all strings equal to "foo"? (same test as above)
    // Your turn: use method reference to define the tester
    boolean ok4 = checkStrings(strings, "foo"::equals);

    System.out.println("all strings are equals to 'foo': " + ok4);

    // Test #5: are all strings equal to "foo"? (same test as above)
    // Your turn: use method with generic type
    boolean ok5 = checkItems(strings, "foo"::equals);

    System.out.println("all strings are equals to 'foo': " + ok5);


    // Test #6: are all item equal to 1? (same test as above)
    // Your turn: use method with generic type
    Integer[] integers = new Integer[] {1,2,3,4};

    boolean ok6 = checkItems(integers, i -> i.equals(1));

    System.out.println("all integers are equals to 1: " + ok6);

    Arrays.asList(integers).forEach(i -> {if(i.equals(1)) System.out.println(i);});
  }
}
