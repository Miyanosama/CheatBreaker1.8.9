package com.cheatbreaker.client.module;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.Setting$Type;
import com.cheatbreaker.client.event.type.DisconnectEvent;
import com.cheatbreaker.client.event.type.LoadWorldEvent;
import com.cheatbreaker.client.module.staff.NametagsModule;
import com.cheatbreaker.client.module.staff.StaffModule;
import com.cheatbreaker.client.module.staff.TrimpModule;
import com.cheatbreaker.client.module.staff.XRayModule;
import com.cheatbreaker.client.module.type.AnimationsModule;
import com.cheatbreaker.client.module.type.AutoTextModule;
import com.cheatbreaker.client.module.type.BlockOverlayModule;
import com.cheatbreaker.client.module.type.BossBarModule;
import com.cheatbreaker.client.module.type.CPSModule;
import com.cheatbreaker.client.module.type.ChatModule;
import com.cheatbreaker.client.module.type.ClockModule;
import com.cheatbreaker.client.module.type.ComboCounterModule;
import com.cheatbreaker.client.module.type.CoordinatesModule;
import com.cheatbreaker.client.module.type.CrosshairModule;
import com.cheatbreaker.client.module.type.DamageTintModule;
import com.cheatbreaker.client.module.type.DirectionHudModule;
import com.cheatbreaker.client.module.type.EnchantmentGlintModule;
import com.cheatbreaker.client.module.type.EnvironmentModule;
import com.cheatbreaker.client.module.type.FPSModule;
import com.cheatbreaker.client.module.type.HitboxesModule;
import com.cheatbreaker.client.module.type.HypixelModule;
import com.cheatbreaker.client.module.type.MemoryUsageModule;
import com.cheatbreaker.client.module.type.MiniMapModule;
import com.cheatbreaker.client.module.type.MotionBlurModule;
import com.cheatbreaker.client.module.type.NametagModule;
import com.cheatbreaker.client.module.type.NickHiderModule;
import com.cheatbreaker.client.module.type.PackDisplayModule;
import com.cheatbreaker.client.module.type.ParticlesModule;
import com.cheatbreaker.client.module.type.PerspectiveModule;
import com.cheatbreaker.client.module.type.PingModule;
import com.cheatbreaker.client.module.type.PotionCounterModule;
import com.cheatbreaker.client.module.type.PotionStatusModule;
import com.cheatbreaker.client.module.type.ReachDisplayModule;
import com.cheatbreaker.client.module.type.SaturationModule;
import com.cheatbreaker.client.module.type.ScoreboardModule;
import com.cheatbreaker.client.module.type.ServerAddressModule;
import com.cheatbreaker.client.module.type.SprintResetCounterModule;
import com.cheatbreaker.client.module.type.TNTTimerModule;
import com.cheatbreaker.client.module.type.TeammatesModule;
import com.cheatbreaker.client.module.type.TextureOptionsModule;
import com.cheatbreaker.client.module.type.ToggleSprintModule;
import com.cheatbreaker.client.module.type.armourstatus.ArmourStatusModule;
import com.cheatbreaker.client.module.type.cooldowns.CooldownsModule;
import com.cheatbreaker.client.module.type.keystrokes.KeystrokesModule;
import com.cheatbreaker.client.module.type.notifications.CBNotificationsModule;
import com.cheatbreaker.client.ui.element.type.custom.KeybindElement;
import com.cheatbreaker.client.util.voicechat.VoiceChat;
import io.netty.buffer.AbstractDerivedByteBuf;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.stats.StatBase;
import net.minecraft.util.EnumChatFormatting;
import recovered.unidentified.UnidentifiedClass0806;
import recovered.unidentified.UnidentifiedClass1349;
import recovered.unidentified.UnidentifiedClass3379;
import recovered.unidentified.UnidentifiedClass3556;
import recovered.unidentified.UnidentifiedClass3624;
import recovered.unidentified.UnidentifiedClass3946;
import recovered.unidentified.UnidentifiedClass4040;
import recovered.unidentified.UnidentifiedClass4396;
import recovered.unidentified.UnidentifiedClass4575;
import recovered.unidentified.UnidentifiedClass4790;
import recovered.unidentified.UnidentifiedClass4913;

public class ModuleManager {
   public CBNotificationsModule notifications;
   public FPSModule fpsModule;
   public String field_0028;
   public XRayModule xray;
   public BlockOverlayModule field_0010;
   public ComboCounterModule field_0014;
   public TeammatesModule teammatesModule;
   public AbstractDerivedByteBuf field_0043;
   public UnidentifiedClass1349 field_0015;
   public PotionCounterModule field_0056;
   public ReachDisplayModule field_0009;
   public AutoTextModule field_0032;
   public MiniMapModule minmap;
   public PotionStatusModule potionStatus;
   public DirectionHudModule directionHud;
   public CooldownsModule cooldowns;
   public UnidentifiedClass3379 field_0007;
   public UnidentifiedClass4575 field_0022;
   public PackDisplayModule packDisplayModule;
   public HitboxesModule field_0051;
   public ServerAddressModule field_0006;
   public NickHiderModule field_0005;
   public List<AbstractModule> field_0008 = new ArrayList<>();
   public SprintResetCounterModule field_0003;
   public BossBarModule field_0048;
   public KeystrokesModule keyStrokes;
   public CoordinatesModule coordinatesModule;
   public UnidentifiedClass4913 field_0026;
   public SaturationModule saturationModule;
   public MemoryUsageModule field_0039;
   public ArmourStatusModule armourStatus;
   public TrimpModule field_0011;
   public NametagsModule field_0025;
   public NametagModule field_0020;
   public VoiceChat voiceChat;
   public EnchantmentGlintModule field_0018;
   public ScoreboardModule scoreboard;
   public MotionBlurModule field_0058;
   public ToggleSprintModule field_0033;
   public AnimationsModule field_0041;
   public PingModule field_0030;
   public StateMapperBase field_0024;
   public ChatModule chatModule;
   public HypixelModule field_0001;
   public Map<Setting, KeybindElement> field_0013;
   public CrosshairModule field_0016;
   public TNTTimerModule field_0036;
   public StatBase field_0040;
   public PerspectiveModule field_0012;
   public int field_0017;
   public List<StaffModule> field_0049 = new ArrayList<>();
   public EnvironmentModule field_0042;
   public ClockModule clockModule;
   public TextureOptionsModule field_0047;
   public CPSModule cpsModule;
   public UnidentifiedClass3946 field_0027;
   public UnidentifiedClass4040 field_0037;
   public ParticlesModule field_0044;
   public DamageTintModule field_0004;

   public ModuleManager(UnidentifiedClass3556 var1) {
      this.field_0013 = new HashMap<>();
      CheatBreaker.getInstance().field_0034.info(CheatBreaker.getInstance().field_0016 + "Created Mod Manager");
      this.field_0008.add(this.coordinatesModule = new CoordinatesModule());
      this.field_0008.add(this.field_0041 = new AnimationsModule());
      this.field_0008.add(this.minmap = new MiniMapModule());
      this.field_0008.add(this.field_0033 = new ToggleSprintModule());
      this.field_0008.add(this.potionStatus = new PotionStatusModule());
      this.field_0008.add(this.armourStatus = new ArmourStatusModule());
      this.field_0008.add(this.keyStrokes = new KeystrokesModule());
      this.field_0008.add(this.scoreboard = new ScoreboardModule());
      this.field_0008.add(this.chatModule = new ChatModule());
      this.field_0008.add(this.field_0048 = new BossBarModule());
      this.field_0008.add(this.cooldowns = new CooldownsModule());
      this.field_0008.add(this.notifications = new CBNotificationsModule());
      this.field_0008.add(this.directionHud = new DirectionHudModule());
      this.field_0008.add(this.field_0042 = new EnvironmentModule());
      this.field_0008.add(this.field_0018 = new EnchantmentGlintModule());
      this.field_0008.add(this.field_0016 = new CrosshairModule());
      this.field_0008.add(this.field_0004 = new DamageTintModule());
      this.field_0008.add(this.field_0044 = new ParticlesModule());
      this.field_0008.add(this.field_0020 = new NametagModule());
      this.field_0008.add(this.field_0005 = new NickHiderModule());
      this.field_0008.add(this.field_0032 = new AutoTextModule());
      this.field_0008.add(this.field_0036 = new TNTTimerModule());
      this.field_0008.add(this.field_0010 = new BlockOverlayModule());
      this.field_0008.add(this.field_0047 = new TextureOptionsModule());
      this.field_0008.add(this.field_0012 = new PerspectiveModule());
      this.field_0008.add(this.field_0051 = new HitboxesModule());
      this.field_0008.add(this.field_0001 = new HypixelModule());
      new Timer()
         .scheduleAtFixedRate(
            new UnidentifiedClass3624(),
            TimeUnit.SECONDS.toMillis(3863257791754246415L & -3863257792089800609L),
            TimeUnit.MINUTES.toMillis(1444259931L & 4325665L)
         );
      this.field_0008.add(this.cpsModule = new CPSModule());
      this.field_0008.add(this.fpsModule = new FPSModule());
      this.field_0008.add(this.field_0039 = new MemoryUsageModule());
      this.field_0008.add(this.field_0056 = new PotionCounterModule());
      this.field_0008.add(this.field_0014 = new ComboCounterModule());
      this.field_0008.add(this.field_0003 = new SprintResetCounterModule());
      this.field_0008.add(this.field_0009 = new ReachDisplayModule());
      this.field_0008.add(this.field_0006 = new ServerAddressModule());
      this.field_0008.add(this.field_0030 = new PingModule());
      this.field_0008.add(this.clockModule = new ClockModule());
      this.field_0008.add(this.saturationModule = new SaturationModule());
      this.field_0008.add(this.packDisplayModule = new PackDisplayModule());
      this.field_0008.add(this.field_0058 = new MotionBlurModule());
      this.field_0049.add(this.xray = new XRayModule());
      this.field_0049.add(this.field_0025 = new NametagsModule());
      this.field_0049.add(this.field_0037 = new UnidentifiedClass4040());
      this.field_0049.add(this.field_0011 = new TrimpModule());
      this.voiceChat = new VoiceChat();
      this.field_0027 = new UnidentifiedClass3946();
      this.field_0007 = new UnidentifiedClass3379();
      this.teammatesModule = new TeammatesModule();
      this.field_0015 = new UnidentifiedClass1349();
      this.teammatesModule.method_01340(true);
      new UnidentifiedClass4790();
      this.field_0026 = new UnidentifiedClass4913();
      this.field_0026.method_29289();
      var1.method_21938(LoadWorldEvent.class, this::method_21673);
      var1.method_21938(UnidentifiedClass0806.class, this::method_21667);
      var1.method_21938(UnidentifiedClass4396.class, var1x -> {
         ServerData var2 = Minecraft.getMinecraft().currentServerData;
         if (var2 != null) {
            CheatBreaker.getInstance().method_19779(var2.serverIP, var2.field_0005, var2.field_0000);

            for (AbstractModule var4 : this.field_0008) {
               var4.method_28862(Arrays.toString((Object[])var4.method_28774()).contains(var2.serverIP));
            }
         }
      });
      var1.method_21938(DisconnectEvent.class, var1x -> {
         CheatBreaker.getInstance().method_19779("", "", 0);

         for (AbstractModule var3 : this.field_0049) {
            var3.setState(false);
            var3.setStaffModuleEnabled(false);
         }

         for (AbstractModule var5 : this.field_0008) {
            var5.method_28862(false);
         }
      });
   }

   public AbstractModule method_21669(String var1) {
      return this.field_0008.stream().filter(var1x -> var1x.getName().equalsIgnoreCase(var1)).findFirst().orElse(null);
   }

   public boolean method_21668(Setting var1, int var2) {
      boolean var3 = false;

      for (Setting var5 : this.field_0013.keySet()) {
         if (var5.getType() == Setting$Type.field_0002) {
            if (var5.method_08867() && var2 == var5.method_08877()) {
               var3 = true;
            }

            if (var5.method_08879() && var2 == var5.method_08912()) {
               var3 = true;
            }

            if ((var5.method_08911().toLowerCase().startsWith("hot key") || var5.method_08911().toLowerCase().endsWith("keybind"))
               && var2 == var5.method_08912()) {
               var3 = true;
            }
         }
      }

      if (var3) {
         CheatBreaker.getInstance()
            .getModuleManager()
            .notifications
            .queueNotification(
               "error", EnumChatFormatting.RED + var1.method_08911() + " is already bound to another keybind!", 8221571371672230792L & -8221571372451359848L
            );
      }

      return var3;
   }

   public void method_21673(LoadWorldEvent var1) {
      if (this.field_0042.isEnabled()) {
         this.field_0042.method_20137();
      }
   }

   public void method_21666(int var1) {
      this.field_0017 = var1;
   }

   public void method_21667(UnidentifiedClass0806 var1) {
      if (var1.method_05523() != 0) {
         for (StaffModule var3 : this.field_0049) {
            if (var3.isStaffEnabledModule() && (Integer)var3.getKeybindSetting().getValue() == var1.method_05523()) {
               var3.setState(!var3.isEnabled());
            }
         }
      }
   }

   public void method_21674(String var1) {
      this.field_0028 = var1;
   }
}
