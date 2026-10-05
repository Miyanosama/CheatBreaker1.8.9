package net.minecraft.nbt;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.network.LanServerDetector;
import net.minecraft.client.renderer.entity.RenderItem$9;
import net.minecraft.network.play.client.C0FPacketConfirmTransaction;

public class JsonToNBT$List extends JsonToNBT$Any {
   public List<JsonToNBT$Any> field_150492_b = Lists.newArrayList();
   public LanServerDetector field_0000;
   public RenderItem$9 field_0001;
   public C0FPacketConfirmTransaction field_0003;

   public JsonToNBT$List(String var1) {
      this.a = var1;
   }

   @Override
   public NBTBase parse() {
      NBTTagList var1 = new NBTTagList();

      for (JsonToNBT$Any var3 : this.field_150492_b) {
         var1.appendTag(var3.parse());
      }

      return var1;
   }
}
