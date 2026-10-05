package net.minecraft.world.demo;

import io.netty.buffer.SwappedByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.server.management.ItemInWorldManager;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class DemoWorldManager extends ItemInWorldManager {
   public int field_73104_e;
   public int field_73102_f;
   public boolean demoTimeExpired;
   public SwappedByteBuf field_0000;
   public boolean field_73105_c;

   @Override
   public boolean tryUseItem(EntityPlayer var1, World var2, ItemStack var3) {
      if (this.demoTimeExpired) {
         this.sendDemoReminder();
         return false;
      } else {
         return super.tryUseItem(var1, var2, var3);
      }
   }

   @Override
   public boolean activateBlockOrUseItem(EntityPlayer var1, World var2, ItemStack var3, BlockPos var4, EnumFacing var5, float var6, float var7, float var8) {
      if (this.demoTimeExpired) {
         this.sendDemoReminder();
         return false;
      } else {
         return super.activateBlockOrUseItem(var1, var2, var3, var4, var5, var6, var7, var8);
      }
   }

   @Override
   public void onBlockClicked(BlockPos var1, EnumFacing var2) {
      if (this.demoTimeExpired) {
         this.sendDemoReminder();
      } else {
         super.onBlockClicked(var1, var2);
      }
   }

   @Override
   public void updateBlockRemoving() {
      super.updateBlockRemoving();
      this.field_73102_f++;
      long var1 = this.theWorld.K();
      long var3 = var1 / (72711616L & 1056852093430160866L) + (-4929820875017895423L & 4929820873194013151L);
      if (!this.field_73105_c && this.field_73102_f > 20) {
         this.field_73105_c = true;
         this.b.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(5, 0.0F));
      }

      this.demoTimeExpired = var1 > (269088436L & 1074910972L);
      if (this.demoTimeExpired) {
         this.field_73104_e++;
      }

      if (var1 % (-5169797202619793962L & 33841096L) == (1258734070L & 3467278925708726772L)) {
         if (var3 <= (7480326L & -5238109474463378481L)) {
            this.b.addChatMessage(new ChatComponentTranslation("demo.day." + var3));
         }
      } else if (var3 == (908072579L & 135309377L)) {
         if (var1 == (848308086L & -1536038123622547227L)) {
            this.b.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(5, 101.0F));
         } else if (var1 == (621029567L & 8616763839667503791L)) {
            this.b.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(5, 102.0F));
         } else if (var1 == (3136135504886006778L & -3136135506178207489L)) {
            this.b.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(5, 103.0F));
         }
      } else if (var3 == (12723589L & -465840237528690091L) && var1 % (331636194L & 6631037606978190784L) == (-6246388888006240260L & 6246388886688650737L)) {
         this.b.addChatMessage(new ChatComponentTranslation("demo.day.warning"));
      }
   }

   public DemoWorldManager(World var1) {
      super(var1);
   }

   @Override
   public void blockRemoving(BlockPos var1) {
      if (!this.demoTimeExpired) {
         super.blockRemoving(var1);
      }
   }

   @Override
   public boolean tryHarvestBlock(BlockPos var1) {
      return this.demoTimeExpired ? false : super.tryHarvestBlock(var1);
   }

   public void sendDemoReminder() {
      if (this.field_73104_e > 100) {
         this.b.addChatMessage(new ChatComponentTranslation("demo.reminder"));
         this.field_73104_e = 0;
      }
   }
}
