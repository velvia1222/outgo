package com.outgo;

import com.outgo.controller.TestController;
import com.outgo.entity.Outgo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class OutgoApplication {

	public static void main(String[] args) {
		SpringApplication.run(OutgoApplication.class, args);
	}

	@Autowired
    TestController testController;

	@RequestMapping(path = "/")
	List<Outgo> all() {
        List<Outgo> outgoes = testController.searchOutgo();
        return outgoes;
	}
}
