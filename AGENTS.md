# CheatBreaker 1.8.9 工作约定

## 每轮修改的完成流程

- 每轮修改完成后，必须将所有变更提交到 Git，提交信息说明修改内容。
- 提交后从零重新构建全部产物，执行 `mvnw.cmd clean package`，确保编译、Java 测试和 recovery audit 全部通过；不得仅复制旧 JAR 或进行增量打包。
- 构建成功后执行 `python tools/package_client.py` 和 `python tools/deploy_neo.py`，将本轮产物部署到 `C:\Users\hp\AppData\Roaming\.minecraft\versions\CheatBreakerNeo-1.8.9`，供用户实际测试。
- 部署必须更新该版本的 JAR、JSON 和 natives，并保留部署脚本生成的旧产物备份。保留用户游戏配置、存档及其他运行数据。
- 验证部署 JAR 与本轮构建 JAR 的 SHA-256 一致，版本 JSON 的 id/jar 为 `CheatBreakerNeo-1.8.9`，clientVersion 为 `1.8.9`，并核对全部 natives。
- 若部署生成受 Git 管理的报告变更，完成后也必须提交这些变更，确保最终工作区干净。
- 最终回复说明提交号、构建/测试结果和部署目录。游戏内效果未经实际验证时须如实说明。

## 修改范围

- `src/main/java` 是实际编译源码；`recovery/decompiled-complete` 是反编译参考档案。
- 参考 `F:\Work\CheatBreakerZ` 时，不修改参考项目。
- 构建、日志和备份产物写入 `.target/`，不提交到 Git。
