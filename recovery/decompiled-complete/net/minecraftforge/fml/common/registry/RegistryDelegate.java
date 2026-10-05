package net.minecraftforge.fml.common.registry;

import net.minecraft.util.ResourceLocation;

public interface RegistryDelegate<T> {
   Class<T> type();

   ResourceLocation name();

   T get();
}
