// Estrutura feita - falta escalar!
// package com.foodminder.FoodMinder.domain.rabbitmq.connections;
//
// import com.foodminder.FoodMinder.domain.rabbitmq.constantes.RabbitMQConstantes;
// import jakarta.annotation.PostConstruct;
// import org.springframework.amqp.core.AmqpAdmin;
// import org.springframework.amqp.core.Binding;
// import org.springframework.amqp.core.DirectExchange;
// import org.springframework.amqp.core.Queue;
// import org.springframework.stereotype.Component;
//
// @Component
// public class RabbitMQConnection {
//     private static final String NOME_EXCHENGE = "amq.direct";
//     private AmqpAdmin amqpAdmin;
//     public RabbitMQConnection(AmqpAdmin amqpAdmin){
//         this.amqpAdmin = amqpAdmin;
//     }
//     private Queue fila(String nomeFila){
//         return new Queue(nomeFila, true, false, false);
//     }
//     private DirectExchange directExchange() {
//         return new DirectExchange(NOME_EXCHENGE);
//     }
//     private Binding relacionamento(Queue fila, DirectExchange directExchange) {
//         return new Binding(fila.getName(), Binding.DestinationType.QUEUE, directExchange.getName(), fila.getName(), null);
//     }
//     @PostConstruct
//     public void adiciona() {
//         Queue filaPlanejamento = this.fila(RabbitMQConstantes.FILA_PLANEJAMENTO);
//         Queue filaRefeicao = this.fila(RabbitMQConstantes.FILA_REFEICAO);
//         DirectExchange troca = this.directExchange();
//         Binding conexaoPlanejamento = this.relacionamento(filaPlanejamento, troca);
//         Binding conexaoRefeicao = this.relacionamento(filaRefeicao, troca);
//         this.amqpAdmin.declareQueue(filaPlanejamento);
//         this.amqpAdmin.declareQueue(filaRefeicao);
//         this.amqpAdmin.declareExchange(troca);
//         this.amqpAdmin.declareBinding(conexaoPlanejamento);
//         this.amqpAdmin.declareBinding(conexaoRefeicao);
//     }
// }
