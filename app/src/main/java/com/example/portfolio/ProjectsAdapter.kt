import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.portfolio.DataModel
import com.example.portfolio.R

class ProjectsAdapter(private val dataList: List<DataModel>,private val listener: OnItemClickListener) :
    RecyclerView.Adapter<ProjectsAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(data: DataModel)
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val imgView: ImageView = itemView.findViewById(R.id.img)
        val textView: TextView = itemView.findViewById(R.id.proj_subtitle)
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(dataList[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.projects , parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.imgView.setImageResource(data.imageResource)
        holder.textView.text = data.text
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}