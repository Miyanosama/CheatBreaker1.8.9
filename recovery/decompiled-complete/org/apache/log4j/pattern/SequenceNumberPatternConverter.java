package org.apache.log4j.pattern;

import javazoom.jl.decoder.huffcodetab;
import net.minecraft.network.play.server.S3EPacketTeams;
import net.minecraft.server.management.BanList;
import org.apache.log4j.chainsaw.Main;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryExplorerTree;
import org.apache.log4j.spi.LoggingEvent;

public class SequenceNumberPatternConverter extends LoggingEventPatternConverter {
   public BanList field_0003;
   public S3EPacketTeams field_0005;
   public Main field_0002;
   public static SequenceNumberPatternConverter INSTANCE = new SequenceNumberPatternConverter();
   public CategoryExplorerTree field_0000;
   public huffcodetab field_0001;

   public void format(LoggingEvent var1, StringBuffer var2) {
      var2.append("0");
   }

   public static SequenceNumberPatternConverter newInstance(String[] var0) {
      return INSTANCE;
   }

   public SequenceNumberPatternConverter() {
      super("Sequence Number", "sn");
   }
}
