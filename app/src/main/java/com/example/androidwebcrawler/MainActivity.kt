package com.example.androidwebcrawler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.example.androidwebcrawler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val db = SqliteDB(this).writableDatabase // 로그인 저장
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 뷰 바인딩
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // id창 입력
        binding.userid.addTextChangedListener { // 로그인 버튼 활성화
            binding.signin.isEnabled = it?.length!! > 0 && binding.userpassword.length() > 0
        }
        binding.userid.setOnEditorActionListener { textView, i, keyEvent ->
            if(i == EditorInfo.IME_ACTION_NEXT) { // id창에서 엔터 또는 완료 누를 경우 password창으로 이동
                binding.userpassword.requestFocus()
            }
            true
        }

        // password창 입력
        binding.userpassword.addTextChangedListener { // 로그인 버튼 활성화
            binding.signin.isEnabled = it?.length!! > 0 && binding.userid.length() > 0
        }
        binding.userpassword.setOnEditorActionListener { textView, i , keyEvent ->
            if(i == EditorInfo.IME_ACTION_DONE) { // password창에서 엔터 또는 완료를 누를 경우 singin버튼 클릭 이벤트 발생
                binding.signin.callOnClick()
            }
            true
        }

        // 로그인 버튼 리스너
        binding.signin.setOnClickListener {
            // 액티비티 전환 후 웹 크롤링
            // 일단 데이터 변환부터해야됨
        }
    }
}