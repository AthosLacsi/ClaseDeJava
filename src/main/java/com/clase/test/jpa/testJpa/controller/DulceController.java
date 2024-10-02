package com.clase.test.jpa.testJpa.controller;

import com.clase.test.jpa.testJpa.model.Dulce;
import com.clase.test.jpa.testJpa.repository.DulceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dulces")
public class DulceController {

    @Autowired
    private DulceRepository dulceRepository;

    @GetMapping
    public List<Dulce> getAllDulces() {
        return dulceRepository.findAll();
    }

    @PostMapping
    public Dulce createDulce(@RequestBody Dulce dulce) {
        return dulceRepository.save(dulce);
    }

    @GetMapping("/{id}")
    public Dulce getDulceById(@PathVariable Long id) {
        return dulceRepository.findById(id).orElseThrow(() -> new RuntimeException("Dulce no encontrado"));
    }

    @PutMapping("/{id}")
    public Dulce updateDulce(@PathVariable Long id, @RequestBody Dulce dulceDetails) {
        Dulce dulce = dulceRepository.findById(id).orElseThrow(() -> new RuntimeException("Dulce no encontrado"));

        dulce.setNombre(dulceDetails.getNombre());
        dulce.setSabor(dulceDetails.getSabor());
        dulce.setPrecio(dulceDetails.getPrecio());

        return dulceRepository.save(dulce);
    }

    @DeleteMapping("/{id}")
    public void deleteDulce(@PathVariable Long id) {
        Dulce dulce = dulceRepository.findById(id).orElseThrow(() -> new RuntimeException("Dulce no encontrado"));
        dulceRepository.delete(dulce);
    }

        // Método para obtener dulces que cuestan menos de un dólar
    @GetMapping("/menos-de-un-dolar")
    public List<Dulce> getDulcesMenosDeUnDolar() {
        return dulceRepository.findAll().stream()
                .filter(dulce -> dulce.getPrecio() < 1)
                .collect(Collectors.toList());
    }
}

