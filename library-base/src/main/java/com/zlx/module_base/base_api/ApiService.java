package com.zlx.module_base.base_api;

import androidx.lifecycle.LiveData;

import com.zlx.module_base.base_api.bean.ApiResponse;
import com.zlx.module_base.bean.res_data.ArticleListRes;
import com.zlx.module_base.bean.res_data.BannerRes;
import com.zlx.module_base.constant.WebUrl;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiService {

    @GET
    Observable<String> get(@Url String url, @QueryMap Map<String, Object> map);

    @FormUrlEncoded
    @POST
    Observable<String> post(@Url String url, @FieldMap Map<String, Object> map);

    @GET
    LiveData<String> getWithLiveData(@Url String url, @QueryMap Map<String, Object> map);

    @Multipart
    @POST()
    Observable<String> uploadOneFile(@Url String url, @Part List<MultipartBody.Part> partList);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST
    Observable<String> postWithJson(@Url String url, @Body RequestBody body);

    @FormUrlEncoded
    @POST("/api/user/getUserInfo")
    Call<ResponseBody> getUserInfo(@Field("id") int id, @Field("friend_id") int friend_id, @Field("token") String token);

    @POST
    LiveData<String> getBanner(@Url String url);


    /**
     * 获取轮播图
     * @return
     */
    @GET("banner/json")
    LiveData<ApiResponse<List<BannerRes>>> getBanner();

    /**
     * 获取文章列表
     * @param page 页码，拼接在连接中，从0开始。
     * @return
     */
    @GET("article/list/{page}/json")
    LiveData<ApiResponse<ArticleListRes>> listArticle(@Path("page") int page);

}
