# 1. Message Chain
## Code Location
`worldedit-core/src/main/java/com/sk89q/worldedit/EditSessionFactory.java`
## Code Snippet
```java
@Override
public EditSession getEditSession(World world, int maxBlocks, BlockBag blockBag, Actor actor) {
    return WorldEdit.getInstance().newEditSessionBuilder()
        .world(world)
        .maxBlocks(maxBlocks)
        .blockBag(blockBag)
        .actor(actor)
        .build();
}
```
## Why
- Message chains indicate a strong dependency on multiple structures.
- In this case, getEditSession has to navigate through WorldEdit and EditSessionBuilder, creating a dependency on the internal structure of these classes.
- This makes the code fragile and hard to maintain because if the structure of WorldEdit or EditSessionBuilder changes, this code would also need changes.
## How to Fix
- Encapsulate the chained calls in a method inside WorldEdit that handles the configuration.
- Instead of chaining, call the new method directly.


# 2. God Class
## Code Location
`worldedit-core/src/main/java/com/sk89q/worldedit/EditSession.java`
## Code Snippet
The class has 2981 lines of code.
## Why
- This is a God Class because it has too many responsibilities and has a large amount of code (like 2981 lines), making it hard to understand, test, and maintain.
## How to Fix
-  Refactor by breaking the class, extracting smaller and more focused classes, each handling a specific responsibility.


# 3. Long Parameter List
## Code Location
`worldedit-core/src/main/java/com/sk89q/worldedit/EditSession.java`
## Code Snippet
````java
 public int makeBiomeShape(final Region region, final Vector3 zero, final Vector3 unit, final BiomeType biomeType,
                              final String expressionString, final boolean hollow, final int timeout)
                              throws ExpressionException { 
````
## Why
- A long parameter list, like the seven parameters here, makes a method hard to understand and prone to errors.
- It can also lead to unclear usage, as it’s challenging to remember the purpose and order of each parameter

## How to Fix
- Group related parameters into a configuration object, to reduce the number of individual parameters
