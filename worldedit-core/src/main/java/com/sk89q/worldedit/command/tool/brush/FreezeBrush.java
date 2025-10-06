package com.sk89q.worldedit.command.tool.brush;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.command.factory.FeatureGeneratorFactory;
import com.sk89q.worldedit.function.Contextual;
import com.sk89q.worldedit.function.EditContext;
import com.sk89q.worldedit.function.RegionMaskingFilter;
import com.sk89q.worldedit.function.factory.ApplyRegion;
import com.sk89q.worldedit.function.generator.FeatureGenerator;
import com.sk89q.worldedit.function.mask.Mask;
import com.sk89q.worldedit.function.mask.NoiseFilter;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.function.pattern.Pattern;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.math.noise.RandomNoise;
import com.sk89q.worldedit.regions.factory.RegionFactory;
import com.sk89q.worldedit.world.generation.ConfiguredFeatureType;

import javax.annotation.Nullable;

public class FreezeBrush implements Brush, Contextual<RegionMaskingFilter> {
    private final ConfiguredFeatureType type; // Feature to be applied
    private final double intensity;

    private final OperationFactoryBrush operationFactoryBrush; // Brush that handles features
    private final SnowSmoothBrush snowSmoothBrush; // Brush that handles snow

    //FeatureGenerator
    public FreezeBrush(int iterations, int snowBlockLayer, @Nullable Mask mask, ConfiguredFeatureType type, RegionFactory shape, double intensity, int iceSpikeFactor,
                       LocalSession session) {
        this.type = type;
        this.intensity = intensity;

        Contextual<? extends Operation> operationFactory = new ApplyRegion(new FeatureGeneratorFactory(type, intensity/iceSpikeFactor));
        this.operationFactoryBrush = new OperationFactoryBrush(operationFactory, shape, session);
        this.snowSmoothBrush = new SnowSmoothBrush(iterations, snowBlockLayer, mask);
    }

    @Override
    public void build(EditSession editSession, BlockVector3 position, Pattern pattern, double size) throws MaxChangedBlocksException {

            // Places snow blocks and layers
            snowSmoothBrush.build(editSession, position, pattern, size);

            if (intensity == 2 || intensity == 3) {
                // Makes sure World Edit registers the previous changes to the world
                Operations.completeLegacy(editSession.commit());

                // Places Ice Spikes above the previously placed snow blocks
                operationFactoryBrush.build(editSession, position, pattern, size);
            }
    }

    @Override
    public RegionMaskingFilter createFromContext(EditContext input) {
        return new RegionMaskingFilter(
                new NoiseFilter(new RandomNoise(), this.intensity),
                new FeatureGenerator((EditSession) input.getDestination(), this.type)
        );
    }

    @Override
    public String toString() {
        return "feature of type " + type;
    }
}