package com.github.tyonakaisan.yummytoast.packet;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.github.tyonakaisan.yummytoast.Toast;
import net.kyori.adventure.key.Key;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractPacket {

    // This packet is for adding advancement
    protected final PacketContainer addHandle = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.ADVANCEMENTS);
    // This packet is for removing advancement
    protected final PacketContainer removeHandle = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.ADVANCEMENTS);
    protected final Key key = Key.key("yummy_toast", "delicious");
    protected final Toast toast;

    protected AbstractPacket(@NotNull final Toast toast) {
        this.toast = toast;
    }

    /**
     * Send packet.
     * @param player the {@link Player}
     */
    public abstract void sendTo(@NotNull Player player);
}
