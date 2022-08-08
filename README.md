# spark-base- hdfs
### 
5 file log được ghi-> đẩy lên hdfs lưu trữ
```
Sử dụng docker để sử dụng các công cụ xử lý dữ liệu lớn : Spark , Hbase
  B1: docker pull liliasfaxi/spark-hadoop:hv-2.7.2
  B2: Cấu hình :
    docker run -itd --net=hadoop -p 50070:50070 -p 8088:8088 -p 7077:7077 -p 16010:16010 \
            --name hadoop-master --hostname hadoop-master \
            liliasfaxi/spark-hadoop:hv-2.7.2

  docker run -itd -p 8040:8042 --net=hadoop \
        --name hadoop-slave1 --hostname hadoop-slave1 \
              liliasfaxi/spark-hadoop:hv-2.7.2

  docker run -itd -p 8041:8042 --net=hadoop \
        --name hadoop-slave2 --hostname hadoop-slave2 \
              liliasfaxi/spark-hadoop:hv-2.7.2
B3: Chạy hệ thống lưu trữ file Hdfs:
    docker exec -it hadoop-master bash
```
Buid project thành file Jar rồi sử dụng câu lệnh : 
```
  spark-submit --class main.Main --master local[2] SparkTutorials-V1.jar
```
Kết quả được lưu dưới dạng file csv và hiển thị dưới dạng các biểu đồ.
