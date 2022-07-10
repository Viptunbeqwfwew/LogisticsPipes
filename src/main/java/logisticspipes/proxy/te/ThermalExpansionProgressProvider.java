package logisticspipes.proxy.te;

import cofh.thermalexpansion.block.machine.TileMachineBase;
import logisticspipes.proxy.interfaces.IGenericProgressProvider;
import net.minecraft.tileentity.TileEntity;

public class ThermalExpansionProgressProvider implements IGenericProgressProvider {

    @Override
    public boolean isType(TileEntity tile) {
        return tile instanceof TileMachineBase;
    }

    @Override
    public byte getProgress(TileEntity tile) {
        return (byte) Math.max(0, Math.min(((TileMachineBase) tile).getScaledProgress(100), 100));
    }
}
