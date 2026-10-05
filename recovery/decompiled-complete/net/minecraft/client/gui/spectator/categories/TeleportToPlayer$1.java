package net.minecraft.client.gui.spectator.categories;

import com.google.common.collect.ComparisonChain;
import io.netty.handler.codec.spdy.SpdyHttpResponseStreamIdHandler;
import java.util.Comparator;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.ai.attributes.ServersideAttributeMap;
import net.minecraft.realms.RealmsVertexFormatElement;
import net.minecraft.util.Cartesian$Product$ProductIterator;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNodeEditor$5;
import recovered.unidentified.UnidentifiedClass3909;

public class TeleportToPlayer$1 implements Comparator<NetworkPlayerInfo> {
   public RealmsVertexFormatElement field_0003;
   public CategoryNodeEditor$5 field_0005;
   public ServersideAttributeMap field_0002;
   public SpdyHttpResponseStreamIdHandler field_0004;
   public UnidentifiedClass3909 field_0000;
   public Cartesian$Product$ProductIterator field_0001;

   public int compare(NetworkPlayerInfo var1, NetworkPlayerInfo var2) {
      return ComparisonChain.start().compare(var1.getGameProfile().getId(), var2.getGameProfile().getId()).result();
   }
}
