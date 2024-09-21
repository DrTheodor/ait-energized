package dev.drtheo.aitenergized.mixin.compat.botarium;

import dev.drtheo.aitenergized.compat.botarium.energy.RefuelingTardisEnergyContainer;
import earth.terrarium.botarium.common.energy.base.BotariumEnergyBlock;
import earth.terrarium.botarium.common.energy.impl.WrappedBlockEnergyContainer;
import loqor.ait.api.link.v2.block.AbstractLinkableBlockEntity;
import loqor.ait.core.blockentities.ExteriorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ExteriorBlockEntity.class)
public class ExteriorBlockEntityMixin extends AbstractLinkableBlockEntity implements BotariumEnergyBlock<WrappedBlockEnergyContainer> {

    @Unique protected WrappedBlockEnergyContainer container;

    public ExteriorBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public WrappedBlockEnergyContainer getEnergyStorage() {
        if (container != null)
            return container;

        return container = new WrappedBlockEnergyContainer(
                this, new RefuelingTardisEnergyContainer(
                this::tardis, 750, 500
        ));
    }
}