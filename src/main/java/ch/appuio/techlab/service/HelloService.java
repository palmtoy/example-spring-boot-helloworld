package ch.appuio.techlab.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import ch.appuio.techlab.repository.HelloRepository;

@Service
public class HelloService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloService.class);
    @Autowired
    private HelloRepository helloRepository;

    @Async
    public void deleteHelloAsync(Long helloId) {
      LOGGER.trace("_deleteHelloAsync ~ Start delete helloId ( {} ) at {}", helloId, System.currentTimeMillis());
      try {
        Thread.sleep(6000);
      } catch (InterruptedException e) {
        LOGGER.error("_deleteHelloAsync ~ Error: {}", e.getMessage());
        Thread.currentThread().interrupt();
      }
      helloRepository.deleteById(helloId);
      LOGGER.trace("_deleteHelloAsync ~ End delete helloId ( {} ) at {}", helloId, System.currentTimeMillis());
    }
}