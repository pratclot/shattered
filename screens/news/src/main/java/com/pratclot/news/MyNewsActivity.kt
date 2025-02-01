package com.pratclot.news

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.test.espresso.IdlingResource
import com.pratclot.IdlingResourceProvider
import com.pratclot.domain.NewsItem
import com.pratclot.news.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.pow
import kotlin.math.sin

@AndroidEntryPoint
class MyNewsActivity : AppCompatActivity(), IdlingResourceProvider {

    /**
     * Smooth, eh?
     */
    override val idlingResource: IdlingResource by lazy { viewModel.idlingResource }

    private val viewModel: MyNewsViewModel by viewModels()
    lateinit var recyclerView: RecyclerView

    lateinit var myAdapter: NewsAdapter

    fun interface NewsItemClickListener {
        operator fun invoke(news: NewsItem)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.newsRecyclerView)
        recyclerView = findViewById(R.id.newsRecyclerView)
        myAdapter = NewsAdapter(viewModel.imageLoader) { viewModel.userClickedOn(it) }
        with(recyclerView) {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(this@MyNewsActivity)
        }
        observeNavigationEvents()
    }

    override fun onResume() {
        super.onResume()

        viewModel.run {
            contentLoading.observe(this@MyNewsActivity) {
                findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_layout).run {
                    isRefreshing = it
                    setOnRefreshListener { refreshTriggered() }
                }
                findViewById<FrameLayout>(R.id.overlay).run {
                    isVisible = it
                    isClickable = it
                }
            }
            items.observe(this@MyNewsActivity) {
                myAdapter.submitList(it)
            }
            error.observe(this@MyNewsActivity) {
                if (it) findViewById<TextView>(R.id.error_view).run {
                    appearBounceFadeAway(lifecycleScope)
                }
            }
        }
    }

    private fun observeNavigationEvents() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.openLinkEvent.collect {
                    openNews(it)
                }
            }
        }
    }
}

fun Activity.openNews(link: String) = startActivity(
    Intent(Intent.ACTION_VIEW, Uri.parse(link))
)

/**
 * Copypasta: https://stackoverflow.com/a/54321211/13442292
 * From here: https://stackoverflow.com/a/55648103/13442292
 * And here: https://stackoverflow.com/a/6822116/13442292
 */
fun View.appearBounceFadeAway(lifecycleScope: LifecycleCoroutineScope) {
    animation?.cancel()
    isVisible = true
    val fadeOutAnimation = AlphaAnimation(1f, 0f).also {
        it.interpolator = AccelerateInterpolator()
        it.startOffset = 1_000
        it.duration = 1_000
        it.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                /**
                 * Sorry about that, this is purely for Espresso - it will not assert a view while it is bouncing.
                 */
                lifecycleScope.launch {
                    delay(500)
                    isVisible = false
                }
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }
        })
    }
    val bounceAnimation = AnimationUtils.loadAnimation(context, R.anim.bounce).also {
        it.setInterpolator { 1 - 1 * sin(2 * Math.PI * it).pow(2).toFloat() }
        it.startOffset = 1_000
        it.duration = 1_000
        it.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                startAnimation(fadeOutAnimation)
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }
        })
    }
    startAnimation(bounceAnimation)
}
