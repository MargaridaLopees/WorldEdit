# Halstead Metrics
The Halstead complexity metric measures software complexity statically by analyzing source code, breaking it into tokens, and counting operators and operands without running the program.
## Data

See attached `metrics.ods` file.

## Metrics

- Number of distinct operators: n1
- Number of distinct operands: n2
- Total number of operators: N1
- Total number of operands: N2
- Halstead Vocabulary: n = n1 + n2
- Halstead Length: N = N1 + N2
- Halstead Difficulty: D = (n1 / 2) * (N2 / n2)
- Halstead Volume: V = N * log2(n)
- Halstead Effort: E = V * D
- Halstead Errors: Er = ( E ^ (2/3) ) / 3000

The collected metrics are:

- **Package Average Halstead Difficulty (PAHD)** : assesses the complexity of the code based on the ratio of operators to operands.
- **Package Average Halstead Length (PACHL)**: total number of operators and operands in the code.
- **Package Average Halstead Volume (PAHVL)**: measures the size of the code, reflecting how much information is required to understand it.
- **Package Average Halstead Effort (PACHEF)**: calculates the mental effort required to write or understand the code, combining both volume and difficulty.
- **Package Average Halstead Error (PACHER)**: estimates the potential number of errors in the code based on the effort metric.
- **Package Average Halstead Vocabulary (PACHVC)**: measures the number of unique operators and operands in the code.

## Trouble spots and related code smells
Out of all the project's packages, these were some of the more relevant cases to study the code base.

1. According to the Halstead Effort metric, the package `com.sk89q.worldedit` is the most cumbersome to write and understand code. It has also the highest Halstead Error metric and in general is one of the highest for the rest of the metrics (7799 total and 1610 distinct operators & operands) . Having such amount of classes, methods, operators and operands clumped together can be prone to errors. This is most likely where the project started and by virtue of each update the solutions to the problems it was tasked to resolve compounded in this package. It is a common occurrence in projects of this size though it could be improved and most importantly it can be avoided or at the very least mitigated. Without a deeper examination, we can still derive the high probability of code smells such as God Classes. By breaking down these into smaller, more manageable classes, each with a single responsibility (Single Responsibility Principle) to ensure each class does one thing well.
2. `com.sk89q.worldedit.command` is the package with the highest Halstead Difficulty, Length (11137 total operators & operands) and Vocabulary (4177 distinct ones) metrics. Unsurprisingly it also has a very high Halstead Effort, Error and Volume metrics. The high amount and variety of commands make this one of the more troublesome packages to work with, though highly necessary. The high Difficulty and Length suggest that methods within this package might be overly complex with too many operations. Applying Extract Method Refactoring to break down large methods into smaller ones that are easier to understand and maintain would be a viable fix.
3. The next 2 packages are very interesting. Both `com.sk89q.worldedit.world.block` and `com.sk89q.worldedit.world.item` display similar metric numbers. Both have high Volume and Length (as expected since there’s many blocks and items) and moderate Vocabulary, Effort and Error metrics. We already see a distinction from the previous packages, since the high Volume and Length are not so interconnected to the other ones like we saw previously. It can be said blocks and items don’t have as much room to be as complex as commands though still require some sizeable code and operands, and the numbers tell a similar story. This can also point to a better designed code. The com.sk89q.worldedit.world.item in particular had a very low Difficulty metric. Both having high Volume and Length metrics could indicate Large Methods and possibly Data Clumps. Creating an object for use as reference could help to manage related data more effectively.