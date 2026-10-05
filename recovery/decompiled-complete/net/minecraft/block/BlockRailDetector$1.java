package net.minecraft.block;

import com.cheatbreaker.client.ui.mainmenu.MainMenu;
import com.google.common.base.Predicate;
import io.netty.channel.socket.oio.OioSocketChannel$1;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.client.model.ModelWolf;
import net.minecraft.network.play.client.C12PacketUpdateSign;
import org.apache.log4j.lf5.viewer.categoryexplorer.TreeModelAdapter;

public class BlockRailDetector$1 implements Predicate<BlockRailBase$EnumRailDirection> {
   public MainMenu field_0003;
   public C12PacketUpdateSign field_0005;
   public ModelWolf field_0002;
   public ChatLine field_0004;
   public OioSocketChannel$1 field_0000;
   public TreeModelAdapter field_0001;

   public boolean apply(BlockRailBase$EnumRailDirection var1) {
      return var1 != BlockRailBase$EnumRailDirection.NORTH_EAST
         && var1 != BlockRailBase$EnumRailDirection.NORTH_WEST
         && var1 != BlockRailBase$EnumRailDirection.SOUTH_EAST
         && var1 != BlockRailBase$EnumRailDirection.SOUTH_WEST;
   }
}
