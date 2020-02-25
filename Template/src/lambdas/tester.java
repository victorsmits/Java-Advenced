package lambdas;

public interface tester<T> {

  /**
   * Test the specified string for some property.
   *
   * @param string the string to test
   *
   * @return true if the specified string passes the test and false otherwise.
   */
  boolean test(T string);
}
