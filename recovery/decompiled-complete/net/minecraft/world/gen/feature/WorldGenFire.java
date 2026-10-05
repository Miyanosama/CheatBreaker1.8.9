package net.minecraft.world.gen.feature;

import com.cheatbreaker.client.nethandler.client.PacketClientVoice;
import io.netty.channel.sctp.oio.OioSctpServerChannel$OioSctpServerChannelConfig;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ForEachTransformedMappingTask;
import java.util.Random;
import net.minecraft.command.CommandException;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenFire extends WorldGenerator {
   public CommandException field_0003;
   public OioSctpServerChannel$OioSctpServerChannelConfig field_0005;
   public PacketClientVoice field_0002;
   public EntityBoat field_0004;
   public ConcurrentHashMapV8$ForEachTransformedMappingTask field_0000;
   public EntityAIOwnerHurtByTarget field_0001;

   @Override
   public boolean generate(World var1, Random var2, BlockPos var3) {
      for (int var4 = 0; var4 < 64; var4++) {
         BlockPos var5 = var3.add(var2.nextInt(8) - var2.nextInt(8), var2.nextInt(4) - var2.nextInt(4), var2.nextInt(8) - var2.nextInt(8));
         if (var1.isAirBlock(var5) && var1.getBlockState(var5.down()).getBlock() == Blocks.netherrack) {
            var1.a(var5, Blocks.fire.getDefaultState(), 2);
         }
      }

      return true;
   }
}
