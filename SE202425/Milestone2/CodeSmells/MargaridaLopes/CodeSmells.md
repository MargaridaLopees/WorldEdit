# Duplicate Code

Two code fragments look almost identical.

## Code snippet

- **Location** `Worldedit-sponge/src/main/java/com/sk89q.worldedit.sponge/SpongeBlockRegistry.java`
- **Package** `com.sk89q.worldedit.sponge`
- **Class** `SpongeBlockRegistry`
- **Methods** `getRichName` , `getProperties` , `getMaterial`

````java
public Component getRichName(BlockType blockType) {
    return SpongeTextAdapter.convert(Sponge.game().registry(RegistryTypes.BLOCK_TYPE)
            .value(ResourceKey.resolve(blockType.id())).asComponent());
}
````

````java
public Map<String, ? extends Property<?>> getProperties(BlockType blockType) {
    org.spongepowered.api.block.BlockType spongeBlockType =
            Sponge.game().registry(RegistryTypes.BLOCK_TYPE)
                    .value(ResourceKey.resolve(blockType.id()));
    Map<String, Property<?>> map = new TreeMap<>();
    Collection<StateProperty<?>> propertyKeys = spongeBlockType
            .defaultState().stateProperties();
    for (StateProperty<?> key : propertyKeys) {
        map.put(key.name(), SpongeTransmogrifier.transmogToWorldEditProperty(key));
    }
    return map;
}
````

````java
public BlockMaterial getMaterial(BlockType blockType) {
        org.spongepowered.api.block.BlockType spongeBlockType =
            Sponge.game().registry(RegistryTypes.BLOCK_TYPE)
                .value(ResourceKey.resolve(blockType.id()));
        return materialMap.computeIfAbsent(
            spongeBlockType.defaultState(),
            m -> {
                net.minecraft.world.level.block.state.BlockState blockState =
                    (net.minecraft.world.level.block.state.BlockState) m;
                return new SpongeBlockMaterial(
                    blockState,
                    super.getMaterial(blockType)
                );
            }
        );
    }
````


## Why is this a code smell?

The three methods above contain the same piece of code:

````java
Sponge.game().registry(RegistryTypes.BLOCK_TYPE)
                .value(ResourceKey.resolve(blockType.id()))
````


## Preposal of refactoring

Create an auxiliary method for this bit of code and call, in each of the methods, the new auxiliary method. 

````java
public Component getRichName(BlockType blockType) {
    return SpongeTextAdapter.convert(getBlockTypeValue(blockType).asComponent());
}
````

````java
public Map<String, ? extends Property<?>> getProperties(BlockType blockType) {
    org.spongepowered.api.block.BlockType spongeBlockType =
            getBlockTypeValue(blockType);
    Map<String, Property<?>> map = new TreeMap<>();
    Collection<StateProperty<?>> propertyKeys = spongeBlockType
            .defaultState().stateProperties();
    for (StateProperty<?> key : propertyKeys) {
        map.put(key.name(), SpongeTransmogrifier.transmogToWorldEditProperty(key));
    }
    return map;
}
````

````java
public BlockMaterial getMaterial(BlockType blockType) {
        org.spongepowered.api.block.BlockType spongeBlockType =
            getBlockTypeValue();
        return materialMap.computeIfAbsent(
            spongeBlockType.defaultState(blockType),
            m -> {
                net.minecraft.world.level.block.state.BlockState blockState =
                    (net.minecraft.world.level.block.state.BlockState) m;
                return new SpongeBlockMaterial(
                    blockState,
                    super.getMaterial(blockType)
                );
            }
        );
    }
````

### Auxiliary method

````java
public static BlockType getBlockTypeValue(BlockType blockType) {
        return Sponge.game().registry(RegistryTypes.BLOCK_TYPE)
                .value(ResourceKey.resolve(blockType.id()));
    }
````


# Long Method

A method contains too many lines of code.

## Code snippet

- **Location** `Worldedit-sponge/src/main/java/com/sk89q.worldedit.sponge/SpongeWorldEdit.java`
- **Package** `com.sk89q.worldedit.sponge`
- **Class** `SpongeWorldEdit`
- **Method** `serverStarted`


````java
    public void serverStarted(StartedEngineEvent<Server> event) {
        event.engine().scheduler().submit(Task.builder()
                .plugin(container)
                .interval(30, TimeUnit.SECONDS)
                .execute(ThreadSafeCache.getInstance())
                .build());

        event.game().registry(RegistryTypes.BLOCK_TYPE).streamEntries().forEach(blockType -> {
            String id = blockType.key().asString();
            if (!com.sk89q.worldedit.world.block.BlockType.REGISTRY.keySet().contains(id)) {
                com.sk89q.worldedit.world.block.BlockType.REGISTRY.register(id, new com.sk89q.worldedit.world.block.BlockType(
                    id,
                    input -> {
                        BlockType spongeBlockType = Sponge.game().registry(RegistryTypes.BLOCK_TYPE).value(
                            ResourceKey.resolve(input.getBlockType().id())
                        );
                        return SpongeAdapter.adapt(spongeBlockType.defaultState());
                    }
                ));
            }
        });

        event.game().registry(RegistryTypes.ITEM_TYPE).streamEntries().forEach(itemType -> {
            String id = itemType.key().asString();
            if (!com.sk89q.worldedit.world.item.ItemType.REGISTRY.keySet().contains(id)) {
                com.sk89q.worldedit.world.item.ItemType.REGISTRY.register(id, new com.sk89q.worldedit.world.item.ItemType(id));
            }
        });

        event.game().registry(RegistryTypes.ENTITY_TYPE).streamEntries().forEach(entityType -> {
            String id = entityType.key().asString();
            if (!com.sk89q.worldedit.world.entity.EntityType.REGISTRY.keySet().contains(id)) {
                com.sk89q.worldedit.world.entity.EntityType.REGISTRY.register(id, new com.sk89q.worldedit.world.entity.EntityType(id));
            }
        });

        for (ServerWorld world : event.engine().worldManager().worlds()) {
            world.registry(RegistryTypes.BIOME).streamEntries().forEach(biomeType -> {
                String id = biomeType.key().asString();
                if (!BiomeType.REGISTRY.keySet().contains(id)) {
                    BiomeType.REGISTRY.register(id, new BiomeType(id));
                }
            });
        }

        // Disabled until https://github.com/SpongePowered/SpongeAPI/issues/2520 is resolved
        // Will also need implementations in SpongeWorld to do placement
        //        Sponge.server().registry(RegistryTypes.FEATURE).streamEntries().forEach(feature -> {
        //            String id = feature.key().asString();
        //            if (!ConfiguredFeatureType.REGISTRY.keySet().contains(id)) {
        //                ConfiguredFeatureType.REGISTRY.register(id, new ConfiguredFeatureType(id));
        //            }
        //        });
        //        Sponge.server().registry(RegistryTypes.STRUCTURE).streamEntries().forEach(structure -> {
        //            String id = structure.key().asString();
        //            if (!StructureType.REGISTRY.keySet().contains(id)) {
        //                StructureType.REGISTRY.register(id, new StructureType(id));
        //            }
        //        });

        event.game().registry(RegistryTypes.BLOCK_TYPE).tags().forEach(blockTypeTag -> {
            String id = blockTypeTag.key().asString();
            if (!BlockCategory.REGISTRY.keySet().contains(id)) {
                BlockCategory.REGISTRY.register(id, new BlockCategory(id));
            }
        });
        event.game().registry(RegistryTypes.ITEM_TYPE).tags().forEach(itemTypeTag -> {
            String id = itemTypeTag.key().asString();
            if (!ItemCategory.REGISTRY.keySet().contains(id)) {
                ItemCategory.REGISTRY.register(id, new ItemCategory(id));
            }
        });
        Sponge.server().registry(RegistryTypes.BIOME).tags().forEach(biomeTag -> {
            String id = biomeTag.key().asString();
            if (!BiomeCategory.REGISTRY.keySet().contains(id)) {
                BiomeCategory.REGISTRY.register(id, new BiomeCategory(id, () -> 
                        event.game().registry(RegistryTypes.BIOME).taggedValues(biomeTag).stream().map(SpongeAdapter::adapt).collect(Collectors.toSet())));
            }
        });

        config.load();
        WorldEdit.getInstance().getEventBus().post(new PlatformReadyEvent(platform));
    }
````

## Why is this a code smell?

This method has more than **70 lines**, which corresponds to the code smell Long Method.

The code metric **LOC** (Lines of Code) from the class `SpongeWorldEdit` is 325.

The **LOC** for the method `serverStarted` is 76.

Which means that more than 23% of the class's lines of code are just from this method.

The more lines found in a method, the harder it’s to figure out what the method does.

## Preposal of refactoring

To solve this code smell, I would use the 'Extract Method' technique, where I would place groups of code in new methods.

````java
    public void serverStarted(StartedEngineEvent<Server> event) {
        event.engine().scheduler().submit(Task.builder().plugin(container).
                interval(30, TimeUnit.SECONDS).execute(ThreadSafeCache.getInstance()).build());

        registerUnregisterTypes();
        registerUnregisterCategories();
        
        config.load();
        WorldEdit.getInstance().getEventBus().post(new PlatformReadyEvent(platform));
    }
````

### Auxiliary methods

````java
private void registerUnregisterTypes() {
        registerUnregisteredBlockTypes();
        registerUnregisteredItemTypes();
        registerUnregisteredEntityTypes();
        registerUnregisteredBiomeTypes();
        
        // Disabled until https://github.com/SpongePowered/SpongeAPI/issues/2520 is resolved
        // Will also need implementations in SpongeWorld to do placement
        //      registerUnregisteredFeatureTypes(); 
        //      registerUnregisteredStructureTypes();
}
````

````java
private void registerUnregisterCategories() {
    registerUnregisteredBlockCategories(); 
    registerUnregisteredItemCategories(); 
    registerUnregisteredBiomeCategories();
}
````


### Auxiliary methods: ResgisterUnresgisteredTypes

````java
private void registerUnregisteredBlockTypes() {
    event.game().registry(RegistryTypes.BLOCK_TYPE).streamEntries().forEach(blockType -> {
        String id = blockType.key().asString();
        if (!com.sk89q.worldedit.world.block.BlockType.REGISTRY.keySet().contains(id)) {
            com.sk89q.worldedit.world.block.BlockType.REGISTRY.register(id, new com.sk89q.worldedit.world.block.BlockType(
                id,
                input -> {
                    BlockType spongeBlockType = Sponge.game().registry(RegistryTypes.BLOCK_TYPE).value(
                        ResourceKey.resolve(input.getBlockType().id())
                    );
                    return SpongeAdapter.adapt(spongeBlockType.defaultState());
                }
            ));
        }
    });
}
````

````java
private void registerUnregisteredItemTypes() {
    event.game().registry(RegistryTypes.ITEM_TYPE).streamEntries().forEach(itemType -> {
        String id = itemType.key().asString();
        if (!com.sk89q.worldedit.world.item.ItemType.REGISTRY.keySet().contains(id)) {
            com.sk89q.worldedit.world.item.ItemType.REGISTRY.register(id, new com.sk89q.worldedit.world.item.ItemType(id));
        }
    });
}
````

````java
private void registerUnregisteredEntityTypes() {
    event.game().registry(RegistryTypes.ENTITY_TYPE).streamEntries().forEach(entityType -> {
        String id = entityType.key().asString();
        if (!com.sk89q.worldedit.world.entity.EntityType.REGISTRY.keySet().contains(id)) {
            com.sk89q.worldedit.world.entity.EntityType.REGISTRY.register(id, new com.sk89q.worldedit.world.entity.EntityType(id));
        }
    });
}
````

````java
private void registerUnregisteredBiomeTypes() {
    for (ServerWorld world : event.engine().worldManager().worlds()) {
        world.registry(RegistryTypes.BIOME).streamEntries().forEach(biomeType -> {
            String id = biomeType.key().asString();
            if (!BiomeType.REGISTRY.keySet().contains(id)) {
                BiomeType.REGISTRY.register(id, new BiomeType(id));
            }
        });
    }
}
````

````java
private void registerUnregisteredFeatureTypes() {
    Sponge.server().registry(RegistryTypes.FEATURE).streamEntries().forEach(feature -> {
        String id = feature.key().asString();
        if (!ConfiguredFeatureType.REGISTRY.keySet().contains(id)) {
            ConfiguredFeatureType.REGISTRY.register(id, new ConfiguredFeatureType(id));
        }
    });
}
````

````java
private void registerUnregisteredStructureTypes() {
    Sponge.server().registry(RegistryTypes.STRUCTURE).streamEntries().forEach(structure -> {
        String id = structure.key().asString();
        if (!StructureType.REGISTRY.keySet().contains(id)) {
            StructureType.REGISTRY.register(id, new StructureType(id));
        }
    });
}
````

### Auxiliary methods: ResgisterUnresgisteredCategories

````java
private void registerUnregisteredBlockCategories() {
    event.game().registry(RegistryTypes.BLOCK_TYPE).tags().forEach(blockTypeTag -> {
        String id = blockTypeTag.key().asString();
        if (!BlockCategory.REGISTRY.keySet().contains(id)) {
            BlockCategory.REGISTRY.register(id, new BlockCategory(id));
        }
    });
}
````

````java
private void registerUnregisteredItemCategories() {
    event.game().registry(RegistryTypes.ITEM_TYPE).tags().forEach(itemTypeTag -> {
        String id = itemTypeTag.key().asString();
        if (!ItemCategory.REGISTRY.keySet().contains(id)) {
            ItemCategory.REGISTRY.register(id, new ItemCategory(id));
        }
    });
}
````

````java
private void registerUnregisteredBiomeCategories() {
    Sponge.server().registry(RegistryTypes.BIOME).tags().forEach(biomeTag -> {
        String id = biomeTag.key().asString();
        if (!BiomeCategory.REGISTRY.keySet().contains(id)) {
            BiomeCategory.REGISTRY.register(id, new BiomeCategory(id, () -> 
                    event.game().registry(RegistryTypes.BIOME).taggedValues(biomeTag).stream().map(SpongeAdapter::adapt).collect(Collectors.toSet())));
        }
    });
}
````
# Long Parameter List

More than three or four parameters for a method.

## Code snippet

- **Location** `Worldedit-sponge/src/main/java/com/sk89q.worldedit.sponge/internal/SpongeWorldNativeAccess.java`
- **Package** `com.sk89q.worldedit.sponge.internal`
- **Class** `SpongeWorldNativeAccess`

(to illustrate this code smell, I will only use one method to represent the hole class)

````java
public void notifyBlockUpdate(LevelChunk chunk, BlockPos position, BlockState oldState, BlockState newState) {
    if (chunk.getSections()[getWorld().getSectionIndex(position.getY())] != null) {
        getWorld().sendBlockUpdated(position, oldState, newState, UPDATE | NOTIFY);
    }
}
````

## Why is this a code smell?

Many of the methods in this class have 3 or more parameters.

Furthermore, there are three parameters common to almost all classes: `BlockPos position`, `BlockState oldState`, `BlockState newState`.

This indicates that it is possible and useful to create a helper class with these three parameters.


## Preposal of refactoring

Create a helper class with the three parameters.

````java
public class BlockHelper {
    private final BlockPos pos;
    private final BlockState oldState;
    private final BlockState newState;

    public BlockUpdateContext(BlockPos pos, BlockState oldState, BlockState newState) {
        this.pos = pos;
        this.oldState = oldState;
        this.newState = newState;
    }

    public BlockPos getPos() { return pos; }
    public BlockState getOldState() { return oldState; }
    public BlockState getNewState() { return newState; }
}
````

````java
public void notifyBlockUpdate(LevelChunk chunk, BlockHelper block) {
    if (chunk.getSections()[getWorld().getSectionIndex(block.getPos().getY())] != null) {
        getWorld().sendBlockUpdated(block.getPos(), block.getOldState(), block.getNewState(), UPDATE | NOTIFY);
    }
}
````

(Also by changing the `sendBlockUpdated` method we could just give `block` as a parameter.)


