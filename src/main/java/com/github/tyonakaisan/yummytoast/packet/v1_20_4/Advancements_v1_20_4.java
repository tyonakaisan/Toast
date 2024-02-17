package com.github.tyonakaisan.yummytoast.packet.v1_20_4;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.reflect.accessors.Accessors;
import com.comphenix.protocol.reflect.accessors.ConstructorAccessor;
import com.comphenix.protocol.utility.MinecraftReflection;
import com.comphenix.protocol.wrappers.BukkitConverters;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.github.tyonakaisan.yummytoast.MinecraftAdvancement;
import com.github.tyonakaisan.yummytoast.Toast;
import com.github.tyonakaisan.yummytoast.packet.Advancements;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.json.JSONComponentSerializer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public final class Advancements_v1_20_4 extends Advancements {
    private static final ConstructorAccessor HOLDER_CONSTRUCTOR = Accessors.getConstructorAccessor(
            MinecraftAdvancement.HOLDER_CLASS,
            MinecraftAdvancement.MINECRAFT_KEY_CLASS,
            MinecraftAdvancement.ADVANCEMENT_CLASS
    );

    private static final ConstructorAccessor MINECRAFT_KEY_CONSTRUCTOR = Accessors.getConstructorAccessor(
            MinecraftAdvancement.MINECRAFT_KEY_CLASS,
            String.class,
            String.class
    );

    private static final ConstructorAccessor ADVANCEMENT_CONSTRUCTOR = Accessors.getConstructorAccessor(
            MinecraftAdvancement.ADVANCEMENT_CLASS,
            Optional.class,
            Optional.class,
            MinecraftAdvancement.REWARDS_CLASS,
            Map.class,
            MinecraftAdvancement.REQUIREMENTS_CLASS,
            boolean.class,
            Optional.class
    );

    private static final ConstructorAccessor REWARDS_CONSTRUCTOR = Accessors.getConstructorAccessor(
            MinecraftAdvancement.REWARDS_CLASS,
            int.class,
            List.class,
            List.class,
            Optional.class
    );

    private static final ConstructorAccessor REQUIREMENTS_CONSTRUCTOR = Accessors.getConstructorAccessor(
            MinecraftAdvancement.REQUIREMENTS_CLASS,
            List.class
    );

    private static final ConstructorAccessor DISPLAY_CONSTRUCTOR = Accessors.getConstructorAccessor(
            MinecraftAdvancement.DISPLAY_CLASS,
            MinecraftReflection.getItemStackClass(), // icon
            MinecraftReflection.getIChatBaseComponentClass(), // title
            MinecraftReflection.getIChatBaseComponentClass(), // description
            Optional.class, // background
            MinecraftAdvancement.FRAME_TYPE_CLASS, // frame
            boolean.class, // showToast
            boolean.class, // announceToChat
            boolean.class // hidden
    );

    private static final ConstructorAccessor PROGRESS_CONSTRUCTOR = Accessors.getConstructorAccessor(
            MinecraftAdvancement.PROGRESS_CLASS,
            Map.class
    );

    private static final ConstructorAccessor CRITERION_PROGRESS_CONSTRUCTOR = Accessors.getConstructorAccessor(
            MinecraftAdvancement.CRITERION_PROGRESS_CLASS,
            Instant.class
    );

    public Advancements_v1_20_4(@NotNull final Toast toast) {
        super(toast);
    }

    @Override
    public void sendTo(@NotNull final Player player) {
        var type = PacketType.Play.Server.ADVANCEMENTS;
        var addPacket = ProtocolLibrary.getProtocolManager().createPacket(type);
        var removePacket = ProtocolLibrary.getProtocolManager().createPacket(type);

        var addPacketModifier = addPacket.getModifier();
        addPacketModifier.writeSafely(1, List.of(this.craftAdvancementHolder()));
        addPacketModifier.writeSafely(3, Map.of(this.craftMinecraftKey(), this.craftProgress()));

        var removePacketModifier = removePacket.getModifier();
        removePacketModifier.writeSafely(2, Set.of(this.craftMinecraftKey()));

        ProtocolLibrary.getProtocolManager().sendServerPacket(player, addPacket);
        ProtocolLibrary.getProtocolManager().sendServerPacket(player, removePacket);
    }

    public Object craftAdvancementHolder() {
        return HOLDER_CONSTRUCTOR.invoke(
                this.craftMinecraftKey(),
                this.craftAdvancement()
        );
    }

    private Object craftMinecraftKey() {
        return MINECRAFT_KEY_CONSTRUCTOR.invoke(
                NAMESPACE,
                VALUE
        );
    }

    private Object craftAdvancement() {
        return ADVANCEMENT_CONSTRUCTOR.invoke(
                Optional.empty(),
                Optional.of(this.craftDisplay()),
                this.craftReward(),
                Map.of(),
                this.craftRequirement(),
                true,
                Optional.empty()
        );
    }

    private Object craftReward() {
        return REWARDS_CONSTRUCTOR.invoke(
                0,
                List.of(),
                List.of(),
                Optional.empty()
        );
    }

    private Object craftRequirement() {
        return REQUIREMENTS_CONSTRUCTOR.invoke(
                List.of(List.of(TRIGGER))
        );
    }

    private Object craftDisplay() {
        var title = WrappedChatComponent.fromJson(JSONComponentSerializer.json().serialize(this.toast.getTitle()));
        var description =WrappedChatComponent.fromJson(JSONComponentSerializer.json().serialize(Component.empty()));

        return DISPLAY_CONSTRUCTOR.invoke(
                BukkitConverters.getItemStackConverter().getGeneric(this.toast.getIcon()),
                BukkitConverters.getWrappedChatComponentConverter().getGeneric(title),
                BukkitConverters.getWrappedChatComponentConverter().getGeneric(description),
                Optional.empty(),
                MinecraftAdvancement.FRAME_TYPE_CLASS.getEnumConstants()[this.toast.getFrameType().ordinal()],
                true,
                false,
                false
        );
    }

    private Object craftProgress() {
        return PROGRESS_CONSTRUCTOR.invoke(
                Map.of(TRIGGER, this.craftCriterion())
        );
    }

    private Object craftCriterion() {
        return CRITERION_PROGRESS_CONSTRUCTOR.invoke(
                Instant.now()
        );
    }
}
