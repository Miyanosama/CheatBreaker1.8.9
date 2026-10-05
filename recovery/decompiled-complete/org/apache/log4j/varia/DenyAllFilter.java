package org.apache.log4j.varia;

import com.cheatbreaker.client.util.thread.AliasesThread;
import com.cheatbreaker.client.util.title.Title$TitleType;
import net.minecraft.network.play.server.S42PacketCombatEvent$1;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

public class DenyAllFilter extends Filter {
   public S42PacketCombatEvent$1 field_0000;
   public AliasesThread field_0002;
   public Title$TitleType field_0001;

   public void setOption(String var1, String var2) {
   }

   public int decide(LoggingEvent var1) {
      return -1;
   }

   public String[] getOptionStrings() {
      return null;
   }
}
