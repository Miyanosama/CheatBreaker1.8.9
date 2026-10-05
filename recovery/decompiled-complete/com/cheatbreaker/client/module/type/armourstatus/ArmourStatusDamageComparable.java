package com.cheatbreaker.client.module.type.armourstatus;

import com.cheatbreaker.client.module.type.notifications.CBNotificationRenderer;
import io.netty.channel.AbstractChannelHandlerContext$10;
import java.util.List;
import javazoom.jl.decoder.LayerIDecoder$SubbandLayer1IntensityStereo;
import net.minecraft.creativetab.CreativeTabs$4;
import net.minecraft.entity.monster.EntitySilverfish$AIHideInStone;

public class ArmourStatusDamageComparable implements Comparable<ArmourStatusDamageComparable> {
   public CBNotificationRenderer field_0003;
   public String colorCode;
   public LayerIDecoder$SubbandLayer1IntensityStereo field_0002;
   public AbstractChannelHandlerContext$10 field_0004;
   public CreativeTabs$4 field_0000;
   public int percent;
   public EntitySilverfish$AIHideInStone field_0006;

   public int compare(ArmourStatusDamageComparable var1) {
      return Integer.compare(this.percent, var1.percent);
   }

   public ArmourStatusDamageComparable(int var1, String var2) {
      this.percent = var1;
      this.colorCode = var2;
   }

   public static String getDamageColor(List<ArmourStatusDamageComparable> var0, int var1) {
      for (ArmourStatusDamageComparable var3 : var0) {
         if (var1 <= var3.percent) {
            return var3.colorCode;
         }
      }

      return "f";
   }

   public int compareTo(ArmourStatusDamageComparable var1) {
      return this.compare(var1);
   }
}
