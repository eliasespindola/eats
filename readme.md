https://refactorfirst.com/spring-cloud-stream-with-kafka-communication

- TODO
  - Criar o evento de processing no restaurant
  - Fazer o save de dominio
  - Criar um topico de resposta e enviar o evento de processing la
  - Atualizar na order o evento
  - Fazer dar exception para cair na DLQ(Criar DLQ)
  - Ler dados da DLQ e enviar para o topico de resposta falando o que deu errado com base na exception
  - Mudar como estoura a exception ao inves de dar um throw ir adicionando os error em uma classe e assim enviar como resposta
