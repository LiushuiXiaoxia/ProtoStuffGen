package cn.mycommons.stuff.action.tag

import cn.mycommons.stuff.action.base.RemoveAction
import cn.mycommons.stuff.core.JsonType

open class TagRemoveAction :RemoveAction() {

    override fun getJsonType(): JsonType = JsonType.ProtoStuffTag
}