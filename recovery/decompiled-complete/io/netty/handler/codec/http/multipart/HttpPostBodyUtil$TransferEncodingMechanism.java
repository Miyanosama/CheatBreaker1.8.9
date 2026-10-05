package io.netty.handler.codec.http.multipart;

import io.netty.handler.codec.compression.ZlibUtil$1;
import net.minecraft.block.BlockGrass;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.item.crafting.CraftingManager$1;
import net.minecraft.network.NetworkSystem$6;
import org.apache.log4j.pattern.NameAbbreviator$DropElementAbbreviator;

public enum HttpPostBodyUtil$TransferEncodingMechanism {
   BIT7("7bit"),
   BINARY("binary"),
   BIT8("8bit");

   public ZlibUtil$1 __junk7661051862432145901;
   public String value;
   public NetworkSystem$6 __junk7201090725141111639;
   public EntityAITempt __junk5191350756831314418;
   public CraftingManager$1 __junk3972009099942199412;
   public NameAbbreviator$DropElementAbbreviator __junk7781449886809290698;
   public BlockGrass __junk5252248661642505591;

   public HttpPostBodyUtil$TransferEncodingMechanism(String var3) {
      this.value = var3;
   }

   public HttpPostBodyUtil$TransferEncodingMechanism() {
      this.value = this.name();
   }

   @Override
   public String toString() {
      return this.value;
   }

   public String value() {
      return this.value;
   }
}
