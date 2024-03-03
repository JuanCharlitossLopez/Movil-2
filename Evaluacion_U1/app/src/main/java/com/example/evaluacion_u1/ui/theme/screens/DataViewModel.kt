package com.example.evaluacion_u1.ui.theme.screens

import androidx.lifecycle.ViewModel
import com.example.evaluacion_u1.model.AlumnoAcademicoResult
import com.example.evaluacion_u1.model.CalificacionUnidad
import com.example.evaluacion_u1.model.CargaAcademicaItem
import com.example.evaluacion_u1.model.Kardex
import com.example.evaluacion_u1.model.Promedio

class DataViewModel:ViewModel(){
    var alumnoAcademicoResult:AlumnoAcademicoResult?= null
    var CargaByAlumno: List<CargaAcademicaItem>?=null
    var kardexByAlumno: Kardex?=null
    var califXunit: List<CalificacionUnidad>?= null
}