# CheatBreaker 1.8.9

<p align="center">
  🌐 README available in: <a href="README.md">简体中文</a> | <a href="README.zh-Hant.md">繁體中文</a> | <a href="README.ja.md">日本語</a> | <a href="README.en.md">English</a>
</p>

## 專案介紹

CheatBreaker 1.8.9 是面向 Minecraft Java Edition 1.8.9 的用戶端專案，提供 CheatBreaker 風格的介面、可設定的 HUD 與模組，以及本機飾品功能。專案旨在改善日常遊玩體驗，並透過原始碼維護持續修正問題、改善操作體驗。

## 主要功能

- 自訂主選單、伺服器列表和模組設定介面，支援調整用戶端 UI 大小。
- HUD 與輔助模組，包括藥水狀態、Hitboxes，以及可調整受傷鏡頭搖晃強度的 Hurtcam。
- 本機披風與翅膀，支援飾品搜尋、選擇及儲存。
- Raw Mouse Input、FullBright，以及輸入和介面回應方面的改善。
- 伺服器卡片拖曳排序與重新連線功能。

## 使用方式

啟動用戶端後，按 **右 Shift** 開啟模組與設定介面，在主選單的 **COSMETICS** 頁面選擇飾品。本機飾品由目前的用戶端繪製，不會自動同步給其他玩家。

## 建置

需要 **Java 8 JDK**、**Python 3**，以及 `pom.xml` 宣告的建置相依套件。Minecraft 啟動器相依套件需準備於 `.target/launcher-libs/`，檔案清單見 `recovery/launcher-dependencies.json`。在 Windows 專案根目錄執行：

```bat
mvnw.cmd clean package
python tools/package_client.py
```

建置會執行編譯、測試及稽核。正式 JAR 位於 `.target/maven-source/CheatBreaker1.8.9.jar`，封裝目錄為 `.target/client/CheatBreaker1.8.9/`。

## 貢獻

歡迎透過 Issues 回報問題，或透過 Pull Requests 提交改善。請說明重現步驟、預期行為及實際行為；提交日誌前請移除帳號資訊、權杖及個人路徑。原始碼位於 `src/main/java/`，資源位於 `src/main/resources/`。

## 獨立性聲明與授權條款

本專案與 [Mojang AB（Mojang Studios）](https://www.minecraft.net/en-us/usage-guidelines)、[OptiFine（作者：sp614x）](https://optifine.net/copyright)、[Microsoft Corporation](https://www.microsoft.com/) 及 [CheatBreaker®](https://github.com/CheatBreaker) 均無關聯，也未獲得上述主體的贊助、認可或官方授權。

本專案的原創程式碼採用 [MIT 授權條款](LICENSE)。第三方程式碼、資源、相依套件及商標的權利歸各自權利人所有，並繼續適用其原有授權條款或使用條款。
