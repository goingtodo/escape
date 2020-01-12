package com.ss2.escape.util

import android.util.Log

//Log 사용시 사용 클래스와 위치를 알려주는 Log 호환 클래스
//변수 Debug를 변경함으로써 빌드 시 Log를 지우지 않고 Log 기능 off가능
class SLog {

    companion object {
        var debug : Boolean = true
        fun e(message: String?) {
            if (debug) Log.e(tagLog(), message)
        }

        /** Log Level Warning  */
        fun w(message: String?) {
            if (debug) Log.w(tagLog(), message)
        }

        /** Log Level Information  */
        fun i(message: String?) {
            if (debug) Log.i(tagLog(), message)
        }

        /** Log Level Debug  */
        fun d(message: String?) {
            if (debug) Log.d(tagLog(), message)
        }

        /** Log Level Verbose  */
        fun v(message: String?) {
            if (debug) Log.v(tagLog(), message)
        }

        fun tagLog(): String? {
            val tag = "Escape "
            val ste = Thread.currentThread().stackTrace[4]
            val sb = StringBuilder()
            sb.append(tag)
            sb.append("[")
            sb.append(ste.fileName.replace(".java", ""))
            sb.append("::")
            sb.append(ste.methodName)
            sb.append("]  ")
            return sb.toString()
        }
    }

}