package cn.mycommons.stuff.action.jackson

import cn.mycommons.stuff.action.base.RemoveAction
import cn.mycommons.stuff.core.JsonType

open class JacksonRemoveAction : RemoveAction() {

    override fun getJsonType(): JsonType = JsonType.Jackson
}