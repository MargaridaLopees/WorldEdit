package com.sk89q.worldedit.command.tool.brush;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.function.pattern.Pattern;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.block.BlockType;
import com.sk89q.worldedit.world.block.BlockTypes;

public class DesertBrush implements Brush {
    private final double intensity;
    private static final  BlockType CACTUS = BlockTypes.CACTUS;
    private static final  BlockType DEAD_BUSH = BlockTypes.DEAD_BUSH;

    public DesertBrush(double intensity) {
        this.intensity = intensity;
    }

    @Override
    public void build(EditSession editSession, BlockVector3 position, Pattern pattern, double size) throws MaxChangedBlocksException {

        // Fills floor with sand blocks
        fillSand(editSession, position, size);

        if (intensity == 2) {
            // Adds vegetation
            addVegetation(editSession, position, size);
        } else if (intensity == 3) {
            // Adds dunes
            addDunes(editSession, position, size);

            // Makes sure World Edit registers the previous changes to the world
            Operations.completeLegacy(editSession.commit());

            // Adds vegetation
            addVegetation(editSession, position, size);
        }
    }


    private void fillSand (EditSession editSession, BlockVector3 position, double size) throws MaxChangedBlocksException {
        { int minX = position.x() - (int) size;
            int maxX = position.x() + (int) size;
            int minZ = position.z() - (int) size;
            int maxZ = position.z() + (int) size;
            for (int x = minX; x <= maxX; x++) {
                for (int z = minZ; z <= maxZ; z++) {
                    int y = editSession.getHighestTerrainBlock(x, z, -63, 319);

                    // If block below isn't a cactus
                    if (editSession.getBlock(BlockVector3.at(x, y, z)) != BlockTypes.CACTUS.getDefaultState()) {
                        editSession.setBlock(BlockVector3.at(x, y, z), BlockTypes.SAND.getDefaultState());
                    }
                }
            }
        }
    }

    private void addDunes(EditSession editSession, BlockVector3 position, double size) throws MaxChangedBlocksException {

        // Computes how many dunes
        int numDunes = (int)(Math.round(Math.random() * size) + intensity);

        for (int i = 0; i < numDunes; i++) {
            //Defines a random angle and distance to position the dune
            double angle = Math.random() * 2 * Math.PI;
            double distance = Math.random() * (size * 0.7);

            // Computes dune's final coordinates
            int duneX = position.x() + (int)(Math.cos(angle) * distance);
            int duneZ = position.z() + (int)(Math.sin(angle) * distance);

            // Computes dune's base radius and height
            double duneRadius = size / 4 + (Math.random() * size / 4 );
            double duneHeight = Math.max( ((Math.log(size) / Math.log(2)) + 0 ) * Math.random() , 1); //(Math.random() * intensity);

            for (int x = (int)(duneX - duneRadius); x <= duneX + duneRadius; x++) {
                for (int z = (int)(duneZ - duneRadius); z <= duneZ + duneRadius; z++) {

                    // Calculates distance from dune's center
                    double dx = Math.abs(x - duneX);
                    double dz = Math.abs(z - duneZ);

                    // Verifies if the coords are inside the dune's radius
                    if (dx + dz <= duneRadius) {

                        // Gets the highest block in the coords (x, z)
                        int baseY = editSession.getHighestTerrainBlock(x, z, -63, 319);

                        // Computes dune's height
                        double height = duneHeight * (1 - (dx + dz) / duneRadius);

                        // Places and chooses dune's blocks
                        int maxY = baseY + (int)height;
                        for (int y = baseY + 1; y < maxY; y++) {
                            editSession.setBlock(BlockVector3.at(x, y, z), BlockTypes.SAND.getDefaultState());
                        }
                    }
                }
            }
        }
    }


    private void addDetails(EditSession editSession, BlockVector3 position, double size, BlockType block, int maxHeight) throws MaxChangedBlocksException {

        // Chooses how much vegetation
        int maxVegetation = (int)(size * (intensity / 10.0));
        int numVegetation = (int)(Math.round((Math.random() * maxVegetation)));

        for (int i = 0; i < numVegetation; i++) {
            // Defines a random angle and distance to position the vegetation
            double angle = Math.random() * 2 * Math.PI;
            double distance = Math.random() * size;

            // Chooses vegetation height
            int height = (int)(Math.round((1 + Math.random() * maxHeight)));

            // Computes vegetation's final coordinates
            int x = position.x() + (int)(Math.round((Math.cos(angle) * distance)));
            int z = position.z() + (int)(Math.round((Math.sin(angle) * distance)));

            // Verifies if the coords are inside the placeable radius
            if (position.distanceSq(BlockVector3.at(x, position.y(), z)) <= size * size) {

                    // Ensures that vegetation is placed on the terrain's surface
                    int y = editSession.getHighestTerrainBlock(x, z, -63, 319);

                for (int j = 1; j <= height; j++) {

                    // Ensures vegetation can be placed
                    if (isValidLocation(editSession,BlockVector3.at(x, y + j, z), block)) {
                    editSession.setBlock(BlockVector3.at(x, y + j, z), block.getDefaultState());}
                    else {break;}
                }
            }
        }
    }

    private boolean isValidLocation(EditSession editSession, BlockVector3 blockPos, BlockType blockType) {

        // Specifies adjacent blocks
        BlockType blockBelow = editSession.getBlock(blockPos.add(0, -1, 0)).getBlockType();
        BlockType blockNorth = editSession.getBlock(blockPos.add(0, 0, -1)).getBlockType();
        BlockType blockSouth = editSession.getBlock(blockPos.add(0, 0, 1)).getBlockType();
        BlockType blockEast = editSession.getBlock(blockPos.add(1, 0, 0)).getBlockType();
        BlockType blockWest = editSession.getBlock(blockPos.add(-1, 0, 0)).getBlockType();

        // Specify requisites
        if (blockType == CACTUS) {
            return (blockBelow != BlockTypes.DEAD_BUSH &&
                    blockNorth == BlockTypes.AIR &&
                    blockSouth == BlockTypes.AIR &&
                    blockEast == BlockTypes.AIR &&
                    blockWest == BlockTypes.AIR);
        } else {
            return (blockBelow == BlockTypes.SAND &&
                    blockNorth != BlockTypes.CACTUS &&
                    blockSouth != BlockTypes.CACTUS &&
                    blockEast != BlockTypes.CACTUS &&
                    blockWest != BlockTypes.CACTUS);
        }
    }

    private void addVegetation(EditSession editSession, BlockVector3 position, double size) throws MaxChangedBlocksException {
        int cactusMaxHeight = 3;
        int deadBushMaxHeight = 1;

        addDetails(editSession, position, size, DEAD_BUSH, deadBushMaxHeight);

        addDetails(editSession, position, size, CACTUS, cactusMaxHeight);
    }
}