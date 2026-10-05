package com.cheatbreaker.client.util.dash;

import io.netty.channel.PendingWriteQueue$PendingWrite$1;
import io.netty.channel.udt.UdtChannelOption;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;
import javax.xml.parsers.DocumentBuilderFactory;
import net.minecraft.util.ResourceLocation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Station {
   public String coverURL = "";
   public boolean favourite;
   public String field_0006;
   public boolean play;
   public UdtChannelOption field_0002;
   public ResourceLocation previousResource;
   public String field_0015;
   public String name;
   public String currentSongURL;
   public String field_0016;
   public LocalDateTime field_0001;
   public PendingWriteQueue$PendingWrite$1 field_0008;
   public String logoURL;
   public String genre;
   public String streamURL;
   public ResourceLocation currentResource;
   public int duration;

   public String getElement(Element var1, String var2) {
      try {
         NodeList var3 = var1.getElementsByTagName(var2);
         Element var4 = (Element)var3.item(0);
         return var4.getChildNodes().item(0).getNodeValue().trim();
      } catch (Exception var5) {
         return "";
      }
   }

   public void setArtist(String var1) {
      this.field_0006 = var1;
   }

   public String getStreamURL() {
      return this.streamURL;
   }

   public boolean isFavourite() {
      return this.favourite;
   }

   public String getName() {
      return this.name;
   }

   public void setTitle(String var1) {
      this.field_0016 = var1;
   }

   public String getTitle() {
      return this.field_0016;
   }

   public void method_05603(ResourceLocation var1) {
      this.previousResource = var1;
   }

   public String method_05593() {
      return this.field_0006;
   }

   public LocalDateTime method_05594() {
      return this.field_0001;
   }

   public void setFavourite(boolean var1) {
      this.favourite = var1;
   }

   public String method_05617() {
      return this.field_0015;
   }

   public String getCurrentSongURL() {
      return this.currentSongURL;
   }

   public void setCoverURL(String var1) {
      this.coverURL = var1;
   }

   @Override
   public String toString() {
      return "Station(streamUrl="
         + this.getStreamURL()
         + ", currentSongUrl="
         + this.getCurrentSongURL()
         + ", genre="
         + this.getGenre()
         + ", logoUrl="
         + this.getLogoURL()
         + ", name="
         + this.getName()
         + ", favorite="
         + this.isFavourite()
         + ", startTime="
         + this.method_05594()
         + ", title="
         + this.getTitle()
         + ", artist="
         + this.method_05593()
         + ", album="
         + this.method_05617()
         + ", coverUrl="
         + this.getCoverURL()
         + ", duration="
         + this.getDuration()
         + ", RESOURCE_CURRENT="
         + this.getCurrentResource()
         + ", RESOURCE_PREVIOUS="
         + this.getPreviousResource()
         + ", play="
         + this.isPlay()
         + ")";
   }

   public void endStream() {
      DashUtil.end(DashUtil.dashHelpers(this.streamURL));
   }

   public String getCoverURL() {
      return this.coverURL;
   }

   public String getGenre() {
      return this.genre;
   }

   public ResourceLocation getCurrentResource() {
      return this.currentResource;
   }

   public int getDuration() {
      return this.duration;
   }

   public Station(String var1, String var2, String var3, String var4, String var5) {
      this.name = var1;
      this.logoURL = var2;
      this.genre = var3;
      this.currentSongURL = var4;
      this.streamURL = var5;
   }

   public void getStreamURL(String var1) {
      this.field_0015 = var1;
   }

   public void setDuration(int var1) {
      this.duration = var1;
   }

   public boolean isPlay() {
      return this.play;
   }

   public void setStartTime(LocalDateTime var1) {
      this.field_0001 = var1;
   }

   public String getLogoURL() {
      return this.logoURL;
   }

   public void getData() {
      try {
         Document var1 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this.getCurrentSongURL());
         NodeList var2 = var1.getElementsByTagName("playlist");

         for (int var3 = 0; var3 < var2.getLength(); var3++) {
            Node var4 = var2.item(var3);
            Element var5 = (Element)var4;
            this.setTitle(this.getElement(var5, "title"));
            this.setArtist(this.getElement(var5, "artist"));
            this.getStreamURL(this.getElement(var5, "album"));
            this.setCoverURL(this.getElement(var5, "cover"));
            this.setDuration(Integer.parseInt(this.getElement(var5, "duration")));
            String var6 = this.getElement(var5, "programStartTS");
            String var7 = "dd MMM yy hh:mm:ss";
            SimpleDateFormat var8 = new SimpleDateFormat(var7);
            var8.setTimeZone(TimeZone.getTimeZone("UTC"));
            if (this.currentResource != null && !("client/songs/" + this.getTitle()).equals(this.currentResource.getResourcePath())) {
               this.previousResource = this.currentResource;
               this.currentResource = null;
            }

            try {
               Date var9 = var8.parse(var6);
               this.setStartTime(LocalDateTime.ofInstant(var9.toInstant(), ZoneId.systemDefault()));
            } catch (Exception var10) {
               var10.printStackTrace();
            }
         }
      } catch (Exception var11) {
         var11.printStackTrace();
      }

      if (this.play) {
         this.play = false;
         this.endStream();
      }
   }

   public ResourceLocation getPreviousResource() {
      return this.previousResource;
   }

   public void method_05615(ResourceLocation var1) {
      this.currentResource = var1;
   }

   public void method_05616(boolean var1) {
      this.play = var1;
   }
}
