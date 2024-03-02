package com.example.evaluacion_u1.ui.theme.screens

import androidx.lifecycle.ViewModel
import com.example.evaluacion_u1.model.AlumnoAcademicoResult
import com.example.evaluacion_u1.model.CargaAcademicaItem

class DataViewModel:ViewModel(){
    var alumnoAcademicoResult:AlumnoAcademicoResult?= null
    var CargaByAlumno: List<CargaAcademicaItem>?=null
}