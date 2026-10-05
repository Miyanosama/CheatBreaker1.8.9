package net.minecraft.client.gui.stream;

import net.minecraft.client.particle.EntityBubbleFX$Factory;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.gen.layer.GenLayerAddSnow;
import org.apache.log4j.lf5.viewer.LogFactor5ErrorDialog$1;
import recovered.unidentified.UnidentifiedClass1534;

public enum GuiStreamUnavailable$Reason {
   ACCOUNT_NOT_MIGRATED(
      new ChatComponentTranslation("stream.unavailable.account_not_migrated"), new ChatComponentTranslation("stream.unavailable.account_not_migrated.okay")
   ),
   FAILED_TWITCH_AUTH_ERROR(new ChatComponentTranslation("stream.unavailable.failed_auth_error")),
   UNSUPPORTED_OS_OTHER(new ChatComponentTranslation("stream.unavailable.not_supported.other")),
   UNSUPPORTED_OS_MAC(
      new ChatComponentTranslation("stream.unavailable.not_supported.mac"), new ChatComponentTranslation("stream.unavailable.not_supported.mac.okay")
   ),
   UNKNOWN(new ChatComponentTranslation("stream.unavailable.unknown"), new ChatComponentTranslation("stream.unavailable.report_to_mojang")),
   LIBRARY_ARCH_MISMATCH(new ChatComponentTranslation("stream.unavailable.library_arch_mismatch")),
   LIBRARY_FAILURE(new ChatComponentTranslation("stream.unavailable.library_failure"), new ChatComponentTranslation("stream.unavailable.report_to_mojang")),
   NO_FBO(new ChatComponentTranslation("stream.unavailable.no_fbo")),
   UNSUPPORTED_OS_WINDOWS(new ChatComponentTranslation("stream.unavailable.not_supported.windows")),
   FAILED_TWITCH_AUTH(new ChatComponentTranslation("stream.unavailable.failed_auth"), new ChatComponentTranslation("stream.unavailable.failed_auth.okay")),
   ACCOUNT_NOT_BOUND(
      new ChatComponentTranslation("stream.unavailable.account_not_bound"), new ChatComponentTranslation("stream.unavailable.account_not_bound.okay")
   ),
   INITIALIZATION_FAILURE(
      new ChatComponentTranslation("stream.unavailable.initialization_failure"), new ChatComponentTranslation("stream.unavailable.report_to_mojang")
   );

   public UnidentifiedClass1534 field_0016;
   public IChatComponent field_152575_n;
   // $VF: synthetic field
   public static GuiStreamUnavailable$Reason[] $VALUES = new GuiStreamUnavailable$Reason[]{
      GuiStreamUnavailable$Reason.NO_FBO,
      GuiStreamUnavailable$Reason.LIBRARY_ARCH_MISMATCH,
      GuiStreamUnavailable$Reason.LIBRARY_FAILURE,
      GuiStreamUnavailable$Reason.UNSUPPORTED_OS_WINDOWS,
      GuiStreamUnavailable$Reason.UNSUPPORTED_OS_MAC,
      GuiStreamUnavailable$Reason.UNSUPPORTED_OS_OTHER,
      ACCOUNT_NOT_MIGRATED,
      GuiStreamUnavailable$Reason.ACCOUNT_NOT_BOUND,
      GuiStreamUnavailable$Reason.FAILED_TWITCH_AUTH,
      FAILED_TWITCH_AUTH_ERROR,
      GuiStreamUnavailable$Reason.INITIALIZATION_FAILURE,
      GuiStreamUnavailable$Reason.UNKNOWN
   };
   public LogFactor5ErrorDialog$1 field_0012;
   public EntityBubbleFX$Factory field_0009;
   public GenLayerAddSnow field_0013;
   public IChatComponent field_152574_m;

   public GuiStreamUnavailable$Reason(IChatComponent var3, IChatComponent var4) {
      this.field_152574_m = var3;
      this.field_152575_n = var4;
   }

   public IChatComponent func_152559_b() {
      return this.field_152575_n;
   }

   public GuiStreamUnavailable$Reason(IChatComponent var3) {
      this(var3, (IChatComponent)null);
   }

   public IChatComponent func_152561_a() {
      return this.field_152574_m;
   }
}
