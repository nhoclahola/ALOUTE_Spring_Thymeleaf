docker run --network mariadb-network --name mariadb-container -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mariadb:latest
sudo docker run -d --network mariadb-network  --name social-network-container -p 8080:8080 --env-file .env social-network-v1

