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
