import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.work.ListenableWorker
import androidx.work.testing.TestListenableWorkerBuilder
import com.example.bluromatic.workers.CleanupWorker
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class WorkerInstrumentationTest {
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun cleanupWorker_doWork_resultSuccess() {
        val worker = TestListenableWorkerBuilder<CleanupWorker>(context).build()
        runBlocking {
            val result = worker.doWork()
            assertTrue(result is ListenableWorker.Result.Success)
        }
    }
}