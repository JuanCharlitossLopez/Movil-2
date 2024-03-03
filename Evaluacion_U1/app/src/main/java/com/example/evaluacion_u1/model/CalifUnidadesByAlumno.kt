package com.example.evaluacion_u1.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "Envelope", strict = false)
data class Envelope4 @JvmOverloads constructor(
    @field:Element(name = "Body", required = false)
    var body4: Body4 = Body4()
)
@Root(name = "Body", strict = false)
data class Body4 @JvmOverloads constructor(
    @field:Element(name = "getCalifUnidadesByAlumnoResponse ", required = false)
    var getCalifUnidadesByAlumnoResponse: GetCalifUnidadesByAlumnoResponse  = GetCalifUnidadesByAlumnoResponse()
)

@Root(name = "getCalifUnidadesByAlumnoResponse ", strict = false)
data class GetCalifUnidadesByAlumnoResponse  @JvmOverloads constructor(
    @field:Element(name = "getCalifUnidadesByAlumnoResult", required = false)
    var getCalifUnidadesByAlumnoResult: String = ""
)

@Serializable
data class CalificacionUnidad(
    @SerialName("Observaciones")
    val observaciones: String?,

    @SerialName("C13")
    val c13: String?,

    @SerialName("C12")
    val c12: String?,

    @SerialName("C11")
    val c11: String?,

    @SerialName("C10")
    val c10: String?,

    @SerialName("C9")
    val c9: String?,

    @SerialName("C8")
    val c8: String?,

    @SerialName("C7")
    val c7: String?,

    @SerialName("C6")
    val c6: String?,

    @SerialName("C5")
    val c5: String?,

    @SerialName("C4")
    val c4: String?,

    @SerialName("C3")
    val c3: String?,

    @SerialName("C2")
    val c2: String?,

    @SerialName("C1")
    val c1: String?,

    @SerialName("UnidadesActivas")
    val unidadesActivas: String,

    @SerialName("Materia")
    val materia: String,

    @SerialName("Grupo")
    val grupo: String
)