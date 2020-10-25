# Pre-requisites
**Java 11 (Long Term Support) JDK** needs to be installed on your system.

For installation details please check - https://adoptopenjdk.net

# Running the playground samples
## Sum of pairs
Please use the following gradle command to run the SumOfPairs main class in the project root folder:

`./gradlew sumOfPairs` - for Unix based systems

`gradlew.bat sumOfPairs` - for Windows

On the first line please provide the desired sum.

On subsequent lines please provide a list of unique integers.

To proceed to computing number of pairs that add to given sum please type `exit` in the console prompt.

**Disclaimer** - two implementations have been provided for SumOfPairs, the first one with `O(n^2)` complexity, followed by an `O(n)` complexity implementation.
This has been done to showcase the difference in nanoseconds between the two implementations and why it's always good to think of potential optimisations.

### Sample execution:
```
Top stop type - exit
Please enter the desired sum: 
6
Please enter array elements one line at a time: 
1
2
3
4
5
6
C
Non integer values are ignored: C
D
Non integer values are ignored: D
exit
Given sum: 6
Given array: [1, 2, 3, 4, 5, 6]
Inefficient algorithm result: 2
Total execution time O(n^2) complexity in nanoseconds is:23868210
Optimised algorithm result: 2
Total execution time O(n) complexity in nanoseconds is: 1315583
O(n) algorithm was faster by 22552627 nanoseconds
```
 
## Polish notation expressions
Please use the following gradle command to run the PolishNotation main class in the project root folder:

`./gradlew polishNotations` - for Unix based systems

`gradlew.bat polishNotations` - for Windows

Please enter expressions written in polish notation on separate lines.

To proceed to expression evaluation one must type `exit` in the console prompt. 

**Disclaimer** - division by 0 will return `error` even though using `Double` offers protection from potential issues by returnint `Infinity`

### Sample execution:
```
Top stop type - exit
Please enter the expressions in polish notation on separate lines
+ 1 1
2 2 +
2 + 2
/ 1 0
exit
Given expressions: [+ 1 1, 2 2 +, 2 + 2, / 1 0]
2.00
error
error
error
Total execution time in nanoseconds is: 19802126
```

## Polish notation expressions as a REST service
Please use the following gradle command to run the PolishNotation Spring Boot app in the project root folder:

`./gradlew bootRun` - for Unix based systems

`gradlew.bat bootRun` - for Windows

### Sample Input

### Sample Output

### Implemented features

### Running tests

### Disclaimer