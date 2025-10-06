# Chidamber & Kemerer metrics 

The Chidamber & Kemerer metrics consists of 6 metrics calculated for each class:

## Code Metrics
### WMC (Weighted Methods Per Class)

- **Number of methods defined in class.**
- Helps estimate the time and effort needed to develop and maintain a class. 
- When a class has many methods, it can affect its subclasses more significantly, as these subclasses will inherit some or all of those methods from the base class.
- Common value for this metric is less than 12, casual - between 12 and 34, uncommon is greater than 34.

### DIT (Depth Inheritance Tree)

- **Maximum inheritance path from the class to the root class.**
- When a class is situated deep within an inheritance hierarchy, it tends to inherit a larger number of methods and variables, which increases its complexity. Such deep inheritance trees signal higher design complexity.
- Common value for this metric is less than 3, casual - between 3 and 4, uncommon is greater than 4.

### NOC (Number of Children)

- **Number of immediate subclasses of a class.**
- Measures the number of subclasses that will inherit the methods of a parent class. Provides an estimate of the level of reuse within an application. As NOC grows, so does the testing effort, since more subclasses mean greater responsibilities. Therefore, NOC reflects the testing effort required for the class and its potential for reuse.
- Common value for this metric is less than 2, casual - between 2 and 3, uncommon is greater than 3.

### CBO (Coupling between Object Classes) 

- **Number of classes to which a class is coupled.**
- Excessive coupling between object classes can negatively impact modular design and hinder reuse. The more independent a class is, the easier it is to reuse in other applications. To enhance modularity and ensure proper encapsulation, it's important to minimize inter-object class dependencies. A higher number of inter-class dependencies increases sensitivity to changes in other parts of the design, making maintenance more challenging.
- Common value for this metric is less than 14, casual - between 14 and 17, uncommon is greater than 17.

### RFC (Response For Class)
- **Total number of methods that can potentially be executed in response to a message received by an object of a class.**
- Classes with a high RFC (Response for a Class) are typically more intricate and challenging to comprehend. This complexity complicates testing and debugging efforts. Having an estimate of the worst-case scenario for possible responses can help allocate testing time more effectively.
- The threshold value of this metric is 44.

### LCOM (Lack of Cohesion of Methods)

- This measure evaluates the number of method pairs in a class that are not connected by any shared instance variables, indicating independent sections with no cohesion. It calculates the difference between the number of method pairs that do not share instance variables and those that do. This metric helps identify how well the methods within a class work together, with higher values suggesting lower cohesion and potentially poorer design.

## Data

The data was extracted from the `TreeMetrics` extension. 
For simplicity, only the classes from the `Sponge`, `Session`, 
`Scripting`, and `Regions` packages were considered.
