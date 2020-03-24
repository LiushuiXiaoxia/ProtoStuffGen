package cn.mycommons.stuff.core.gen

import cn.mycommons.stuff.core.IGenerateRule

/**
 * 不转换
 * abc --> abc
 * ABC --> ABC
 */
class OriginGenerateImpl : IGenerateRule {

    override fun gen(name: String): String = name
}