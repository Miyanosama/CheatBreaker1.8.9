package net.minecraft.entity.item;

import com.google.common.collect.Lists;
import io.netty.channel.sctp.nio.NioSctpChannel;
import java.util.ArrayList;
import net.minecraft.client.renderer.StitcherException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class EntityPainting extends EntityHanging {
   public S2BPacketChangeGameState field_0000;
   public EntitySilverfish field_0001;
   public NioSctpChannel field_0004;
   public StitcherException field_0003;
   public EntityPainting$EnumArt art;

   @Override
   public void writeEntityToNBT(NBTTagCompound var1) {
      var1.setString("Motive", this.art.title);
      super.writeEntityToNBT(var1);
   }

   public EntityPainting(World var1, BlockPos var2, EnumFacing var3, String var4) {
      this(var1, var2, var3);

      for (EntityPainting$EnumArt var8 : EntityPainting$EnumArt.values()) {
         if (var8.title.equals(var4)) {
            this.art = var8;
            break;
         }
      }

      this.a(var3);
   }

   public EntityPainting(World var1) {
      super(var1);
   }

   public EntityPainting(World var1, BlockPos var2, EnumFacing var3) {
      super(var1, var2);
      ArrayList var4 = Lists.newArrayList();

      for (EntityPainting$EnumArt var8 : EntityPainting$EnumArt.values()) {
         this.art = var8;
         this.a(var3);
         if (this.j()) {
            var4.add(var8);
         }
      }

      if (!var4.isEmpty()) {
         this.art = (EntityPainting$EnumArt)var4.get(this.V.nextInt(var4.size()));
      }

      this.a(var3);
   }

   @Override
   public void onBroken(Entity var1) {
      if (this.o.Q().getBoolean("doEntityDrops")) {
         if (var1 instanceof EntityPlayer) {
            EntityPlayer var2 = (EntityPlayer)var1;
            if (var2.bA.isCreativeMode) {
               return;
            }
         }

         this.a(new ItemStack(Items.painting), 0.0F);
      }
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound var1) {
      String var2 = var1.getString("Motive");

      for (EntityPainting$EnumArt var6 : EntityPainting$EnumArt.values()) {
         if (var6.title.equals(var2)) {
            this.art = var6;
         }
      }

      if (this.art == null) {
         this.art = EntityPainting$EnumArt.KEBAB;
      }

      super.readEntityFromNBT(var1);
   }

   @Override
   public int getWidthPixels() {
      return this.art.sizeX;
   }

   @Override
   public void setPositionAndRotation2(double var1, double var3, double var5, float var7, float var8, int var9, boolean var10) {
      BlockPos var11 = this.a.add(var1 - this.s, var3 - this.t, var5 - this.u);
      this.b(var11.getX(), var11.getY(), var11.getZ());
   }

   @Override
   public void a_(double var1, double var3, double var5, float var7, float var8) {
      BlockPos var9 = this.a.add(var1 - this.s, var3 - this.t, var5 - this.u);
      this.b(var9.getX(), var9.getY(), var9.getZ());
   }

   @Override
   public int getHeightPixels() {
      return this.art.sizeY;
   }
}
