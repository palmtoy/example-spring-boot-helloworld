package ch.appuio.techlab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import ch.appuio.techlab.repository.HelloRepository;

@Service
public class HelloService {
    @Autowired
    private HelloRepository helloRepository;

    @Async
    public void deleteHelloAsync(Long helloId) {
        helloRepository.deleteById(helloId);
    }
}