package net.minecraft.scoreboard;

import com.cheatbreaker.client.ui.ServerRiskWarningGui;
import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.client.model.ModelBook;
import org.apache.log4j.chainsaw.LoggingReceiver$Slurper;
import recovered.unidentified.UnidentifiedClass3249;

public enum IScoreObjectiveCriteria$EnumRenderType {
   HEARTS("hearts"),
   INTEGER("integer");

   public static Map<String, IScoreObjectiveCriteria$EnumRenderType> field_178801_c = Maps.newHashMap();
   public String field_178798_d;
   // $VF: synthetic field
   public static IScoreObjectiveCriteria$EnumRenderType[] $VALUES = new IScoreObjectiveCriteria$EnumRenderType[]{
      IScoreObjectiveCriteria$EnumRenderType.INTEGER, HEARTS
   };
   public LoggingReceiver$Slurper field_0001;
   public ModelBook field_0008;
   public UnidentifiedClass3249 field_0005;
   public ServerRiskWarningGui field_0002;

   public String func_178796_a() {
      return this.field_178798_d;
   }

   public IScoreObjectiveCriteria$EnumRenderType(String var3) {
      this.field_178798_d = var3;
   }

   static {
      for (IScoreObjectiveCriteria$EnumRenderType var3 : values()) {
         field_178801_c.put(var3.func_178796_a(), var3);
      }
   }

   public static IScoreObjectiveCriteria$EnumRenderType func_178795_a(String var0) {
      IScoreObjectiveCriteria$EnumRenderType var1 = field_178801_c.get(var0);
      return var1 == null ? INTEGER : var1;
   }
}
