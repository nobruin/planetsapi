package br.com.evenflow.planetsapi.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotEmpty

@Entity
data class Planet(
    @Id
    @GeneratedValue
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    val id: Long = 0L,
    val name: String = "",
    val climate: String = "",
    val terrain: String = ""
)