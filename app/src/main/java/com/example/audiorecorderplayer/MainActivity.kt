package com.example.audiorecorderplayer

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.audiorecorderplayer.databinding.ActivityMainBinding
import java.io.File
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mediaRecorder: MediaRecorder? = null
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var archivoAudio: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Habilitar Edge-to-Edge
        enableEdgeToEdge()
        
        // Inicializar ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Manejar insets de las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configurar ruta del archivo de audio
        val cacheDir = externalCacheDir
        archivoAudio = "${cacheDir?.absolutePath ?: filesDir.absolutePath}/grabacion.3gp"

        // Solicitar permisos si es necesario
        checkPermissions()

        // Configurar Listeners
        setupListeners()

        // Estado inicial de los botones
        updateButtonStates(isRecording = false, isPlaying = false)
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                REQUEST_AUDIO_PERMISSION
            )
        }
    }

    private fun setupListeners() {
        binding.btnGrabar.setOnClickListener { iniciarGrabacion() }
        binding.btnDetener.setOnClickListener { detenerGrabacion() }
        binding.btnReproducir.setOnClickListener { reproducirAudio() }
        binding.btnDetenerAudio.setOnClickListener { detenerReproduccion() }
    }

    private fun updateButtonStates(isRecording: Boolean, isPlaying: Boolean) {
        binding.btnGrabar.isEnabled = !isRecording && !isPlaying
        binding.btnDetener.isEnabled = isRecording
        binding.btnReproducir.isEnabled = !isRecording && !isPlaying
        binding.btnDetenerAudio.isEnabled = isPlaying
    }

    private fun iniciarGrabacion() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED
        ) {
            checkPermissions()
            return
        }

        try {
            mediaRecorder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                MediaRecorder(this)
            } else {
                @Suppress("DEPRECATION")
                MediaRecorder()
            }.apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                setOutputFile(archivoAudio)
                prepare()
                start()
            }

            // Iniciar Cronómetro
            binding.cronometro.base = SystemClock.elapsedRealtime()
            binding.cronometro.visibility = View.VISIBLE
            binding.cronometro.start()

            binding.txtEstado.setText(R.string.estado_grabando)
            Toast.makeText(this, R.string.grabacion_iniciada, Toast.LENGTH_SHORT).show()
            updateButtonStates(isRecording = true, isPlaying = false)

        } catch (e: Exception) {
            Log.e(TAG, "Error al iniciar grabación", e)
            Toast.makeText(this, R.string.error_iniciar_grabacion, Toast.LENGTH_SHORT).show()
            updateButtonStates(isRecording = false, isPlaying = false)
        }
    }

    private fun detenerGrabacion() {
        mediaRecorder?.let {
            try {
                it.stop()
                
                // Detener Cronómetro
                binding.cronometro.stop()
                
                binding.txtEstado.setText(R.string.estado_finalizada)
                Toast.makeText(this, R.string.audio_guardado, Toast.LENGTH_SHORT).show()
            } catch (e: RuntimeException) {
                Log.e(TAG, "Error al detener grabación (posiblemente muy corta)", e)
                Toast.makeText(this, R.string.error_detener, Toast.LENGTH_SHORT).show()
                binding.cronometro.stop()
                binding.cronometro.visibility = View.INVISIBLE
            } finally {
                it.release()
                mediaRecorder = null
                updateButtonStates(isRecording = false, isPlaying = false)
            }
        }
    }

    private fun reproducirAudio() {
        val file = File(archivoAudio)
        if (!file.exists()) {
            Toast.makeText(this, R.string.no_grabacion, Toast.LENGTH_SHORT).show()
            return
        }

        mediaPlayer = MediaPlayer().apply {
            try {
                setDataSource(archivoAudio)
                prepare()
                start()

                // Configurar cronómetro para reproducción
                binding.cronometro.base = SystemClock.elapsedRealtime()
                binding.cronometro.visibility = View.VISIBLE
                binding.cronometro.start()

                binding.txtEstado.setText(R.string.estado_reproduciendo)
                Toast.makeText(this@MainActivity, R.string.reproduciendo_audio, Toast.LENGTH_SHORT).show()
                updateButtonStates(isRecording = false, isPlaying = true)

                setOnCompletionListener {
                    detenerReproduccion()
                    Toast.makeText(this@MainActivity, R.string.reproduccion_finalizada, Toast.LENGTH_SHORT).show()
                }
            } catch (e: IOException) {
                Log.e(TAG, "Error al reproducir audio", e)
                Toast.makeText(this@MainActivity, R.string.no_grabacion, Toast.LENGTH_SHORT).show()
                updateButtonStates(isRecording = false, isPlaying = false)
            }
        }
    }

    private fun detenerReproduccion() {
        mediaPlayer?.let {
            try {
                if (it.isPlaying) {
                    it.stop()
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error al detener MediaPlayer", e)
            } finally {
                it.release()
                mediaPlayer = null
                
                // Detener Cronómetro
                binding.cronometro.stop()
                binding.cronometro.visibility = View.INVISIBLE
                
                binding.txtEstado.setText(R.string.estado_listo)
                updateButtonStates(isRecording = false, isPlaying = false)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        
        if (requestCode == REQUEST_AUDIO_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, R.string.permiso_concedido, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, R.string.permiso_denegado, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaRecorder?.release()
        mediaRecorder = null
        mediaPlayer?.release()
        mediaPlayer = null
    }

    companion object {
        private const val TAG = "AudioRecorder"
        private const val REQUEST_AUDIO_PERMISSION = 100
    }
}
