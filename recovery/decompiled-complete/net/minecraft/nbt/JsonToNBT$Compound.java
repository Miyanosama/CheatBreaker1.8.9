package net.minecraft.nbt;

import com.google.common.collect.Lists;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$NotEnoughDataDecoderException;
import io.netty.handler.codec.marshalling.ThreadLocalUnmarshallerProvider;
import java.util.List;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces;

public class JsonToNBT$Compound extends JsonToNBT$Any {
   public ContainerFurnace field_0003;
   public ThreadLocalUnmarshallerProvider field_0000;
   public List<JsonToNBT$Any> field_150491_b = Lists.newArrayList();
   public HttpPostRequestDecoder$NotEnoughDataDecoderException field_0004;
   public ComponentScatteredFeaturePieces field_0002;

   public JsonToNBT$Compound(String var1) {
      this.a = var1;
   }

   @Override
   public NBTBase parse() {
      NBTTagCompound var1 = new NBTTagCompound();

      for (JsonToNBT$Any var3 : this.field_150491_b) {
         var1.setTag(var3.a, var3.parse());
      }

      return var1;
   }
}
