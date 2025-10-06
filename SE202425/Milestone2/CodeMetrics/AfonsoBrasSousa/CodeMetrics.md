# Robert C. Martin Metrics

For the Milestone 2 report I have decided to look into the Robert C. Martin metric set, also known as the Martin metrics or Uncle Bob's metrics.

## Data

See attached `metrics.ods` file.

## Metrics

The collected metrics are:

- **Abstractness** (A): The ratio of abstract classes and interfaces in a package to the total number of classes in the package.

- **Afferent Coupling** (Ca): The number of external classes that depend on classes of a package.

- **Efferent Coupling** (Ce): The number of classes of a package that depend on external classes.

- **Instability** (I): The ratio between the efferent coupling and the total coupling of classes in a package. A measure of the package's resilience to change. I = Ce / (Ca + Ce)

- **Distance from the Main Sequence** (D): The balance between stability and abstractness (D = |A + I - 1|). Ideally packages that are stable (I ≃ 0) should also be abstract (A ≃ 1), while packages that depend a lot on other packages (I ≃ 1) should be concrete (A ≃ 0).

## Trouble spots and related code smells

I have applied conditional formatting to help visualize trouble spots (see attached file). As the list of potential trouble spots is extensive, only a few of the more egregious examples will be discussed below:

1. The math package (`com.sk89q.worldedit.math`) has extremely high dependencies (Ca = 317) and maximum distance (D = 1) thanks to its zero abstraction and instability. This indicates that the package relies on specific, concrete implementations rather than abstract interfaces. What likely started as a utility package became a critical dependency, playing a crucial role in the overall architecture that is extensively used throughout the project. Proper interface abstraction and package modularization should be implemented. Likely code smells are *Large Class* (too many operations that other classes rely on) and *Primitive obsession* (heavy use of primitive types rather than proper abstractions or external objects) 

2. The command package (`com.sk89q.worldedit.command`) has extremely high Ce (316). The fact that it has very low abstractness (A = 0.0278) and high instability (I = 0.9906) might not be a problem in itself since it's D is very good (D = 0.0184), but it does make it very hard to modify and extend and more prone to the *Shotgun Surgery* code smell, since changing one command could require modifications in multiple unrelated locations.

3. The core WorldEdit package (`com.sk89q.worldedit`) has very high coupling (Ca = 320, Ce = 107) and deviation from main sequence (D = 0.6694). This indicates that the mod might have started off simple with the central package handling core functionality without proper boundaries, but quickly outgrew its complexity as new features were added. High coupling is related to rigid design while high D might indicate insufficient abstraction layers. This might indicate a *God class* code smell, a class that has too much responsibilities and knows too much about other classes.
