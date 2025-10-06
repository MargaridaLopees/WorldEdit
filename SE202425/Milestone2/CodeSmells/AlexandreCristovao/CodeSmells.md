# 1. Duplicate Code
## Code Location
`worldedit-neoforge/src/main/java/com/sk89q/worldedit/neoforge/NeoForgeWorldEdit.java`
- ***Package***: com.sk89q.worldedit.neoforge
- ***Class***: NeoForgeWorldEdit

## Code Snippet
```java
public class NeoForgeWorldEdit {

    //
    
    public void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        if (skipInteractionEvent(event.getEntity(), event.getHand()) || event.getUseItem().isFalse()) {
            return;
        }

        ServerPlayer playerEntity = (ServerPlayer) event.getEntity();
        WorldEdit we = WorldEdit.getInstance();
        NeoForgePlayer player = adaptPlayer(playerEntity);
        NeoForgeWorld world = getWorld((ServerLevel) playerEntity.level());
        Direction direction = NeoForgeAdapter.adaptEnumFacing(event.getFace());

        BlockPos blockPos = event.getPos();
        Location pos = new Location(world, blockPos.getX(), blockPos.getY(), blockPos.getZ());

        boolean result = we.handleBlockLeftClick(player, pos, direction) || we.handleArmSwing(player);
        debouncer.setLastInteraction(player, result);

        if (result) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (skipInteractionEvent(event.getEntity(), event.getHand()) || event.getUseItem().isFalse()) {
            return;
        }

        ServerPlayer playerEntity = (ServerPlayer) event.getEntity();
        WorldEdit we = WorldEdit.getInstance();
        NeoForgePlayer player = adaptPlayer(playerEntity);
        NeoForgeWorld world = getWorld((ServerLevel) playerEntity.level());
        Direction direction = NeoForgeAdapter.adaptEnumFacing(event.getFace());

        BlockPos blockPos = event.getPos();
        Location pos = new Location(world, blockPos.getX(), blockPos.getY(), blockPos.getZ());

        boolean result = we.handleBlockRightClick(player, pos, direction) || we.handleRightClick(player);
        debouncer.setLastInteraction(player, result);

        if (result) {
            event.setCanceled(true);
        }
    }
    
    //
}
```
## Rationale
- Both of these methods share almost exact code with slight variances.
- If changes need to be made, the programmer needs to be careful to change both methods

## Proposed Refactoring
- Since both methods are on the same class, we can create a  method that encapsulates the common logic for skipping events, getters , and setting the last interaction.
- Now refactored, both methods can now do their own separate logic and use the newly created method to perform the shared logic.
- This way, changes to the shared logic will be easier to maintain and test.

# 2. God Class

## Code Location
`worldedit-fabric/src/main/java/com/sk89q/worldedit/fabric/FabricDataFixer.java`
- ***Package***: com.sk89q.worldedit.fabric;
- ***Class***: FabricDataFixer
## Code Snippet
```java
/**
 * Handles converting all Pre 1.13.2 data using the Legacy DataFix System (ported to 1.13.2)
 *
 * <p>
 * We register a DFU Fixer per Legacy Data Version and apply the fixes using legacy strategy
 * which is safer, faster and cleaner code.
 * </p>
 *
 * <p>
 * The pre DFU code did not fail when the Source version was unknown.
 * </p>
 *
 * <p>
 * This class also provides util methods for converting compounds to wrap the update call to
 * receive the source version in the compound.
 * </p>
 */

//
class FabricDataFixer implements com.sk89q.worldedit.world.DataFixer {
   //
}

```
## Rationale
- The class has 2764 lines of code (with many methods partaking in the Long Method code smell)
- Like the upper comment says, the class converts data. That in itself may require higher than average class size, but it also states `This class also provides util methods for converting compounds to wrap the update call to receive the source version in the compound.` It takes too big responsibilities with better alternatives available such as...

## Proposed Refactoring
- Refactor the code and extract classes, subclasses or interfaces depending on the needs

# 3. Message Chains

## Code Location
`worldedit-bukkit/adapters/adapter-1.20.2/src/main/java/com/sk89q/worldedit/bukkit/adapter/impl/v1_20_R2/PaperweightAdapter.java`
- ***Package***: com.sk89q.worldedit.bukkit.adapter.impl.v1_20_R2;
- ***Class***: PaperweightAdapter

## Code Snippet
```java
public final class PaperweightAdapter implements BukkitImplAdapter {
    
    //

    private static Block getBlockFromType(BlockType blockType) {
        return DedicatedServer.getServer().registryAccess().registryOrThrow(Registries.BLOCK).get(ResourceLocation.tryParse(blockType.id()));
    }

    private static Item getItemFromType(ItemType itemType) {
        return DedicatedServer.getServer().registryAccess().registryOrThrow(Registries.ITEM).get(ResourceLocation.tryParse(itemType.id()));
    }
            
    //
}

```
## Rationale
- Both of these methods are examples of sequences of methods called together and creating dependencies. This makes maintaining code significantly harder and more prone to errors from unforeseen dependencies. Independent tests are much harder to execute. 

## Proposed Refactoring
- Ensure each method only interacts with methods of its own class or parameters.