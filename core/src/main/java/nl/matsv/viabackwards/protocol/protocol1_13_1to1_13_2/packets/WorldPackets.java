package nl.matsv.viabackwards.protocol.protocol1_13_1to1_13_2.packets;

import us.myles.ViaVersion.api.PacketWrapper;
import us.myles.ViaVersion.api.protocol.Protocol;
import us.myles.ViaVersion.api.remapper.PacketHandler;
import us.myles.ViaVersion.api.remapper.PacketRemapper;
import us.myles.ViaVersion.api.type.Type;
import us.myles.ViaVersion.packets.State;

/**
 * Created by Marco Neuhaus on 12.11.2018 for the Project ViaBackwards.
 */
public class WorldPackets {

    public static void register(Protocol protocol) {
        //spawn particle
        protocol.registerOutgoing(State.PLAY, 0x24, 0x24, new PacketRemapper() {
            @Override
            public void registerMap() {
                map(Type.INT); // 0 - Particle ID
                map(Type.BOOLEAN); // 1 - Long Distance
                map(Type.FLOAT); // 2 - X
                map(Type.FLOAT); // 3 - Y
                map(Type.FLOAT); // 4 - Z
                map(Type.FLOAT); // 5 - Offset X
                map(Type.FLOAT); // 6 - Offset Y
                map(Type.FLOAT); // 7 - Offset Z
                map(Type.FLOAT); // 8 - Particle Data
                map(Type.INT); // 9 - Particle Count
                handler(new PacketHandler() {
                    @Override
                    public void handle(PacketWrapper wrapper) throws Exception {
                        int id = wrapper.get(Type.INT, 0);
                        if (id == 27) {
                            wrapper.write(Type.FLAT_ITEM, wrapper.read(Type.FLAT_VAR_INT_ITEM));
                        }
                    }
                });
            }
        });
    }
}
