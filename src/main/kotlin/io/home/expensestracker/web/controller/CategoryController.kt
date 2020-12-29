package io.home.expensestracker.web.controller

import io.home.expensestracker.model.Category
import io.home.expensestracker.repository.CategoryRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.persistence.EntityNotFoundException

@RestController
@RequestMapping("/v1/categories")
class CategoryController(val categoryRepository: CategoryRepository) {

    private val uriComponents: UriComponentsBuilder = UriComponentsBuilder.fromPath("/v1/categories/{id}")

    @GetMapping
    fun list():ResponseEntity<List<Category>>{
        return ResponseEntity.ok(categoryRepository.findAll())
    }

    @PostMapping
    fun create(@RequestBody newCategory: Category): ResponseEntity<Category>{
        val savedCategory = categoryRepository.save(newCategory)

        return ResponseEntity
                .created(uriComponents
                        .buildAndExpand(savedCategory.id).toUri())
                .body(savedCategory)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id")id:Long,
               @RequestBody updateCategory:Category): ResponseEntity<Category>{
        if(!categoryRepository.existsById(id)){
            throw EntityNotFoundException("No entity with id $id found")
        }

        updateCategory.id = id
        val updated = categoryRepository.save(updateCategory)

        return ResponseEntity
                .created(uriComponents
                        .buildAndExpand(updateCategory.id).toUri())
                .body(updated)
    }

}