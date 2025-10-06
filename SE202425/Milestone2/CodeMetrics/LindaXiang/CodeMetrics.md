# Code Metrics

### Metric set colected: Lozenz-Kidd metric set

This metric set is used to evaluate the quality of object-oriented software provided by Lorenz & Kidds, it constitues in four categories:internal, external,
inheritance and size. 
It focuses on different aspects of object-oriented design: cohesion within classes, reusability across classes, efficient inheritance through class hierarchies, and measuring class size and complexity.

reference:https://www.ijcsit.com/docs/Volume%207/vol7issue3/ijcsit2016070333.pdf

**NOA:**
Number of attributes - this metric measures the total number of attributes defined within a class,it reflects the complexity and degree of state information contained by the class.Higher NOA indicates a more complex class, which may impact the reading difficulty.

**NOO:**
Number of operations-this metric measures the total number of methods defined within a class. Higher NOO means a feature-rich class, but also affects the class's maintainability and understandability.

**NOOM:**
Number of overttidden methods-this metric measures the total number of redefined methods defined within a class.This metric measures how much a subclass customizes, which can impact the flexibility and complexity of the class hierarchy.Higher NOOM means higher complexity.

### Identification of potential trouble spots in the codebase

**NOA**-High number of attributes can lead to Long Method or Feature Envy. Eg: com.sk89q.worldedit.bukkit.adapter.impl.v1_20_R4.PaperweightFakePlayer com valor 441

**NOO**-High number of operations can lead to Large class smells.Eg: com.sk89q.worldedit.bukkit.adapter.impl.v1_21.PaperweightFakePlayer com 1342.

**NOOM**-High number of overridden operations can lead to refused bequest smells.Eg: com.sk89q.worldedit.fabric.FabricWorld com 23.




