package com.github.tyonakaisan.yummytoast;

import com.comphenix.protocol.utility.MinecraftReflection;

public final class MinecraftAdvancement {

    private MinecraftAdvancement() {}

    /**
     * Get AdvancementHolder class.
     */
    public static final Class<?> HOLDER_CLASS = MinecraftReflection.getMinecraftClass("advancements.AdvancementHolder");
    /**
     * Get Advancement class.
     */
    public static final Class<?> ADVANCEMENT_CLASS = MinecraftReflection.getMinecraftClass("advancements.Advancement", "Advancement");
    /**
     * Get AdvancementRewards class.
     */
    public static final Class<?> REWARDS_CLASS = MinecraftReflection.getMinecraftClass("advancements.AdvancementRewards", "AdvancementRewards");
    /**
     * Get AdvancementRequirements class.
     */
    public static final Class<?> REQUIREMENTS_CLASS = MinecraftReflection.getMinecraftClass("advancements.AdvancementRequirements", "AdvancementRequirements");
    /**
     * Get AdvancementDisplay class.
     */
    public static final Class<?> DISPLAY_CLASS = MinecraftReflection.getMinecraftClass("advancements.AdvancementDisplay", "AdvancementDisplay");
    /**
     * Get AdvancementFrameType class.
     */
    public static final Class<?> FRAME_TYPE_CLASS = MinecraftReflection.getMinecraftClass("advancements.AdvancementFrameType", "AdvancementFrameType");
    /**
     * Get AdvancementProgress class.
     */
    public static final Class<?> PROGRESS_CLASS = MinecraftReflection.getMinecraftClass("advancements.AdvancementProgress");
    /**
     * Get CriterionProgress class.
     */
    public static final Class<?> CRITERION_PROGRESS_CLASS = MinecraftReflection.getMinecraftClass("advancements.CriterionProgress");
    /**
     * Get the MinecraftKey class.
     * Also known as the ResourceLocation class.
     */
    public static final Class<?> MINECRAFT_KEY_CLASS = MinecraftReflection.getMinecraftKeyClass();
}
