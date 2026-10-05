package com.cheatbreaker.client.ui.overlay.element;

import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import com.cheatbreaker.client.util.Vec2d;
import java.util.concurrent.atomic.AtomicBoolean;
import org.lwjgl.input.Mouse;

public abstract class DraggableElement extends AbstractElement {
   public AtomicBoolean dragging;
   public Vec2d position = new Vec2d();

   public void updateDraggingPosition(float var1, float var2) {
      this.position.set(var1 - this.x, var2 - this.y);
      this.dragging.set(true);
   }

   public void drag(float var1, float var2) {
      if (this.dragging.get()) {
         if (!Mouse.isButtonDown(0)) {
            this.dragging.set(false);
            return;
         }

         double var3 = var1 - this.position.x;
         double var5 = var2 - this.position.y;
         this.setElementSize((float)var3, (float)var5, this.width, this.height);
      }
   }

   public DraggableElement() {
      this.dragging = new AtomicBoolean();
   }

   public void method_01849() {
      if (this.dragging.get()) {
         this.dragging.set(false);
      }
   }
}
