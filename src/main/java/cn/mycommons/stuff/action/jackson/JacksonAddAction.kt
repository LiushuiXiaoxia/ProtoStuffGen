package cn.mycommons.stuff.action.jackson

import cn.mycommons.stuff.action.base.AddAction
import cn.mycommons.stuff.core.JsonType

class JacksonAddAction : AddAction() {

    override fun getJsonType(): JsonType = JsonType.Jackson
}