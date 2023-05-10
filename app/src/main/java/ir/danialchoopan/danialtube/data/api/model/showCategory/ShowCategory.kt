package ir.danialchoopan.danialtube.data.api.model.showCategory


import com.google.gson.annotations.SerializedName

data class ShowCategory(
    @SerializedName("courseCategories")
    val courseCategories: List<CourseCategory>
)