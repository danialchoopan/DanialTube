package ir.danialchoopan.danialtube.data.api.model.homepage


import com.google.gson.annotations.SerializedName

data class HomePageRequestDataModel(
    @SerializedName("allCoursesWithTeacherBestSelling")
    val allCoursesWithTeacherBestSelling: List<AllCoursesWithTeacherBestSelling>? = null,
    @SerializedName("allCoursesWithTeacherMostPopular")
    val allCoursesWithTeacherMostPopular: AllCoursesWithTeacherMostPopular? = null,
    @SerializedName("allCoursesWithVideosPopular")
    val allCoursesWithVideosPopular: List<AllCoursesWithVideosPopular>? = null,
    @SerializedName("coursesCategory")
    val coursesCategory: List<CoursesCategory>? = null,
    @SerializedName("homePageSlider")
    val homePageSlider: List<HomePageSlider>? = null
)