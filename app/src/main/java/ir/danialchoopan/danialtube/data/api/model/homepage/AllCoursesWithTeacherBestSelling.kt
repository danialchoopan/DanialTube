package ir.danialchoopan.danialtube.data.api.model.homepage


import com.google.gson.annotations.SerializedName

data class AllCoursesWithTeacherBestSelling(
    @SerializedName("category_id")
    val categoryId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name_title")
    val nameTitle: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("sub_course_categories")
    val subCourseCategories: SubCourseCategories,
    @SerializedName("teacher_id")
    val teacherId: Int,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user")
    val user: User,
    @SerializedName("videos")
    val videos: List<Video>
)