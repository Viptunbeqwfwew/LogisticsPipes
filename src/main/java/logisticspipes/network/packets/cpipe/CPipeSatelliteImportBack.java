package logisticspipes.network.packets.cpipe;

import logisticspipes.modules.ModuleCrafter;
import logisticspipes.network.abstractpackets.InventoryModuleCoordinatesPacket;
import logisticspipes.network.abstractpackets.ModernPacket;
import lombok.experimental.Accessors;
import net.minecraft.entity.player.EntityPlayer;

@Accessors(chain = true)
public class CPipeSatelliteImportBack extends InventoryModuleCoordinatesPacket {

    public CPipeSatelliteImportBack(int id) {
        super(id);
    }

    @Override
    public ModernPacket template() {
        return new CPipeSatelliteImportBack(getId());
    }

    @Override
    public void processPacket(EntityPlayer player) {
        ModuleCrafter module = this.getLogisticsModule(player, ModuleCrafter.class);
        if (module == null) {
            return;
        }
        for (int i = 0; i < getStackList().size(); i++) {
            module.setDummyInventorySlot(i, getStackList().get(i));
        }
    }
}
