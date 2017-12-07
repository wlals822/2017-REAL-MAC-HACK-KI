package com.amigotrip.android.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amigotrip.android.activities.NewPartyActivity
import com.amigotrip.android.adpaters.LocalListAdapter
import com.amigotrip.android.datas.Article
import com.amigotrip.android.remote.AmigoService
import com.amigotrip.android.utils.ChatUtil
import com.amigotrip.anroid.R
import kotlinx.android.synthetic.main.fragment_local_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 */
class LocalListFragment : Fragment(), LocalListAdapter.OnLocalListItemClickListener{

    private val amigoService = AmigoService.getService(AmigoService::class.java)


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_local_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val localsAdapter = LocalListAdapter()
        localsAdapter.setOnLocalItemClickListener(this)
        recycler_locals.adapter = localsAdapter

        val call = amigoService.getArticles()
        call.enqueue(object : Callback<List<Article>>{
            override fun onResponse(call: Call<List<Article>>?, response: Response<List<Article>>) {
                val articles = response.body()

                localsAdapter.addAll(articles!!)

            }

            override fun onFailure(call: Call<List<Article>>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })

    }


    //writer 가 오게 한다면 ?
    override fun onLocalItemClick(view: View?, article: Article) {
//        val intent = Intent(context, DetailActivity::class.java)
//        intent.putExtra("email", article.writer.email)
//        startActivity(intent)
        val chatUtil = ChatUtil(activity)
        chatUtil.startChatWith(article.writer.email)
    }

    private fun writeNewPost() {
        startActivity(Intent(activity, NewPartyActivity::class.java))
    }

}
