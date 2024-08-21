package dev.drtheo.aitenergized.compat.botarium.energy;

import loqor.ait.tardis.link.v2.TardisRef;

import java.util.function.Supplier;

public class RefuelingTardisEnergyContainer extends TardisEnergyContainer {

    public RefuelingTardisEnergyContainer(Supplier<TardisRef> ref, long maxExtract, long maxInsert) {
        super(ref, maxExtract, maxInsert);
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
