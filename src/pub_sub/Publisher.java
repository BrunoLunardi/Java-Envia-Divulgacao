package pub_sub;

//Imports para utilizar o protocolo MQTT
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import configs.ConfigServer;


public class Publisher {
	
	//String que enviará a conta para o outro sistema
    String mensagemEnviaConta;
    //string para topicos
    String topico;
    //construtor da classe para enviar a mensagem
	public Publisher(String topico, String mensagemEnviaConta){
		this.topico = topico;
		this.mensagemEnviaConta = mensagemEnviaConta;
	}
    
	//Método para enviar a mensagem para o mosquitto
	public void enviarMensagem() throws MqttException {
		//Local para onde será enviado a mensagem (broker)
		ConfigServer configServer = new ConfigServer();
		//MqttClient client = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());
		MqttClient client = new MqttClient(configServer.getEnderecoServidor(), 
				MqttClient.generateClientId());
		//abre conexão com o broker (Mosquitto)
		client.connect();
		//objeto de envio de mensagem do broker
		MqttMessage menssagem = new MqttMessage();
			//Payload, conteudo da mensagem montagem da mensagem que será enviada ao broker
		menssagem.setPayload(mensagemEnviaConta.getBytes());
	    //Publica a mensagem, com seu tópico (para alguém se escrever neste tópico) e a mensagem montada
	    client.publish(topico, menssagem);
	    //fecha conexão com broker mosquitto
	    client.disconnect();
	}	

}