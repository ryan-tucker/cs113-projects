# CS113-HW07-QueueAndRecursion
## HW #7 for CS113 - PrinterQueue and Recursion Problems

[![Build Status](https://travis-ci.com/MiraCostaCS-Nery/cs113-hw7-queueandrecrusion-ryan-tucker.svg?token=QzQgffEoThyygXgUJKxa&branch=master)](https://travis-ci.com/MiraCostaCS-Nery/cs113-hw7-queueandrecrusion-ryan-tucker)

This homework consists of two projects. Make sure to make a package for each, `edu.miracosta.cs113.printerQueue` and `edu.miracosta.cs113.change`, respectively.


## Printer Queues (Programming Project #5, pg. 240):
An operating system assigns jobs to print queues based on the number of pages to be printed (less than 10 pages, less than 20 pages, or more than 20 pages). You may assume that the system printers are able to print 10 pages per minute. Smaller print jobs are printed before larger print jobs, and print jobs of the same priority are queued up in the order in which they are received. The system administrator would like to compare the time required to process a set of print jobs using one, two, or three system printers.

Write a program to simulate processing 100 print jobs of varying lengths using one, two, or three printers. Assume that a print request is made every minute and that the number of pages to print varies from 1 to 50 pages.

The output from your program should indicate the order in which the jobs were received, the order in which they were printed, and the time required to process the set of print jobs. If more than one printer is being used, indicate which printer each job was printed on.

**_NOTE:_** _You may use any Queue you’ve implemented, or the built in `Queue` interface with a `LinkedList` instantiation to accomplish the above project. The definition of the ADT/s described above are open to your interpretation, so make sure to **provide JUnit tests** for all classes (except your driver)._


## [Recursive] Change Calculator (Programming Project #7, pg. 291):

Using the provided ChangeCalculator class, implement the recursive method `calculateChange(int)` which will dispense change for a given amount of money. The method will **display and return** the total number of combinations of quarters, dimes, nickels, and pennies that equal the desired amount and all of the combinations as well. Avoid duplication. 

If you choose to use a data structure, it must be one that we've covered and you must thoroughly justify why it was the best choice: I chose to use an ArrayList to hold all of the valid combinations of coins (stored in Strings). I chose to do this because it allows me to use the Strings for both output to console, as well as output to to a file. An ArrayList seemed like the best choice because theres no way to know beforehand how many combinations there will be, so a regular array of type String wouldn't work. Another option would have been a linked list, however I think both ArrayList and LinkedList would have been valid options.

Next, you will also implemenet the method `printCombinationsToFile(int)`, which should contain a call to the recursive solution that you will create. It should open a text file in the program's directory named `"CoinCombinations.txt"`, and write each combination produced into their own separate lines. This will be used by the tester class to verify that your recursive solution avoids duplicate values. 

**_NOTES:_** _Your program should dispense the highest coin first (quarters, then dimes, then nickels, then pennies). The format for printing each combination is otherwise up to you- as long as the output is consistent. Valid Strings include "1 quarter/s, 2 dime/s, 3 nickel/s, 4 penny/ies", "1Q 2D 3N 4P", and "[1, 2, 3, 4]". For example, the generated text file would then contain the following contents, where the given input is 10 cents:_

`CoinCombinations.txt`

``` 
[0, 1, 0, 0]
[0, 0, 2, 0]
[0, 0, 1, 5]
[0, 0, 0, 10]

``` 

_The two methods will be tested using 5 cent increments between 5 cents and 30 cents, larger tests for 75 cents through 100, and values that are not multiples of 5 (3 through 101 cents). For example, 75 cents equates to 121 unique combinations._


----------


### Make sure to commit + push *before* the deadline to have your code be considered for grading.



>Pro-Tips:

>- Refer to the given documentation in the ChangeCalculator class as you plan and design your implementation of its two methods. 
