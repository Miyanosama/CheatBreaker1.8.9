package org.apache.log4j.lf5.viewer.categoryexplorer;

import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$NotEnoughDataDecoderException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import net.minecraft.entity.monster.EntitySlime$AISlimeFaceRandom;
import net.optifine.Lang;

public class CategoryNodeEditor$2 extends MouseAdapter {
   public EntitySlime$AISlimeFaceRandom field_0001;
   public CategoryNodeEditor this$0;
   public HttpPostRequestDecoder$NotEnoughDataDecoderException field_0000;
   public Lang field_0002;

   public void mousePressed(MouseEvent var1) {
      if ((var1.getModifiers() & 4) != 0) {
         this.this$0.showPopup(this.this$0._lastEditedNode, var1.getX(), var1.getY());
      }

      this.this$0.stopCellEditing();
   }

   public CategoryNodeEditor$2(CategoryNodeEditor var1) {
      this.this$0 = var1;
      super();
   }
}
