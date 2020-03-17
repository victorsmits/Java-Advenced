package exercise4.client;

import exercise4.Task;

public class IntegerAdder implements Task<Integer> {

  int A;
  int B;

  public IntegerAdder(int a, int b){
    A = a;
    B = b;
  }

  @Override
  public Integer execute() {
    return A+B;
  }
}
