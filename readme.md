#Planet Api

É uma api escrita me kotlin usando springboot, jpa, swagger,containers(docker). 
Para rodar a aplicação com o docker.
Para criar o .jar da aplicação é preciso usar o comando:  
```
./gradlew bootjar   
```

Após é precisa dar rodar o comando:
```docker-compose up ```

Para conhecer os endpoints é preciso ir ao endereço:
```
http://{server}/swagger-ui.html 
```

O banco de dados esta como drop-down por causa do teste o plano era criar um datasource para rodar os testes em memória. 

