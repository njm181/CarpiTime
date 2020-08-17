package com.njm.carpintime.domain.utils.excepction

import java.lang.Exception

/**
Created by Nicolas Molina 16/8/2020
 */
class ParametersZeroException: Exception() {
     override val message: String = "Algun parametro o ambos son iguales a cero (LAT, LONG)"
}