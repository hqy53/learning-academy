package com.jk.project

import java.io.Serializable

class Class(
    var id: Int,
    var title: String,
    var detail: String,
    var imgClassId: Int,
    var isComplete: Boolean,
    var description: String,
    var videoLink: String,
    var notes: String? = null
) : Serializable {
    override fun toString(): String {
        return "Class(id=$id, title='$title', detail='$detail', imgClassId=$imgClassId, isComplete=$isComplete, description='$description', videoLink='$videoLink', notes=$notes)"
    }
}