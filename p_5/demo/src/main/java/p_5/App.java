package p_5;

/**
 * Hello world!
 *
 */
public class  App {
    public static void main(String[] args) {
      System.out.println(sum(1,2));
      System.out.println(evaluate("2+3+4"));
    }
    
    // adds two ints
    public static int sum(int a, int b) {
      int sum = a + b;
      return sum;
    }
 
    // calculates an additional expression
    public static int evaluate(String expression) {
      int sum = 0;
      for (String summand: expression.split("\\+"))
        sum += Integer.valueOf(summand);
      return sum;
    }
  }