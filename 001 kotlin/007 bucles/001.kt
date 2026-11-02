/*
 * Implementa las dos funciones:
 *   - `printUppercase`: usa `forEach` para imprimir cada elemento en mayúsculas.
 *
 * Salida esperada:
 *   MANZANA
 *   BANANA
 *   KIWI
 */

fun printUppercase(items: List<String>) {
    for(item in items){
        println(item.uppercase())
    }
}

fun printUppercase2(items: List<String>) {
    items.forEach {
        println(it.uppercase())
    }
}

fun main() {
    val items = listOf("manzana", "banana", "kiwi")
    printUppercase(items)
    printUppercase2(items)
}