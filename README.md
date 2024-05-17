# Puzzles-MaxProfit

Given a log of stock prices compute the maximum possible earning.

## Background

The lesson task suggests that the max profit question fits the maximum slice problem.

### Maximum Slice Problem

Let's define a problem relating to maximum slices. You are given a sequence of *n* integers
*a*<sub>0</sub>, *a*<sub>1</sub>, ..., *a*<sub>*n*-1</sub> and the task is to find the
slice with the largest sum. More precisely, we are looking for two indices *p*, *q* such
that the total *a*<sub>p</sub> + *a*<sub>*p*+1</sub> + ... + a<sub>q</sub> is maximal.
We assume that the slice can be empty and its sum equals 0.

*a*<sub>0</sub> = 5

*a*<sub>1</sub> = -7

*a*<sub>2</sub> = 3 ****

*a*<sub>3</sub> = 5 ****

*a*<sub>4</sub> = -2 ****

*a*<sub>5</sub> = 4 ****

*a*<sub>6</sub> = -1

In the picture, the slice with the largest sum is highlighted in asterisks. The sum of this slice
equals 10 and there is no slice with a larger sum. Notice that the slice we are looking for
may contain negative numbers, as shown above.

#### Solution with *O*(*n*<sup>3</sup>)

The simplest approach is to analyze all the slices and choose the one with the largest sum.

```
def slow_max_slice(A):
    n = len(A)
    result = 0
    for p in xrange(n):
        for q in xrange(p, n):
            sum = 0
            for i in xrange(p, q + 1):
                sum += A[i]
            result = max(result, sum)
    return result
```

Analyzing all possible slices requires *O*(*n*<sup>2</sup>) time complexity,
and for each of them we compute the total in *O*(*n*) time complexity.
It is the most straight forward solution, however it is far from optimal.

#### Solution with *O*(*n*<sup>2</sup>)

We can easily improve on our last solution. Assume that we know the sum
of slice (p, q), s = *a*<sub>*p*</sub> + *a*<sub>*p+1*</sub> + *a*<sub>*q*</sub>.
The sum of the slice with one more element (*p*, *q + 1*) equals *s* + *a*<sub>*q + 1*</sub>.
Following this observation, there is no need to compute the sum each time
from the beginning; we can use the previously calculated sum.

```
def quadratic_max_slice(A):
    n = len(A)
    result = 0
    for p in xrange(n):
        sum = 0
        for q in xrange(p, n):
            sum += A[q]
            result = max(result, sum)
    return result
```

#### Solution with *O*(*n*)

This problem can be solved even faster. For each position, we compute the largest
sum that ends with that position. If we assume that the maximum sum of a slice
ending in position *i* equals *max_ending*, then the maximum slice ending
in position *i* + 1 equals max(0, *max_ending* + *a*<sub>*i+1*</sub>).

```
def golden_max_slice(A):
    max_ending = max_slice = 0
    for a in A:
        max_ending = max(0, max_ending + a)
        max_slice = max(max_slice, max_ending)
    return result
```

This time, the fastest algorithm is the one with the simplest implementation,
however it is conceptually more difficult. We have used here a very popular
and important technique. Based on the solution for shorter sequences,
we can find the solution for longer sequences.

### The Kadane Algorithm

#### Definition

See (wikipedia of maximum subarray problem)[https://en.wikipedia.org/wiki/Maximum_subarray_problem].

Brief timeline of the  maximum subarray problem.

- Proposed by Ulf Grenander was an algorithm that was *O*(*n*<sup>3</sup>) in 1977.
- Michael Shamos improved this with a divide-and-conquer algorithm that was *O*(*n*<sup>3</sup>) in the early 1980's.
- Jay Kadane improved this further with the algorithm above that was *O*(*n*) in the early 1980's.
- David Gries obtained the same *O*(*n*)-time algorithm by applying Dijkstra's "standard strategy" in 1982.
- Richard Bird derived it by purely algebraic manipulation of the brute-forc algorithm using Bird-Meertens formalism in 1989.

Accomplishments of contributors.

- Ulf Grenander is a Ph.D. statistician and professor of applied mathematics.
- Michael Shamos is a Ph.D. research fellow and mathematician.
- Jay Kadane is a Ph.D. professor of statistics.
- David Gries is a Ph.D. computer scientist and professor.
- Richard Bird is a Supernumerary Fellow of Computation at Lincoln College, University of Oxford.

The Codility team finally told on themselves with the following quote from the lesson task.

"We have used here a very popular and important technique."

This is the only clue they reveal that something very specific is happening in this lesson task.

They are painting the "golden" answer as the preferred answer anyone should get.

However, it is an answer that is the product of years of research and papers by Ph.D fellows.

There is another layer.

The Kadane Algorithm is one of **hundreds** of algorithms from the research world
that are consolidated and learned by followers of competitive programming.
The competitive programmers do not create the algorithms.
The competitive programmers are adept at matching existing algorithms to coding problems.

This is when I had the realization of just how unscrupulous companies are that use Codility.

They will claim that they just want to get a sense of your coding style,
and how you think. However, when they choose a lesson task that is clearly,
to the informed eye, meant to utilize a specific algorithm, like the Kadane
algorithm, then it is obvious they are telling everyone that is neither
a Ph.D computer scientist nor a competitive programmer to fuck off.
The probability that everybody else will have an epiphany in the 100-minute
coding test time and replicate the expected algorithm is very small. This
is especially true for anyone who has never heard of this algorithm and
does not memorize competitive programming algorithms. This is something
that is simply not used in the workplace.

#### Exercising the Kadane Algorithm by hand

We will use the same input array as the codility lesson task.

*a*<sub>0</sub> = 5

*a*<sub>1</sub> = -7

*a*<sub>2</sub> = 3 ****

*a*<sub>3</sub> = 5 ****

*a*<sub>4</sub> = -2 ****

*a*<sub>5</sub> = 4 ****

*a*<sub>6</sub> = -1

Initialize max_ending and max_slice to 0.

For i = 0, a[0] = 5
- max_ending = maximum of 0 or max_ending + (5) = 5
- max_slice = maxiumum of max_slice or max_ending = 5
- the maximum subarray is {a[0]}, range of i = {0}

For i = 1, a[1] = -7
- max_ending = maximum of 0 or max_ending + (-7) = 0
- max_slice = maximum of max_slice or max_ending = 5
- the maximum subarray is still {a[0]}, range of i = {0}
- however the index of of max_ending is implicitly reset to start at j = 2

For i = 2, a[2] = 3
- max_ending = maximum of 0 or max_ending + (3) = 3
- max_slice = maximum of max_slice or max_ending = 5
- the maximum subarray is still {a[0]}, range of i = {0}
- the max_ending subarray is implicitly now {a[2]}, range of j = {2}

For i = 3, a[3] = 5
- max_ending = maximum of 0 or max_ending + 5 = 8
- max_slice = maximum of max_slice or max_ending = 8
- the maximum subarray is implicitly now {a[2], a[3]}, range of i = {2, 3}
- the max_ending subarray is implicitly now {a[2], a[3]}, range of i = {2, 3}

For i = 4, a[4] = -2
- max_ending = maximum of 0 or max_ending + (-2) = 6
- max_slice = maximum of max_slice or max_ending = 8
- the maximum subarray is still implicitly {a[2], a[3]}, range of i = {2, 3}
- the max_ending subarray is implicitly now {a[2], a[3], a[4]}, range of i = {2, 3, 4}

For i = 5, a[5] = 4
- max_ending = maximum of 0 or max_ending + (4) = 10
- max_slice = maximum of max_slice or max_ending = 10
- the maximum subarray is implicitly now {a[2], a[3], a[4], a[5]}, range of i = {2, 3, 4, 5}
- the max_ending subarray is implicitly now {a[2], a[3], a[4], a[5]}, range of i = {2, 3, 4, 5}

For i = 6, a[6] = -1
- max_ending = maximum of 0 or max_ending + (-1) = 9
- max_slice = maximum of max_slice or max_ending = 10
- the maximum subarray is implicitly now {a[2], a[3], a[4], a[5]}, range of i = {2, 3, 4, 5}
- the max_ending subarray is implicitly now {a[2], a[3], a[4], a[5], a[6]}, range of i = {2, 3, 4, 5, 6}

The returned result (max_slice) is 10.
