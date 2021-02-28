package br.com.evenflow.planetsapi.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Note(
    @Id
    @GeneratedValue
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    val id: Long = 0L,
    val title: String = "",
    val description: String = ""
)

