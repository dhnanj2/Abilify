package org.tensorflow.lite.codelabs.Abilify

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.divyanshu.draw.widget.DrawView
import android.speech.tts.TextToSpeech;
import java.util.*


class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

  private var drawView: DrawView? = null
  private var clearButton: ImageButton? = null
  private var predictedTextView: TextView? = null
  private var digitClassifier = DigitClassifier(this)
  private var textToSpeech: TextToSpeech? = null

  @SuppressLint("ClickableViewAccessibility")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // Setup view instances.
    drawView = findViewById(R.id.draw_view)
    drawView?.setStrokeWidth(70.0f)
    drawView?.setColor(Color.WHITE)
    drawView?.setBackgroundColor(Color.BLACK)
    clearButton = findViewById(R.id.backspace)
    predictedTextView = findViewById(R.id.no_to_dial)
    textToSpeech = TextToSpeech(this, this)

    // Setup clear drawing button.
    clearButton?.setOnClickListener {
//      drawView?.clearCanvas()
        var str = predictedTextView?.text.toString()
        if(str.isEmpty()) return@setOnClickListener

//        define text to speak
        var dig = "Erased " + str.substring(str.length-1)

//      pop out the last digit
        str =  str.substring(0,str.length-1);
        predictedTextView?.text = str;

//      speak out the erased digit
        textToSpeech!!.speak(dig,TextToSpeech.QUEUE_ADD,null,"")
    }

    // Setup classification trigger so that it classify after every stroke drew.
    drawView?.setOnTouchListener { _, event ->
      // As we have interrupted DrawView's touch event,
      // we first need to pass touch events through to the instance for the drawing to show up.
      drawView?.onTouchEvent(event)

      // Then if user finished a touch event, run classification
      if (event.action == MotionEvent.ACTION_UP) {
        classifyDrawing()
        drawView?.clearCanvas()
      }

      true
    }

    // Setup digit classifier.
    digitClassifier
      .initialize()
      .addOnFailureListener { e -> Log.e(TAG, "Error to setting up digit classifier.", e) }
  }

  override fun onDestroy() {
    // Sync DigitClassifier instance lifecycle with MainActivity lifecycle,
    // and free up resources (e.g. TF Lite instance) once the activity is destroyed.
    digitClassifier.close()
    textToSpeech?.shutdown()
    super.onDestroy()
  }

  private fun classifyDrawing() {
    val bitmap = drawView?.getBitmap()

    if ((bitmap != null) && (digitClassifier.isInitialized)) {
      digitClassifier
        .classifyAsync(bitmap)
        .addOnSuccessListener { resultText -> predictedTextView?.append(resultText)

          //speak out the recognised digit
          textToSpeech!!.speak(resultText,TextToSpeech.QUEUE_ADD,null,"")

          //if 10 digits of the number has been entered, request a phone call through Intent
          if(predictedTextView?.text?.length==10) {
            val numTxt = (predictedTextView?.text?.toString()?.trim())
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+numTxt))
            startActivity(intent)

            //speak out the dialing message
            textToSpeech!!.speak("Dialing",TextToSpeech.QUEUE_ADD,null,"")
          }
        }
        .addOnFailureListener { e ->
          predictedTextView?.text = getString(
            R.string.classification_error_message,
            e.localizedMessage
          )
          Log.e(TAG, "Error classifying drawing.", e)
        }
    }
  }

  companion object {
    private const val TAG = "MainActivity"
  }

  override fun onInit(status: Int) {
    if (status == TextToSpeech.SUCCESS) {
      // set US English as language for tts
      val result = textToSpeech!!.setLanguage(Locale.US)

      if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
        Log.e("TTS","The Language specified is not supported!")
      } else {
        clearButton!!.isEnabled = true

        //speak out to message to enter number
        textToSpeech!!.speak("Draw digits to Dial",TextToSpeech.QUEUE_ADD,null,"")
      }

    } else {
      Log.e("TTS", "Initilization Failed!")
    }
  }

}
