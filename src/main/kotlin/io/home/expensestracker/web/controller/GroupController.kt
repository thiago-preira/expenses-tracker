package io.home.expensestracker.web.controller

import io.home.expensestracker.model.Group
import io.home.expensestracker.repository.GroupRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponents
import org.springframework.web.util.UriComponentsBuilder
import javax.persistence.EntityNotFoundException


@RestController
@RequestMapping("/v1/groups")
class GroupController(val groupRepository: GroupRepository) {

    @GetMapping
    fun list():ResponseEntity<List<Group>>{
        return ResponseEntity.ok(groupRepository.findAll().sortedBy { it.name })
    }

    @PostMapping
    fun create(@RequestBody newGroup: Group): ResponseEntity<Group>{
        val savedGroup = groupRepository.save(newGroup)

        val uriComponents: UriComponents = UriComponentsBuilder.fromPath("/v1/groups/{id}")
                .buildAndExpand(savedGroup.id)

        return ResponseEntity
                .created(uriComponents.toUri())
                .body(savedGroup)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id")id:Long,
               @RequestBody updateGroup:Group): ResponseEntity<Group>{
        val targetGroup = groupRepository.findById(id)
        val group = targetGroup.orElseThrow { EntityNotFoundException("No Entity found with id $id") }
        updateGroup.id = id
        groupRepository.save(updateGroup)

        val uriComponents: UriComponents = UriComponentsBuilder.fromPath("/v1/groups/{id}")
                .buildAndExpand(id)

        return ResponseEntity
                .created(uriComponents.toUri())
                .body(updateGroup)
    }

}