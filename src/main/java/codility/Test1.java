package codility;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Mir on 13/10/2016.
 */
public class Test1 {

    static int solution(int A[], int N) {
        long sum1 = A[0];
        long sum2 = 1;
        for (int i = 1; i < N; ++i) {
            sum1 += A[i];
            sum2 += i + 1;
        }
        sum2 += N + 1;
        return (int) (sum2 - sum1);
    }

    static int solution2(int n) {
        String binary = Integer.toBinaryString(n);

        int maxSofar = 0;
        int count = 0;
        for (int i = 0; i < binary.length(); ++i) {
            char c = binary.charAt(i);
            if (c == '1') {
                if (count > maxSofar) {
                    maxSofar = count;
                }
                count = 0;
            } else {
                ++count;
            }
        }
        if (count > maxSofar) {
            maxSofar = count;
        }

        return maxSofar;
    }

    static int[] solution3(int[] A, int noSteps) {
        int[] result = new int[A.length];
        for (int i = noSteps; i < A.length; ++i) {
            result[i] = A[i - noSteps];
        }
        for (int i = noSteps - 1; i >= 0; --i) {
            result[i] = A[i + noSteps];
        }
        return result;
    }

    static int solution4(int[] A) {
        Optional<Integer> odd = IntStream.of(A).boxed().filter(i -> i % 2 == 1).findFirst();
        return (odd.orElse(-1));
    }

    static int solution5(int[] A) {

        if (A == null || A.length == 0) {
            return -1;
        }

        long rightSum = 0L;
        for (int i = 1; i < A.length; ++i) {
            rightSum += A[i];
        }
        long leftSum = A[0];
        int bestSplitSofar = 1;
        long lowestDiffSofar = rightSum - leftSum;
        for (int i = 1; i < A.length; ++i) {
            leftSum += A[i];
            rightSum -= A[i];
            if (rightSum - leftSum < lowestDiffSofar) {
                lowestDiffSofar = rightSum - leftSum;
                bestSplitSofar = i;
            }
        }
        return bestSplitSofar;
    }

    static int solution55 (int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }
        if (A.length == 1) {
            return 0;
        }
        long rightSum = 0L;
        for (int i = 0; i < A.length; ++i) {
            rightSum += A[i];
        }
        long leftSum = 0L;
        int index = -1;
        for (int i = 0; i < A.length; ++i) {
            rightSum -= A[i];
            if (rightSum == leftSum) {
                index = i;
                break;
            }
            leftSum += A[i];
        }
        return index;
    }

    static int solution6(int X, int Y, int D) {
        int distance = Y - X;
        return (distance / D) + ((distance % D) == 0 ? 0 : 1);
    }

    static int solution7(int[] A) {
        int eastMovingCars = 0;
        int totalPairs = 0;
        for (int i = 0; i < A.length; ++i) {
            if (A[i] == 0) {
                ++eastMovingCars;
            } else {
                totalPairs += eastMovingCars;
            }
        }
        return totalPairs;
    }

    static int solution8(int[] A) {
        Comparator<Integer> ascending = (i1, i2) -> Integer.compare(i2, i1);
        List<Integer> sorted = IntStream.of(A).boxed().sorted(ascending).collect(Collectors.toList());
        return (sorted.get(0) * sorted.get(1) * sorted.get(2));
    }

    static int solution9(String S) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < S.length(); ++i) {
            if (S.charAt(i) == '(') {
                stack.push(S.charAt(i));
            }
            else if (stack.isEmpty()) {
                return 0;
            }
            else {
                stack.pop();
            }
        }

        return (stack.isEmpty()) ? 1 : 0;
    }

    static int solution10(String S) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < S.length(); ++i) {
            char c = S.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }
            else {
                if (stack.isEmpty() ||
                        (c == ')' && stack.peek() != '(') || (c == ']' && stack.peek() != '[') ||
                        (c == '}' && stack.peek() != '{')) {
                    return 0;
                }
                stack.pop();
            }
        }
        return (stack.isEmpty()) ? 1 : 0;
    }

    static int solution11(int[] A) {
        int leader = -1;
        int candidate = -1;
        int count = 0;
        int size = 0;
        int value = 0;
        for(int i = 0; i < A.length; i++){
            if (size == 0){
                value = A[i];
                size++;
            }
            else if(value != A[i])
                size--;
            else
                size++;
        }
        if(size != 0)
            candidate = value;
        for(int i =0; i < A.length; i++){
            if(A[i] == candidate)
                count++;
            if(count>A.length/2)
                leader = candidate;
        }
        int lcount = 0;
        int equiLeadersCount = 0;
        for(int i =0; i < A.length; i++){
            if(A[i] == leader)
                lcount++;
            int rcount = count-lcount;
            if(lcount>(int)(i+1)/2 && rcount > (int)(A.length-i-1)/2)
                equiLeadersCount++;

        }
        return equiLeadersCount;
    }

    public static void main (String[] args) {
        int[] A = {2, 3, 1, 5};
        System.out.println ("Missing = " + solution(A, A.length));
        System.out.println ("Maxcount = " + solution2(9));
        System.out.println("rotated=" + Arrays.toString(solution3(A, 2)));

        A = new int[]{-10, 3, 5, 2};
        System.out.println("Best split=" + solution5(A));

        System.out.println("jumps=" + solution6(10, 85, 30));

        A = new int[]{0, 1, 0, 1, 1};
        System.out.println("No of pairs=" + solution7(A));

        A = new int[]{-3, 1, 2, -2, 5, 6};
        System.out.println("Max triple=" + solution8(A));

        System.out.println("test1=" + solution9("((()))"));
        System.out.println("test2=" + solution9("((()(("));
        System.out.println("test3=" + solution9("("));

        System.out.println("Another test=" + solution10("{[()]}"));
        System.out.println("Another test=" + solution10("{[}]"));
    }

    public int solution(int[] A) {
        if (A.length == 1) {
            return 0;
        }

        long rightSum = 0L;
        for (int i=1; i < A.length; ++i) {
            rightSum += A[i];
        }
        long leftSum = A[0];
        for (int i=0; i < A.length; ++i) {
            if (leftSum == rightSum) {
                return i;
            }
            leftSum +=A[i];
            rightSum -= A[i];
        }
        return -1;
    }

}
