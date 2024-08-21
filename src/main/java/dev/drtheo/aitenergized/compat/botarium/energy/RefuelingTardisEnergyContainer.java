package dev.drtheo.aitenergized.compat.botarium.energy;

import loqor.ait.tardis.link.v2.TardisRef;

import java.util.function.Supplier;

public abstract class RefuelingTardisEnergyContainer extends TardisEnergyContainer {

    public RefuelingTardisEnergyContainer(Supplier<TardisRef> ref, long maxExtract, long maxInsert) {
        super(ref, maxExtract, maxInsert);
    }

    @Override
    public long maxExtract() {
        return this.allowsExtraction() ? super.maxExtract() : 0;
    }

    @Override
    public long maxInsert() {
        return this.allowsInsertion() ? super.maxInsert() : 0;
    }

    @Override
    public long insertEnergy(long maxAmount, boolean simulate) {
        return this.allowsInsertion() ? super.insertEnergy(maxAmount, simulate) : 0;
    }

    @Override
    public long extractEnergy(long maxAmount, boolean simulate) {
        return this.allowsExtraction() ? super.extractEnergy(maxAmount, simulate) : 0;
    }

    @Override
    public boolean allowsInsertion() {
        return this.tardis().fuel().getRefueling().get();
    }

    @Override
    public boolean allowsExtraction() {
        return !this.allowsInsertion();
    }
}
