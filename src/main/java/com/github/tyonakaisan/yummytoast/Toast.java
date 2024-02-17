package com.github.tyonakaisan.yummytoast;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;

/**
 * Toast is hop-up that appears when a player has achieved a progress achievement.
 */
@SuppressWarnings("unused")
public interface Toast {

    /**
     * Make {@link Toast}.
     * @return the {@link Toast}
     */
    static Toast make() {
        return new ToastImpl();
    }

    /**
     * Make {@link Toast}.
     *
     * @param icon the icon
     * @param title the title
     * @param frameType the frameType
     * @return the {@link Toast}
     */
    static Toast make(@NotNull final ItemStack icon, @NotNull final Component title, @NotNull final FrameType frameType) {
        return new ToastImpl(icon, title, frameType);
    }

    /**
     * Set toast icon.
     *
     * @param newIcon the newIcon
     * @return the {@link Toast}
     */
    @NotNull Toast icon(@NotNull final ItemStack newIcon);

    /**
     * Get the {@link ItemStack} used for the icon.
     *
     * @return the {@link ItemStack}
     */
    @NotNull ItemStack getIcon();

    /**
     * Set toast title.
     *
     * @param newTitle the newTitle
     * @return the {@link Toast}
     */
    @NotNull Toast title(@NotNull final Component newTitle);

    /**
     * Get the {@link Component} used for the title.
     * @return the {@link Component}
     */
    @NotNull Component getTitle();

    /**
     * Set toast {@link FrameType}.
     *
     * @param newFrameType the newFrameType
     * @return the {@link Toast}
     */
    @NotNull Toast frameType(@NotNull final FrameType newFrameType);

    /**
     * Get the {@link FrameType} used for the frame type.
     * @return the {@link FrameType}
     */
    @NotNull FrameType getFrameType();

    /**
     * Send this {@link Toast} to all specified {@link Player}s.
     * @param players the {@link Player}s
     */
    default void sendTo(@NotNull final Player... players) {
        this.sendTo(Arrays.asList(players));
    }

    /**
     * Send this {@link Toast} to all specified {@link Player}s.
     * @param players the {@link Player}s
     */
    default void sendTo(@NotNull final Collection<Player> players) {
        players.forEach(this::sendTo);
    }

    /**
     * Send this {@link Toast} to the {@link Player}.
     * @param player the {@link Player}
     */
    void sendTo(@NotNull final Player player);

    /**
     * Frame type.
     */
    enum FrameType {
        TASK,
        CHALLENGE,
        GOAL
    }
}
