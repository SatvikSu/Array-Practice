
/**
 * Write a description of class ArrayPractice here.
 *
 * @author Satvik Subbaraman
 * @version 8-7-20
 */
import java.util.Random;
import java.util.ArrayList;
public class ArrayPractice
{
    private boolean containsVal(int [] arr, int val, int slotNum)
    {
        //returns true if value is contanied in array from 0 to slot, not including slot
        for (int i = 0; i < slotNum; i++)
        {
            if (arr[i] == val)
            {
                return true;
            }
        }
        return false;
    }

    public int [] permutationOfNums(int start, int end, int numElements)
    {
        int [] results = new int [numElements];
        Random rrr = new Random();
        results[0] = rrr.nextInt(end - start + 1) + start;

        for (int i = 1; i < numElements; i++)
        {
            results[i] = rrr.nextInt(end - start + 1) + start;
            while (containsVal(results, results[i], i) == true)
            {
                results[i] = rrr.nextInt(end - start+ 1) + start;
            }
        }

        return results;
    }

    public int [] valueSetter(int [] data, int [] setIndex)
    {
        int [] results = {-123};
        return results; //qqq
    }

    public void testStringToWords()
    {
        String input = "My092340234name_is234234Satvik";
        String [] results = stringToWords(input);
        System.out.println ("input: " + input);
        System.out.println ("results:");
        for (int i = 0; i < results.length; i++)
        {
            System.out.print (results[i] + "  ");
        }
        System.out.println ("\nNumber of words: " + numWords(input));
    }

    public String [] stringToWords (String str)
    {
        String [] words = new String [numWords(str)];
        for (int i = 0; i < words.length; i++)
        {
            words[i] = "";
        }

        boolean isWord = false;
        int wordsIndex = 0;
        for (int i = 0; i < str.length(); i++)
        {
            if (Character.isLetter(str.charAt(i)) == true)
            {
                isWord = true;
            }
            else 
            {
                isWord = false;
            }
            //
            if (isWord == true)
            {
                words[wordsIndex] += str.substring(i, i+1);
            }
            if (i>=1 && isWord == false && Character.isLetter(str.charAt(i-1)) == true)
            {
                wordsIndex++;
            }
        }
        return words;
    }

    private int numWords (String str)
    {
        //method tells you how many "words" there are. 
        //ex. str = "hello_hi" = 2
        boolean isWord = false;
        int total = 0;
        for (int i = 0; i < str.length(); i++)
        {
            if (isWord == true && Character.isLetter(str.charAt(i)) == false)
            {
                total++;
            }
            else if (isWord == true && i == str.length() - 1)
            {
                total++;
            }
            if (Character.isLetter(str.charAt(i)) == false)
            {
                isWord = false;
            }
            else if (Character.isLetter(str.charAt(i)) == true)
            {
                isWord = true;
            }
        }
        return total;
    }

    //==========================================================================

    public void primeNumbers(int start, int end)
    {
        //to test is a # is prime, test dividing by all numbers from 2 to sqrt of num
        /*if (start<0 || end < 0)
        {
        System.out.println ("Invalid start or end numbers. Can't be negative");
        return;
        }*/
        if (start > end)
        {
            System.out.println ("Start must be less than or equal to end");
            return;
        }

        ArrayList<Integer> results = new ArrayList<Integer>();
        for (int i = start; i <= end; i++)
        {
            if (isPrime(i) == true)
            {
                results.add(i);
            }
        }
        System.out.println ("Prime numbers from range " + start + " to " + end + ": ");
        for (int i = 0; i < results.size(); i++)
        {
            System.out.print (results.get(i) + "\t");
            if (i != 0 && (i+1)% 10 == 0)
                System.out.print ("\n\n");
        }
    }

    //==========================================================================

    public int [] primeFactorization (int num)
    {
        //returns an int array with the factors and the exponents paired up.
        //ex. 100 returns {2, 2, 5, 2};

        //takes care of annoying cases
        if (isPrime(num) == true || num == 0 || num == 1)
        {
            int [] results = {num, 1};
            return results;
        }
        else if (num < 0)
        {
            num = num / (-1);
        }

        ArrayList<Integer> rawFactors = new ArrayList<Integer>(); 
        //will hold all of the prime factors
        //but won't hold the powers
        int maxDivisor = (int)Math.sqrt(num);
        for (int i = 2; i <= maxDivisor; i++)
        {
            if (isPrime(i) == true  && num % i == 0)
            {
                rawFactors.add(i);  
                if (num / i != i && isPrime(num / i) == true) //what this if statement does:
                //ex. if num = 10 and i =2, it would also add 5 to rawFactors
                {
                    rawFactors.add(num/i);
                }
            }
            else if (isPrime(i) == false && isPrime(num / i) == true && num % (num/i) == 0)
            {
                rawFactors.add (num / i);
            }
        }
        int [] primeFactors = new int [2* (rawFactors.size())];
        int rawFactorsTracker = 0;
        for (int i = 0; i <= 2*(rawFactors.size() - 1); i+=2)
        {
            primeFactors[i] = rawFactors.get(rawFactorsTracker);
            primeFactors[i+1] = powerDivisible(num, rawFactors.get(rawFactorsTracker));
            rawFactorsTracker++;
        }
        return primeFactors;
    }

    private int powerDivisible (int num, int factor) //make private
    {
        if (factor > num || num % factor != 0)
        {
            return 0;
        }
        //num % factor = 0
        int count = 0;
        int originalFactor = factor;
        while (num%factor == 0)
        {
            count++;
            factor *= originalFactor;
        }
        return count;
    }

    private boolean isPrime (int num) //more efficient isPrime method
    {
        //take care of annoying cases
        if (num < 0)
        {
            num = num * -1;
        }
        if (num == 0 || num == 1)
        {
            return false;
        }
        if (num == 2)
        {
            return true;
        }

        int maxDivisor = (int)Math.sqrt(num);

        int i = 2;
        if (num % i == 0)
        {
            return false;
        }
        //below only happens if still not returned
        for (i = 3; i <= maxDivisor; i+=2)
        {
            if (num % i == 0)
            {
                return false;
            }
        }

        //to skip even #s: start from 3 and process 1-2 "manually" outside the loop
        return true;
    }

    public void testPrimeFactorization(int num)
    {
        String results = "";
        int [] ans = primeFactorization(num);
        for (int i = 0; i < ans.length; i++)
        {
            if (i % 2 == 0)
            {
                results += ans[i];
            }
            else if (i % 2 == 1 & i != ans.length - 1)
            {
                results += "^" + ans[i] + " * ";
            }
            else //if (i == ans.length - 1)
            {
                results += "^" + ans[i];
            }
        }
        System.out.println (results);
    }

    // ===========================================================================

    public int lcm(int num1, int num2)
    {
        int results = 1;

        int [] fac1 = primeFactorization (num1);
        int [] fac2 = primeFactorization (num2);
        //find out what factors are and are not in common?
        for (int i = 0; i <= fac1.length - 2; i+=2)
        {
            if (containsFactor(fac2, fac1[i]) == -1)
            {
                results *= Math.pow (fac1[i], fac1[i+1]);
            }
            else //fac2 contains fac1[i]
            {
                int maxPow = Math.max(containsFactor(fac1, fac1[i]), containsFactor(fac2, fac1[i]));
                results *= Math.pow (fac1[i], maxPow);
            }
        }

        for (int j = 0; j <= fac2.length - 2; j+=2)
        {
            if (containsFactor(fac1, fac2[j]) == -1)
            {
                results *= Math.pow(fac2[j], fac2[j+1]);
            }
        }

        return results;
    }

    private int containsFactor (int [] factors, int num)
    {
        //method sees if num is one of the factors (even index) of the factors array
        for (int i = 0; i <= factors.length - 2; i+=2)
        {
            if (factors[i] == num)
            {
                return factors[i+1]; //returns the exponent of this factor
            }
        }
        return -1; //if it does not contain it
    }

    //============================================================================

    public int testGetSubsets()
    {
        int [] test = {1, 8, 9, 39, 52, 91};
        int [] [] results = getSubsets(test);
        int count = 0;
        for (int i = 0; i < results.length; i++)
        {
            if (arrTotal(results[i]) >= 100)
            {
                count++;
            }
        }
        return count;
    }
    
    public int [] [] getSubsets (int [] arr)
    {
        int [] [] results;
        results = new int [(int)Math.pow(2,arr.length)][]; //should it be [arr.length + 1][]?
        int subsetCount = 0;

        while (subsetCount < results.length) //should it be arr.length?
        {
            long subsetBinary = toBinary(subsetCount);
            String strBinary = Long.toString(subsetBinary);
            ArrayList<Integer> rawSubset = new ArrayList<Integer>();
            for (int i = 0; i <= strBinary.length() - 1; i++)
            {
                if (strBinary.charAt(strBinary.length() - 1 - i) == '1')
                {
                    rawSubset.add(arr[i]);
                }
            }
            int [] subset = alToArray(rawSubset);
            results[subsetCount] = new int [subset.length];
            for (int j = 0; j < subset.length; j++)
            {
                results[subsetCount][j] = subset[j];
            }

            subsetCount++;
        }

        return results;
    }

    private int arrTotal (int [] arr)
    {
        int total = 0;
        for (int i = 0; i < arr.length; i++)
        {
            total+=arr[i];
        }
        return total;
    }

    private int [] alToArray(ArrayList<Integer> input)
    {
        int [] output = new int [input.size()];
        for (int i = 0; i < output.length; i++)
        {
            output[i] = input.get(i);
        }
        return output;
    }

    private int combination (int n, int k)
    {
        if ((n < 0 || k < 0) || n < k)
        {
            return 0;
        }
        int results = factorial(n) / (factorial(k) * factorial (n-k));
        return results;
    }

    private int factorial(int num)
    {
        int results = 1;
        if (num < 0)
        {
            return num;
        }
        else if (num == 0)
        {
            return 1;
        }
        for (int i = 1; i <= num; i++)
        {
            results *= i;
        }
        return results;
    }

    private long toBinary (int num) //make this private; use it in getSubsets() method
    {
        long results = 0;
        boolean isNegative = false;
        if (num <= -1)
        {
            isNegative = true;
            num *= -1;
        }

        if (num > 524287 || num < -524287)
        {
            results = 0;
            return results;
        }

        int numOfDigits = 0;
        while (num > Math.pow (2, numOfDigits))
        {
            numOfDigits++;
        }
        if (num == Math.pow (2, numOfDigits))
        {
            numOfDigits++;
        }
        //results = (int)Math.pow(10, numOfDigits - 1);
        //I removed the above line. Is it needed?
        //ex. if num = 10, numOfDigits = 4, results = 1000
        int digitTracker = numOfDigits - 1;
        while (num != 0)//should i make it: while (digitTracker > 0)?
        {
            if ( num >= Math.pow (2, digitTracker) )
            {
                num -= Math.pow (2, digitTracker);
                results+= Math.pow (10, digitTracker);
            }
            digitTracker--;
        }

        if (isNegative == true)
        {
            results *= -1;
        }
        return results;
    }
}
