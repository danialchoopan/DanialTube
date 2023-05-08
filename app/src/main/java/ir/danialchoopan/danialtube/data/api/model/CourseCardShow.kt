package ir.danialchoopan.danialtube.data.api.model

import ir.danialchoopan.danialtube.data.api.model.homepage.*


data class CourseCardShow(
    val categoryId: Int,
    val createdAt: String,
    val description: String,
    val id: Int,
    val nameTitle: String,
    val price: Int,
    val subCourseCategories: SubCourseCategories,
    val teacherId: Int,
    val thumbnail: String,
    val updatedAt: String,
    val user: UserXX,
    val videos: List<Video>
)