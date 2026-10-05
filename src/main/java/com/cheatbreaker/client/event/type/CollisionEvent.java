package com.cheatbreaker.client.event.type;

import com.cheatbreaker.client.event.CancellableEvent;

import java.util.List;
import net.minecraft.util.AxisAlignedBB;

public class CollisionEvent extends CancellableEvent {
   public double recoveredField1263;
   public double recoveredField1264;
   public double recoveredField1265;
   public List<AxisAlignedBB> recoveredField1266;

   public double method_21507() {
      return this.recoveredField1263;
   }

   public CollisionEvent(List<AxisAlignedBB> var1, double var2, double var4, double var6) {
      this.recoveredField1266 = var1;
      this.recoveredField1265 = var2;
      this.recoveredField1264 = var4;
      this.recoveredField1263 = var6;
   }

   public List<AxisAlignedBB> method_21505() {
      return this.recoveredField1266;
   }

   public double method_21506() {
      return this.recoveredField1264;
   }

   public double method_21504() {
      return this.recoveredField1265;
   }
}
