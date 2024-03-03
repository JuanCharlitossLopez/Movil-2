package com.example.evaluacion_u1.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "Envelope", strict = false)
data class Envelope3 @JvmOverloads constructor(
    @field:Element(name = "Body", required = false)
    var body3: Body3 = Body3()
)
@Root(name = "Body", strict = false)
data class Body3 @JvmOverloads constructor(
    @field:Element(name = "getAllKardexConPromedioByAlumnoResponse", required = false)
    var getAllKardexConPromedioByAlumnoResponse: GetAllKardexConPromedioByAlumnoResponse = GetAllKardexConPromedioByAlumnoResponse()
)

@Root(name = "getAllKardexConPromedioByAlumnoResponse", strict = false)
data class GetAllKardexConPromedioByAlumnoResponse @JvmOverloads constructor(
    @field:Element(name = "getAllKardexConPromedioByAlumnoResult", required = false)
    var getAllKardexConPromedioByAlumnoResult: String = ""
)

@Serializable
data class KardexItem(
    @SerialName("S3")
    val s3: String?,

    @SerialName("P3")
    val p3: String?,

    @SerialName("A3")
    val a3: String?,

    @SerialName("ClvMat")
    val clvMat: String,

    @SerialName("ClvOfiMat")
    val clvOfiMat: String,

    @SerialName("Materia")
    val materia: String,

    @SerialName("Cdts")
    val cdts: Int,

    @SerialName("Calif")
    val calif: Int,

    @SerialName("Acred")
    val acred: String,

    @SerialName("S1")
    val s1: String,

    @SerialName("P1")
    val p1: String,

    @SerialName("A1")
    val a1: String,

    @SerialName("S2")
    val s2: String?,

    @SerialName("P2")
    val p2: String?,

    @SerialName("A2")
    val a2: String?
)

@Serializable
data class Promedio(
    @SerialName("PromedioGral")
    val promedioGral: Double,

    @SerialName("CdtsAcum")
    val cdtsAcum: Int,

    @SerialName("CdtsPlan")
    val cdtsPlan: Int,

    @SerialName("MatCursadas")
    val matCursadas: Int,

    @SerialName("MatAprobadas")
    val matAprobadas: Int,

    @SerialName("AvanceCdts")
    val avanceCdts: Double
)

@Serializable
data class Kardex(
    @SerialName("lstKardex")
    val lstKardex: List<KardexItem>,

    @SerialName("Promedio")
    val promedio: Promedio
)