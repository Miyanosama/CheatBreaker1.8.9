package com.cheatbreaker.client.util.dash;

import com.cheatbreaker.client.CheatBreaker;
import java.util.List;
import com.cheatbreaker.client.util.dash.StationDataQueueThread;
import com.cheatbreaker.client.util.dash.StationRefreshThread;

public class CBDashManager {
   public StationDataQueueThread recoveredField2022;
   public StationRefreshThread recoveredField2023;
   public List<Station> stations = DashUtil.dashHelpers();
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
      this.recoveredField2022 = new StationDataQueueThread();
      CheatBreaker.getInstance().recoveredField1579.info(CheatBreaker.getInstance().recoveredField1553 + "Created Dash Manager");
      this.recoveredField2022.start();
      this.recoveredField2023 = new StationRefreshThread();
      this.recoveredField2023.start();
      if (this.stations.size() > 0) {
         this.station = this.stations.get(0);
         this.recoveredField2022.method_19837(this.station);
      }
   }

   public StationRefreshThread method_05559() {
      return this.recoveredField2023;
   }

   public StationDataQueueThread method_05562() {
      return this.recoveredField2022;
   }
}
