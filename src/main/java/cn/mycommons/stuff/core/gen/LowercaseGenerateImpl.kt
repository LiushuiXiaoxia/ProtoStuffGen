package cn.mycommons.stuff.core.gen

import cn.mycommons.stuff.core.IGenerateRule

/**
 * ABC --> abc
 */
class LowercaseGenerateImpl : IGenerateRule {

    override fun gen(name: String): String = name.toLowerCase()
}