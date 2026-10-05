package net.minecraft.world;

import net.minecraft.block.BlockButton$1;
import net.minecraft.client.model.ModelWither;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.network.EnumConnectionState$1;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import org.json.XML;

public class MinecraftException extends Exception {
   public EnumConnectionState$1 field_0003;
   public EntityMob field_0005;
   public ModelWither field_0002;
   public ExtendedBlockStorage field_0004;
   public XML field_0000;
   public BlockButton$1 field_0001;

   public MinecraftException(String var1) {
      super(var1);
   }
}
