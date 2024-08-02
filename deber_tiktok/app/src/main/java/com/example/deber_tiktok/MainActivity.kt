package com.example.deber_tiktok
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

class MainActivity : AppCompatActivity() {
    private lateinit var videoRecyclerView: RecyclerView
    private lateinit var commentsRecyclerView: RecyclerView
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var gestureDetector: GestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        videoRecyclerView = findViewById(R.id.videoRecyclerView)
        commentsRecyclerView = findViewById(R.id.commentsRecyclerView)
        bottomNav = findViewById(R.id.bottomNav)

        setupVideoRecyclerView()
        setupCommentsRecyclerView()
        setupBottomNavigation()
        setupGestureDetector()
    }

    private fun setupVideoRecyclerView() {
        val videoAdapter = VideoAdapter(generateDummyVideos())
        videoRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        videoRecyclerView.adapter = videoAdapter

        // Añadir PagerSnapHelper para el efecto de deslizamiento de página
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(videoRecyclerView)
    }

    private fun setupCommentsRecyclerView() {
        val commentAdapter = CommentAdapter(generateDummyComments())
        commentsRecyclerView.layoutManager = LinearLayoutManager(this)
        commentsRecyclerView.adapter = commentAdapter
    }

    private fun setupBottomNavigation() {
        bottomNav.setOnItemSelectedListener { menuItem ->
            // Aquí iría la lógica de navegación (no implementada para este ejemplo)
            true
        }
    }

    private fun setupGestureDetector() {
        gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onDoubleTap(e: MotionEvent): Boolean {
                toggleCommentsVisibility()
                return true
            }
        })

        videoRecyclerView.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            false
        }
    }

    private fun toggleCommentsVisibility() {
        if (commentsRecyclerView.visibility == View.VISIBLE) {
            commentsRecyclerView.visibility = View.GONE
        } else {
            commentsRecyclerView.visibility = View.VISIBLE
        }
    }

    private fun generateDummyVideos(): List<VideoItem> {
        return listOf(
            VideoItem("Erick", "¡Descubre los secretos de la cocina! Hoy te enseñamos una receta rápida y deliciosa que puedes hacer en casa. ¡No te lo pierdas! #RecetasFáciles #CocinaRápida #TikTokChef", R.drawable.placeholder_receta),
            VideoItem("Jeniffer", "Transforma tu rutina de ejercicios con estos movimientos efectivos. ¡Sigue estos sencillos pasos para ponerte en forma! #FitnessEnCasa #EjerciciosSimples #Motivación", R.drawable.placeholder_fitness),
            VideoItem("Lissette", "¿Fan de las manualidades? Aprende a crear decoraciones hermosas y únicas para cualquier ocasión. ¡Es más fácil de lo que piensas! #DIY #Manualidades #Creatividad", R.drawable.placeholder_manualidad)
        )
    }

    private fun generateDummyComments(): List<CommentItem> {
        return listOf(
            CommentItem("Sebastian", "¡Me encantó este video! Siempre aprendo algo nuevo contigo."),
            CommentItem("Jenny", "¡Sigue haciendo contenido así! Realmente disfruto cada uno de tus videos."),
            CommentItem("Sebas", "¡Gracias por compartir! Esto es justo lo que necesitaba.")
        )
    }
}
