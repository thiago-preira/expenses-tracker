package io.home.expensestracker.model

import com.fasterxml.jackson.annotation.JsonCreator
import javax.persistence.*

@Table(name="groups")
@Entity
data class Group(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(columnDefinition = "serial")
        var id: Long? = null,
        val name: String
) {
        @JsonCreator
        constructor():this(name="")
}