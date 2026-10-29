# CIT360 coursework

Java coursework, data structures, algorithms, and assignment materials.

## Repository layout

| Directory | Contents |
| --- | --- |
| `src/Activity0/` | Sorting implementations and benchmark driver |
| `src/Activity2/` | Maze and maze-solving assignment |
| `src/AbstractDataTypes/` | Linked lists, stacks, trees, heaps, and hash table |
| `src/Queues/` | Queue implementation, interface, and demo |
| `src/` | Main sorting demo and recursion demo |
| `tests/Test/` | Existing JUnit linked-list tests |
| `tests/HashTest.java` | Hash-table tests |
| `tests/RegressionTest.java` | Regression tests for arrays, recursion, list searches, HTML tags, and BFS |
| `docs/` | Assignment instructions, reports, and activity notes |
| `data/` | Saved benchmark results |
| `submissions/` | Local original submission archives |
| `lib/` | Local JUnit and Hamcrest dependencies |
| `build/` | Generated classes (ignored by Git) |

Java package names are preserved. `src/` is the source root and `tests/` is
the test source root. Tree and hash examples, `Main`, and `RecursionDemo`
currently use Java's default package.

## Build and run

Install a JDK with `java` and `javac` on PATH. From the repository root:

```powershell
New-Item -ItemType Directory -Force build/classes | Out-Null
$sources = Get-ChildItem src -Recurse -Filter *.java | ForEach-Object FullName
javac -d build/classes $sources
java -cp build/classes Main
java -cp build/classes RecursionDemo
java -cp build/classes Queues.QueueDemo
java -cp build/classes Activity2.MazeDemo
java -cp build/classes AbstractDataTypes.LinkedLists.LinkedListDemoDriver
java -cp build/classes HeapDemo
```

## Tests

```powershell
New-Item -ItemType Directory -Force build/test-classes | Out-Null
$tests = Get-ChildItem tests -Recurse -Filter *.java | ForEach-Object FullName
javac -cp "build/classes;lib/*" -d build/test-classes $tests
java -cp "build/classes;build/test-classes;lib/*" org.junit.runner.JUnitCore Test.LinkedListTest RegressionTest HashTest
```

After building the sources, this compiles and runs the existing JUnit 4 tests on Windows. See [lib/README.md](lib/README.md)
for required local libraries. All 20 tests currently pass.

Hash-table insertion returns `false` for an existing key and preserves its value.
Removal requires both key and value to match.

`HtmlValidator` checks explicit tag nesting, attributes, void tags, comments,
and self-closing tags. It is not a full HTML5 parser; optional closing tags,
script/style raw text, and full attribute syntax validation are outside its scope.

## Assignment materials

- [Activity 0: sorting benchmark](docs/Activity0/README.md)
- [Activity 0 report](docs/Activity0/KaelHufford_Assignment0.pdf)
- [Activity 0 saved results](data/Activity0/SortsTiming.csv)
- [Activity 2 instructions](docs/Activity2/AssignmentDoc.pdf)

The sorting benchmark can be expensive to run and writes new results to the
current user's Downloads directory. The saved CSV in `data/` is kept separately.
