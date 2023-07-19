package com.elcodee.epic

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class JadwalAdapter: RecyclerView.Adapter<JadwalAdapter.userViewHolder>(){
    private var userlist = mutableListOf<User>()
//    private var onClickView:((User)-> Unit)? = null
    private var onClickDelete:((User)-> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userViewHolder {
        val context = parent.context
        val inflate = LayoutInflater.from(context)
        val itemView = inflate.inflate(R.layout.listitem, parent, false)
        return userViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: userViewHolder, position: Int) {
        val data = userlist[position]
        holder.setItem(data)
        holder.update.setOnClickListener {
            val i = Intent(holder.itemView.context, SettingActivity::class.java)
            i.putExtra("hr", data.hari)
            i.putExtra("n1", data.nm1)
            i.putExtra("n2", data.nm2)
            holder.itemView.context.startActivity(i)
        }
//        holder.setOnClickView {
//            onClickView?.invoke(it)
//        }
        holder.setOnClickDelete {
            onClickDelete?.invoke(it)
        }
    }

    override fun getItemCount(): Int {
        return userlist.size
    }

//    fun setOnClickView(callback:(User)->Unit){
//        this.onClickView = callback
//    }
    fun setOnClickDelete(callback:(User)->Unit){
        this.onClickDelete = callback
    }

    fun setItems(list: MutableList<User>){
        this.userlist = list
        notifyDataSetChanged()
    }

    class userViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
//        private var tvHari: TextView? = null
        private var  tvNm1: TextView? = null
        private var  tvNm2: TextView? = null
//        private var  ivUpdate: ImageView? = null
        private var  ivDelete: ImageView? = null
//        private var onClickView: ((User) -> Unit)? = null
        var tvHari = itemView.findViewById<TextView>(R.id.tv_hari)
        val update = itemView.findViewById<ImageView>(R.id.iv_update)
        private var onClickDelete: ((User) -> Unit)? = null

        fun setItem(data:User){
//            tvHari = itemView.findViewById(R.id.tv_hari)
            tvNm1 = itemView.findViewById(R.id.tv_nama1)
            tvNm2 = itemView.findViewById(R.id.tv_nama2)
            ivDelete = itemView.findViewById(R.id.iv_delete)

            tvHari?.text = data.hari
            tvNm1?.text = data.nm1
            tvNm2?.text = data.nm2

//            ivUpdate?.setOnClickListener {
//               onClickView?.invoke(data)
//            }
            ivDelete?.setOnClickListener {
                onClickDelete?.invoke(data)
            }
        }

//        fun setOnClickView(callback:(User)->Unit){
//            this.onClickView = callback
//        }
        fun setOnClickDelete(callback:(User)->Unit){
            this.onClickDelete = callback
        }
    }
}