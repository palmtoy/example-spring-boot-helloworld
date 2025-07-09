package ch.appuio.techlab.controller;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ch.appuio.techlab.model.Hello;
import ch.appuio.techlab.service.HelloService;
import ch.appuio.techlab.repository.HelloRepository;

@RestController
@RequestMapping("/hellos")
public class HelloRestController {
	@Autowired
	private HelloRepository helloRepository;
	@Autowired
	private HelloService helloService;

	@RequestMapping(method = RequestMethod.GET)
	public Collection<Hello> hellos(){
		return helloRepository.findAllByOrderByIdAsc();
	}
	
	@RequestMapping(value = "/{name}", method = RequestMethod.POST)
	public ResponseEntity<?> add(@PathVariable String name){
		Hello result = helloRepository.save(new Hello(name,
				new Date()));
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(result.getId()).toUri());
		return new ResponseEntity<>(result, httpHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{helloId}", method = RequestMethod.GET)
	public Optional<Hello> get(@PathVariable Long helloId) {
		return this.helloRepository.findById(helloId);
	}

	// curl -X DELETE -v http://localhost:8081/hellos/3
	@Transactional
	@DeleteMapping(value = "/{helloId}")
	public ResponseEntity<?> delete(@PathVariable Long helloId) {
		helloService.deleteHelloAsync(helloId);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}
