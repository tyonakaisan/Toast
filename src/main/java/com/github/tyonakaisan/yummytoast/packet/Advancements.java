package com.github.tyonakaisan.yummytoast.packet;

import com.github.tyonakaisan.yummytoast.Toast;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class Advancements {

    @NotNull
    protected final Toast toast;

    protected static final String TRIGGER = "impossible";
    protected static final String NAMESPACE = "yummy_toast";
    protected static final String VALUE = "delicious";

    protected Advancements(@NotNull final Toast toast) {
        this.toast = toast;
    }

    public abstract void sendTo(@NotNull final Player player);
}
