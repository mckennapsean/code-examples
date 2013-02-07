// by Sean McKenna on February 10th, 2010
// how many N-bits (1) exist in two's complement representation of numbers
// solution to Problem #1 of the ACM 2009 Regional Competition

public class Nbits{
  private static final int A = 5;
  private static final int B = 14;
  private static final int N = 3;
  
  public static void main(String[] args){
    int count = 0;
    
    for(int i = A; i <= B; i++){
      int bits = Integer.bitCount(i);
      if(bits == N)
        count++;
    }
    System.out.println(count);
  }
}
