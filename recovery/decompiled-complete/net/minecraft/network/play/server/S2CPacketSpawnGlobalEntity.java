package net.minecraft.network.play.server;

import com.cheatbreaker.client.module.type.EnvironmentModule;
import net.minecraft.block.BlockHalfWoodSlab;
import net.minecraft.client.model.ModelDragon;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.MathHelper;
import org.apache.log4j.lf5.viewer.configure.ConfigurationManager;

public class S2CPacketSpawnGlobalEntity implements Packet<INetHandlerPlayClient> {
   public EnvironmentModule field_0005;
   public int type;
   public int x;
   public ConfigurationManager field_0007;
   public int y;
   public BlockHalfWoodSlab field_0002;
   public int entityId;
   public EntityTracker field_0006;
   public ModelDragon field_0003;
   public int z;
   public LayerBipedArmor field_0000;

   public int func_149050_e() {
      return this.y;
   }

   public int func_149051_d() {
      return this.x;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleSpawnGlobalEntity(this);
   }

   public S2CPacketSpawnGlobalEntity() {
   }

   public int func_149049_f() {
      return this.z;
   }

   public S2CPacketSpawnGlobalEntity(Entity var1) {
      this.entityId = var1.F();
      this.x = MathHelper.floor_double(var1.s * 32.0);
      this.y = MathHelper.floor_double(var1.t * 32.0);
      this.z = MathHelper.floor_double(var1.u * 32.0);
      if (var1 instanceof EntityLightningBolt) {
         this.type = 1;
      }
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.entityId = var1.readVarIntFromBuffer();
      this.type = var1.readByte();
      this.x = var1.readInt();
      this.y = var1.readInt();
      this.z = var1.readInt();
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeVarIntToBuffer(this.entityId);
      var1.writeByte(this.type);
      var1.writeInt(this.x);
      var1.writeInt(this.y);
      var1.writeInt(this.z);
   }

   public int func_149053_g() {
      return this.type;
   }

   public int func_149052_c() {
      return this.entityId;
   }
}
