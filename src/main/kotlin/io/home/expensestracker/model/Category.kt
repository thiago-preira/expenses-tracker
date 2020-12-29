package io.home.expensestracker.model

import javax.persistence.*

@Entity
@Table(name = "categories")
data class Category(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(columnDefinition = "serial")
        var id: Long? = null,
        val name: String,
        @ManyToOne
        @JoinColumn(name="group_id",columnDefinition = "int4")
        val group: Group
){
        constructor():this(name="",group = Group())
}