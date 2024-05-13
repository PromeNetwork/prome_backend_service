#!/bin/bash

set -e
echo "删除备份"
ssh ubuntu@54.236.172.208 "rm -rf /home/ubuntu/backend/source/back-admin/back-admin.jar.bak"
# 1. back remote file
echo "备份远程文件"
ssh ubuntu@54.236.172.208 "mv /home/ubuntu/backend/source/back-admin/back-admin.jar /home/ubuntu/backend/source/back-admin/back-admin.jar.bak"
echo "上传文件"
# 2. upload local file
scp -l 1000000 -o ConnectTimeout=60000 ./ruoyi-admin/target/back-admin.jar ubuntu@54.236.172.208:/home/ubuntu/backend/source/back-admin
echo "启动远程服务"
# 3. 关闭远程java进程
#ssh ubuntu@52.87.178.171 "ps -ef | grep java | grep -v grep | awk '{print \$2}' | xargs kill -9"
# 4. 启动远程java进程
#ssh ubuntu@52.87.178.171 "cd  /home/ubuntu/backend-testing && docker compose build back-admin && docker compose up -d back-admin"
echo "deploy success"
set +e