package com.cheatbreaker.client.util.dash;

import com.cheatbreaker.client.CheatBreaker;
import java.util.List;
import net.minecraft.client.renderer.entity.layers.LayerHeldBlock;
import net.minecraft.item.ItemMonsterPlacer;
import org.apache.log4j.pattern.NameAbbreviator$MaxElementAbbreviator;
import recovered.unidentified.UnidentifiedClass3177;
import recovered.unidentified.UnidentifiedClass3436;

public class CBDashManager {
   public ItemMonsterPlacer field_0003;
   public UnidentifiedClass3177 field_0005;
   public LayerHeldBlock field_0002;
   public UnidentifiedClass3436 field_0004;
   public List<Station> stations = DashUtil.dashHelpers();
   public NameAbbreviator$MaxElementAbbreviator field_0001;
   public Station station;

   public void setCurrentStation(Station var1) {
      this.station = var1;
   }

   public List<Station> getStations() {
      return this.stations;
   }

   public Station getCurrentStation() {
      return this.station;
   }

   public CBDashManager() {
      this.field_0005 = new UnidentifiedClass3177();
      CheatBreaker.getInstance().field_0034.info(CheatBreaker.getInstance().field_0016 + "Created Dash Manager");
      this.field_0005.start();
      this.field_0004 = new UnidentifiedClass3436();
      this.field_0004.start();
      if (this.stations.size() > 0) {
         this.station = this.stations.get(0);
         this.field_0005.method_19837(this.station);
      }
   }

   public UnidentifiedClass3436 method_05559() {
      return this.field_0004;
   }

   public UnidentifiedClass3177 method_05562() {
      return this.field_0005;
   }
}
