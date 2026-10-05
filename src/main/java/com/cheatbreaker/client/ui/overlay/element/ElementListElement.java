package com.cheatbreaker.client.ui.overlay.element;

import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import java.util.ArrayList;
import java.util.List;

public class ElementListElement<T extends AbstractElement> extends AbstractElement {
   public List<T> elements = new ArrayList<>();

   @Override
   public void handleElementUpdate() {
      this.elements.forEach(AbstractElement::handleElementUpdate);
   }

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      for (AbstractElement var5 : this.elements) {
         var5.drawElement(var1, var2, var3);
      }
   }

   @Override
   public void handleElementKeyTyped(char var1, int var2) {
      this.elements.forEach(var2x -> var2x.handleElementKeyTyped(var1, var2));
   }

   @Override
   public boolean handleElementMouseClicked(float var1, float var2, int var3, boolean var4) {
      if (!var4) {
         return false;
      } else {
         boolean var5 = false;

         for (AbstractElement var7 : this.elements) {
            if (var5) {
               break;
            }

            var5 = var7.handleElementMouseClicked(var1, var2, var3, var4);
         }

         return var5;
      }
   }

   public ElementListElement(List<T> var1) {
      this.elements.addAll(var1);
   }

   @Override
   public boolean handleElementMouseRelease(float var1, float var2, int var3, boolean var4) {
      if (!var4) {
         return false;
      } else {
         boolean var5 = false;

         for (AbstractElement var7 : this.elements) {
            if (var5) {
               break;
            }

            var5 = var7.handleElementMouseRelease(var1, var2, var3, var4);
         }

         return var5;
      }
   }

   public List<T> getElements() {
      return this.elements;
   }

   @Override
   public void handleElementClose() {
      this.elements.forEach(AbstractElement::handleElementClose);
   }
}
