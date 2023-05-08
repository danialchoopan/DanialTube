package ir.danialchoopan.danialtube.data.api.model.homepage


import com.google.gson.annotations.SerializedName

data class HomePageDataModelRequest(
    @SerializedName("allCoursesWithTeacherBestSelling")
    val allCoursesWithTeacherBestSelling: List<AllCoursesWithTeacherBestSelling>,
    @SerializedName("allCoursesWithVideosPopular")
    val allCoursesWithVideosPopular: List<AllCoursesWithVideosPopular>,
    @SerializedName("coursesCategory")
    val coursesCategory: List<CoursesCategory>,
    @SerializedName("CoursesWithTeacherMostPopular")
    val coursesWithTeacherMostPopular: CoursesWithTeacherMostPopular,
    @SerializedName("homePageSlider")
    val homePageSlider: List<HomePageSlider>
)