/*
 * Implementa `checkAccess` con un `when` como sentencia:
 *   - si el usuario está en `bannedUsers` -> imprime "<user>: acceso denegado"
 *   - si el usuario es "root" o "admin" -> imprime "<user>: bienvenido"
 *   - cualquier otro usuario -> no imprime nada.
 *
 * Salida esperada:
 *   root: bienvenido
 *   elena: acceso denegado
 *   admin: bienvenido
 */

val bannedUsers = listOf("elena", "pablo")

fun checkAccess(user: String) {
    when (user) {
        in bannedUsers -> println("$user: acceso denegado")
        "root", "admin" -> println("$user: bienvenido")
    }
}

fun main() {
    for (user in listOf("root", "alicia", "elena", "admin")) {
        checkAccess(user)
    }
}