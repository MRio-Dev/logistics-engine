package com.fleetOps.logistics_engine;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@RestController
@RequestMapping("/test")
public class TestCrudController {

    @Autowired
    private TestService service;

    // CREATE
    @PostMapping
    public TestEntity create(@RequestBody TestEntity entity) {
        System.out.println("POST called: " + entity.getName());
        return service.save(entity);
    }

    // READ ALL
    @GetMapping
    public List<TestEntity> getAll() {
        return service.getAll();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public TestEntity getById(@PathVariable Long id) {
        return service.getById(id).orElseThrow();
    }

    // UPDATE
    @PutMapping("/{id}")
    public TestEntity update(@PathVariable Long id, @RequestBody TestEntity updated) {
        TestEntity entity = service.getById(id).orElseThrow();
        entity.setName(updated.getName());
        return service.save(entity);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted successfully";
    }
}


// ================= ENTITY =================

@Entity
@Table(name = "test_entity")
class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // getters & setters
    public Long getId() { return id; }

    public String getName() { return name; }

    public void setId(Long id) { this.id = id; }

    public void setName(String name) { this.name = name; }
}


// ================= REPOSITORY =================

interface TestRepository extends JpaRepository<TestEntity, Long> {}


// ================= SERVICE =================

@Service
class TestService {

    @Autowired
    private TestRepository repository;

    public TestEntity save(TestEntity entity) {
        return repository.save(entity);
    }

    public List<TestEntity> getAll() {
        return repository.findAll();
    }

    public Optional<TestEntity> getById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
