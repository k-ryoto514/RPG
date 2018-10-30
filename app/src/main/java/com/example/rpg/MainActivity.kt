package com.example.rpg

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener





class MainActivity : AppCompatActivity() {
    private val TAG = "hoge"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference()


        // POST users
        val user = User("yamasaki", 1, "山口組")

        myRef.child("users").child("yamasaki").setValue(user)


        // Read from the database
        database.getReference("users").child("yamasaki").addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // Get user value
                    val user = dataSnapshot.getValue(User::class.java)

                    // ...
                    Log.w(TAG, user.toString())
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.w(TAG, "getUser:onCancelled", databaseError.toException())
                }
            })

    }



}

// Userの定義
class User {

    var username: String = "hoge"
    var id: Int  = 0
    var team: String = "hoge"

    constructor() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    constructor(username: String, id: Int , team: String) {
        this.username = username
        this.id = id
        this.team = team
    }

}




