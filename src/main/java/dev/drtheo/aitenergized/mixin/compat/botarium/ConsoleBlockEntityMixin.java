package dev.drtheo.aitenergized.mixin.compat.botarium;

import dev.drtheo.aitenergized.compat.botarium.energy.TardisEnergyContainer;
import earth.terrarium.botarium.common.energy.base.BotariumEnergyBlock;
import earth.terrarium.botarium.common.energy.impl.WrappedBlockEnergyContainer;
import loqor.ait.core.blockentities.ConsoleBlockEntity;
import loqor.ait.tardis.link.v2.block.AbstractLinkableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ConsoleBlockEntity.class)
public abstract class ConsoleBlockEntityMixin extends AbstractLinkableBlockEntity implements BotariumEnergyBlock<WrappedBlockEnergyContainer> {

    @Unique protected WrappedBlockEnergyContainer container;

    public ConsoleBlockEntityMixin(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    @Override
    public WrappedBlockEnergyContainer getEnergyStorage() {
        if (container != null)
            return container;

        return container = new WrappedBlockEnergyContainer(
                this, new TardisEnergyContainer(
                this::tardis, 1000, 1000
        ));
    }
}
