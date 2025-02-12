
[中文](https://github.com/LuckyPuppy514/jproxy/blob/main/changelog.md) | [English](https://github.com/LuckyPuppy514/jproxy/blob/main/changelog.en_US.md)

# 变更日志

## v3.0.6 2023-04-08

1. 新增修改 TMDB 标题的功能

## v3.0.5 2023-04-08

1. 修复部分数据无法下载的问题

## v3.0.4 2023-04-08

1. 修复追加 TMDB 标题查询 BUG

## v3.0.3 2023-04-07

1. 优化标题匹配逻辑

## v3.0.2 2023-04-07

1. 优化 Radarr 标题匹配逻辑

## v3.0.1 2023-04-07

1. 新增规则备用地址，避免因无法访问 github 导致无法同步规则
2. Docker 底层镜像变更，以支持 ARM 架构

## v3.0.0 2023-04-06

🚨 代码重构，不兼容 v2 版本

1. 前后端分离，全新 WebUI
2. 格式化逻辑重构，匹配方式由原来的单一规则，分离成多个标记规则，更自由且识别率更高
3. 引入 TMDB，自动追加所选语言标题优化 Sonarr 查询
4. 添加对 Radarr 的支持，自动追加主语言标题优化查询，同时适配新逻辑，格式化结果，提升识别率
5. 规则分享改成 Pull Request 形式，规则下载改为直接同步本仓库 `src/main/resources/rule` 目录下规则

## v2.6.5 2023-01-17

1. 提升: 种子名称格式化（减少因为种子名称不规范导致不自动导入或导入错误季的问题）

## v2.6.4 2022-09-21

1. 修复：市场-查询，下载次数排序问题
2. 新增：修改密码时可修改用户名

## v2.6.3 2022-09-02

1. 修复：使用 RestTemplate 代替 WebClient 以解决偶尔请求异常的问题

## v2.6.2 2022-08-10

1. 修复：当系列类型为：Standard, Daily 时，查询关键字季集，日期等参数无法格式化的问题

## v2.6.1 2022-08-07

1. 修复：部分 BT/PT 站当使用 qBittorrent 代理时无法下载的问题

## v2.6.0 2022-08-05

1. 新功能：qBittorrent 代理
2. 新功能：新增搜索条件：备注
3. 修复：当 sonarr 无法识别种子标题时，导入错误季的问题

## v2.5.2 2022-08-01

1. 新功能：保存代理配置时，先进行连通性测试

## v2.5.1 2022-07-30

1. 更新 README.md
2. 变更：sqlite-jdbc 版本到：3.39.2-SNAPSHOT，用于修复在 aarch64 机器上 docker build 出错的问题
3. 修复：同步出错的问题
4. 修复：prowlarr 错误

## v2.5.0 2022-07-30

1. 简单界面：支持中文和英文
2. 代理配置：配置 Jackett / Prowlarr 的地址，端口等信息
3. 新增规则：包括查询规则和结果规则
4. 规则管理：查询，编辑，删除，分享，以及导入导出等
5. 规则市场：可以查询大家分享的规则，并下载
6. 用例测试：可以批量添加标题进行测试，查看格式化后的效果
