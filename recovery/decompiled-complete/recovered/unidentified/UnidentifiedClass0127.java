package recovered.unidentified;

import net.minecraft.client.renderer.EnumFaceDirection$VertexInformation;
import net.minecraft.client.renderer.entity.layers.LayerEnderDragonEyes;
import net.minecraft.client.renderer.entity.layers.LayerSpiderEyes;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.ResourceLocation;

public class UnidentifiedClass0127 {
   public LayerEnderDragonEyes field_0004;
   public S35PacketUpdateTileEntity field_0007;
   public InventoryPlayer field_0003;
   public String field_0006;
   public Object field_0000;
   public ResourceLocation field_0001;
   public EnumFaceDirection$VertexInformation field_0008;
   public UnidentifiedClass0000 field_0005;
   public LayerSpiderEyes field_0002;

   public ResourceLocation method_00994() {
      return this.field_0001;
   }

   public String method_00995() {
      return this.field_0006;
   }

   public Object method_00993() {
      return this.field_0000;
   }

   public UnidentifiedClass0127(Object var1, String var2, ResourceLocation var3) {
      this.field_0000 = var1;
      this.field_0006 = var2;
      this.field_0001 = var3;
   }
}
