package com.example.ticketmastermvvm.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.ticketmastermvvm.core.RetrofitHelper
import com.example.ticketmastermvvm.data.network.ApiClient
import retrofit2.HttpException
import java.io.IOException


const val STARTING_PAGE_INDEX: Int = 0

class EventPagingSource(private val retrofit: RetrofitHelper, private val keyword: String?,
                        private val countryCode: String?, private val segmentName: String?): PagingSource<Int, EventData>() {

    override fun getRefreshKey(state: PagingState<Int, EventData>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EventData> {
        val currentPage = params.key ?: STARTING_PAGE_INDEX
        return try{
            val instance = retrofit.getRetrofit().create(ApiClient::class.java)
            val response = instance.getSearchedEvents(keyword, countryCode, segmentName, currentPage)
            val responseList = mutableListOf<EventData>()
            val emptyList =  mutableListOf<EventData>()
            val data = response.body()?.Embedded?.events ?: emptyList()
            //Log.d("Gata",data.toString())
            responseList.addAll(data)

            val prevKey = if(currentPage == 0) null else currentPage - 1
            Log.d("Gata","valor page:"+currentPage)
            if(!data.isEmpty()){

                LoadResult.Page(
                    data= responseList,
                    prevKey = prevKey,
                    nextKey = currentPage.plus(1)
                )
            }else{
                LoadResult.Page(
                    data = emptyList,
                    prevKey= prevKey,
                    nextKey = null

                )
            }

        }catch (exception: IOException){
            return LoadResult.Error(exception)
        }catch (exception: HttpException){
            return LoadResult.Error(exception)
        }
    }
}