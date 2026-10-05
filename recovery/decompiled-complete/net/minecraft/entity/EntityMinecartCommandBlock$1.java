package net.minecraft.entity;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.cors.CorsConfig;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.util.BlockPos;
import net.minecraft.util.HttpUtil$1;
import net.minecraft.util.IChatComponent$Serializer;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityMinecartCommandBlock$1 extends CommandBlockLogic {
   public HttpUtil$1 field_0000;
   public CorsConfig field_0002;

   @Override
   public void func_145757_a(ByteBuf var1) {
      var1.writeInt(this.field_145768_a.F());
   }

   @Override
   public void updateCommand() {
      this.field_145768_a.H().updateObject(23, this.getCommand());
      this.field_145768_a.H().updateObject(24, IChatComponent$Serializer.componentToJson(this.getLastOutput()));
   }

   @Override
   public int func_145751_f() {
      return 1;
   }

   @Override
   public World s_() {
      return this.field_145768_a.o;
   }

   @Override
   public Vec3 q_() {
      return new Vec3(this.field_145768_a.s, this.field_145768_a.t, this.field_145768_a.u);
   }

   public EntityMinecartCommandBlock$1(EntityMinecartCommandBlock var1) {
      this.field_145768_a = var1;
      super();
   }

   @Override
   public BlockPos getPosition() {
      return new BlockPos(this.field_145768_a.s, this.field_145768_a.t + 0.5, this.field_145768_a.u);
   }

   @Override
   public Entity p_() {
      return this.field_145768_a;
   }
}
