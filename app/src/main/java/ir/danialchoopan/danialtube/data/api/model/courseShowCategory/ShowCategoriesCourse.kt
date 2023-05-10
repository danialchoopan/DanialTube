package ir.danialchoopan.danialtube.data.api.model.courseShowCategory


import com.google.gson.annotations.SerializedName

data class ShowCategoriesCourse(
    @SerializedName("subCourseCategoriesCourses")
    val subCourseCategoriesCourses: List<SubCourseCategoriesCourse>
)