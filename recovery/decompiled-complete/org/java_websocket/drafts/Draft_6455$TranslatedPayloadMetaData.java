package org.java_websocket.drafts;

import net.minecraft.util.ChatComponentProcessor;
import recovered.unidentified.UnidentifiedClass0715;

public class Draft_6455$TranslatedPayloadMetaData {
   public UnidentifiedClass0715 field_0002;
   public ChatComponentProcessor field_0001;
   public int realPackageSize;
   public int payloadLength;

   public int getRealPackageSize() {
      return this.realPackageSize;
   }

   public int getPayloadLength() {
      return this.payloadLength;
   }

   public Draft_6455$TranslatedPayloadMetaData(Draft_6455 var1, int var2, int var3) {
      this.this$0 = var1;
      super();
      this.payloadLength = var2;
      this.realPackageSize = var3;
   }
}
