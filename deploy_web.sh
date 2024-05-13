#!/bin/bash

set -e
echo "删除备份"
ssh ubuntu@52.87.178.171 "rm -rf /home/ubuntu/backend-testing/source/admin-web/dist/*"
# 1. back remote file
#echo "备份远程文件"
#ssh ubuntu@52.87.178.171 "mv /home/ubuntu/backend-testing/source/back-admin/back-admin.jar /home/ubuntu/backend-testing/source/back-admin/back-admin.jar.bak"
echo "上传文件"
# 2. upload local file
scp -r -l 1000000 -o ConnectTimeout=60000 ./ruoyi-ui/dist/*  ubuntu@52.87.178.171:/home/ubuntu/backend-testing/source/admin-web/dist/
echo "启动远程服务"
# 3. 关闭远程java进程
# 4. 启动远程java进程
echo "deploy success"
set +e