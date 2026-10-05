package net.minecraft.client.stream;

import com.cheatbreaker.client.util.cbagent.CBAgentResources;
import io.netty.handler.codec.marshalling.ThreadLocalMarshallerProvider;
import net.minecraft.stats.Achievement;
import net.optifine.gui.GuiDetailSettingsOF;

public class MetadataAchievement extends Metadata {
   public GuiDetailSettingsOF field_0001;
   public CBAgentResources field_0000;
   public ThreadLocalMarshallerProvider field_0002;

   public MetadataAchievement(Achievement var1) {
      super("achievement");
      this.func_152808_a("achievement_id", var1.statId);
      this.func_152808_a("achievement_name", var1.getStatName().getUnformattedText());
      this.func_152808_a("achievement_description", var1.getDescription());
      this.func_152807_a("Achievement '" + var1.getStatName().getUnformattedText() + "' obtained!");
   }
}
