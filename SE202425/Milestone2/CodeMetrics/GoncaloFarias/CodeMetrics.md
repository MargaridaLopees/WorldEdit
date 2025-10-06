For Milestone 2, I decided to analyze the G. Ann Campbell Metrics Set and Chr. Clemens Lee Metrics Set

## Data
See attached `metrics.xlsx` file


# G. Ann Campbell Metrics Set

## Metrics
The metrics chosen for analysis were:
- <b>Cyclomatic Complexity of Classes (CCC)</b>: Measures the logical complexity of classes.
- <b>Cyclomatic Complexity of Methods (CCM)</b>: Measures the logical complexity of methods.

  - High values of CCC/CCC indicate code with many possible execution paths, making it harder to understand, test, and maintain.

## Trouble spots and related code smells
To identify problematic points and related code smells based on cyclomatic complexity metrics (CCC and CCM), I've analyzed the highest values in both metrics.

<b>Classes with High Cyclomatic Complexity (CCC):</b>
- Classes with high CCC values indicate multiple execution paths and, therefore, greater logical complexity. Examples of code smells here include e.g God Class
- Example: Classes with CCC above 100 should be analyzed to check if they can be split into smaller, more cohesive classes
- In this project the classes with higher CCC are: 
  - `com.sk89q.worldedit.EditSession` (value: 381)
  - `com.sk89q.worldedit.command.SelectionCommands` (value: 2022)
  - `com.sk89q.util.yaml.YAMLNode` (value: 104)
  - `com.sk89q.worldedit.bukkit.adapter.impl.v1_20_R4.PaperweightAdapter` (value: 100)
  - `com.sk89q.worldedit.bukkit.adapter.impl.v1_21.PaperweightAdapter` (value: 100)

<b>Methods with High Cyclomatic Complexity (CCM):</b>
- Methods with high CCM values have high control complexity, with many conditional branches or nested loops, making them difficult to understand and test. Common code smells are Long Method and Shotgun Surgery.
- Example: Methods with CCM above 50 are candidates for refactoring, potentially breaking them into smaller, more specific methods using techniques like Extract Method.
- In this project the methods with higher CCM are:
  - `com.sk89q.worldedit.command.SelectionCommands.trim(Actor, World, LocalSession, Mask)` (value: 140)
  - `com.sk89q.worldedit.EditSession.makeSphere(BlockVector3, Pattern, double, double, double, boolean)` (value: 68)
  - `com.sk89q.worldedit.extension.factory.parser.DefaultBlockParser.parseLogic(String, ParserContext)` (value: 64)
  - `com.sk89q.worldedit.extent.transform.BlockTransformExtent.transform(B, Transform)` (value: 54)


#  Chr. Clemens Lee Metrics Set

## Metrics
The metric I've chosen for this metric set, into the Class Metrics, was:
 - <b>Non-Commenting Source Statements (NCSS):</b> represents the number of lines of code that contain effective code statements, excluding comments and blank lines.
   - The NCSS metric provides an overview of code size and density, enabling the identification of large and complex classes or methods that may need refactoring. 
   - Code with high NCSS can be more challenging to maintain and modify. It highlights areas where the code may need to be divided into smaller, more manageable parts.
   
## Trouble spots and related code smells
To identify problematic points and related code smells based on Non-Commenting Source Statements (NCSS), I've analyzed the highest values of the metric.
- Although it does not directly measure logical complexity, a high NCSS value in a class or method often indicates potential complexity or multiple responsibilities, suggesting possible code smells like God Class or Long Method.
- If a class has a very high NCSS (such as hundreds of lines of executable code), it may suggest that the class is doing more than it should and could be violating the Single Responsibility Principle.
- According to the guidelines (applicable to classes):
  - Below 200 lines: Generally considered acceptable, especially if the class is well-structured and follows the Single Responsibility Principle.
  - Between 200 and 500 lines: Slightly high, depending on complexity. Classes in this range should be reviewed to check for multiple responsibilities.
  - Over 500 lines: Considered high. Classes with more than 500 lines are strong candidates for refactoring and may indicate God Class, Bloated Class, or other code smells, as they tend to have multiple responsibilities and high complexity.

- In this project the classes with higher NCSS are:
  - `com.sk89q.worldedit.EditSession` (value: 1266)
  - `com.sk89q.worldedit.bukkit.adapter.impl.v1_20_R4.PaperweightAdapter` (value: 489)
  - `com.sk89q.worldedit.bukkit.adapter.impl.v1_21.PaperweightAdapter` (value: 489)
  - `com.sk89q.worldedit.bukkit.adapter.impl.v1_20_R3.PaperweightAdapter` (value: 483)
  - `com.sk89q.worldedit.bukkit.adapter.impl.v1_20_R2.PaperweightAdapter` (value: 483)
  - `com.sk89q.worldedit.command.SelectionCommands`	(value: 410)
  