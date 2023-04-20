package com.example.sqlitedatabase


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var entername: EditText
    lateinit var enterage: EditText
    lateinit var addname: Button
    lateinit var printname: Button
    lateinit var displayname: TextView
    lateinit var displayage: TextView

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        entername = findViewById(R.id.enterName)
        enterage = findViewById(R.id.enterAge)
        addname = findViewById(R.id.addName)
        printname = findViewById(R.id.printName)
        displayname = findViewById(R.id.Name)
        displayage = findViewById(R.id.Age)

        // below code is to add on click
        // listener to our add name button
        addname.setOnClickListener{

            // below we have created
            // a new DBHelper class,
            // and passed context to it
            val db = DBHelper(this, null)

            // creating variables for values
            // in name and age edit texts
            val name = entername.text.toString()
            val age = enterage.text.toString()

            // calling method to add
            // name to our database
            db.addName(name, age)

            // Toast to message on the screen
            Toast.makeText(this, name + " added to database", Toast.LENGTH_LONG).show()

            // at last, clearing edit texts
            entername.text.clear()
            enterage.text.clear()
        }

        // below code is to add on click
        // listener to our print name button
        printname.setOnClickListener{

            // creating a DBHelper class
            // and passing context to it
            val db = DBHelper(this, null)

            // below is the variable for cursor
            // we have called method to get
            // all names from our database
            // and add to name text view
            val cursor = db.getName()

            // moving the cursor to first position and
            // appending value in the text view
            cursor!!.moveToFirst()
            displayname.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
            displayage.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")

            // moving our cursor to next
            // position and appending values
            while(cursor.moveToNext()){
                displayname.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
                displayage.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")
            }

            // at last we close our cursor
            cursor.close()
        }
    }
}
