package com.outgo;

import com.outgo.dao.OutgoDao;
import com.outgo.entity.Outgo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RestController
public class OutgoApplication {

	@Autowired
	OutgoDao outgoDao;

	public static void main(String[] args) {
		SpringApplication.run(OutgoApplication.class, args);
	}

	@RequestMapping(path = "/")
	List<Outgo> all() {
		return outgoDao.selectAll();
	}
}
