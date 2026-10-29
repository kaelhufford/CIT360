# Java Sorting Performance Analysis

This project compares the measured performance of five sorting algorithms on randomly generated integer arrays:

- Bubble Sort
- Selection Sort
- Insertion Sort
- Quick Sort
- Merge Sort

The benchmark tests array sizes from 0 through 200,000 in increments of 100. Each algorithm receives a copy of the same random input array. Five sorting tasks run concurrently through a fixed thread pool, and their execution times are recorded in milliseconds.

## Project Files

- `src/Activity0/AssignmentZero.java` - benchmark driver, multithreading, timing, and CSV export
- `src/Activity0/ArrayOperations.java` - shared array helpers and simple sorting algorithms
- `src/Activity0/QuickSort.java` - Quick Sort implementation
- `src/Activity0/MergeSort.java` - Merge Sort implementation
- `data/Activity0/SortsTiming.csv` - raw benchmark results
- `KaelHufford_Assignment0.pdf` - analysis report and chart

## Running the Benchmark

From the repository root (source and data paths above are relative to that root):

```powershell
New-Item -ItemType Directory -Force build/classes | Out-Null
javac -d build/classes src/Activity0/*.java
java -cp build/classes Activity0.AssignmentZero
```

The program writes `SortsTiming.csv` to the current user's Downloads folder.

## Notes

Bubble Sort, Selection Sort, and Insertion Sort show approximately quadratic growth. Quick Sort and Merge Sort scale closer to `O(n log n)`. Because the algorithms run concurrently, individual measurements include CPU scheduling and resource-contention noise.
