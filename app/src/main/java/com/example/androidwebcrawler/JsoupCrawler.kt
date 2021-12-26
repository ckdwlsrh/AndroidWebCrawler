package com.example.androidwebcrawler

import org.jsoup.Connection
import org.jsoup.Jsoup

// 공지사항 목록 데이터 구조
data class NoticeListForm(val num: String, val title: String, val name: String, val date: String,val data_id: String,val notice_check: Boolean)

class JsoupCrawler {
    val WAGGLE_NOTICE_URL = "https://www.changwon.ac.kr/portal/na/ntt/selectMainAtNttList.do?mi=14003&currPage=" //와글 공지
    val CE_URL = "https://www.changwon.ac.kr/ce/na/ntt/selectNttList.do?" //컴퓨터공학과 기본 url
    val IE_URL = "https://www.changwon.ac.kr/it/na/ntt/selectNttList.do?" //정보통신공학과 기본 url
    //val LOGIN_PAGE_URL = "https://www.changwon.ac.kr/portal/main.do"
    //val LOGIN_ACTION_PAGE_URL = "https://www.changwon.ac.kr/portal/lo/login/ssoLogin.do"
    //val USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36"

    fun boardCrawling(where: Int) : MutableList<NoticeListForm> { // 현재페이지값이 존재하지 않을경우 1로 고정, 오버로딩
        return boardCrawling("1", where)
    }
    fun boardCrawling(currPage: String, where: Int) : MutableList<NoticeListForm> { // 보드 크롤링 메소드
        val data_list = mutableListOf<NoticeListForm>() // 데이터 담을 리스트 객체 생성
        try {
            var url = ""
            when(where) { //where을 통해 보드 선택
                1 -> url = CE_URL + "mi=6627&bbsId=2187&currPage=" + currPage // 컴공 공지
                2 -> url = CE_URL + "mi=6634&bbsId=2190&currPage=" + currPage // 컴공 자유 게시판
                3 -> url = IE_URL + "mi=6661&bbsId=2198&currPage=" + currPage // 정통 공지
                4 -> url = IE_URL + "mi=6665&bbsId=2199&currPage=" + currPage // 정통 수업 게시판
                5 -> url = WAGGLE_NOTICE_URL + currPage
                else -> throw Exception()// where이 값이 정상적이지 않으면 예외처리
            }
            val page = Jsoup.connect(url)
                .method(Connection.Method.GET)
                .execute().parse()
            val el_board = page.getElementById("sub_content")
            val page_list = el_board
                ?.select("div[class=BD_list]")
                ?.select("table")
                ?.select("tbody")
                ?.select("tr")

            val list_iterator = page_list?.listIterator()

            while (list_iterator!!.hasNext()) {
                val temp = list_iterator.next()
                val num = temp.select("td").first()!!.text()
                val notice_check = num == "공지"

                if(notice_check && currPage.toInt() > 1) continue

                val title = temp.select("td").eachText().get(1)
                val name = temp.select("td").eachText().get(2)
                val date = temp.select("td").eachText().get(3)
                val data_id = temp.select("a[class=nttInfoBtn]").attr("data-id")
                println(num+": "+title+"\n"+name+","+date+","+data_id)

                data_list.add(NoticeListForm(num,title,name,date,data_id,notice_check))
            }

        }catch (e: Exception) {
            e.printStackTrace()
        }
        return data_list
    }
    fun postCrawling() {

    }
    fun NoticeCheck() {

    }
/*
    fun login(id: String, pw: String) {
        try {
            val loginpage_response = Jsoup.connect(LOGIN_PAGE_URL)
                .userAgent(USER_AGENT)
                .method(Connection.Method.GET)
                .execute()

            val loginpage_cookies = loginpage_response.cookies()

            for (i in loginpage_cookies) {
                println(i.key + ": " + i.value)
            }
            val login_data = hashMapOf<String,String>()
            login_data.put("username","20193044")
            login_data.put("password","zollaman12@")
            login_data.put("returnUrl","")

            val response = Jsoup.connect(LOGIN_ACTION_PAGE_URL)
                .timeout(3000)
                .userAgent(USER_AGENT)
                .cookies(loginpage_cookies)
                .data(login_data)
                .method(Connection.Method.POST)
                .execute()

            val session = response.cookies()

            val check = Jsoup.connect("https://www.changwon.ac.kr/portal/main.do")
                .userAgent(USER_AGENT)
                .cookies(session)
                .get()
            println(check.text())
        }
        catch(e: Exception) {
            e.printStackTrace()
        }
    }*/
}
fun main() {
    JsoupCrawler().boardCrawling(5)
}