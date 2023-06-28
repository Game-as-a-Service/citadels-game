# 使用官方的 Node.js 映像檔作為基底
FROM node:18

# 設定工作目錄
WORKDIR /app

# 複製應用程式內容到容器中
COPY . .

# 安裝相依套件
RUN npm ci

# 指定容器執行時的預設命令
ENTRYPOINT [ "npm", "run", "dev"]

EXPOSE 3001
