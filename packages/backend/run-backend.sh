#!/bin/bash

# 設定目標檔案名稱
target_file="citadels-backend-0.0.1-SNAPSHOT.jar"

# 使用 mvnw.cmd 執行 Maven package 命令
./mvnw package

# 切換到 "spring/target" 資料夾
cd "$(dirname "$0")/spring/target"

# 移動檔案至 "spring" 資料夾
mv "$target_file" "$(dirname "$0")/spring"

# 切換回 "spring" 資料夾
cd "$(dirname "$0")/spring"

# 執行 docker-compose up 命令
docker-compose up -d --build