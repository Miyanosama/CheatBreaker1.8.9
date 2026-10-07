<p align="center">
  <img src="docs/assets/cheatbreaker-logo.jpg" alt="CheatBreaker" width="200" height="200">
</p>

<h1 align="center">CheatBreaker 1.8.9</h1>

CheatBreaker 1.8.9 是一个提供性能优化、PvP模组、免费饰品的开源客户端，衍生于2018年因争议而停止更新的版本。本版本由社区开源并维护！

<p align="center">
  🌐 README available in: <a href="README.md">简体中文</a> | <a href="README.zh-Hant.md">繁體中文</a> | <a href="README.ja.md">日本語</a> | <a href="README.en.md">English</a>
</p>

## 主要功能

- 自定义主菜单、服务器列表和模组设置界面，支持调整客户端 UI 大小。
- HUD 与辅助模组，包括药水状态、Hitboxes 和可调节受伤镜头摇晃强度的 Hurtcam。
- 本地披风与翅膀，支持饰品搜索、选择及保存。
- Raw Mouse Input、FullBright，以及输入和界面响应方面的改进。
- 服务器卡片拖拽排序与重连功能。

## 使用方式

启动客户端后，按 **右 Shift** 打开模组与设置界面，在主菜单的 **COSMETICS** 页面选择饰品。本地饰品由当前客户端渲染，不会自动同步给其他玩家。

## 构建

需要 **Java 8 JDK**、**Python 3**，以及 `pom.xml` 声明的构建依赖。Minecraft 启动器依赖需准备在 `.target/launcher-libs/`，文件清单见 `recovery/launcher-dependencies.json`。在 Windows 项目根目录执行：

```bat
mvnw.cmd clean package
python tools/package_client.py
```

构建会执行编译、测试和审计。正式 JAR 位于 `.target/maven-source/CheatBreaker1.8.9.jar`，打包目录为 `.target/client/CheatBreaker1.8.9/`。

## 贡献

欢迎通过 Issues 报告问题或通过 Pull Requests 提交改进。请说明复现步骤、预期行为和实际行为；提交日志前请移除账号信息、令牌及个人路径。源码位于 `src/main/java/`，资源位于 `src/main/resources/`。

## 独立性声明与许可证

本项目与 [Mojang AB（Mojang Studios）](https://www.minecraft.net/en-us/usage-guidelines)、[OptiFine（作者：sp614x）](https://optifine.net/copyright)、[Microsoft Corporation](https://www.microsoft.com/) 及 [CheatBreaker®](https://github.com/CheatBreaker) 均无关联，也未获得上述主体的赞助、认可或官方授权。

本项目的原创代码采用 [MIT 许可证](LICENSE)。第三方代码、资源、依赖及商标的权利归各自权利人所有，并继续适用其原有许可证或使用条款。
