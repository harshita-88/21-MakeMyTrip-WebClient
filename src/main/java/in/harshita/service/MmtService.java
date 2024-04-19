package in.harshita.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import in.harshita.binding.Passenger;
import in.harshita.binding.Ticket;
import reactor.core.publisher.Mono;


@Service
public class MmtService {
	
	public Mono<Ticket> bookTicket(Passenger p) {
		
		String apiUrl = "http://51.20.73.24:8080/ticket";
		WebClient webclient = WebClient.create();
		
		Mono<Ticket> body = webclient.post()
							 .uri(apiUrl)
							 .body(BodyInserters.fromValue(p))
							 .retrieve()
							 .bodyToMono(Ticket.class);
				
		
		return body;
	}
	
	public Mono<Ticket[]> getAllTickets(){
		String apiUrl = "http://51.20.73.24:8080/tickets";
		WebClient webclient = WebClient.create();
		
		Mono<Ticket[]> bodyToMono = webclient.get()
									.uri(apiUrl)
									.retrieve()
									.bodyToMono(Ticket[].class);
		 
	
		return bodyToMono;
		
	}
	

}
