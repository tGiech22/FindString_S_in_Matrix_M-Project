import java.util.*;
import java.util.Arrays; 
/**
 * Write a description of class FindStringS_In_M here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FindStringS_In_M
{
    public static void main()
    {
        String s = "AABCDE";
        char M[][] = { {'A', 'B', 'A', 'A'},
                       {'B', 'C', 'D', 'A'},
                       {'A', 'B', 'C', 'D'},
                       {'E', 'E', 'A', 'B'} };
                       
        findString(M, s);
    }

    public static void findString(char[][] M, String s)
    {
        int k = 0;
        String compare = "";
        String coordinates = "";
        int lengthOfString = s.length();
        
        for(int i = 0; i < M.length; i++)
        {
            for(int j = 0; j < M[i].length; j++)
            {                
                if(M[i][j] == s.charAt(k))
                {
                    k++;
                    compare += M[i][j];
                    coordinates += "(" + i + "," + j + "), ";
                }
                else
                {
                    k = 0;
                    compare = "";
                    coordinates = "";
                    
                    if(M[i][j] == s.charAt(k))
                    {
                        k++;
                        compare += M[i][j];
                        coordinates += "(" + i + "," + j + "), ";
                    }
                }
                if(compare.length() == lengthOfString)
                {
                    break;
                }
            }
        }
        
        if(compare.equals(s))
        {
            System.out.println("YES");
            System.out.println(coordinates);
            System.out.println("origin: " + coordinates.substring(0, 5));
        }
    }
    
    public static void findSRecursion(char[][] m, String s)
    {
        //ArrayList to store coordinates
        ArrayList<String> l = new ArrayList<String>();
        
        for(int i = 0; i < m.length; i++)
        {
            for(int j = 0; j < m[i].length; j++)
            {
                //if it doesn't match clear coordinates
                l.clear();
                
                //checks to see if first char in matrix 
                //matches with first char of string
                if(m[i][j] == s.charAt(0))
                {
                    //calls travel - recursive method
                    l = travel(m, s, l, 0, i, j);
                    
                    //checks to see if string has been found
                    if(l.size() == s.length())
                    {
                        System.out.println("answer: Yes");
                        System.out.println("origin: " + l.get(0));
                        System.out.println("coordinates: " + l);
                        System.exit(0);
                    }
                }                
            }
        }
        
        System.out.println("answer: No");
    }
    public static ArrayList<String> travel(char[][] m, String s, ArrayList<String> coordinates, int index, int i, int j)
    {
        //System.out.println("i: " + i + "\tj: " + j + "\tindex: " + index);
        
        //if String s has been found in matrix m
        if(coordinates.size() == s.length() && checkEqual(m,s,coordinates))
        {
            return coordinates;
        }//if doesn't match then goes back to loop through matrix
        else if(m[i][j] != s.charAt(index))
        {
            return coordinates;
        }//if matches
        else if(m[i][j] == s.charAt(index))
        {
            //adds coordinates into ArrayList
            coordinates.add("(" + i + "," + j + ")");
            
            //recursively checks up, left, bottom, right respectively
            if(i != 0) //if position isn't at top edge go up
            {
                travel(m, s, coordinates, index + 1, i - 1, j);
            }
            if(j != 0) //if position isn't at left edge go left
            {
                travel(m, s, coordinates, index + 1, i, j - 1);
            }
            if(i != m.length - 1) //if position isn't at bottom go down
            {
                travel(m, s, coordinates, index + 1, i + 1, j);
            }
            if(j != m[i].length - 1) //if position isn't at right edge go right
            {
                travel(m, s, coordinates, index + 1, i, j + 1);
            }
        }
        
        return coordinates;
    }
    
    //checks to make sure coordinates give string equal to s
    public static boolean checkEqual(char[][] m, String s, ArrayList<String> list)
    {
        for(int i = 0; i < s.length(); i++)
        {
            if(s.charAt(i) != m[(int)list.get(i).charAt(1) - 48][(int)list.get(i).charAt(3) - 48])
            {
                return false;
            }
        }
        
        return true;
    }
}
