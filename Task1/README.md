# Readme

## Goals
General Description   
Implement your task and push to the public git repository. Share the link with the mentor. Create a simple java class that will read data from the console and will print results to console.   

Task 3 (Array Couples)  
Have the function ArrayChallenge(arr) take the arr parameter being passed which will be an array of an even number of positive integers, and determine if each pair of integers, [k, k+1], [k+2, k+3], etc. in the array has a corresponding reversed pair somewhere else in the array. For example: if arr is [4, 5, 1, 4, 5, 4, 4, 1] then your program should output the string yes because the first pair 4, 5 has the reversed pair 5, 4 in the array, and the next pair, 1, 4 has the reversed pair 4, 1 in the array as well. But if the array doesn't contain all pairs with their reversed pairs, then your program should output a string of the integer pairs that are incorrect, in the order that they appear in the array.  
For example: if arr is [6, 2, 2, 6, 5, 14, 14, 1] then your program should output the string 5,14,14,1 with only a comma separating the integers.  
Examples  
Input: [2,1,1,2,3,3]  
Output: 3,3  
  
  
Input: [5,4,6,7,7,6,4,5]  
Output: yes

## Array Input Instructions

Please follow these instructions when entering the data:

1. Ensure that the input array is not null. If you provided an empty or null array, please rerun the application and provide a valid array.

2. Enter a comma-separated list of positive integers. For example: `4, 5, 1, 4, 5, 4, 4, 1`.

3. The array must contain at least 2 pairs of numbers. If your array does not meet this requirement, please rerun the application and provide a valid array with at least 2 pairs of numbers.

4. Make sure the input array contains an even quantity of numbers. If the array contains an odd quantity of numbers, please rerun the application and provide an array with an even quantity of numbers.

5. The input array must contain only positive numbers. Ensure that all the numbers in the array are positive integers. If any negative numbers or non-integer values are present, please rerun the application and provide an array with only positive integers.
