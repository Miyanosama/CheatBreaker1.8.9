# CheatBreaker 1.8.9

[简体中文](#简体中文) · [繁體中文](#繁體中文) · [日本語](#日本語) · [English](#english)

## 简体中文

### 项目介绍

CheatBreaker 1.8.9 是面向 Minecraft Java Edition 1.8.9 的客户端项目，提供 CheatBreaker 风格的界面、可配置的 HUD 与模组，以及本地饰品功能。项目旨在改善日常游玩体验，并通过源码维护持续修复问题、优化交互。

### 主要功能

- 自定义主菜单、服务器列表和模组设置界面，支持调整客户端 UI 大小。
- HUD 与辅助模组，包括药水状态、Hitboxes 和可调节受伤镜头摇晃强度的 Hurtcam。
- 本地披风与翅膀，支持饰品搜索、选择及保存。
- Raw Mouse Input、FullBright，以及输入和界面响应方面的改进。
- 服务器卡片拖拽排序与重连功能。

### 使用方式

启动客户端后，按 **右 Shift** 打开模组与设置界面，在主菜单的 **COSMETICS** 页面选择饰品。本地饰品由当前客户端渲染，不会自动同步给其他玩家。

### 构建

需要 **Java 8 JDK**、**Python 3**，以及 `pom.xml` 声明的构建依赖。Minecraft 启动器依赖需准备在 `.target/launcher-libs/`，文件清单见 `recovery/launcher-dependencies.json`。在 Windows 项目根目录执行：

```bat
mvnw.cmd clean package
python tools/package_client.py
```

构建会执行编译、测试和审计。正式 JAR 位于 `.target/maven-source/CheatBreaker1.8.9.jar`，打包目录为 `.target/client/CheatBreaker1.8.9/`。

### 贡献

欢迎通过 Issues 报告问题或通过 Pull Requests 提交改进。请说明复现步骤、预期行为和实际行为；提交日志前请移除账号信息、令牌及个人路径。源码位于 `src/main/java/`，资源位于 `src/main/resources/`。

### 独立性声明与许可证

本项目与 Mojang、OptiFine、微软（Microsoft）及 CheatBreaker LLC 均无关联，也未获得上述主体的赞助、认可或官方授权。

本项目的原创代码采用 [MIT 许可证](LICENSE)。第三方代码、资源、依赖及商标的权利归各自权利人所有，并继续适用其原有许可证或使用条款。

## 繁體中文

### 專案介紹

CheatBreaker 1.8.9 是面向 Minecraft Java Edition 1.8.9 的用戶端專案，提供 CheatBreaker 風格的介面、可設定的 HUD 與模組，以及本機飾品功能。專案旨在改善日常遊玩體驗，並透過原始碼維護持續修正問題、改善操作體驗。

### 主要功能

- 自訂主選單、伺服器列表和模組設定介面，支援調整用戶端 UI 大小。
- HUD 與輔助模組，包括藥水狀態、Hitboxes，以及可調整受傷鏡頭搖晃強度的 Hurtcam。
- 本機披風與翅膀，支援飾品搜尋、選擇及儲存。
- Raw Mouse Input、FullBright，以及輸入和介面回應方面的改善。
- 伺服器卡片拖曳排序與重新連線功能。

### 使用方式

啟動用戶端後，按 **右 Shift** 開啟模組與設定介面，在主選單的 **COSMETICS** 頁面選擇飾品。本機飾品由目前的用戶端繪製，不會自動同步給其他玩家。

### 建置

需要 **Java 8 JDK**、**Python 3**，以及 `pom.xml` 宣告的建置相依套件。Minecraft 啟動器相依套件需準備於 `.target/launcher-libs/`，檔案清單見 `recovery/launcher-dependencies.json`。在 Windows 專案根目錄執行：

```bat
mvnw.cmd clean package
python tools/package_client.py
```

建置會執行編譯、測試及稽核。正式 JAR 位於 `.target/maven-source/CheatBreaker1.8.9.jar`，封裝目錄為 `.target/client/CheatBreaker1.8.9/`。

### 貢獻

歡迎透過 Issues 回報問題，或透過 Pull Requests 提交改善。請說明重現步驟、預期行為及實際行為；提交日誌前請移除帳號資訊、權杖及個人路徑。原始碼位於 `src/main/java/`，資源位於 `src/main/resources/`。

### 獨立性聲明與授權條款

本專案與 Mojang、OptiFine、微軟（Microsoft）及 CheatBreaker LLC 均無關聯，也未獲得上述主體的贊助、認可或官方授權。

本專案的原創程式碼採用 [MIT 授權條款](LICENSE)。第三方程式碼、資源、相依套件及商標的權利歸各自權利人所有，並繼續適用其原有授權條款或使用條款。

## 日本語

### プロジェクトについて

CheatBreaker 1.8.9 は、Minecraft Java Edition 1.8.9 向けのクライアントプロジェクトです。CheatBreaker スタイルの UI、設定可能な HUD とモジュール、ローカルのコスメティック機能を提供します。日常のプレイ体験を改善し、ソースコードの保守を通じて不具合の修正と操作性の向上を続けることを目指しています。

### 主な機能

- カスタムのメインメニュー、サーバーリスト、モジュール設定画面と、クライアント UI のサイズ調整。
- ポーション効果の表示、Hitboxes、ダメージ時のカメラの揺れを調整する Hurtcam などの HUD・補助モジュール。
- ローカルのマントと翼。コスメティックの検索、選択、保存に対応。
- Raw Mouse Input、FullBright、入力処理と UI の応答性の改善。
- サーバーカードのドラッグによる並べ替えと再接続機能。

### 使い方

クライアントの起動後、**右 Shift** キーでモジュールと設定画面を開きます。コスメティックはメインメニューの **COSMETICS** ページで選択できます。ローカルのコスメティックは使用中のクライアントで描画され、他のプレイヤーには自動で同期されません。

### ビルド

**Java 8 JDK**、**Python 3**、および `pom.xml` に定義されたビルド依存関係が必要です。Minecraft ランチャーの依存ファイルを `.target/launcher-libs/` に用意してください。ファイル一覧は `recovery/launcher-dependencies.json` にあります。Windows でプロジェクトのルートディレクトリから実行します。

```bat
mvnw.cmd clean package
python tools/package_client.py
```

ビルドではコンパイル、テスト、監査を実行します。ビルド済み JAR は `.target/maven-source/CheatBreaker1.8.9.jar`、パッケージの出力先は `.target/client/CheatBreaker1.8.9/` です。

### コントリビューション

Issues での不具合報告や Pull Requests での改善を歓迎します。再現手順、期待する動作、実際の動作を記載してください。ログを投稿する前に、アカウント情報、トークン、個人のパスを削除してください。ソースコードは `src/main/java/`、リソースは `src/main/resources/` にあります。

### 独立性に関する声明とライセンス

本プロジェクトは Mojang、OptiFine、Microsoft、CheatBreaker LLC のいずれとも関係がなく、これらの組織によるスポンサーシップ、承認、公式な許諾を受けていません。

本プロジェクト独自のコードには [MIT ライセンス](LICENSE) を適用します。第三者のコード、リソース、依存ライブラリ、商標の権利は各権利者に帰属し、それぞれの既存のライセンスまたは利用条件が引き続き適用されます。

## English

### About

CheatBreaker 1.8.9 is a client project for Minecraft Java Edition 1.8.9. It provides a CheatBreaker-style interface, configurable HUD elements and modules, and local cosmetics. The project aims to improve everyday gameplay through ongoing source maintenance, bug fixes, and interface improvements.

### Features

- Custom main menu, server list, and module settings, with adjustable client UI size.
- HUD and utility modules, including potion status, Hitboxes, and Hurtcam with adjustable camera shake when taking damage.
- Local capes and wings, with cosmetic search, selection, and saved preferences.
- Raw Mouse Input, FullBright, and improvements to input handling and UI responsiveness.
- Drag-to-reorder server cards and a reconnect feature.

### Usage

After launching the client, press **Right Shift** to open the modules and settings interface. Select cosmetics on the **COSMETICS** page in the main menu. Local cosmetics are rendered by the current client and are not automatically synchronized with other players.

### Building

You need **Java 8 JDK**, **Python 3**, and the build dependencies declared in `pom.xml`. Place the Minecraft launcher dependencies in `.target/launcher-libs/`; the file list is provided in `recovery/launcher-dependencies.json`. Run these commands from the project root on Windows:

```bat
mvnw.cmd clean package
python tools/package_client.py
```

The build runs compilation, tests, and audits. The resulting JAR is `.target/maven-source/CheatBreaker1.8.9.jar`, and the packaged client is written to `.target/client/CheatBreaker1.8.9/`.

### Contributing

Bug reports through Issues and improvements through Pull Requests are welcome. Include reproduction steps, expected behavior, and actual behavior. Remove account details, tokens, and personal paths before submitting logs. Source code is in `src/main/java/`, and resources are in `src/main/resources/`.

### Independence and License

This project is not affiliated with, sponsored by, endorsed by, or officially authorized by Mojang, OptiFine, Microsoft, or CheatBreaker LLC.

Original code contributed to this project is licensed under the [MIT License](LICENSE). Third-party code, assets, dependencies, and trademarks remain the property of their respective rights holders and remain subject to their existing licenses or terms of use.
