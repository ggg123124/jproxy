<p align="center">
  <a href="https://github.com/LuckyPuppy514/jproxy">
    <img alt="JProxy Logo" width="200" src="https://raw.githubusercontent.com/LuckyPuppy514/image/main/2023/2023-04-02/logo.png">
  </a>
</p>
<p align="center">
  <a href="https://github.com/LuckyPuppy514/jproxy"><img allt="stars" src="https://badgen.net/github/stars/LuckyPuppy514/jproxy"/></a>
  <a href="https://github.com/LuckyPuppy514/jproxy"><img allt="forks" src="https://badgen.net/github/forks/LuckyPuppy514/jproxy"/></a>
  <a href="./LICENSE"><img allt="MIT License" src="https://badgen.net/github/license/LuckyPuppy514/jproxy"/></a>
</p>

[中文](https://github.com/LuckyPuppy514/jproxy/blob/main/README.md) | [English](https://github.com/LuckyPuppy514/jproxy/blob/main/README.en_US.md)

- [🌟 Introduce](#-introduce)
- [🧱 Install](#-install)
  - [Docker](#docker)
  - [Windows](#windows)
- [☃️ Basic Configuration](#️-basic-configuration)
- [😘 Contributing](#-contributing)
- [👏 Related Efforts](#-related-efforts)
- [🃏 License](#-license)

## 🌟 Introduce

A proxy between `Sonarr / Radarr` and `Jackett / Prowlarr`, mainly used to optimize search and improve recognition rate

```mermaid
graph LR
    1[Sonarr / Radarr] == request Jackett / Prowlarr Torznab interface ==> 2(JProxy) == proxy Sonarr / Radarr request ==> 3(Jackett / Prowlarr)

    3(Jackett / Prowlarr) == return raw result ==> 2(JProxy) == return formatted result ==> 1(Sonarr / Radarr)
    
    2(JProxy) == optimize search keywords ==> 2(JProxy)
    2(JProxy) == format search result ==> 2(JProxy)
```

![20230405044128](https://github.com/LuckyPuppy514/image/raw/main/2023/2023-04-05/20230405044128.webp)
![20230406181845](https://github.com/LuckyPuppy514/image/raw/main/2023/2023-04-06/20230406181845.webp)
![20230406181914](https://github.com/LuckyPuppy514/image/raw/main/2023/2023-04-06/20230406181914.webp)

## 🧱 Install

### Docker

```text
version: '3.0'
services:
   jproxy:
      image: luckypuppy514/jproxy:latest
      container_name: jproxy
      restart: unless-stopped
      environment:
      - PUID=1000
      - PGID=1000
      - TZ=Asia/Shanghai
      - REDIS_HOST=jproxy-redis
      - REDIS_PORT=6379
      - JAVA_OPTS=-Xms512m -Xmx512m
      links:
      - jproxy-redis
      ports:
      - 8117:8117
      volumes:
      - /docker/jproxy/database:/app/database

   jproxy-redis:
      image: redis:latest
      container_name: jproxy-redis
      restart: unless-stopped
```

If you want deploy via `docker run` ，see [docker-run.sh](https://github.com/LuckyPuppy514/jproxy/blob/main/docker/docker-run.sh)

|   Parameter    |  Default  |              Description              |
| :------------: | :-------: | :-----------------------------------: |
|      PUID      |     0     |                User ID                |
|      PGID      |     0     |               Group ID                |
|   REDIS_HOST   | 127.0.0.1 |              Redis host               |
|   REDIS_PORT   |   6379    |              Redis port               |
|   JAVA_OPTS    |     -     | Suggested values: ​​-Xms512m -Xmx512m |
| REDIS_PASSWORD |     -     |            Redis password             |
| REDIS_DATABASE |     0     |            Redis database             |

### Windows

1. [Download jdk17](https://kutt.lckp.top/yrnerc), install and configure environment variables
2. [Download windows.zip](https://github.com/LuckyPuppy514/jproxy/releases) ，unzip to the installation directory

|   Filename   |        Explanation        |                 Remark                  |
| :----------: | :-----------------------: | :-------------------------------------: |
| startup.bat  |       starup script       |                    -                    |
| shutdown.bat |      shutdown script      |                    -                    |
| startup.vbs  | startup background script | hidden window running in the background |
|   database   |         database          |          keep it while upgrade          |
|    config    |    configuration files    |                    -                    |
|  jproxy.jar  |   Runnable jar package    |                    -                    |
|    redis     |      redis database       |                    -                    |

## ☃️ Basic Configuration

- URL: `http://127.0.0.1:8117/login`
- User: `jproxy`
- Password: `jproxy@2023`

![20230406181038](https://github.com/LuckyPuppy514/image/raw/main/2023/2023-04-06/20230406181038.webp)

① Fill in `Sonarr Server Url`, `API KEY`, and `Indexer Address` in `System -  Configure` (Jackett or Prowlarr)

![20230404182207](https://github.com/LuckyPuppy514/image/raw/main/2023/2023-04-04/20230404182207.webp)
![20230406182057](https://github.com/LuckyPuppy514/image/raw/main/2023/2023-04-06/20230406182057.webp)

💡 After saving, it should normally be as shown in the picture below ✅, otherwise please check the input and network connectivity

![20230406182207](https://github.com/LuckyPuppy514/image/raw/main/2023/2023-04-06/20230406182207.webp)

② For the first use, it is recommended to manually synchronize `Series Title` and `Series Rule` once (it will be automatically synchronized later)

![20230406182240](https://github.com/LuckyPuppy514/image/raw/main/2023/2023-04-06/20230406182240.webp)
![20230406182304](https://github.com/LuckyPuppy514/image/raw/main/2023/2023-04-06/20230406182304.webp)

③ In `Sonarr - Indexers`, modify the `IP` and `Port` of the indexer `URL` to the `IP` and `Port` of JProxy, and append the path `/sonarr/jackett` (Prowlarr should append with `/ sonarr/prowlarr`)

`http://192.168.6.15:9117/api/v2.0/......` ➡️ `http://192.168.6.14:8117/sonarr/jackett/api/v2.0/......`
  
![20230404172541](https://github.com/LuckyPuppy514/image/raw/main/2023/2023-04-04/20230404172541.webp)

💡 It is recommended to modify one first, and then modify other indexer configurations after confirming that it is normal

![20230405044447](https://github.com/LuckyPuppy514/image/raw/main/2023/2023-04-05/20230405044447.webp)

[🎗️ For advanced configuration and usage, see wiki](https://github.com/LuckyPuppy514/jproxy/wiki)

## 😘 Contributing

Feel free to dive in！[Open an issue](https://github.com/LuckyPuppy514/jproxy/issues/new/choose) or submit PRs.

- [arco-design-pro-vue](https://github.com/arco-design/arco-design-pro-vue)
- [spring-boot](https://github.com/spring-projects/spring-boot)
- [sqlite](https://github.com/sqlite/sqlite)
- [liquibase](https://github.com/liquibase/liquibase)
- [mybatis](https://github.com/mybatis/mybatis-3)
- [mybatis-plus](https://github.com/baomidou/mybatis-plus)
- [redis](https://github.com/redis/redis)
- [knife4j](https://github.com/xiaoymin/knife4j)
- [charon](https://github.com/mkopylec/charon-spring-boot-starter)
- [jib](https://github.com/GoogleContainerTools/jib)

## 👏 Related Efforts

- [Sonarr](https://github.com/Sonarr/Sonarr)
- [Radarr](https://github.com/radarr/radarr)
- [Jackett](https://github.com/Jackett/Jackett)
- [Prowlarr](https://github.com/Prowlarr/Prowlarr)
- [qBittorrent](https://github.com/qbittorrent/qBittorrent)

## 🃏 License

[MIT](https://github.com/LuckyPuppy514/jproxy/blob/main/LICENSE) © LuckyPuppy514
