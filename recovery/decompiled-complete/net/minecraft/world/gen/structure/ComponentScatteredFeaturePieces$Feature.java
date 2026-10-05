package net.minecraft.world.gen.structure;

import io.netty.channel.sctp.SctpChannelOption;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$CollectionView;
import java.util.Random;
import net.minecraft.client.model.ModelLeashKnot;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos$MutableBlockPos;
import net.minecraft.util.EnumFacing$Plane;
import net.minecraft.world.World;
import org.apache.log4j.DailyRollingFileAppender;
import org.java_websocket.exceptions.NotSendableException;
import recovered.unidentified.UnidentifiedClass0849;
import recovered.unidentified.UnidentifiedClass1187;

public abstract class ComponentScatteredFeaturePieces$Feature extends StructureComponent {
   public int c;
   public int scatteredFeatureSizeY;
   public int field_74936_d = -1;
   public UnidentifiedClass1187 field_0003;
   public NioSocketChannel field_0012;
   public NotSendableException field_0001;
   public ConcurrentHashMapV8$CollectionView field_0006;
   public DailyRollingFileAppender field_0007;
   public int a;
   public SctpChannelOption field_0009;
   public DefaultPlayerSkin field_0010;
   public ModelLeashKnot field_0000;
   public UnidentifiedClass0849 field_0004;

   public boolean a(World var1, StructureBoundingBox var2, int var3) {
      if (this.field_74936_d >= 0) {
         return true;
      } else {
         int var4 = 0;
         int var5 = 0;
         BlockPos$MutableBlockPos var6 = new BlockPos$MutableBlockPos();

         for (int var7 = this.l.minZ; var7 <= this.l.maxZ; var7++) {
            for (int var8 = this.l.minX; var8 <= this.l.maxX; var8++) {
               var6.set(var8, 64, var7);
               if (var2.isVecInside(var6)) {
                  var4 += Math.max(var1.getTopSolidOrLiquidBlock(var6).getY(), var1.t.getAverageGroundLevel());
                  var5++;
               }
            }
         }

         if (var5 == 0) {
            return false;
         } else {
            this.field_74936_d = var4 / var5;
            this.l.offset(0, this.field_74936_d - this.l.minY + var3, 0);
            return true;
         }
      }
   }

   public ComponentScatteredFeaturePieces$Feature() {
   }

   @Override
   public void writeStructureToNBT(NBTTagCompound var1) {
      var1.setInteger("Width", this.a);
      var1.setInteger("Height", this.scatteredFeatureSizeY);
      var1.setInteger("Depth", this.c);
      var1.setInteger("HPos", this.field_74936_d);
   }

   @Override
   public void readStructureFromNBT(NBTTagCompound var1) {
      this.a = var1.getInteger("Width");
      this.scatteredFeatureSizeY = var1.getInteger("Height");
      this.c = var1.getInteger("Depth");
      this.field_74936_d = var1.getInteger("HPos");
   }

   public ComponentScatteredFeaturePieces$Feature(Random var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      super(0);
      this.a = var5;
      this.scatteredFeatureSizeY = var6;
      this.c = var7;
      this.m = EnumFacing$Plane.HORIZONTAL.random(var1);
      switch (ComponentScatteredFeaturePieces$1.field_175956_a[this.m.ordinal()]) {
         case 1:
         case 2:
            this.l = new StructureBoundingBox(var2, var3, var4, var2 + var5 - 1, var3 + var6 - 1, var4 + var7 - 1);
            break;
         default:
            this.l = new StructureBoundingBox(var2, var3, var4, var2 + var7 - 1, var3 + var6 - 1, var4 + var5 - 1);
      }
   }
}
