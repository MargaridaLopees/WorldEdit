# Collected Metrics: Li-Henry

This set was developed by two authors, Wei Li and Sallie Henry, who developed a set of metrics that, 
dedicated to object-oriented code, mainly address the complexity and coupling of the class.

## DAC

The metric which measures the coupling complexity caused by ADTs is DAC (Data Abstraction Coupling). This metric is calculated as follows:
DAC = number of ADTs defined in a class. 
The number of variables having an ADT type may indicate the number of the data structures dependent upon the definitions of other 
classes. The more ADTs a class has, the more complex the coupling is of that class with other classes.

## MPC

Also known as message passing coupling, is used to measure the complexity of message passing among classes in this research. 
Since the pattern of the message is defined by a class and used by objects of the class, the MPC metric also gives an indication 
of how many messages are passed among objects of the classes, calculated as follows:
MPC= number of send-statements defined in a class

## SIZE2

The second size metric used in the research is the number of properties (including the attributes and methods) defined in a class. 
This size metric is referred to as SIZE2 and is calculated as follows:
SIZE2 = number of attributes + number of local methods.

**References**: Li, W., & Henry, S. M. (1993). Computer Science Technical Reports - Object-Oriented Metrics 
Which Predict Maintainability. Vt.edu. http://eprints.cs.vt.edu/archive/00000347/

# Identification of potential trouble spots in the codebase and relation to the identified code smells

## SIZE2: 

In the SIZE2 metric, high values may indicate that the class is too large and complex, containing many methods and attributes, 
contributing to an increase in coupling and dependencies. In this way, it can be associated with the code smell **God Class**,
**Large Class** or even **Divergent class**. (values around 400 in some packages.) 
Example:com.sk89q.worldedit.bukkit.adapter.impl.v1_20_R4.PaperweightFakePlayer

## MPC:

In this case, in the range of 100, values of this type may indicate that there is a high coupling complexity, so that 
several method calls to other methods and/or classes may be occurring. Some code smells such as **Feature Envy**, 
**Inappropriate Intimacy** may be associated with this problem.
Example:com.sk89q.worldedit.bukkit.adapter.impl.v1_20_R3.PaperweightDataConverters.DataInspectorBlockEntity

## DAC:

In the DAC,  with values around 200s, classes may contain problems such as high coupling and others similar to the 
metric discussed previously.
Example: com.sk89q.worldedit.EditSession 

