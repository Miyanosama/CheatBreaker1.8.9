package io.netty.util.internal.chmv8;

import com.cheatbreaker.client.event.type.RenderWorldEvent;
import net.minecraft.client.model.ModelIronGolem;
import net.minecraft.creativetab.CreativeTabs$3;
import net.optifine.entity.model.ModelAdapterVillager;

public class ForkJoinPool$EmptyTask extends ForkJoinTask<Void> {
   public static long serialVersionUID;
   public RenderWorldEvent __junk840625480553107578;
   public ModelAdapterVillager __junk8077130281751284550;
   public ModelIronGolem __junk5650734628821104832;
   public CreativeTabs$3 __junk839278528782098971;

   public void setRawResult(Void var1) {
   }

   @Override
   public boolean exec() {
      return true;
   }

   public ForkJoinPool$EmptyTask() {
      this.status = -268435456;
   }

   public Void getRawResult() {
      return null;
   }
}
