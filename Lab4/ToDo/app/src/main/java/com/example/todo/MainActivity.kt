package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.room.Room
import com.example.todo.dao.TodoDao
import com.example.todo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: AppDatabase
    private lateinit var todoDao: TodoDao

    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        db = Room.databaseBuilder(this, AppDatabase::class.java, "todo_db")
//        .allowMainThreadQueries()
//            .fallbackToDestructiveMigration()
//            .build()
//        todoDao = db.todoDao()
//
//        val todolist = ToDoList(todoDao)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        AppPreferences.setup(applicationContext)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navHostController = navHostFragment.navController
    }
}