docker run --network mariadb-network --name mariadb-container -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mariadb:latest
sudo docker run -d --network mariadb-network  --name social-network-container -p 8080:8080 -e DBMS_CONNECTION=jdbc:mariadb://mariadb-container:3306/social_network_v1 social-network-v1

