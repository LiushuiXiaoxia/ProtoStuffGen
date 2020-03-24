package cn.mycommons.stuff.core

/**
 * JsonType <br>
 * Created by xiaqiulei on 2018-10-18.
 */
enum class JsonType(val classPackage: String, val annotationName: String) {

    Gson("com.google.gson.annotations", "SerializedName") {

        override fun genAnnotationText(param: GenParam): String {
            return if (param.fileType == FileType.JavaFile) {
                """$annotationName("${param.fieldName}")"""
            } else {
                """@$annotationName("${param.fieldName}")"""
            }
        }
    },
    Jackson("com.fasterxml.jackson.annotation", "JsonProperty") {

        override fun genAnnotationText(param: GenParam): String {
            return if (param.fileType == FileType.JavaFile) {
                """$annotationName("${param.fieldName}")"""
            } else {
                """@$annotationName("${param.fieldName}")"""
            }
        }
    },
    FastJson("com.alibaba.fastjson.annotation", "JSONField") {

        override fun genAnnotationText(param: GenParam): String {
            return if (param.fileType == FileType.JavaFile) {
                """$annotationName(name = "${param.fieldName}")"""
            } else {
                """@$annotationName(name = "${param.fieldName}")"""
            }
        }
    },
    ProtoStuffTag("io.protostuff", "Tag") {

        override fun genAnnotationText(param: GenParam): String {
            return if (param.fileType == FileType.JavaFile) {
                """$annotationName(${param.fieldIndex + 1})"""
            } else {
                """@$annotationName(${param.fieldIndex + 1})"""
            }
        }
    };

    override fun toString(): String {
        return "JsonType(classPackage='$classPackage', annotationName='$annotationName')"
    }

    abstract fun genAnnotationText(param: GenParam): String

    fun getFullName() = "$classPackage.$annotationName"
}