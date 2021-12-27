import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cwnu.androidwebcrawler.MainActivity
import com.cwnu.androidwebcrawler.NoticeListForm
import com.cwnu.androidwebcrawler.PostFragment
import com.cwnu.androidwebcrawler.R
import kotlinx.android.synthetic.main.item_contacts.view.*

class ContactsListAdapter(val context: Context, val itemList : List<NoticeListForm>) :
    RecyclerView.Adapter<ContactsListAdapter.ContactsViewHolder>(){


    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contacts, parent, false)
        return ContactsViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val item = itemList[position]
        holder.apply {
            bind(item)
        }
    }
    inner class ContactsViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var view : View = v

        fun bind(item: NoticeListForm) {
            if(item.num == "공지") {
                view.mNum.setTextColor(Color.RED)
                view.mNum.setTypeface(null, Typeface.BOLD)
            }
            else{
                view.mNum.setTextColor(Color.BLACK)
                view.mNum.setTypeface(null, Typeface.NORMAL)
            }
            view.mNum.text = item.num
            view.mTitle.text = item.title
            view.mName.text = item.name
            view.mDate.text = item.date

            view.setOnClickListener {
                PostFragment(item.where,item.data_id).show((context as MainActivity).supportFragmentManager,"post")
            }
        }
    }
}