# 1) Code Smell: Message Chains

## Illustrating code snipped.

```
public GameMode getGameMode() {
    return GameModes.get(player.gameMode().get().key(RegistryTypes.GAME_MODE).asString());
```

## The exact location on the code base.

<br/>The code is located at:
<br/>**Class**: SpongePlayer. **Package**: internal. **Line**: 210.
<br/>**Path**: WorldEditPrivate\worldedit-sponge\src\main\java\com\sk89q\worldedit\sponge\SpongePlayer.java.

## An explanation of the rationale for identifying this code smell.

This method has all the characteristics of the code smell message chains. Instead of calling an auxiliary method, we are actually 
calling much more. It's called the get method, then another and another... It can be confusing and can contribute to a higher 
complexity, dependency and coupling.

## The proposal of a refactoring that would remove the smell.

I would propose another way to get the expected object or result or even an extracted method so we can understand it better, and it's complexity decreases.

# 2) Code Smell: Long Method

## Illustrating code snipped.

```
public <B extends BlockStateHolder<B>> boolean setBlock(BlockVector3 position, B block, SideEffectSet sideEffects) throws WorldEditException {
    checkNotNull(position);
    checkNotNull(block);

    ServerWorld world = getWorld();

    org.spongepowered.api.block.BlockState newState = SpongeAdapter.adapt(block.toImmutableState());

    boolean didSet = world.setBlock(
        position.x(), position.y(), position.z(),
        newState,
        BlockChangeFlags.NONE
            .withUpdateNeighbors(sideEffects.shouldApply(SideEffect.NEIGHBORS))
            .withNotifyClients(true)
            .withPhysics(sideEffects.shouldApply(SideEffect.UPDATE))
            .withNotifyObservers(sideEffects.shouldApply(SideEffect.UPDATE))
            .withLightingUpdates(sideEffects.shouldApply(SideEffect.LIGHTING))
            .withPathfindingUpdates(sideEffects.shouldApply(SideEffect.ENTITY_AI))
            .withNeighborDropsAllowed(false)
            .withBlocksMoving(false)
            .withForcedReRender(false)
            .withIgnoreRender(false)
    );
    if (!didSet) {
        // still update NBT if the block is the same
        if (world.block(position.x(), position.y(), position.z()) == newState) {
            didSet = block.toBaseBlock().getNbt() != null;
        }
    }

    // Create the TileEntity
    if (didSet && block instanceof BaseBlock baseBlock) {
        LinCompoundTag nbt = baseBlock.getNbt();
        if (nbt != null) {
            BlockEntityArchetype.builder()
                .state(newState)
                .blockEntity(
                    Sponge.game().registry(RegistryTypes.BLOCK_ENTITY_TYPE)
                        .<BlockEntityType>value(ResourceKey.resolve(baseBlock.getNbtId()))
                )
                .blockEntityData(NbtAdapter.adaptFromWorldEdit(nbt))
                .build()
                .apply(ServerLocation.of(world, position.x(), position.y(), position.z()));
        }
    }

    return true;
}

```

## The exact location on the code base.

<br/>The code is located at:
<br/>**Class**: SpongePlayer. **Package**: internal. **Line**: 182.
<br/>**Path**: WorldEditPrivate\worldedit-sponge\src\main\java\com\sk89q\worldedit\sponge\SpongeWorld.java.

## An explanation of the rationale for identifying this code smell.

This method is really long, containing excessive lines of code and contributing to a higher complexity, often handling 
multiple responsibilities. In addition, this makes it harden to understand the code and makes it harder to maintain as well.
It's harder to debug and reuse.

## The proposal of a refactoring that would remove the smell.

I would propose extracting pieces of code into smaller methods, with understandable names.

# 3) Code Smell: Large Class

## Illustrating code snipped.

The code has 2764 lines.

## The exact location on the code base.

<br/>The code is located at:
<br/>**Class**: NeoForge. **Package**: net.handler
<br/>**Path**: WorldEditPrivate\worldedit-neoforge\src\main\java\com\sk89q\worldedit\neoforge\NeoForgeDataFixer.java.

## An explanation of the rationale for identifying this code smell.

This class contains too many methods and responsibilities. Furthermore, it contributes to a higher complexity,
a decrease of modularity and increase of coupling, making it harder to understand, maintain and extend, for example.

## The proposal of a refactoring that would remove the smell.

I would propose refactoring and extract some code to smaller and more cohesive classes with a single responsibility,
so it has less complexity and coupling.

