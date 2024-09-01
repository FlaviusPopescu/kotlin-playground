import kotlin.test.Test
import kotlin.test.assertTrue

class JvmAppTest {
    @Test fun test() {
        JvmApp().run {
            assertTrue { systemVersion.isNotEmpty() }
        }
    }
}
