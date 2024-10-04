package org.example;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Solution solution=new Solution();

        int arr[]={1,2,3,4,5,6};

        System.out.println(solution.dividePlayers(arr));
    }
}

class Solution {
    public long dividePlayers(int[] skill) {
        Arrays.sort(skill);

        int total=0,n=skill.length;
        long pair=skill[0]+skill[skill.length-1];

        System.out.println("pair: "+pair);
        for(int i=0;i< n/2;i++){

            System.out.println(i+" pair "+(n-i-1)+" "+(skill[i]+skill[n-i-1]));
            if(skill[i]+skill[n-i-1]!=pair) return -1;
            else {
                total+=skill[i]*skill[n-i-1];
                System.out.println("total: "+total);
            }
        }
        return total;
    }
}