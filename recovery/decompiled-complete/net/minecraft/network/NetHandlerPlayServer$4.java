package net.minecraft.network;

import io.netty.handler.codec.http.websocketx.WebSocket08FrameEncoder;
import io.netty.util.concurrent.MultithreadEventExecutorGroup$PowerOfTwoEventExecutorChooser;
import net.minecraft.network.play.client.C07PacketPlayerDigging$Action;
import net.minecraft.network.play.client.C0BPacketEntityAction$Action;
import net.minecraft.network.play.client.C16PacketClientStatus$EnumState;
import net.optifine.shaders.ShaderPackNone;
import org.apache.log4j.config.PropertyGetter;
import recovered.unidentified.UnidentifiedClass0842;

// $VF: synthetic class
public class NetHandlerPlayServer$4 {
   public WebSocket08FrameEncoder field_0003;
   public UnidentifiedClass0842 field_0006;
   public ShaderPackNone field_0002;
   public MultithreadEventExecutorGroup$PowerOfTwoEventExecutorChooser field_0005;
   public PropertyGetter field_0004;

   static {
      try {
         field_180223_c[C16PacketClientStatus$EnumState.PERFORM_RESPAWN.ordinal()] = 1;
      } catch (NoSuchFieldError var16) {
      }

      try {
         field_180223_c[C16PacketClientStatus$EnumState.REQUEST_STATS.ordinal()] = 2;
      } catch (NoSuchFieldError var15) {
      }

      try {
         field_180223_c[C16PacketClientStatus$EnumState.OPEN_INVENTORY_ACHIEVEMENT.ordinal()] = 3;
      } catch (NoSuchFieldError var14) {
      }

      field_180222_b = new int[C0BPacketEntityAction$Action.values().length];

      try {
         field_180222_b[C0BPacketEntityAction$Action.START_SNEAKING.ordinal()] = 1;
      } catch (NoSuchFieldError var13) {
      }

      try {
         field_180222_b[C0BPacketEntityAction$Action.STOP_SNEAKING.ordinal()] = 2;
      } catch (NoSuchFieldError var12) {
      }

      try {
         field_180222_b[C0BPacketEntityAction$Action.START_SPRINTING.ordinal()] = 3;
      } catch (NoSuchFieldError var11) {
      }

      try {
         field_180222_b[C0BPacketEntityAction$Action.STOP_SPRINTING.ordinal()] = 4;
      } catch (NoSuchFieldError var10) {
      }

      try {
         field_180222_b[C0BPacketEntityAction$Action.STOP_SLEEPING.ordinal()] = 5;
      } catch (NoSuchFieldError var9) {
      }

      try {
         field_180222_b[C0BPacketEntityAction$Action.RIDING_JUMP.ordinal()] = 6;
      } catch (NoSuchFieldError var8) {
      }

      try {
         field_180222_b[C0BPacketEntityAction$Action.OPEN_INVENTORY.ordinal()] = 7;
      } catch (NoSuchFieldError var7) {
      }

      field_180224_a = new int[C07PacketPlayerDigging$Action.values().length];

      try {
         field_180224_a[C07PacketPlayerDigging$Action.DROP_ITEM.ordinal()] = 1;
      } catch (NoSuchFieldError var6) {
      }

      try {
         field_180224_a[C07PacketPlayerDigging$Action.DROP_ALL_ITEMS.ordinal()] = 2;
      } catch (NoSuchFieldError var5) {
      }

      try {
         field_180224_a[C07PacketPlayerDigging$Action.RELEASE_USE_ITEM.ordinal()] = 3;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_180224_a[C07PacketPlayerDigging$Action.START_DESTROY_BLOCK.ordinal()] = 4;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_180224_a[C07PacketPlayerDigging$Action.ABORT_DESTROY_BLOCK.ordinal()] = 5;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_180224_a[C07PacketPlayerDigging$Action.STOP_DESTROY_BLOCK.ordinal()] = 6;
      } catch (NoSuchFieldError var1) {
      }
   }
}
