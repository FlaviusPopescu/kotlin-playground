class JvmApp {
    val systemVersion: String = "Java ${System.getProperty("java.version")}"
}

fun main() = JvmApp().run {
    println("Hello from $systemVersion")
}
