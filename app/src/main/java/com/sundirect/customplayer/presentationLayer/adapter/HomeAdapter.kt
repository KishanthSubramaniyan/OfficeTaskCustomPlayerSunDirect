import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sundirect.customplayer.datalayer.dto.HomeItem
import com.sundirect.customplayer.databinding.AdapterInflateViewdesignBinding
import com.sundirect.customplayer.databinding.AdapterInflateViewdesigncontinueBinding
import com.sundirect.customplayer.databinding.AdapterInflateViewdesigngeneresBinding
import com.sundirect.customplayer.presentationLayer.HomeViewType
import com.sundirect.customplayer.presentationLayer.Transfer
import com.sundirect.customplayer.presentationLayer.adapter.ContinueWatchingViewHolder
import com.sundirect.customplayer.presentationLayer.adapter.GenresViewHolder
import com.sundirect.customplayer.presentationLayer.adapter.TrendingViewHolder

class HomeAdapter(
    private val items: List<HomeItem>, private val transfer: Transfer.Alpha<Int>? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return items[position].viewType.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        when (viewType) {

            HomeViewType.CONTINUE_WATCHING.type -> {
                val mBinding : AdapterInflateViewdesigncontinueBinding = AdapterInflateViewdesigncontinueBinding.inflate(inflater,parent,false)
                return ContinueWatchingViewHolder(mBinding, transfer)
            }

            HomeViewType.TRENDING.type -> {
                val mBinding : AdapterInflateViewdesignBinding = AdapterInflateViewdesignBinding.inflate(inflater,parent,false)
                return TrendingViewHolder(mBinding, transfer)
            }

            HomeViewType.GENRES.type -> {
                val mBinding : AdapterInflateViewdesigngeneresBinding = AdapterInflateViewdesigngeneresBinding.inflate(inflater,parent,false)
                return GenresViewHolder(mBinding, transfer)
            }

            else -> throw IllegalArgumentException("Unknown type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is ContinueWatchingViewHolder -> holder.bind((items[position] as HomeItem.ContinueWatchingView).items)
            is TrendingViewHolder -> holder.bind((items[position] as HomeItem.TrendingView).items)
            is GenresViewHolder -> holder.bind((items[position] as HomeItem.Genres).items)
        }
    }

    override fun getItemCount() = items.size
}
