package in.harshita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.harshita.binding.Passenger;
import in.harshita.binding.Ticket;
import in.harshita.service.MmtService;
import reactor.core.publisher.Mono;

@Controller
public class MmtController {
	
	@Autowired
	private MmtService service;
	
	@PostMapping("/ticket")
	public String ticketBooking(@ModelAttribute("p") Passenger p, Model model) {
		Mono<Ticket> bookTicket = service.bookTicket(p);
		model.addAttribute("msg", "Your Ticket has been booked");
		model.addAttribute("ticket",bookTicket);
		return "bookTicket";
	}
	
	@GetMapping("/book-ticket")
	public String bookTicket(Model model) {
		model.addAttribute("p", new Passenger());
		model.addAttribute("ticket", new Ticket());
		return "bookTicket";
	}
		
	@GetMapping("/")
	public String index(Model model) {
		Mono<Ticket[]> allTickets = service.getAllTickets();
		model.addAttribute("tickets",allTickets);
		return "index";
	}


}
