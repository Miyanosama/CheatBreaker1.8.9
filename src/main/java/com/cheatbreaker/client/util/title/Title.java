package com.cheatbreaker.client.util.title;

public class Title {
   public long fadeInTimeMs;
   public String message;
   public float scale;
   public Title.TitleType titleEnum;
   public long currentTimeMillis = System.currentTimeMillis();
   public long displayTimeMs;
   public long fadeOutTimeMs;

   public Title.TitleType getTitleEnum() {
      return this.titleEnum;
   }

   public long getDisplayTimeMs() {
      return this.displayTimeMs;
   }

   public boolean method_26170() {
      return System.currentTimeMillis() > this.currentTimeMillis + this.displayTimeMs - this.fadeOutTimeMs;
   }

   public long getFadeOutTimeMs() {
      return this.fadeOutTimeMs;
   }

   public long getFadeInTimeMs() {
      return this.fadeInTimeMs;
   }

   public long method_26168() {
      return this.currentTimeMillis;
   }

   public boolean method_26175() {
      return System.currentTimeMillis() < this.currentTimeMillis + this.fadeInTimeMs;
   }

   public String getMessage() {
      return this.message;
   }

   public float getScale() {
      return this.scale;
   }

   public Title(String var1, Title.TitleType var2, float var3, long var4, long var6, long var8) {
      this.message = var1;
      this.titleEnum = var2;
      this.scale = var3;
      this.displayTimeMs = var4;
      this.fadeInTimeMs = var6;
      this.fadeOutTimeMs = var8;
   }

   public static enum TitleType {
      TITLE,
      SUBTITLE;
      // $VF: synthetic field
      public static Title.TitleType[] recoveredField1037 = new Title.TitleType[]{Title.TitleType.TITLE, Title.TitleType.SUBTITLE};
   }
}
