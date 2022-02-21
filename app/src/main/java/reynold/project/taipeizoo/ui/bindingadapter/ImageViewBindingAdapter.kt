package reynold.project.taipeizoo.ui.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("imageUrl")
fun bindImageFromUrl(imageView: ImageView, url: String) {
    // Avoid Exception: Cleartext HTTP traffic to www.zoo.gov.tw not permitted
    val httpsUrl = url.replaceFirst("http://", "https://", true)
    Glide.with(imageView)
        .load(httpsUrl)
        .centerCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imageView)
}