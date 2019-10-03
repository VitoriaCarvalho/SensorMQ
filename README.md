# SensorMQ

Sistema distribuído para detecção de proximidade usando o protocolo MQTT como broker de mensagens e o Raspberry Pi 3 como subscribe.

## Iniciando o sistema

### Requisitos

Possuir o Android Studio, o Docker e o HiveMQ instalados, sendo que o HiveMQ é um contêiner Docker. Além disso, é necessária a instalação das bibliotecas pygame e paho-mqtt, para as funcionalidades do subscribe.

### Execução

Para execução do servidor broker, execute o HiveMQ com o seguinte comando:
    
    docker run -p 8080:8080 -p 1883:1883 hivemq/hivemq4

Para executar a aplicação publisher, ou seja, a aplicação Android, é necessário criar a build do aplicativo através do Android Studio.

Para executar a aplicação subscribe, ou seja, a aplicação que rodará no Raspberry Pi 3, deve-se logar no dispositivo por meio de ssh ou de forma convencional e executar o seguinte comando:
    
    python3 SensorMQ/sub.py
    
Com os ambientes configurados, você poderá ativar o sensor de proximidade do dipositivo precionando no botão switch. Assim, sempre que o sensor detectar uma aproximação, um alarme será soado no Raspberry.

## Desenvolvedores

* Açucena Rodrigues dos Santos Soares - [acucenarodrigues1998](<https://github.com/acucenarodrigues1998/>)
* Jederson Sousa Luz - [JedersonLuz](<https://github.com/JedersonLuz/>)
* Vitória de Carvalho Brito - [VitoriaCarvalho](<https://github.com/VitoriaCarvalho/>)

## Licença

[MIT](https://github.com/JedersonLuz/BibWorld/blob/master/LICENSE)

