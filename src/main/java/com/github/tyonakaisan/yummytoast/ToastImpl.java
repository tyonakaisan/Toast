package com.github.tyonakaisan.yummytoast;

import com.github.tyonakaisan.yummytoast.packet.Advancements;
import com.github.tyonakaisan.yummytoast.packet.v1_20_4.Advancements_v1_20_4;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

final class ToastImpl implements Toast {
    private ItemStack icon;
    private Component title;
    private FrameType frameType;

    private static final String NOT_NULL_ICON = "Icon must not be null.";
    private static final String NOT_NULL_TITLE = "Title must not be null.";
    private static final String NOT_NULL_FRAME_TYPE = "FrameType must not be null.";

    ToastImpl() {
        this.icon = new ItemStack(Material.STONE);
        this.title = Component.empty();
        this.frameType = FrameType.TASK;
    }

    ToastImpl(@NotNull final ItemStack icon, @NotNull final Component title, @NotNull final FrameType frameType) {
        Objects.requireNonNull(icon, NOT_NULL_ICON);
        Objects.requireNonNull(title, NOT_NULL_TITLE);
        Objects.requireNonNull(frameType, NOT_NULL_FRAME_TYPE);

        this.icon = icon;
        this.title = title;
        this.frameType = frameType;
    }

    @Override
    public @NotNull Toast icon(@NotNull final ItemStack newIcon) {
        Objects.requireNonNull(newIcon, NOT_NULL_ICON);

        if (!this.icon.equals(newIcon)) {
            this.icon = newIcon;
        }

        return this;
    }

    @Override
    public @NotNull ItemStack getIcon() {
        return this.icon;
    }

    @Override
    public @NotNull Toast title(@NotNull final Component newTitle) {
        Objects.requireNonNull(newTitle, NOT_NULL_TITLE);

        if (!this.title.equals(newTitle)) {
            this.title = newTitle;
        }

        return this;
    }

    @Override
    public @NotNull Component getTitle() {
        return this.title;
    }

    @Override
    public @NotNull Toast frameType(@NotNull final FrameType newFrameType) {
        Objects.requireNonNull(newFrameType, NOT_NULL_FRAME_TYPE);

        if (!this.frameType.equals(newFrameType)) {
            this.frameType = newFrameType;
        }

        return this;
    }

    @Override
    public @NotNull FrameType getFrameType() {
        return this.frameType;
    }

    @Override
    public void sendTo(@NotNull final Player player) {
        var version = Bukkit.getServer().getMinecraftVersion();
        Advancements advancement;

        if (version.equals("1.20.4")) {
            advancement = new Advancements_v1_20_4(this);
        } else {
            var message = String.format("[Toast] %s is not supported!", version);
            Bukkit.getLogger().warning(message);
            return;
        }

        advancement.sendTo(player);
    }
}
