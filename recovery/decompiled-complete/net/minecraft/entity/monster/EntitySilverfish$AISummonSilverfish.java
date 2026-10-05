package net.minecraft.entity.monster;

import io.netty.channel.group.ChannelMatchers$1;
import io.netty.handler.timeout.IdleStateHandler$1;
import java.util.Random;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.EntityPortalFX$Factory;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item$5;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import recovered.unidentified.UnidentifiedClass5030;

public class EntitySilverfish$AISummonSilverfish extends EntityAIBase {
   public ChannelMatchers$1 field_0003;
   public Item$5 field_0005;
   public EntityPortalFX$Factory field_0002;
   public IdleStateHandler$1 field_0004;
   public UnidentifiedClass5030 field_0000;
   public int field_179463_b;
   public EntitySilverfish silverfish;

   @Override
   public void updateTask() {
      this.field_179463_b--;
      if (this.field_179463_b <= 0) {
         World var1 = this.silverfish.o;
         Random var2 = this.silverfish.getRNG();
         BlockPos var3 = new BlockPos(this.silverfish);

         for (int var4 = 0; var4 <= 5 && var4 >= -5; var4 = var4 <= 0 ? 1 - var4 : 0 - var4) {
            for (int var5 = 0; var5 <= 10 && var5 >= -10; var5 = var5 <= 0 ? 1 - var5 : 0 - var5) {
               for (int var6 = 0; var6 <= 10 && var6 >= -10; var6 = var6 <= 0 ? 1 - var6 : 0 - var6) {
                  BlockPos var7 = var3.add(var5, var4, var6);
                  IBlockState var8 = var1.getBlockState(var7);
                  if (var8.getBlock() == Blocks.monster_egg) {
                     if (var1.Q().getBoolean("mobGriefing")) {
                        var1.destroyBlock(var7, true);
                     } else {
                        var1.a(var7, var8.getValue(BlockSilverfish.VARIANT).getModelBlock(), 3);
                     }

                     if (var2.nextBoolean()) {
                        return;
                     }
                  }
               }
            }
         }
      }
   }

   @Override
   public boolean shouldExecute() {
      return this.field_179463_b > 0;
   }

   public void func_179462_f() {
      if (this.field_179463_b == 0) {
         this.field_179463_b = 20;
      }
   }

   public EntitySilverfish$AISummonSilverfish(EntitySilverfish var1) {
      this.silverfish = var1;
   }
}
