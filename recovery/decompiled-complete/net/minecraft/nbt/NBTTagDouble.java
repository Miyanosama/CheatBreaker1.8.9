package net.minecraft.nbt;

import com.cheatbreaker.client.util.cbagent.CBAgentResources;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ForEachEntryTask;
import java.io.DataInput;
import java.io.DataOutput;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.realms.RealmsLevelSummary;
import net.minecraft.util.MathHelper;
import net.minecraft.world.gen.layer.GenLayerAddSnow;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$1;
import net.optifine.shaders.ShaderProgramData;

public class NBTTagDouble extends NBTBase$NBTPrimitive {
   public ConcurrentHashMapV8$ForEachEntryTask field_0008;
   public CBAgentResources field_0001;
   public C01PacketChatMessage field_0004;
   public RealmsLevelSummary field_0005;
   public GenLayerAddSnow field_0003;
   public double data;
   public C0EPacketClickWindow field_0007;
   public ShaderProgramData field_0000;
   public StructureStrongholdPieces$1 field_0002;

   @Override
   public int getInt() {
      return MathHelper.floor_double(this.data);
   }

   @Override
   public byte getId() {
      return 6;
   }

   public NBTTagDouble() {
   }

   @Override
   public void write(DataOutput var1) {
      var1.writeDouble(this.data);
   }

   @Override
   public long getLong() {
      return (long)Math.floor(this.data);
   }

   @Override
   public NBTBase copy() {
      return new NBTTagDouble(this.data);
   }

   public NBTTagDouble(double var1) {
      this.data = var1;
   }

   @Override
   public void read(DataInput var1, int var2, NBTSizeTracker var3) {
      var3.read(2311196079284127889L & -2311196079685630336L);
      this.data = var1.readDouble();
   }

   @Override
   public double getDouble() {
      return this.data;
   }

   @Override
   public int hashCode() {
      long var1 = Double.doubleToLongBits(this.data);
      return super.hashCode() ^ (int)(var1 ^ var1 >>> 32);
   }

   @Override
   public float getFloat() {
      return (float)this.data;
   }

   @Override
   public String toString() {
      return "" + this.data + "d";
   }

   @Override
   public boolean equals(Object var1) {
      if (super.equals(var1)) {
         NBTTagDouble var2 = (NBTTagDouble)var1;
         return this.data == var2.data;
      } else {
         return false;
      }
   }

   @Override
   public byte getByte() {
      return (byte)(MathHelper.floor_double(this.data) & 0xFF);
   }

   @Override
   public short getShort() {
      return (short)(MathHelper.floor_double(this.data) & 65535);
   }
}
