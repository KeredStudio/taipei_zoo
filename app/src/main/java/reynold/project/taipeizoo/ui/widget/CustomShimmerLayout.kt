package reynold.project.taipeizoo.ui.widget

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.facebook.shimmer.ShimmerFrameLayout
import reynold.project.taipeizoo.R
import reynold.project.taipeizoo.databinding.ShimmerMainLayoutBinding

class CustomShimmerLayout(context: Context, attrs: AttributeSet) : ShimmerFrameLayout(context, attrs) {

    init {
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomShimmerLayout, 0, 0)
        val shimmerItemCount = typedArray.getInt(R.styleable.CustomShimmerLayout_shimmerItemCount, 0)
        val shimmerItemLayoutRes = typedArray.getResourceId(R.styleable.CustomShimmerLayout_shimmerItemLayout, 0)
        typedArray.recycle()

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val shimmerMainBinding = DataBindingUtil.inflate<ShimmerMainLayoutBinding>(inflater, R.layout.shimmer_main_layout, this, true)

        for (i in 0..shimmerItemCount) {
            DataBindingUtil.inflate<ViewDataBinding>(inflater, shimmerItemLayoutRes, shimmerMainBinding.mainLayout, true)
        }
    }
}