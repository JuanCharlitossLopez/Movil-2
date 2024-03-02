package com.example.evaluacion_u1.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "Envelope", strict = false)
data class Envelope2 @JvmOverloads constructor(
    @field:Element(name = "Body", required = false)
    var body2: Body2 = Body2()
)
@Root(name = "Body", strict = false)
data class Body2 @JvmOverloads constructor(
    @field:Element(name = "getCargaAcademicaByAlumnoResponse", required = false)
    var getCargaAcademicaByAlumnoResponse: GetCargaAcademicaByAlumnoResponse = GetCargaAcademicaByAlumnoResponse()
)

@Root(name = "getCargaAcademicaByAlumnoResponse", strict = false)
data class GetCargaAcademicaByAlumnoResponse @JvmOverloads constructor(
    @field:Element(name = "getCargaAcademicaByAlumnoResult", required = false)
    var getCargaAcademicaByAlumnoResult: String = ""
)

@Serializable
data class CargaAcademicaItem(
    @SerialName("Semipresencial")
    val semipresencial: String?,

    @SerialName("Observaciones")
    val observaciones: String?,

    @SerialName("Docente")
    val docente: String,

    @SerialName("clvOficial")
    val clvOficial: String,

    @SerialName("Sabado")
    val sabado: String?,

    @SerialName("Viernes")
    val viernes: String?,

    @SerialName("Jueves")
    val jueves: String?,

    @SerialName("Miercoles")
    val miercoles: String?,

    @SerialName("Martes")
    val martes: String?,

    @SerialName("Lunes")
    val lunes: String?,

    @SerialName("EstadoMateria")
    val estadoMateria: String,

    @SerialName("CreditosMateria")
    val creditosMateria: Int,

    @SerialName("Materia")
    val materia: String,

    @SerialName("Grupo")
    val grupo: String
)