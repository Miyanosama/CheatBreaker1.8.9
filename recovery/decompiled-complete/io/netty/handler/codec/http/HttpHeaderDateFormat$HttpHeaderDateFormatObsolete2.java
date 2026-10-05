package io.netty.handler.codec.http;

import io.netty.channel.sctp.nio.NioSctpChannel$2;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import net.minecraft.client.audio.GuardianSound;
import net.minecraft.client.renderer.block.model.BlockPartFace$Deserializer;
import net.minecraft.command.CommandEffect;
import net.minecraft.entity.ai.EntityJumpHelper;
import net.optifine.gui.GuiChatOF;
import recovered.unidentified.UnidentifiedClass1294;

public class HttpHeaderDateFormat$HttpHeaderDateFormatObsolete2 extends SimpleDateFormat {
   public GuardianSound __junk7380456068444938143;
   public GuiChatOF __junk3005928741464685233;
   public static long serialVersionUID;
   public UnidentifiedClass1294 __junk627634064550977563;
   public CommandEffect __junk9157064901078981884;
   public NioSctpChannel$2 __junk8096420413976916774;
   public BlockPartFace$Deserializer __junk7663003514031244871;
   public EntityJumpHelper __junk7307286537927296829;

   public HttpHeaderDateFormat$HttpHeaderDateFormatObsolete2() {
      super("E MMM d HH:mm:ss yyyy", Locale.ENGLISH);
      this.setTimeZone(TimeZone.getTimeZone("GMT"));
   }
}
