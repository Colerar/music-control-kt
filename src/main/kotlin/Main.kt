import org.jnativehook.GlobalScreen
import org.jnativehook.keyboard.NativeKeyEvent
import org.jnativehook.keyboard.NativeKeyListener
import java.util.concurrent.TimeUnit
import java.util.logging.Level
import java.util.logging.Logger

const val CTRL_ALT_CMD = 40974
const val UP_ARROW = 57416
const val DOWN_ARROW = 57424
const val LEFT_ARROW = 57419
const val RIGHT_ARROW = 57421
const val P_KEY = 25

fun jax(script: String) =
  ProcessBuilder().inheritIO().command("osascript", "-l", "JavaScript", "-e", script).start()
    .waitFor(5, TimeUnit.SECONDS)

val appName by lazy(LazyThreadSafetyMode.NONE) {
  val env = System.getenv("MUSIC_CONTROLLER_APP_NAME")
  if (env.isNullOrEmpty()) "Music" else env
}

fun playpause() = jax("Application('$appName').playpause();")

fun volumeUp() = jax(
  """
  var app = Application('$appName');
  var volume = app.soundVolume();
  app.soundVolume = volume + 10 < 100 ? volume + 10 : 100;
  """.trimIndent()
)

fun volumeDown() = jax(
  """
  var app = Application('$appName');
  var volume = app.soundVolume();
  app.soundVolume = volume - 10 > 0 ? volume - 10 : 0;
  """.trimIndent()
)

fun nextTrack() = jax("Application('$appName').nextTrack();")

fun previousTrack() = jax("Application('$appName').previousTrack();")

fun main() {
  Logger.getLogger(GlobalScreen::class.java.getPackage().name).level = Level.OFF
  GlobalScreen.registerNativeHook()
  GlobalScreen.addNativeKeyListener(object : NativeKeyListener {
    override fun nativeKeyTyped(nativeEvent: NativeKeyEvent) {}
    override fun nativeKeyReleased(nativeEvent: NativeKeyEvent) {}

    override fun nativeKeyPressed(nativeEvent: NativeKeyEvent) {
      if (nativeEvent.modifiers != CTRL_ALT_CMD) return
      when (nativeEvent.keyCode) {
        UP_ARROW -> {
          println("volumeUp()")
          volumeUp()
        }

        DOWN_ARROW -> {
          println("volumeDown()")
          volumeDown()
        }

        LEFT_ARROW -> {
          println("nextTrack()")
          nextTrack()
        }

        RIGHT_ARROW -> {
          println("previousTrack()")
          previousTrack()
        }

        P_KEY -> {
          println("playpause()")
          playpause()
        }
      }
    }
  })
}
