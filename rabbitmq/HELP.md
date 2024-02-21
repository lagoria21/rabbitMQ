# Getting Started

### Guides Docker 

### Download Image RabbitMQ
- docker pull rabbitmq:3.12.13-management

### Run RabbitMQ
- docker run --rm -it -p 15672:15672 -p 5672:5672 rabbitmq:3.12.13-management

### Rabbit Management
- http://localhost:15672/
- user : guest
- password : guest