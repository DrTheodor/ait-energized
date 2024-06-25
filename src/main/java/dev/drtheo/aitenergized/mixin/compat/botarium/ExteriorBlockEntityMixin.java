package dev.drtheo.aitenergized.mixin.compat.botarium;

import dev.drtheo.aitenergized.compat.botarium.energy.RefuelingTardisEnergyContainer;
import earth.terrarium.botarium.common.energy.base.BotariumEnergyBlock;
import earth.terrarium.botarium.common.energy.impl.WrappedBlockEnergyContainer;
import loqor.ait.core.blockentities.ExteriorBlockEntity;
import loqor.ait.tardis.link.v2.AbstractLinkableBlockEntity;
import loqor.ait.tardis.link.v2.TardisRef;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ExteriorBlockEntity.class)
public class ExteriorBlockEntityMixin extends BlockEntity implements BotariumEnergyBlock<WrappedBlockEnergyContainer> {

    @Unique protected WrappedBlockEnergyContainer container;

    public ExteriorBlockEntityMixin(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    @Override
    public WrappedBlockEnergyContainer getEnergyStorage() {
        if (container != null)
            return container;

        return container = new WrappedBlockEnergyContainer(
                this, new RefuelingTardisEnergyContainer(
                this::ais$tardis, 750, 500
        ));
    }

    @Unique
    private TardisRef ais$tardis() {
        return ((AbstractLinkableBlockEntity) (Object) this).tardis();
    }
}