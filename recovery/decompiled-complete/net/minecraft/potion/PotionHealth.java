package net.minecraft.potion;

import io.netty.handler.codec.compression.JZlibEncoder$1;
import io.netty.util.internal.logging.MessageFormatter;
import net.minecraft.client.model.ModelArmorStand;
import net.minecraft.client.renderer.block.model.ModelBlockDefinition;
import net.minecraft.command.server.CommandMessageRaw;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.MapGenStronghold;

public class PotionHealth extends Potion {
   public MessageFormatter field_0000;
   public ModelArmorStand field_0002;
   public CommandMessageRaw field_0003;
   public ModelBlockDefinition field_0004;
   public MapGenStronghold field_0005;
   public JZlibEncoder$1 field_0001;

   @Override
   public boolean isInstant() {
      return true;
   }

   public PotionHealth(int var1, ResourceLocation var2, boolean var3, int var4) {
      super(var1, var2, var3, var4);
   }

   @Override
   public boolean isReady(int var1, int var2) {
      return var1 >= 1;
   }
}
