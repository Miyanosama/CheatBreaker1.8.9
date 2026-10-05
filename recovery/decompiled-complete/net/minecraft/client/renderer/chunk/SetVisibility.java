package net.minecraft.client.renderer.chunk;

import com.cheatbreaker.client.ui.element.module.ModuleSettingsElement;
import java.util.Set;
import net.minecraft.client.renderer.entity.RenderItem$8;
import net.minecraft.network.play.server.S2APacketParticles;
import net.minecraft.util.EnumFacing;

public class SetVisibility {
   public static int COUNT_FACES = EnumFacing.values().length;
   public long bits;
   public S2APacketParticles field_0001;
   public ModuleSettingsElement field_0003;
   public RenderItem$8 field_0000;

   public void setManyVisible(Set<EnumFacing> var1) {
      for (EnumFacing var3 : var1) {
         for (EnumFacing var5 : var1) {
            this.setVisible(var3, var5, true);
         }
      }
   }

   public void setAllVisible(boolean var1) {
      if (var1) {
         this.bits = -1L & -1L;
      } else {
         this.bits = -4486919479736786432L & 4486919477837223048L;
      }
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(' ');

      for (EnumFacing var5 : EnumFacing.values()) {
         var1.append(' ').append(var5.toString().toUpperCase().charAt(0));
      }

      var1.append('\n');

      for (EnumFacing var14 : EnumFacing.values()) {
         var1.append(var14.toString().toUpperCase().charAt(0));

         for (EnumFacing var9 : EnumFacing.values()) {
            if (var14 == var9) {
               var1.append("  ");
            } else {
               boolean var10 = this.isVisible(var14, var9);
               var1.append(' ').append((char)(var10 ? 'Y' : 'n'));
            }
         }

         var1.append('\n');
      }

      return var1.toString();
   }

   public void setVisible(EnumFacing var1, EnumFacing var2, boolean var3) {
      this.setBit(var1.ordinal() + var2.ordinal() * COUNT_FACES, var3);
      this.setBit(var2.ordinal() + var1.ordinal() * COUNT_FACES, var3);
   }

   public void setBit(int var1, boolean var2) {
      if (var2) {
         this.setBit(var1);
      } else {
         this.clearBit(var1);
      }
   }

   public boolean isVisible(EnumFacing var1, EnumFacing var2) {
      return this.getBit(var1.ordinal() + var2.ordinal() * COUNT_FACES);
   }

   public void clearBit(int var1) {
      this.bits &= ~(1 << var1);
   }

   public boolean getBit(int var1) {
      return (this.bits & 1 << var1) != (8096924087510237198L & -8096924087856363760L);
   }

   public void setBit(int var1) {
      this.bits |= 1 << var1;
   }
}
