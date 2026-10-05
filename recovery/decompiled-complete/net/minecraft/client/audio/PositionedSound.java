package net.minecraft.client.audio;

import com.cheatbreaker.client.ui.overlay.friend.FriendRequest;
import io.netty.handler.codec.base64.Base64Decoder;
import net.minecraft.client.stream.ChatController$EnumChannelState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldType;
import recovered.unidentified.UnidentifiedClass3854;

public abstract class PositionedSound implements ISound {
   public boolean g;
   public WorldType field_0009;
   public float e;
   public int h;
   public ResourceLocation positionedSoundLocation;
   public float f;
   public UnidentifiedClass3854 field_0005;
   public float volume = 1.0F;
   public float d;
   public ChatController$EnumChannelState field_0002;
   public FriendRequest field_0001;
   public Base64Decoder field_0004;
   public float pitch = 1.0F;
   public ISound$AttenuationType i;

   @Override
   public ISound$AttenuationType getAttenuationType() {
      return this.i;
   }

   @Override
   public float g() {
      return this.d;
   }

   @Override
   public int getRepeatDelay() {
      return this.h;
   }

   @Override
   public float getVolume() {
      return this.volume;
   }

   @Override
   public float h() {
      return this.e;
   }

   @Override
   public boolean canRepeat() {
      return this.g;
   }

   @Override
   public float getPitch() {
      return this.pitch;
   }

   public PositionedSound(ResourceLocation var1) {
      this.g = false;
      this.h = 0;
      this.i = ISound$AttenuationType.LINEAR;
      this.positionedSoundLocation = var1;
   }

   @Override
   public float i() {
      return this.f;
   }

   @Override
   public ResourceLocation getSoundLocation() {
      return this.positionedSoundLocation;
   }
}
