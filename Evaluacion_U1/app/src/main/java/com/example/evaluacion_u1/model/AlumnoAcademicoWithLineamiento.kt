package com.example.evaluacion_u1.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "Envelope", strict = false)
data class Envelope @JvmOverloads constructor(
    @field:Element(name = "Body", required = false)
    var body: Body = Body()
)

@Root(name = "Body", strict = false)
data class Body @JvmOverloads constructor(
    @field:Element(name = "getAlumnoAcademicoWithLineamientoResponse", required = false)
    var getAlumnoAcademicoWithLineamientoResponse: GetAlumnoAcademicoWithLineamientoResponse = GetAlumnoAcademicoWithLineamientoResponse()
)

@Root(name = "getAlumnoAcademicoWithLineamientoResponse", strict = false)
data class GetAlumnoAcademicoWithLineamientoResponse @JvmOverloads constructor(
    @field:Element(name = "getAlumnoAcademicoWithLineamientoResult", required = false)
    var getAlumnoAcademicoWithLineamientoResult: String = ""
)

@Serializable
data class AlumnoAcademicoResult(
    val fechaReins: String,
    @SerialName("modEducativo")
    val modEducativo: Int,
    val adeudo: Boolean,
    val urlFoto: String,
    val adeudoDescripcion: String,
    val inscrito: Boolean,
    val estatus: String,
    @SerialName("semActual")
    val semActual: Int,
    @SerialName("cdtosAcumulados")
    val cdtosAcumulados: Int,
    @SerialName("cdtosActuales")
    val cdtosActuales: Int,
    val especialidad: String,
    val carrera: String,
    val lineamiento: Int,
    val nombre: String,
    val matricula: String
)