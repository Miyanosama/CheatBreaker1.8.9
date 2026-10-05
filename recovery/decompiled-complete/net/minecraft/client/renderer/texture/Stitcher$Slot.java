package net.minecraft.client.renderer.texture;

import com.google.common.collect.Lists;
import io.netty.buffer.UnpooledDirectByteBuf;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandshakeHandler;
import java.util.List;
import net.minecraft.block.BlockPackedIce;
import net.minecraft.world.chunk.storage.RegionFile;

public class Stitcher$Slot {
   public int height;
   public int originX;
   public int width;
   public Stitcher$Holder holder;
   public UnpooledDirectByteBuf field_0000;
   public WebSocketServerProtocolHandshakeHandler field_0001;
   public List<Stitcher$Slot> subSlots;
   public int originY;
   public BlockPackedIce field_0002;
   public RegionFile field_0009;

   public Stitcher$Holder getStitchHolder() {
      return this.holder;
   }

   @Override
   public String toString() {
      return "Slot{originX="
         + this.originX
         + ", originY="
         + this.originY
         + ", width="
         + this.width
         + ", height="
         + this.height
         + ", texture="
         + this.holder
         + ", subSlots="
         + this.subSlots
         + '}';
   }

   public boolean addSlot(Stitcher$Holder var1) {
      if (this.holder != null) {
         return false;
      } else {
         int var2 = var1.getWidth();
         int var3 = var1.getHeight();
         if (var2 <= this.width && var3 <= this.height) {
            if (var2 == this.width && var3 == this.height) {
               this.holder = var1;
               return true;
            } else {
               if (this.subSlots == null) {
                  this.subSlots = Lists.newArrayListWithCapacity(1);
                  this.subSlots.add(new Stitcher$Slot(this.originX, this.originY, var2, var3));
                  int var4 = this.width - var2;
                  int var5 = this.height - var3;
                  if (var5 > 0 && var4 > 0) {
                     int var6 = Math.max(this.height, var4);
                     int var7 = Math.max(this.width, var5);
                     if (var6 >= var7) {
                        this.subSlots.add(new Stitcher$Slot(this.originX, this.originY + var3, var2, var5));
                        this.subSlots.add(new Stitcher$Slot(this.originX + var2, this.originY, var4, this.height));
                     } else {
                        this.subSlots.add(new Stitcher$Slot(this.originX + var2, this.originY, var4, var3));
                        this.subSlots.add(new Stitcher$Slot(this.originX, this.originY + var3, this.width, var5));
                     }
                  } else if (var4 == 0) {
                     this.subSlots.add(new Stitcher$Slot(this.originX, this.originY + var3, var2, var5));
                  } else if (var5 == 0) {
                     this.subSlots.add(new Stitcher$Slot(this.originX + var2, this.originY, var4, var3));
                  }
               }

               for (Stitcher$Slot var9 : this.subSlots) {
                  if (var9.addSlot(var1)) {
                     return true;
                  }
               }

               return false;
            }
         } else {
            return false;
         }
      }
   }

   public Stitcher$Slot(int var1, int var2, int var3, int var4) {
      this.originX = var1;
      this.originY = var2;
      this.width = var3;
      this.height = var4;
   }

   public int getOriginX() {
      return this.originX;
   }

   public int getOriginY() {
      return this.originY;
   }

   public void getAllStitchSlots(List<Stitcher$Slot> var1) {
      if (this.holder != null) {
         var1.add(this);
      } else if (this.subSlots != null) {
         for (Stitcher$Slot var3 : this.subSlots) {
            var3.getAllStitchSlots(var1);
         }
      }
   }
}
