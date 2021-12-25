package com.example.androidwebcrawler

import android.util.Log
import org.jsoup.Connection
import org.jsoup.Jsoup

data class NoticeListForm(val num: String, val title: String, val depart: String, val date: String,val data_id: String)

class JsoupCrawler {
    val WAGGLE_NOTICE_URL = "https://www.changwon.ac.kr/portal/na/ntt/selectMainAtNttList.do?mi=14003"
    val LOGIN_PAGE_URL = "https://www.changwon.ac.kr/portal/main.do"
    val LOGIN_ACTION_PAGE_URL = "https://www.changwon.ac.kr/portal/lo/login/ssoLogin.do"
    val USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36"

    val waggleDataList = mutableListOf<NoticeListForm>()
    fun waggleNotice() {
        try {
            val waggle_page = Jsoup.connect(WAGGLE_NOTICE_URL)
                .method(Connection.Method.GET)
                .execute().parse()
            val el_waggle_notice = waggle_page.getElementById("sub_content")
            val page_list = el_waggle_notice?.select("div[class=BD_list]")?.select("table")?.select("tbody")?.select("tr")
            val list_iterator = page_list?.listIterator()

            while (list_iterator!!.hasNext()) {
                val temp = list_iterator.next()
                val num = temp.select("td[class=BD_tm_none]").first()!!.text()
                val title = temp.select("td").eachText().get(1)
                val depart = temp.select("td").eachText().get(2)
                val date = temp.select("td").eachText().get(3)
                val data_id = temp.select("a[class=nttInfoBtn]").attr("data-id")
                println(num+": "+title+"\n"+depart+","+date+","+data_id)
                waggleDataList.add(NoticeListForm(num,title,depart,date,data_id))
            }
            //println(page_list?.text())

            val el_waggle = waggle_page.select("strong[class=pc_blue]").first()
            //val num = el_waggle?.text()?.toInt()//data-id , nttInfoBtn, post currPage 로 작동
            //println(num)
        }
        catch(e: Exception) {
            e.printStackTrace()
        }
    }
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
    }
}
fun main() {
  //  JsoupCrawler().login("","")
    JsoupCrawler().waggleNotice()
}