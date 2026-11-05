# Submission cleanup — 2026-11-01

Changes apply only to submissions/Activity2; src/ was not modified.

Removed unused files:
- Queues/QueueDemo.java: standalone queue demonstration.
- Stacks/HtmlValidator.java: unrelated HTML exercise.
- Stacks/KaelHuffordStack.zip: unrelated nested submission archive.
- Stacks/StackADemo.java: standalone stack demonstration.
- Stacks/StackL.java and Stacks/StackLDemo.java: unused linked-list variant;
  removing these also removes the need for the absent LinkedList dependency.

Kept Stack.java, StackI.java, QueueA.java, and QueueI.java, required by MazeSolver.
Corrected visited/waiting/whether/obstacles and stack-comment spelling.
Replaced outdated maze planning pseudocode with an implementation description.
Clarified border generation and finish-selection comments.
Corrected punctuation in QueueA's invalid-capacity exception message.
Added README.md with independent build/run instructions and algorithm notes.
Traversal algorithms, packages, public APIs, and maze generation are unchanged.

Validation results are recorded below after checking the cleaned submission.
- Compiled all eight retained Java sources together using only this submission.
- Ran Activity2.MazeDemo successfully; both DFS and BFS displayed results.
- Compiled classes were kept outside the submission folder.
