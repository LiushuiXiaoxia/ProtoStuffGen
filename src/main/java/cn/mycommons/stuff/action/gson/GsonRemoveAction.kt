package cn.mycommons.stuff.action.gson

import cn.mycommons.stuff.action.base.RemoveAction
import cn.mycommons.stuff.core.JsonType

open class GsonRemoveAction :RemoveAction() {

    override fun getJsonType(): JsonType = JsonType.Gson
}