package com.amigotrip.android.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amigotrip.android.UserInfoManager
import com.amigotrip.android.adpaters.TravelerListAdapter
import com.amigotrip.android.datas.Article
import com.amigotrip.android.remote.AmigoService
import com.amigotrip.anroid.R
import kotlinx.android.synthetic.main.fragment_traveler_list.*
import retrofit2.Call
import retrofit2.Response


class TravelerListFragment : Fragment() {

    private val user = UserInfoManager.getLogineduser()
    private val amigoService = AmigoService.getService(AmigoService::class.java)
    private val adapter = TravelerListAdapter()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_traveler_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list_traveler.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        val call = amigoService.getTravelArticles()

        call.enqueue(object : retrofit2.Callback<List<Article>>{
            override fun onFailure(call: Call<List<Article>>?, t: Throwable?) {
                t?.printStackTrace()
            }

            override fun onResponse(call: Call<List<Article>>?, response: Response<List<Article>>) {
                var articles = response.body()

                adapter.addArticles(articles!!)
            }
        })
    }

}
