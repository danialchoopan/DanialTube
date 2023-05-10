package ir.danialchoopan.danialtube.data.api.model.showSubCategory


import com.google.gson.annotations.SerializedName

data class ShowSubCategory(
    @SerializedName("subCourseCategories")
    val subCourseCategories: List<SubCourseCategory>
)