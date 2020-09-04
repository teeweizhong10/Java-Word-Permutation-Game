# Java-Word-Permutation-Game
A Java Word Permutation Game using Breadth First Search

Author: Wei Zhong Tee 
Last Updated: 13 May 2020
Project type: Java Command Line Game

This game takes two words of equal length and sees if it can permute the source word into a the destination word in a certain number of hops. Each hop, the word is only allowed to alter one letter at a time. For example: The word 'cat' can be altered into the word 'dog' in 3 hops: cat, cot, dot, dog. In each step only one letter can change. The word permutations are done using a Breadth-First Seaerch using the source word as the root of a tree and the destination as the goal. 

The java files must be extracted fromt the zip file and placed into the same folder.
Game.java, HashChain.java, HashNode.java, List.java, Node.java, Queue.java, QueueList.java, SLList.java, TNode.java, and dictionaryWords.txt are the files that must be placed in the same folder.

The text file is passed in after compiling and running Game.java (driver) or *.java(to compile all the java files).
The first argument is the original word and the second argument is the goal word you want it to permutate into. The third argument is how many hops it is allowed to make.
Eg: > means command line input
> javac Game.java
> java Game [originalWord] [goalWord] [numberOfHops]

or
> javac *.java
> java Game [originalWord] [goalWord] [numberOfHops]

Insufficient arguments or passing in arguments of the wrong type will not make the program run.

Decisions I made:
1. The TNodes automatically set their depth based on the parents depth (it is incremented).
2. The BFS implementation is in the main method.

Issues I faced:
1. I initally did not store the depth in the TNode and it was based on how many parents the goal word had.
2. The program was getting to the goal word based on the first word permutation generated.

Solutions:
1. The TNode sets its depth value based on its parent's depth.
2. The queue was not running the way it was supposed to and I fixed it by reposition the method that creates a new word permutation when the goal is not met.
