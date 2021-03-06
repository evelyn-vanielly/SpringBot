package com.eecomerce.farmacia.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eecomerce.farmacia.model.Produto;
import com.eecomerce.farmacia.repository.RepositoryProduto;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping ("/produto")

public class ControllerProduto {
	
	@Autowired
	private RepositoryProduto repository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAll()
	{
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<Produto>getById (@PathVariable long id)
	{
		return repository.findById(id).map (resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Produto>Post(@RequestBody Produto produto)
	{
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(produto));
	}
	@PutMapping
	public ResponseEntity<Produto>Put(@RequestBody Produto produto)
	{
		return ResponseEntity.ok(repository.save(produto));
	}
	
	@DeleteMapping("/{id}")
	public void Delete (@PathVariable long id)
	{
		repository.deleteById(id);
	}
}
