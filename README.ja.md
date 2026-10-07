# CheatBreaker 1.8.9

<p align="center">
  🌐 README available in: <a href="README.md">简体中文</a> | <a href="README.zh-Hant.md">繁體中文</a> | <a href="README.ja.md">日本語</a> | <a href="README.en.md">English</a>
</p>

## プロジェクトについて

CheatBreaker 1.8.9 は、Minecraft Java Edition 1.8.9 向けのクライアントプロジェクトです。CheatBreaker スタイルの UI、設定可能な HUD とモジュール、ローカルのコスメティック機能を提供します。日常のプレイ体験を改善し、ソースコードの保守を通じて不具合の修正と操作性の向上を続けることを目指しています。

## 主な機能

- カスタムのメインメニュー、サーバーリスト、モジュール設定画面と、クライアント UI のサイズ調整。
- ポーション効果の表示、Hitboxes、ダメージ時のカメラの揺れを調整する Hurtcam などの HUD・補助モジュール。
- ローカルのマントと翼。コスメティックの検索、選択、保存に対応。
- Raw Mouse Input、FullBright、入力処理と UI の応答性の改善。
- サーバーカードのドラッグによる並べ替えと再接続機能。

## 使い方

クライアントの起動後、**右 Shift** キーでモジュールと設定画面を開きます。コスメティックはメインメニューの **COSMETICS** ページで選択できます。ローカルのコスメティックは使用中のクライアントで描画され、他のプレイヤーには自動で同期されません。

## ビルド

**Java 8 JDK**、**Python 3**、および `pom.xml` に定義されたビルド依存関係が必要です。Minecraft ランチャーの依存ファイルを `.target/launcher-libs/` に用意してください。ファイル一覧は `recovery/launcher-dependencies.json` にあります。Windows でプロジェクトのルートディレクトリから実行します。

```bat
mvnw.cmd clean package
python tools/package_client.py
```

ビルドではコンパイル、テスト、監査を実行します。ビルド済み JAR は `.target/maven-source/CheatBreaker1.8.9.jar`、パッケージの出力先は `.target/client/CheatBreaker1.8.9/` です。

## コントリビューション

Issues での不具合報告や Pull Requests での改善を歓迎します。再現手順、期待する動作、実際の動作を記載してください。ログを投稿する前に、アカウント情報、トークン、個人のパスを削除してください。ソースコードは `src/main/java/`、リソースは `src/main/resources/` にあります。

## 独立性に関する声明とライセンス

本プロジェクトは [Mojang AB（Mojang Studios）](https://www.minecraft.net/en-us/usage-guidelines)、[OptiFine（作者：sp614x）](https://optifine.net/copyright)、[Microsoft Corporation](https://www.microsoft.com/)、[CheatBreaker®](https://github.com/CheatBreaker) のいずれとも関係がなく、これらの主体によるスポンサーシップ、承認、公式な許諾を受けていません。

本プロジェクト独自のコードには [MIT ライセンス](LICENSE) を適用します。第三者のコード、リソース、依存ライブラリ、商標の権利は各権利者に帰属し、それぞれの既存のライセンスまたは利用条件が引き続き適用されます。
