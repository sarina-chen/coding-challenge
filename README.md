# Coding Challenge Questions - SChen

## Outline & Design Choice:
For my solution, I am using a hash map since it provides a quick, constant lookup time. I converted each word into an alphabetically sorted char array. This char array is then converted back to a string and used as the key to the hash map. Each key is mapped to an array list of anagrams that are sorted lexicographically. All the preprocessing are done at the offline step to allow for quick runtime at the online step.


## Note:
* n = the number of words in the dictionary file
* m = the length of a word


## 1.
#### Offline step:
In this step, each word in the dictionary file is looped through to generate a sorted char array that is used as the hash key, and mapped into a hash map. The runtime here is O(n) for the loop.

The runtime for the java function Arrays.sort() used in this step is O(mlogm). According to documentation, a mergesort is used for this function.

To keeping the anagrams list in lexicographic order, the java function Collection.binarySearch() is also used in this step and has a runtime of O(logn), since it is a binary search.  In the worst case where all words in the dictionary are anagrams, this binarySearch function will need to be run every time.

Thus the overall runtime for the offline step should be n*(mlogm + logn), if we assume that the length of words will be much smaller than the amount of words we have in the dictionary file, we can see “mlogm” as a constant and the runtime becomes O(nlogn)


#### Online step:
In this step, the word again need to be converted into a sorted char array, so the runtime for the java function Arrays.sort() is O(mlogm). The lookup step has a runtime of O(1) since a hash map is used. Since all anagrams need to be printed, the worse case runtime here would be O(n) in the case where all words in the dictionary file are anagrams to each other.

The overall runtime for the online step is O(mlogm + n) = O(n), again assuming “mlogm” is constant.


## 2.
My program uses a HashMap of String to ArrayList for storing the key value mappings of anagrams. This will have a space complexity of O(n), since each word would be stored no more than 2 times, once as the key and once as the value in the array list.


## 3.
We can divide up the dictionary file into smaller sub files that contain words with a range of length.
For example:
* subDictionary[0] ==> contain words length 0..25
* subDictionary[1] ==> contain words length 26..50
* subDictionary[2] ==> contain words length 51..75
* subDictionary[3] ==> contain words length 76..100

We can then determine which subDictionary file that we need to preprocess based on the length of the input word, since anagrams must exist with equal length.
