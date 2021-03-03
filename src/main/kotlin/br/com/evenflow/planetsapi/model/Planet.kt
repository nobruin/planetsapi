package br.com.evenflow.planetsapi.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
data class Planet(
    @Id
    @GeneratedValue
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    val id: Long = 0L,
    @Column(unique = true)
    val name: String = "",
    val climate: String = "",
    val terrain: String = ""
){
    var countFilms: Int = 0
}