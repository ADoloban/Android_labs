package com.example.lab3

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ViewDataActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_data)

        dbHelper = DatabaseHelper(this)
        val listView = findViewById<ListView>(R.id.listViewData)
        val textViewEmpty = findViewById<TextView>(R.id.textViewEmpty)
        val buttonClear = findViewById<Button>(R.id.buttonClear)

        val dataList = dbHelper.getAllData()
        if (dataList.isEmpty()) {
            textViewEmpty.visibility = TextView.VISIBLE
            listView.visibility = ListView.GONE
            buttonClear.visibility = Button.GONE
        } else {
            textViewEmpty.visibility = TextView.GONE
            listView.visibility = ListView.VISIBLE
            buttonClear.visibility = Button.VISIBLE

            val displayList = dataList.map { "${it.first} → ${it.second}" }
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, displayList)
            listView.adapter = adapter
        }

        buttonClear.setOnClickListener {
            dbHelper.clearDatabase()
            finish()
            startActivity(intent)
        }
    }
}