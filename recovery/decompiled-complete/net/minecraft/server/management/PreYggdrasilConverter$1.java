package net.minecraft.server.management;

import com.google.common.base.Predicate;
import javazoom.jl.decoder.LayerIDecoder;
import net.minecraft.client.particle.EntitySpellParticleFX$InstantFactory;
import net.minecraft.server.MinecraftServer$3;
import net.minecraft.util.StringUtils;
import net.optifine.util.KeyUtils;
import recovered.unidentified.UnidentifiedEnum4287;

public class PreYggdrasilConverter$1 implements Predicate<String> {
   public EntitySpellParticleFX$InstantFactory field_0002;
   public KeyUtils field_0004;
   public MinecraftServer$3 field_0001;
   public LayerIDecoder field_0003;
   public UnidentifiedEnum4287 field_0000;

   public boolean apply(String var1) {
      return !StringUtils.isNullOrEmpty(var1);
   }
}
