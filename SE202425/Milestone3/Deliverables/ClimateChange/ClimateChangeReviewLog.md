# Review by Linda 65720 & Mariana 66025

# Brush Commands Class:
## Public methods:
*climateChangeBrush*: Nothing to add. Seems simple and well organised.

## Private methods:
*isValidIntensity* and *fillWater*: Seems simple and straightforward. 

*hotMode*: 
<br> 1) Contains magic numbers, which is not good if someone wants to change the value in the future. They should probably be kept in a final static constant.
<br> 2) Repetitive code in the if clauses. The class is creating the object Paint and calling setOperationBasedBrush() 3 times in row. 
It could be simplified by creating only the block type object in the if clauses, calling the method only one time by the end of it.
<br> 3) Besides the previous points, the code seems solid and well organised. It were created auxiliary methods that contributed
to reduce the code smells.

*coldMode*:
<br> 1) Contains magic numbers as well.
<br> 3) Besides the previous point, the code seems good.

*dryMode*:
<br> 1) Nothing to add. 

*wetMode*:
<br> 1) Contains magic numbers in the if clauses and before.
<br> 2) Repetitive code in the if clauses. The class is creating the object Paint and calling fillWater() 3 times in row.
It could be simplified by creating only the variable (amount) in the if clauses, calling the method only one time by the end of it.
<br> 3) Besides the previous points, the code seems simple and well-structured.

In general, besides the points mentioned earlier, the code seems organised, even though the isValidIntensity() method call
could be in the main method instead of the private ones.

# FreezeBrush:
Seems organised, generally simple and well-planned. Only the magic numbers could be improved.

# DesertBrush:
Could be improved by extracting methods, reducing cycles, if possible, or changing its complexity, as it becomes a little confusing in some parts of the code.

# --------------------------------------------------------------------------------------------------------

# Use Cases:

## Desertification/Flooding/Extreme heatwaves and wildfires/Extreme cold waves

The Use Cases are well-structured and detailed. Although our team is not in agree with 
the existence of the secondary actor. From our point of view, the Permission Manager should 
not be an actor.

*use case diagram*

The Use Case diagram covers all the use cases described. 

# Class Diagram:
The class diagrams are remarkably elaborate.

# Sequence Diagrams:
In general the sequence diagrams seem clear and objective.


THE END :)