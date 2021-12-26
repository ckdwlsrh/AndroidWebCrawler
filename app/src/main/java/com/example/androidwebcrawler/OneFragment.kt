package com.example.androidwebcrawler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidwebcrawler.databinding.FragmentOneBinding
import com.example.mylistapplication.Contacts
import com.example.mylistapplication.ContactsListAdapter

class OneFragment : Fragment() {
    val contactsList : List<Contacts> = listOf(
        Contacts("1","전공 교육표(현행)", "이수현", "2021.09.28", "259"),
        Contacts("2","[졸업생이 후배학생에게]기타 재학생 후배들의 진로나 학교생활 등에 도움 될만한 조언이 있다면?", "이수현", "2021.09.08", "489"),
        Contacts("3", "[졸업생이 후배학생에게]본인이 재직 중인 회사에 관심 있는 후배들에게 취업 조언을 해 준다면?", "이수현", "2021.09.08", "385"),
        Contacts("4", "2021학년도 2학기 졸업논문 계획서 및 논문 제출일 안내", "이수현", "2021.09.08", "347"),
        Contacts("5", "2021-2학기 주요 학사일정", "이수현", "2021.09.01", "165"),
        Contacts("6", "2021-2학기 시간표", "이수현", "2021.07.21", "298"),
        Contacts("7", "코로나19 장기화에 따른 사회봉사교과목 운영 알림", "이수현", "2021.07.25", "369")
    )
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val adapter = ContactsListAdapter(contactsList)
        val binding = FragmentOneBinding.inflate(inflater)
        binding.mRecyclerView.adapter = adapter
        return binding.root
    }
}