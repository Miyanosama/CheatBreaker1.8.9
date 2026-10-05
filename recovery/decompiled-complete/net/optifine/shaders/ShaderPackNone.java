package net.optifine.shaders;

import java.io.InputStream;
import net.minecraft.block.BlockHugeMushroom$1;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.optifine.entity.model.ModelAdapterSilverfish;

public class ShaderPackNone implements IShaderPack {
   public BlockHugeMushroom$1 field_0001;
   public ModelAdapterSilverfish field_0002;
   public S29PacketSoundEffect field_0000;

   @Override
   public InputStream getResourceAsStream(String var1) {
      return null;
   }

   @Override
   public void close() {
   }

   @Override
   public boolean hasDirectory(String var1) {
      return false;
   }

   @Override
   public String getName() {
      return "OFF";
   }
}
