package com.elcodee.epic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.elcodee.epic.databinding.ActivityMainBinding
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    private val binding:  ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var firebaseDatabase: FirebaseDatabase? = null
    private var databaseReference: DatabaseReference? = null
    private var adapter : JadwalAdapter? = null
    private var list = mutableListOf<User>()
    private var selectedHari: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase?.getReference("Users")

        getData()
        initRecycleView()

        binding.apply {
            ivAdd.setOnClickListener {
                startActivity(Intent(this@MainActivity, AddActivity::class.java))
            }
            ivDev.setOnClickListener {
                startActivity(Intent(this@MainActivity, DevActivity::class.java))
            }
            ivBack.setOnClickListener {
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finish()
            }
        }
    }


    private fun initRecycleView() {
        binding.apply {
            adapter = JadwalAdapter()
            rvJadwal.layoutManager = LinearLayoutManager(this@MainActivity)
            rvJadwal.adapter = adapter
        }
//        adapter?.setOnClickView {
//            val i = Intent(this@MainActivity, SettingActivity::class.java)
//            i.putExtra("id", selectedHari)
//            startActivity(i)
//        }
        adapter?.setOnClickDelete {
            selectedHari = it.hari
            databaseReference?.child(selectedHari.orEmpty())?.removeValue()
        }
    }

    private fun getData() {
        databaseReference?.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
//                Log.d("ooo","onDataChange: $snapshot")
                list.clear()
                for (ds in snapshot.children){
                    val hari = ds.child("hari").value.toString()
                    val nm1 = ds.child("nm1").value.toString()
                    val nm2 = ds.child("nm2").value.toString()

                    val user = User(hari = hari, nm1 = nm1, nm2 = nm2)
                    list.add(user)
                }
                Log.d("ooooo","size: ${list.size}")
                adapter?.setItems(list)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("oooo","onCancelled: ${error.toException()}")
            }
        })
    }
}