/*
 * Implementa dos funciones sobre rectángulos:
 *   - `area`: con cuerpo de bloque y `return` explícito.
 *   - `perimeter`: como función de una sola expresión.
 *
 * Salida esperada:
 *   Area: 13.5
 *   Perimeter: 15.0
 */

fun area(width: Double, height: Double): Double {
    val a = width * height
    return a
}

fun perimeter(width: Double, height: Double): Double = width*2 + height*2

fun main() {
    println("Area: ${area(3.0, 4.5)}")
    println("Perímetro: ${perimeter(3.0, 4.5)}")
}
