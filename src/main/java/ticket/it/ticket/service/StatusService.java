package ticket.it.ticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ticket.it.ticket.model.Ticket;
import ticket.it.ticket.repository.StatusRepository;

@Service
public class StatusService {

  @Autowired
  private StatusRepository statusRepository;

}
