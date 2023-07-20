#!/bin/bash

# 切換到 "spring" 資料夾
cd "$(dirname "$0")/spring"

# 執行 docker-compose down 命令
docker-compose down